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

import akguen.liquidschool.mylib2.db.DataSource_Schueler_Lerngruppe;
import akguen.liquidschool.mylib2.db.MyDbHelper;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Lerngruppe;
import akguen.liquidschool.mylib2.model.Schueler;


public class Speichern_Schueler_Lerngruppe extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Schueler_Lerngruppe.class.getSimpleName();

    private DataSource_Schueler_Lerngruppe dataSource;

    private ListView mSchueler_LerngruppesListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_schueler_lerngruppe_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Schueler_Lerngruppe(this);

        initializeSchueler_LerngruppesListView();

        activateAddButton();
        activateFillButton();
        activateFindButton1();
        activateFindButton2();
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

    private void initializeSchueler_LerngruppesListView() {
       /* ContentValues values = new ContentValues();
        values.put(MyDbHelper.LERNGRUPPE_COLUMN_NAME, v1);*/


        List<ContentValues> emptyListForInitialization = new ArrayList<>();

        mSchueler_LerngruppesListView = (ListView) findViewById(R.id.listview_schueler_lerngruppes);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<ContentValues> schueler_lerngruppeArrayAdapter = new ArrayAdapter<ContentValues>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                ContentValues memo = (ContentValues) mSchueler_LerngruppesListView.getItemAtPosition(position);

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

        mSchueler_LerngruppesListView.setAdapter(schueler_lerngruppeArrayAdapter);

        mSchueler_LerngruppesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ContentValues schueler_lerngruppe = (ContentValues) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Schueler_Lerngruppe updatedSchueler_Lerngruppe = dataSource.updateSchueler_Lerngruppe(schueler_lerngruppe.getId(), schueler_lerngruppe.getVorname(), schueler_lerngruppe.getItemType(), schueler_lerngruppe.getPasswort(), schueler_lerngruppe.getKuerzel(), schueler_lerngruppe.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedSchueler_Lerngruppe.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<ContentValues> schueler_lerngruppeList = dataSource.getAllSchueler_Lerngruppes();

        ArrayAdapter<ContentValues> adapter = (ArrayAdapter<ContentValues>) mSchueler_LerngruppesListView.getAdapter();

        adapter.clear();
        adapter.addAll(schueler_lerngruppeList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddSchueler_Lerngruppe = (Button) findViewById(R.id.button_add_schueler_lerngruppe);
        final EditText editText1 = (EditText) findViewById(R.id.editText_schueler_lerngruppe_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_schueler_lerngruppe_22);




        buttonAddSchueler_Lerngruppe.setOnClickListener(new View.OnClickListener() {
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

                ContentValues values = new ContentValues();
                values.put(MyDbHelper.SL_SCHUELER_ID, s1);
                values.put(MyDbHelper.SL_LERNGRUPPE_ID, s2);



                dataSource.createSchueler_Lerngruppe(values);

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
        Button buttonFillSchueler_Lerngruppe = (Button) findViewById(R.id.button_add_schueler_lerngruppes);


        buttonFillSchueler_Lerngruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Filler f = new Filler();
                String[] tempArray;


                String delimiter = "--";

                tempArray = f.alleSchueler_LerngruppeString.split(delimiter);

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




                    dataSource.createSchueler_Lerngruppe(s2, s1, s3, s4, s5);







                }

  */
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
        Button buttonFindSchueler_Lerngruppe = (Button) findViewById(R.id.button_select_schueler_lerngruppe_1);


        buttonFindSchueler_Lerngruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Lerngruppe> l =dataSource.getLerngruppesFromSchuelerById(2);

                if(l!= null && !l.isEmpty()){
                    for(int i=0; i<l.size(); i++)
                    {
                        Log.i("click", "Gesuchte Lerngruppe: " + l.get(i).getName());
                    }
                }else{
                    Log.i("click", "keine Lerngruppen gefunden" );
                }

            }

        });


    }




    private void activateFindButton2() {
        Button buttonFindSchueler_Lerngruppe = (Button) findViewById(R.id.button_select_schueler_lerngruppe_2);


        buttonFindSchueler_Lerngruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Schueler> s =dataSource.getSchuelersFromLerngruppeById(2);

                if(s!= null && !s.isEmpty()){


                    for(int i=0; i<s.size(); i++)
                    {
                        Log.i("click", "Gesuchter Schueler: " + s.get(i).getVorname()+ " " +s.get(i).getItemType());
                    }

                }else{

                    Log.i("click", "keine Schueler gefunden" );
                }

            }

        });


    }







    private void initializeContextualActionBar() {
        final ListView schueler_lerngruppesListView = (ListView) findViewById(R.id.listview_schueler_lerngruppes);
        schueler_lerngruppesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        schueler_lerngruppesListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedSchueler_LerngruppesPositions = schueler_lerngruppesListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedSchueler_LerngruppesPositions.size(); i++) {
                            boolean isChecked = touchedSchueler_LerngruppesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSchueler_LerngruppesPositions.keyAt(i);
                                ContentValues schueler_lerngruppe = (ContentValues) schueler_lerngruppesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + schueler_lerngruppe.toString());
                                dataSource.deleteSchueler_Lerngruppe(schueler_lerngruppe);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedSchueler_LerngruppesPositions.size(); i++) {
                            boolean isChecked = touchedSchueler_LerngruppesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSchueler_LerngruppesPositions.keyAt(i);
                                ContentValues old_schueler_lerngruppe = (ContentValues) schueler_lerngruppesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + old_schueler_lerngruppe.toString());

                                AlertDialog editSchueler_LerngruppeDialog = createEditSchueler_LerngruppeDialog(old_schueler_lerngruppe);
                                editSchueler_LerngruppeDialog.show();
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

    private AlertDialog createEditSchueler_LerngruppeDialog(final ContentValues old_schueler_lerngruppe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_schueler_lerngruppe_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_schueler_lerngruppe_1);
        editText1.setText(old_schueler_lerngruppe.getAsString("schueler_id"));

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_schueler_lerngruppe_2);
        editText2.setText(old_schueler_lerngruppe.getAsString("lerngruppe_id"));






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
                        ContentValues updatedSchueler_Lerngruppe = dataSource.updateSchueler_Lerngruppe(old_schueler_lerngruppe, newValues);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + old_schueler_lerngruppe.getAsString("schueler_id") + " Inhalt: " + old_schueler_lerngruppe.getAsString("lerngruppe_id"));
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedSchueler_Lerngruppe.getAsString("schueler_id") + " Inhalt: " + updatedSchueler_Lerngruppe.getAsString("lerngruppe_id"));

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