package akguen.liquidschool.paulirotlite.activities.debug_activities;

import android.content.ContentValues;
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

import akguen.liquidschool.coredata.db.DataSource_Subjekt_Gruppe;
import akguen.liquidschool.coredata.db.MyDbHelper;
import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.coredata.model.Subjekt;
import akguen.liquidschool.paulirotlite.R;


public class Speichern_Subjekt_Gruppe extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Subjekt_Gruppe.class.getSimpleName();

    private DataSource_Subjekt_Gruppe dataSource;

    private ListView mSubjekt_GruppesListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_subjekt_gruppe_sq);


        dataSource = new DataSource_Subjekt_Gruppe(this);

        initializeSubjekt_GruppesListView();

        activateAddButton();
        activateFillButton();
        activateFindButton1();
        activateFindButton2();
        initializeContextualActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    private void initializeSubjekt_GruppesListView() {
        List<ContentValues> emptyListForInitialization = new ArrayList<>();
        mSubjekt_GruppesListView = (ListView) findViewById(R.id.listview_subjekt_gruppes);

        ArrayAdapter<ContentValues> subjekt_gruppeArrayAdapter = new ArrayAdapter<ContentValues>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                ContentValues memo = (ContentValues) mSubjekt_GruppesListView.getItemAtPosition(position);


                return view;
            }
        };

        mSubjekt_GruppesListView.setAdapter(subjekt_gruppeArrayAdapter);

        mSubjekt_GruppesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ContentValues subjekt_gruppe = (ContentValues) adapterView.getItemAtPosition(position);


            }
        });

    }

    private void showAllListEntries() {
        List<ContentValues> subjekt_gruppeList = dataSource.getAllSubjekt_Gruppes();

        ArrayAdapter<ContentValues> adapter = (ArrayAdapter<ContentValues>) mSubjekt_GruppesListView.getAdapter();

        adapter.clear();
        adapter.addAll(subjekt_gruppeList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddSubjekt_Gruppe = (Button) findViewById(R.id.button_add_subjekt_gruppe);
        final EditText editText1 = (EditText) findViewById(R.id.editText_subjekt_gruppe_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_subjekt_gruppe_22);




        buttonAddSubjekt_Gruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();




                editText1.setText("");
                editText2.setText("");

                ContentValues values = new ContentValues();
                values.put(MyDbHelper.SL_SCHUELER_ID, s1);
                values.put(MyDbHelper.SL_LERNGRUPPE_ID, s2);



                dataSource.createSubjekt_Gruppe(values);

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
        Button buttonFillSubjekt_Gruppe = (Button) findViewById(R.id.button_add_subjekt_gruppes);


        buttonFillSubjekt_Gruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                showAllListEntries();



            }
        });


    }


    private void activateFindButton1() {
        Button buttonFindSubjekt_Gruppe = (Button) findViewById(R.id.button_select_subjekt_gruppe_1);


        buttonFindSubjekt_Gruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Gruppe> l =dataSource.getGruppesFromSubjektById(2);

                if(l!= null && !l.isEmpty()){
                    for(int i=0; i<l.size(); i++)
                    {
                        Log.i("click", "Gesuchte Gruppe: " + l.get(i).getName());
                    }
                }else{
                    Log.i("click", "keine Gruppen gefunden" );
                }

            }

        });


    }




    private void activateFindButton2() {
        Button buttonFindSubjekt_Gruppe = (Button) findViewById(R.id.button_select_subjekt_gruppe_2);


        buttonFindSubjekt_Gruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Subjekt> s =dataSource.getSubjektsFromGruppeByStringId("main");

                if(s!= null && !s.isEmpty()){


                    for(int i=0; i<s.size(); i++)
                    {
                        Log.i("click", "Gesuchter Subjekt: " + s.get(i).getVorname());
                    }

                }else{

                    Log.i("click", "keine Subjekt gefunden" );
                }

            }

        });


    }







    private void initializeContextualActionBar() {
        final ListView subjekt_gruppesListView = (ListView) findViewById(R.id.listview_subjekt_gruppes);
        subjekt_gruppesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        subjekt_gruppesListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedSubjekt_GruppesPositions = subjekt_gruppesListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedSubjekt_GruppesPositions.size(); i++) {
                            boolean isChecked = touchedSubjekt_GruppesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSubjekt_GruppesPositions.keyAt(i);
                                ContentValues subjekt_gruppe = (ContentValues) subjekt_gruppesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + subjekt_gruppe.toString());
                                dataSource.deleteSubjekt_Gruppe(subjekt_gruppe);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedSubjekt_GruppesPositions.size(); i++) {
                            boolean isChecked = touchedSubjekt_GruppesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSubjekt_GruppesPositions.keyAt(i);
                                ContentValues old_subjekt_gruppe = (ContentValues) subjekt_gruppesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + old_subjekt_gruppe.toString());

                                AlertDialog editSubjekt_GruppeDialog = createEditSubjekt_GruppeDialog(old_subjekt_gruppe);
                                editSubjekt_GruppeDialog.show();
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

    private AlertDialog createEditSubjekt_GruppeDialog(final ContentValues old_subjekt_gruppe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_subjekt_gruppe_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_gruppe_1);
        editText1.setText(old_subjekt_gruppe.getAsString("subjekt_id"));

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_subjekt_gruppe_2);
        editText2.setText(old_subjekt_gruppe.getAsString("gruppe_id"));






        builder.setView(dialogsView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s1 = editText1.getText().toString();
                        String s2 = editText2.getText().toString();

                        ContentValues newValues = new ContentValues();
                        newValues.put(MyDbHelper.SL_SCHUELER_ID, s1);
                        newValues.put(MyDbHelper.SL_LERNGRUPPE_ID, s2);




/*                        if ((TextUtils.isEmpty(s1)) || (TextUtils.isEmpty(s2)) || (TextUtils.isEmpty(s3)) || (TextUtils.isEmpty(s4)) || (TextUtils.isEmpty(s5))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }*/


                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        ContentValues updatedSubjekt_Gruppe = dataSource.updateSubjekt_Gruppe(old_subjekt_gruppe, newValues);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + old_subjekt_gruppe.getAsString("subjekt_id") + " Inhalt: " + old_subjekt_gruppe.getAsString("gruppe_id"));
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedSubjekt_Gruppe.getAsString("subjekt_id") + " Inhalt: " + updatedSubjekt_Gruppe.getAsString("gruppe_id"));

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