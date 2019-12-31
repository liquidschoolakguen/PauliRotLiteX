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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import akguen.liquidschool.coredata.db.DataSource_Gruppe2;
import akguen.liquidschool.coredata.model.Gruppe2;
import akguen.liquidschool.paulirotlite.R;
import test_activities.Filler;

public class Speichern_Gruppe2 extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Gruppe2.class.getSimpleName();

    private DataSource_Gruppe2 dataSource;

    private ListView mGruppe2sListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gruppe2_sq);

        
        dataSource = new DataSource_Gruppe2(this);

        initializeGruppe2sListView();

        createMainGruppe2Button();
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

    private void initializeGruppe2sListView() {
        List<Gruppe2> emptyListForInitialization = new ArrayList<>();

        mGruppe2sListView = (ListView) findViewById(R.id.listview_gruppe2s);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<Gruppe2> gruppe2ArrayAdapter = new ArrayAdapter<Gruppe2>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                Gruppe2 memo = (Gruppe2) mGruppe2sListView.getItemAtPosition(position);

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

        mGruppe2sListView.setAdapter(gruppe2ArrayAdapter);

        mGruppe2sListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Gruppe2 gruppe2 = (Gruppe2) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Gruppe2 updatedGruppe2 = dataSource.updateGruppe2(gruppe2.getId(), gruppe2.getVorname(), gruppe2.getItemType(), gruppe2.getPersonaltyp(), gruppe2.getGeburtstag(), gruppe2.getStrasse());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedGruppe2.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<Gruppe2> gruppe2List = dataSource.getAllGruppe2s();
        Collections.sort(gruppe2List, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Gruppe2 p1 = (Gruppe2) o1;
                Gruppe2 p2 = (Gruppe2) o2;
                return p1.getKategorie().compareToIgnoreCase(p2.getKategorie());
            }
        });
        ArrayAdapter<Gruppe2> adapter = (ArrayAdapter<Gruppe2>) mGruppe2sListView.getAdapter();

        adapter.clear();
        adapter.addAll(gruppe2List);
        adapter.notifyDataSetChanged();
    }

    private void createMainGruppe2Button() {
        Button buttonCreateMainGruppe2 = (Button) findViewById(R.id.button_add_gruppe2);




        buttonCreateMainGruppe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                String s4 = editText4.getText().toString();



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
                editText3.setText("");
                editText4.setText("");



                dataSource.createGruppe2(s1, s2, s3, s4);

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
        Button buttonFillGruppe2 = (Button) findViewById(R.id.button_add_gruppe2s);


        buttonFillGruppe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Filler f = new Filler();

                f.fillGruppe2(getBaseContext());



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
        Button buttonFindGruppe2 = (Button) findViewById(R.id.button_select_gruppe2);


        buttonFindGruppe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( dataSource.getGruppe2ById(5)!= null){

                    Log.i("click", "Gesuchter Gruppe2: " + dataSource.getGruppe2ById(5).toString());
                }else{

                    Log.i("click", "Gesuchter Gruppe2 mit der id 5 nicht gefunden" );
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView gruppe2sListView = (ListView) findViewById(R.id.listview_gruppe2s);
        gruppe2sListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        gruppe2sListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedGruppe2sPositions = gruppe2sListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedGruppe2sPositions.size(); i++) {
                            boolean isChecked = touchedGruppe2sPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedGruppe2sPositions.keyAt(i);
                                Gruppe2 gruppe2 = (Gruppe2) gruppe2sListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + gruppe2.toString());
                                dataSource.deleteGruppe2(gruppe2);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedGruppe2sPositions.size(); i++) {
                            boolean isChecked = touchedGruppe2sPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedGruppe2sPositions.keyAt(i);
                                Gruppe2 gruppe2 = (Gruppe2) gruppe2sListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + gruppe2.toString());

                                AlertDialog editGruppe2Dialog = createEditGruppe2Dialog(gruppe2);
                                editGruppe2Dialog.show();
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

    private AlertDialog createEditGruppe2Dialog(final Gruppe2 gruppe2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_gruppe2_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_gruppe2_1);
        editText1.setText(gruppe2.getName());

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_gruppe2_2);
        editText2.setText(gruppe2.getText());

        final EditText editText3 = (EditText) dialogsView.findViewById(R.id.editText_gruppe2_3);
        editText3.setText(gruppe2.getGewicht());

        final EditText editText4 = (EditText) dialogsView.findViewById(R.id.editText_gruppe2_4);
        editText4.setText(gruppe2.getKategorie());




        builder.setView(dialogsView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s1 = editText1.getText().toString();
                        String s2 = editText2.getText().toString();
                        String s3 = editText3.getText().toString();
                        String s4 = editText4.getText().toString();


/*                        if ((TextUtils.isEmpty(s1)) || (TextUtils.isEmpty(s2)) || (TextUtils.isEmpty(s3)) || (TextUtils.isEmpty(s4)) || (TextUtils.isEmpty(s5))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }*/


                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        Gruppe2 updatedGruppe2 = dataSource.updateGruppe2(gruppe2.getId(), s1, s2, s3, s4);


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