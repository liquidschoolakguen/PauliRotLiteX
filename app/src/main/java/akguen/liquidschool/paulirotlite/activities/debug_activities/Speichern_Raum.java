package akguen.liquidschool.paulirotlite.activities.debug_activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.mylib2.db.DataSource_Raum;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Raum;
import akguen.liquidschool.mylib2.model.SP_Fach;
import test_activities.Filler;

public class Speichern_Raum extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Raum.class.getSimpleName();

    private DataSource_Raum dataSource;

    private ListView mRaumsListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_raum_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Raum(this);

        initializeRaumsListView();

        activateAddButton();
        activateFillButton();
        activateFindButton();
        initializeContextualActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }

    private void initializeRaumsListView() {
        List<Raum> emptyListForInitialization = new ArrayList<>();

        mRaumsListView = (ListView) findViewById(R.id.listview_raums);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<Raum> raumArrayAdapter = new ArrayAdapter<Raum>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                Raum memo = (Raum) mRaumsListView.getItemAtPosition(position);

                // Hier prüfen, ob Eintrag abgehakt ist. Falls ja, Text durchstreichen
//                if (memo.isChecked()) {
//                    textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                    textView.setTextColor(Color.rgb(175,175,175));
//                }
//                else {
//                    textView.setPaintFlags( textView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
//                    textView.setTextColor(Color.DKGRAY);
//                }

                return view;
            }
        };

        mRaumsListView.setAdapter(raumArrayAdapter);

        mRaumsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Raum raum = (Raum) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Raum updatedRaum = dataSource.updateRaum(raum.getId(), raum.getVorname(), raum.getItemType(), raum.getPasswort(), raum.getKuerzel(), raum.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedRaum.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<Raum> raumList = dataSource.getAllRaums();

        ArrayAdapter<Raum> adapter = (ArrayAdapter<Raum>) mRaumsListView.getAdapter();

        adapter.clear();
        adapter.addAll(raumList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddRaum = (Button) findViewById(R.id.button_add_raum);
        final EditText editText1 = (EditText) findViewById(R.id.editText_raum_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_raum_22);




        buttonAddRaum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();


   /*             if (TextUtils.isEmpty(s2)) {
                    editText2.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if (TextUtils.isEmpty(s3)) {
                    editText3.setError(getString(R.string.editText_errorMessage));
                    return;
                }*/


                editText1.setText("");
                editText2.setText("");



                dataSource.createRaum(s1, s2);

                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                showAllListEntries();
            }
        });

    }


    private void activateFillButton() {
        Button buttonFillRaum = (Button) findViewById(R.id.button_add_raums);


        buttonFillRaum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Filler f = new Filler();

                f.fillRaum(getBaseContext());

                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                showAllListEntries();




            }
        });


    }


    private void activateFindButton() {
        Button buttonFindRaum = (Button) findViewById(R.id.button_select_raum);


        buttonFindRaum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<SP_Fach> s =dataSource.getSP_FachsFromRaumById(2);

                if(s!= null && !s.isEmpty()){


                    for(int i=0; i<s.size(); i++)
                    {
                        Log.i("click", "Gesuchter SP_Fach: " + s.get(i).getTeststring()+ " " +s.get(i).getThema_id());
                    }

                }else{

                    Log.i("click", "keine SP_Fach gefunden" );
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView raumsListView = (ListView) findViewById(R.id.listview_raums);
        raumsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        raumsListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            int selCount = 0;

            // In dieser Callback-Methode zählen wir die ausgewählen Listeneinträge mit
            // und fordern ein Aktualisieren der Contextual Action Bar mit invalidate() an
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    selCount++;
                } else {
                    selCount--;
                }
                String cabTitle = selCount + " " + getString(R.string.cab_checked_string);
                mode.setTitle(cabTitle);
                mode.invalidate();
            }

            // In dieser Callback-Methode legen wir die CAB-Menüeinträge an
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.menu_contextual_action_bar_sq, menu);
                return true;
            }

            // In dieser Callback-Methode reagieren wir auf den invalidate() Aufruf
            // Wir lassen das Edit-Symbol verschwinden, wenn mehr als 1 Eintrag ausgewählt ist
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                MenuItem item = menu.findItem(R.id.cab_change);
                if (selCount == 1) {
                    item.setVisible(true);
                } else {
                    item.setVisible(false);
                }

                return true;
            }

            // In dieser Callback-Methode reagieren wir auf Action Item-Klicks
            // Je nachdem ob das Löschen- oder Ändern-Symbol angeklickt wurde
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                boolean returnValue = true;
                SparseBooleanArray touchedRaumsPositions = raumsListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedRaumsPositions.size(); i++) {
                            boolean isChecked = touchedRaumsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedRaumsPositions.keyAt(i);
                                Raum raum = (Raum) raumsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + raum.toString());
                                dataSource.deleteRaum(raum);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedRaumsPositions.size(); i++) {
                            boolean isChecked = touchedRaumsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedRaumsPositions.keyAt(i);
                                Raum raum = (Raum) raumsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + raum.toString());

                                AlertDialog editRaumDialog = createEditRaumDialog(raum);
                                editRaumDialog.show();
                            }
                        }

                        mode.finish();
                        break;

                    default:
                        returnValue = false;
                        break;
                }
                return returnValue;
            }

            // In dieser Callback-Methode reagieren wir auf das Schließen der CAB
            // Wir setzen den Zähler auf 0 zurück
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                selCount = 0;
            }
        });

    }

    private AlertDialog createEditRaumDialog(final Raum raum) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_raum_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_raum_1);
        editText1.setText(raum.getHaus());

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_raum_2);
        editText2.setText(raum.getName());






        builder.setView(dialogsView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s1 = editText1.getText().toString();
                        String s2 = editText2.getText().toString();






/*                        if ((TextUtils.isEmpty(s1)) || (TextUtils.isEmpty(s2)) || (TextUtils.isEmpty(s3)) || (TextUtils.isEmpty(s4)) || (TextUtils.isEmpty(s5))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }*/


                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        Raum updatedRaum = dataSource.updateRaum(raum.getId(), s1, s2);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + raum.getId() + " Inhalt: " + raum.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedRaum.getId() + " Inhalt: " + updatedRaum.toString());

                        showAllListEntries();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.dialog_button_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });



        return builder.create();
    }
}