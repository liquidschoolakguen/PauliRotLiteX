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

import akguen.liquidschool.mylib2.db.DataSource_Thema;
import akguen.liquidschool.paulirotlite.R;

import akguen.liquidschool.mylib2.model.SP_Fach;
import akguen.liquidschool.mylib2.model.Thema;
import test_activities.Filler;

public class Speichern_Thema extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Thema.class.getSimpleName();

    private DataSource_Thema dataSource;

    private ListView mThemasListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thema_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Thema(this);

        initializeThemasListView();

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

    private void initializeThemasListView() {
        List<Thema> emptyListForInitialization = new ArrayList<>();

        mThemasListView = (ListView) findViewById(R.id.listview_themas);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<Thema> themaArrayAdapter = new ArrayAdapter<Thema>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                Thema memo = (Thema) mThemasListView.getItemAtPosition(position);

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

        mThemasListView.setAdapter(themaArrayAdapter);

        mThemasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Thema thema = (Thema) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Thema updatedThema = dataSource.updateThema(thema.getId(), thema.getVorname(), thema.getItemType(), thema.getPasswort(), thema.getKuerzel(), thema.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedThema.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<Thema> themaList = dataSource.getAllThemas();

        ArrayAdapter<Thema> adapter = (ArrayAdapter<Thema>) mThemasListView.getAdapter();

        adapter.clear();
        adapter.addAll(themaList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddThema = (Button) findViewById(R.id.button_add_thema);
        final EditText editText1 = (EditText) findViewById(R.id.editText_thema_11);





        buttonAddThema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = editText1.getText().toString();



   /*             if (TextUtils.isEmpty(s2)) {
                    editText2.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if (TextUtils.isEmpty(s3)) {
                    editText3.setError(getString(R.string.editText_errorMessage));
                    return;
                }*/


                editText1.setText("");




                dataSource.createThema(s1);

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
        Button buttonFillThema = (Button) findViewById(R.id.button_add_themas);


        buttonFillThema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Filler f = new Filler();

                f.fillThema(getBaseContext());



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
        Button buttonFindThema = (Button) findViewById(R.id.button_select_thema);


        buttonFindThema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<SP_Fach> s =dataSource.getSP_FachsFromThemaById(2);

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
        final ListView themasListView = (ListView) findViewById(R.id.listview_themas);
        themasListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        themasListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedThemasPositions = themasListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedThemasPositions.size(); i++) {
                            boolean isChecked = touchedThemasPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedThemasPositions.keyAt(i);
                                Thema thema = (Thema) themasListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + thema.toString());
                                dataSource.deleteThema(thema);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedThemasPositions.size(); i++) {
                            boolean isChecked = touchedThemasPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedThemasPositions.keyAt(i);
                                Thema thema = (Thema) themasListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + thema.toString());

                                AlertDialog editThemaDialog = createEditThemaDialog(thema);
                                editThemaDialog.show();
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

    private AlertDialog createEditThemaDialog(final Thema thema) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_thema_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_thema_1);
        editText1.setText(thema.getName());








        builder.setView(dialogsView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s1 = editText1.getText().toString();







/*                        if ((TextUtils.isEmpty(s1)) || (TextUtils.isEmpty(s2)) || (TextUtils.isEmpty(s3)) || (TextUtils.isEmpty(s4)) || (TextUtils.isEmpty(s5))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }*/


                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        Thema updatedThema = dataSource.updateThema(thema.getId(), s1);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + thema.getId() + " Inhalt: " + thema.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedThema.getId() + " Inhalt: " + updatedThema.toString());

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