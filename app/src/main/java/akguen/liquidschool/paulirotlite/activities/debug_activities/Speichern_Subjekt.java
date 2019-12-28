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

import akguen.liquidschool.coredata.db.*;
import akguen.liquidschool.coredata.model.*;
import akguen.liquidschool.paulirotlite.R;


public class Speichern_Subjekt extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Subjekt.class.getSimpleName();

    private DataSource_Subjekt dataSource;

    private ListView mSubjektsListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_subjekt_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Subjekt(this);

        initializeSubjektsListView();

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

    private void initializeSubjektsListView() {
        List<Subjekt> emptyListForInitialization = new ArrayList<>();

        mSubjektsListView = (ListView) findViewById(R.id.listview_subjekts);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<Subjekt> subjektArrayAdapter = new ArrayAdapter<Subjekt>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                Subjekt memo = (Subjekt) mSubjektsListView.getItemAtPosition(position);

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

        mSubjektsListView.setAdapter(subjektArrayAdapter);

        mSubjektsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Subjekt subjekt = (Subjekt) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Subjekt updatedSubjekt = dataSource.updateSubjekt(subjekt.getId(), subjekt.getVorname(), subjekt.getItemType(), subjekt.getPersonaltyp(), subjekt.getGeburtstag(), subjekt.getStrasse());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedSubjekt.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<Subjekt> subjektList = dataSource.getAllSubjekts();

        ArrayAdapter<Subjekt> adapter = (ArrayAdapter<Subjekt>) mSubjektsListView.getAdapter();

        adapter.clear();
        adapter.addAll(subjektList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddSubjekt = (Button) findViewById(R.id.button_add_subjekt);
        final EditText editText1 = (EditText) findViewById(R.id.editText_subjekt_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_subjekt_22);
        final EditText editText3 = (EditText) findViewById(R.id.editText_subjekt_33);
        final EditText editText4 = (EditText) findViewById(R.id.editText_subjekt_44);
        final EditText editText5 = (EditText) findViewById(R.id.editText_subjekt_55);
        final EditText editText6 = (EditText) findViewById(R.id.editText_subjekt_66);
        final EditText editText7 = (EditText) findViewById(R.id.editText_subjekt_77);
        final EditText editText8 = (EditText) findViewById(R.id.editText_subjekt_88);
        final EditText editText9 = (EditText) findViewById(R.id.editText_subjekt_99);
        final EditText editText10 = (EditText) findViewById(R.id.editText_subjekt_1010);
        final EditText editText11 = (EditText) findViewById(R.id.editText_subjekt_1111);
        final EditText editText12 = (EditText) findViewById(R.id.editText_subjekt_1212);




        buttonAddSubjekt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                String s4 = editText4.getText().toString();
                String s5 = editText5.getText().toString();
                String s6 = editText6.getText().toString();
                String s7 = editText7.getText().toString();
                String s8 = editText8.getText().toString();
                String s9 = editText9.getText().toString();
                String s10 = editText10.getText().toString();
                String s11 = editText11.getText().toString();
                String s12 = editText12.getText().toString();


                boolean aktiv;
                boolean männlich;

                aktiv = s10.equals("1")? true : false;
                männlich = s12.equals("1")? true : false;


                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText5.setText("");
                editText6.setText("");
                editText7.setText("");
                editText8.setText("");
                editText9.setText("");
                editText10.setText("");
                editText11.setText("");
                editText12.setText("");


                dataSource.createSubjekt(s1, s2, s3, s4, s5, s6, s7, s8, s9, aktiv, s11, männlich);

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
        Button buttonFillSubjekt = (Button) findViewById(R.id.button_add_subjekts);


        buttonFillSubjekt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Filler f = new Filler();
                String[] tempArray;


                String delimiter = "--";

                tempArray = f.alleSubjektString.split(delimiter);

                for (int i = 0; i < tempArray.length; i++) {
                    //Log.i("click", tempArray[i]);

                    String s1 = null;
                    String s2 = null;
                    String s3 = null;
                    String s4 = null;
                    String s5 = null;


                    String[] tempTempArray;
                    String deDelimiter = ",";

                    tempTempArray = tempArray[i].split(deDelimiter);



                    for (int ii = 0; ii < tempTempArray.length; ii++) {

                        String newString = tempTempArray[ii].replaceAll("[\u0000-\u001f]", "");

                        if (ii == 0) {
                            Log.i("click", "s1: " + newString);
                            s1 = newString;
                        }
                        if (ii == 1) {
                            Log.i("click", "s2: " + newString);
                            s2 = newString;
                        }
                        if (ii == 2) {
                            Log.i("click", "s3: " + newString);
                            s3 = newString;
                        }
                        if (ii == 4) {
                            Log.i("click", "s4: " + newString);

                            s4 = newString;
                        }


                    }

                    Log.i("click", "----------------------------------");




                    dataSource.createSubjekt(s2, s1, s3, s4, s5);







                }

                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                showAllListEntries();


*/

            }
        });


    }


    private void activateFindButton() {
        Button buttonFindSubjekt = (Button) findViewById(R.id.button_select_subjekt);


        buttonFindSubjekt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( dataSource.getSubjektById(2)!= null){

                    Log.i("click", "Gesuchter Subjekt: " + dataSource.getSubjektById(5).toString());
                }else{

                    Log.i("click", "Gesuchter Subjekt mit der id 5 nicht gefunden" );
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView subjektsListView = (ListView) findViewById(R.id.listview_subjekts);
        subjektsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        subjektsListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedSubjektsPositions = subjektsListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedSubjektsPositions.size(); i++) {
                            boolean isChecked = touchedSubjektsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSubjektsPositions.keyAt(i);
                                Subjekt subjekt = (Subjekt) subjektsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + subjekt.toString());
                                dataSource.deleteSubjekt(subjekt);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedSubjektsPositions.size(); i++) {
                            boolean isChecked = touchedSubjektsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSubjektsPositions.keyAt(i);
                                Subjekt subjekt = (Subjekt) subjektsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + subjekt.toString());

                                AlertDialog editSubjektDialog = createEditSubjektDialog(subjekt);
                                editSubjektDialog.show();
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

    private AlertDialog createEditSubjektDialog(final Subjekt subjekt) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_subjekt_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_1);
        editText1.setText(subjekt.getVorname());

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_2);
        editText2.setText(subjekt.getNachname());

        final EditText editText3 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_3);
        editText3.setText(subjekt.getKürzel());

        final EditText editText4 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_4);
        editText4.setText(subjekt.getGeburtstag());

        final EditText editText5 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_5);
        editText5.setText(subjekt.getGeburtsort());

        final EditText editText6 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_6);
        editText6.setText(subjekt.getNationalität());

        final EditText editText7 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_7);
        editText7.setText(subjekt.getBenutzername());

        final EditText editText8 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_8);
        editText8.setText(subjekt.getBenutzerpasswort());

        final EditText editText9 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_9);
        editText9.setText(subjekt.getSchulpfad());

        final EditText editText10 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_10);
        int val = (subjekt.isAktiv()) ? 1 : 0;
        editText10.setText(Integer.toString(val));



        final EditText editText11 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_11);
        editText11.setText(subjekt.getTyp_());

        final EditText editText12 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_12);
        int val2 = (subjekt.isMännlich()) ? 1 : 0;
        editText12.setText(Integer.toString(val2));


        builder.setView(dialogsView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s1 = editText1.getText().toString();
                        String s2 = editText2.getText().toString();
                        String s3 = editText3.getText().toString();
                        String s4 = editText4.getText().toString();
                        String s5 = editText5.getText().toString();
                        String s6 = editText6.getText().toString();
                        String s7 = editText7.getText().toString();
                        String s8 = editText8.getText().toString();
                        String s9 = editText9.getText().toString();
                        String s10 = editText10.getText().toString();
                        String s11 = editText11.getText().toString();
                        String s12 = editText12.getText().toString();
/*                        if ((TextUtils.isEmpty(s1)) || (TextUtils.isEmpty(s2)) || (TextUtils.isEmpty(s3)) || (TextUtils.isEmpty(s4)) || (TextUtils.isEmpty(s5))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }*/

                        boolean aktiv;
                        boolean männlich;

                        aktiv = s10.equals("1")? true : false;
                        männlich = s12.equals("1")? true : false;

                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        Subjekt updatedSubjekt = dataSource.updateSubjekt(subjekt.getId(), s1, s2, s3, s4, s5, s6, s7, s8, s9, aktiv, s11, männlich);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + subjekt.getId() + " Inhalt: " + subjekt.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedSubjekt.getId() + " Inhalt: " + updatedSubjekt.toString());

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