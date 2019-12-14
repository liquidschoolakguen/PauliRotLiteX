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

import akguen.liquidschool.mylib2.db.DataSource_Vergehen_Vergehengruppe;
import akguen.liquidschool.mylib2.db.MyDbHelper;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Vergehengruppe;
import akguen.liquidschool.mylib2.model.Vergehen;


public class Speichern_Vergehen_Vergehengruppe extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Vergehen_Vergehengruppe.class.getSimpleName();

    private DataSource_Vergehen_Vergehengruppe dataSource;

    private ListView mVergehen_VergehengruppesListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vergehen_vergehengruppe_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Vergehen_Vergehengruppe(this);

        initializeVergehen_VergehengruppesListView();

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
    public String annikaComesBack(){
    boolean sheMakesMeHappy = true;
    int herCrazyLevel = 3;

    if(sheMakesMeHappy==true && herCrazyLevel<=10){

        return "Komm bald zurück, ich brauche dich.";
    }else{

        return "Komm trotzdem bald.";
    }

    }
    private void initializeVergehen_VergehengruppesListView() {
       /* ContentValues values = new ContentValues();
        values.put(MyDbHelper.VERGEHENGRUPPE_COLUMN_NAME, v1);*/


        List<ContentValues> emptyListForInitialization = new ArrayList<>();

        mVergehen_VergehengruppesListView = (ListView) findViewById(R.id.listview_vergehen_vergehengruppes);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<ContentValues> vergehen_vergehengruppeArrayAdapter = new ArrayAdapter<ContentValues>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {


            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                ContentValues memo = (ContentValues) mVergehen_VergehengruppesListView.getItemAtPosition(position);

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

        mVergehen_VergehengruppesListView.setAdapter(vergehen_vergehengruppeArrayAdapter);

        mVergehen_VergehengruppesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ContentValues vergehen_vergehengruppe = (ContentValues) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Vergehen_Vergehengruppe updatedVergehen_Vergehengruppe = dataSource.updateVergehen_Vergehengruppe(vergehen_vergehengruppe.getId(), vergehen_vergehengruppe.getVorname(), vergehen_vergehengruppe.getItemType(), vergehen_vergehengruppe.getPasswort(), vergehen_vergehengruppe.getKuerzel(), vergehen_vergehengruppe.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedVergehen_Vergehengruppe.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<ContentValues> vergehen_vergehengruppeList = dataSource.getAllVergehen_Vergehengruppes();

        ArrayAdapter<ContentValues> adapter = (ArrayAdapter<ContentValues>) mVergehen_VergehengruppesListView.getAdapter();
        annikaComesBack();
        adapter.clear();
        adapter.addAll(vergehen_vergehengruppeList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddVergehen_Vergehengruppe = (Button) findViewById(R.id.button_add_vergehen_vergehengruppe);
        final EditText editText1 = (EditText) findViewById(R.id.editText_vergehen_vergehengruppe_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_vergehen_vergehengruppe_22);




        buttonAddVergehen_Vergehengruppe.setOnClickListener(new View.OnClickListener() {
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
                values.put(MyDbHelper.SL_VERGEHEN_ID, s1);
                values.put(MyDbHelper.SL_VERGEHENGRUPPE_ID, s2);



                dataSource.createVergehen_Vergehengruppe(values);

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
        Button buttonFillVergehen_Vergehengruppe = (Button) findViewById(R.id.button_add_vergehen_vergehengruppes);


        buttonFillVergehen_Vergehengruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Filler f = new Filler();
                String[] tempArray;


                String delimiter = "--";

                tempArray = f.alleVergehen_VergehengruppeString.split(delimiter);

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




                    dataSource.createVergehen_Vergehengruppe(s2, s1, s3, s4, s5);







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
        Button buttonFindVergehen_Vergehengruppe = (Button) findViewById(R.id.button_select_vergehen_vergehengruppe_1);


        buttonFindVergehen_Vergehengruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Vergehengruppe> l =dataSource.getVergehengruppesFromVergehenById(4);

                if(l!= null && !l.isEmpty()){
                    for(int i=0; i<l.size(); i++)
                    {
                        Log.i("click", "Gesuchte Vergehengruppe: " + l.get(i).getName());
                    }
                }else{
                    Log.i("click", "keine Vergehengruppen gefunden" );
                }

            }

        });


    }




    private void activateFindButton2() {
        Button buttonFindVergehen_Vergehengruppe = (Button) findViewById(R.id.button_select_vergehen_vergehengruppe_2);


        buttonFindVergehen_Vergehengruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Vergehen> s =dataSource.getVergehensFromVergehengruppeById(4);

                if(s!= null && !s.isEmpty()){


                    for(int i=0; i<s.size(); i++)
                    {
                        Log.i("click", "Gesuchter Vergehen: " + s.get(i).getName()+ " " +s.get(i).getGewicht());
                    }

                }else{

                    Log.i("click", "keine Vergehen gefunden" );
                }

            }

        });


    }







    private void initializeContextualActionBar() {
        final ListView vergehen_vergehengruppesListView = (ListView) findViewById(R.id.listview_vergehen_vergehengruppes);
        vergehen_vergehengruppesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        vergehen_vergehengruppesListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedVergehen_VergehengruppesPositions = vergehen_vergehengruppesListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedVergehen_VergehengruppesPositions.size(); i++) {
                            boolean isChecked = touchedVergehen_VergehengruppesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedVergehen_VergehengruppesPositions.keyAt(i);
                                ContentValues vergehen_vergehengruppe = (ContentValues) vergehen_vergehengruppesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + vergehen_vergehengruppe.toString());
                                dataSource.deleteVergehen_Vergehengruppe(vergehen_vergehengruppe);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedVergehen_VergehengruppesPositions.size(); i++) {
                            boolean isChecked = touchedVergehen_VergehengruppesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedVergehen_VergehengruppesPositions.keyAt(i);
                                ContentValues old_vergehen_vergehengruppe = (ContentValues) vergehen_vergehengruppesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + old_vergehen_vergehengruppe.toString());

                                AlertDialog editVergehen_VergehengruppeDialog = createEditVergehen_VergehengruppeDialog(old_vergehen_vergehengruppe);
                                editVergehen_VergehengruppeDialog.show();
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

    private AlertDialog createEditVergehen_VergehengruppeDialog(final ContentValues old_vergehen_vergehengruppe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_vergehen_vergehengruppe_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_vergehen_vergehengruppe_1);
        editText1.setText(old_vergehen_vergehengruppe.getAsString("vergehen_id"));

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_vergehen_vergehengruppe_2);
        editText2.setText(old_vergehen_vergehengruppe.getAsString("vergehengruppe_id"));






        builder.setView(dialogsView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s1 = editText1.getText().toString();
                        String s2 = editText2.getText().toString();

                        ContentValues newValues = new ContentValues();
                        newValues.put(MyDbHelper.SL_VERGEHEN_ID, s1);
                        newValues.put(MyDbHelper.SL_VERGEHENGRUPPE_ID, s2);




/*                        if ((TextUtils.isEmpty(s1)) || (TextUtils.isEmpty(s2)) || (TextUtils.isEmpty(s3)) || (TextUtils.isEmpty(s4)) || (TextUtils.isEmpty(s5))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }*/


                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        ContentValues updatedVergehen_Vergehengruppe = dataSource.updateVergehen_Vergehengruppe(old_vergehen_vergehengruppe, newValues);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + old_vergehen_vergehengruppe.getAsString("vergehen_id") + " Inhalt: " + old_vergehen_vergehengruppe.getAsString("vergehengruppe_id"));
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedVergehen_Vergehengruppe.getAsString("vergehen_id") + " Inhalt: " + updatedVergehen_Vergehengruppe.getAsString("vergehengruppe_id"));

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