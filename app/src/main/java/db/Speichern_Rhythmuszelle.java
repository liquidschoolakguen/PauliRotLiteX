package db;

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

import akguen.liquidschool.paulirotlite.R;
import model.Rhythmuszelle;

public class Speichern_Rhythmuszelle extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Rhythmuszelle.class.getSimpleName();

    private DataSource_Rhythmuszelle dataSource;

    private ListView mRhythmuszellesListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rhythmuszelle_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Rhythmuszelle(this);

        initializeRhythmuszellesListView();

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

    private void initializeRhythmuszellesListView() {
        List<Rhythmuszelle> emptyListForInitialization = new ArrayList<>();

        mRhythmuszellesListView = (ListView) findViewById(R.id.listview_rhythmuszelles);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<Rhythmuszelle> rhythmuszelleArrayAdapter = new ArrayAdapter<Rhythmuszelle>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                Rhythmuszelle memo = (Rhythmuszelle) mRhythmuszellesListView.getItemAtPosition(position);

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

        mRhythmuszellesListView.setAdapter(rhythmuszelleArrayAdapter);

        mRhythmuszellesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Rhythmuszelle rhythmuszelle = (Rhythmuszelle) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Rhythmuszelle updatedRhythmuszelle = dataSource.updateRhythmuszelle(rhythmuszelle.getId(), rhythmuszelle.getVorname(), rhythmuszelle.getNachname(), rhythmuszelle.getPasswort(), rhythmuszelle.getKuerzel(), rhythmuszelle.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedRhythmuszelle.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<Rhythmuszelle> rhythmuszelleList = dataSource.getAllRhythmuszelles();

        ArrayAdapter<Rhythmuszelle> adapter = (ArrayAdapter<Rhythmuszelle>) mRhythmuszellesListView.getAdapter();

        adapter.clear();
        adapter.addAll(rhythmuszelleList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddRhythmuszelle = (Button) findViewById(R.id.button_add_rhythmuszelle);
        final EditText editText1 = (EditText) findViewById(R.id.editText_rhythmuszelle_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_rhythmuszelle_22);
        final EditText editText3 = (EditText) findViewById(R.id.editText_rhythmuszelle_33);
        final EditText editText4 = (EditText) findViewById(R.id.editText_rhythmuszelle_44);
        final EditText editText5 = (EditText) findViewById(R.id.editText_rhythmuszelle_55);


        buttonAddRhythmuszelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                String s4 = editText4.getText().toString();
                String s5 = editText5.getText().toString();


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
                editText5.setText("");


                dataSource.createRhythmuszelle(s1, s2, s3, s4, s5);

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
        Button buttonFillRhythmuszelle = (Button) findViewById(R.id.button_add_rhythmuszelles);


        buttonFillRhythmuszelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           /*     Filler f = new Filler();
                String[] tempArray;


                String delimiter = "--";

                tempArray = f.alleRhythmuszelleString.split(delimiter);

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




                    dataSource.createRhythmuszelle(s2, s1, s3, s4, s5);







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
        Button buttonFindRhythmuszelle = (Button) findViewById(R.id.button_select_rhythmuszelle);


        buttonFindRhythmuszelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( dataSource.getRhythmuszelleById(5)!= null){

                    Log.i("click", "Gesuchter Rhythmuszelle: " + dataSource.getRhythmuszelleById(5).toString());
                }else{

                    Log.i("click", "Gesuchter Rhythmuszelle mit der id 5 nicht gefunden" );
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView rhythmuszellesListView = (ListView) findViewById(R.id.listview_rhythmuszelles);
        rhythmuszellesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        rhythmuszellesListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedRhythmuszellesPositions = rhythmuszellesListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedRhythmuszellesPositions.size(); i++) {
                            boolean isChecked = touchedRhythmuszellesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedRhythmuszellesPositions.keyAt(i);
                                Rhythmuszelle rhythmuszelle = (Rhythmuszelle) rhythmuszellesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + rhythmuszelle.toString());
                                dataSource.deleteRhythmuszelle(rhythmuszelle);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedRhythmuszellesPositions.size(); i++) {
                            boolean isChecked = touchedRhythmuszellesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedRhythmuszellesPositions.keyAt(i);
                                Rhythmuszelle rhythmuszelle = (Rhythmuszelle) rhythmuszellesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + rhythmuszelle.toString());

                                AlertDialog editRhythmuszelleDialog = createEditRhythmuszelleDialog(rhythmuszelle);
                                editRhythmuszelleDialog.show();
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

    private AlertDialog createEditRhythmuszelleDialog(final Rhythmuszelle rhythmuszelle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_rhythmuszelle_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_rhythmuszelle_1);
        editText1.setText(rhythmuszelle.getWochentag());

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_rhythmuszelle_2);
        editText2.setText(rhythmuszelle.getTyp());

        final EditText editText3 = (EditText) dialogsView.findViewById(R.id.editText_rhythmuszelle_3);
        editText3.setText(String.valueOf(rhythmuszelle.getNummer()));

        final EditText editText4 = (EditText) dialogsView.findViewById(R.id.editText_rhythmuszelle_4);
        editText4.setText(rhythmuszelle.getVon());

        final EditText editText5 = (EditText) dialogsView.findViewById(R.id.editText_rhythmuszelle_5);
        editText5.setText(rhythmuszelle.getBis());


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

/*                        if ((TextUtils.isEmpty(s1)) || (TextUtils.isEmpty(s2)) || (TextUtils.isEmpty(s3)) || (TextUtils.isEmpty(s4)) || (TextUtils.isEmpty(s5))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }*/


                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        Rhythmuszelle updatedRhythmuszelle = dataSource.updateRhythmuszelle(rhythmuszelle.getId(), s1, s2, s3, s4, s5);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + rhythmuszelle.getId() + " Inhalt: " + rhythmuszelle.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedRhythmuszelle.getId() + " Inhalt: " + updatedRhythmuszelle.toString());

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