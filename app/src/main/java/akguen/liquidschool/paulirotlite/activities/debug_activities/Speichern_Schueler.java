package akguen.liquidschool.paulirotlite.activities.debug_activities;

import android.content.DialogInterface;
import android.content.Intent;
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

import akguen.liquidschool.mylib2.db.DataSource_Schueler;
import akguen.liquidschool.paulirotlite.activities.old_activities.E_SchulerprofilActivity;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Schueler;
import test_activities.Filler;

public class Speichern_Schueler extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Schueler.class.getSimpleName();

    private DataSource_Schueler dataSource;

    private ListView mSchuelersListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_schueler_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Schueler(this);

        initializeSchuelersListView();

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

    private void initializeSchuelersListView() {
        List<Schueler> emptyListForInitialization = new ArrayList<>();

        mSchuelersListView = (ListView) findViewById(R.id.listview_schuelers);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<Schueler> schuelerArrayAdapter = new ArrayAdapter<Schueler>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                Schueler memo = (Schueler) mSchuelersListView.getItemAtPosition(position);

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

        mSchuelersListView.setAdapter(schuelerArrayAdapter);

        mSchuelersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Schueler schueler = (Schueler) adapterView.getItemAtPosition(position);

                Intent intent= new Intent(Speichern_Schueler.this, E_SchulerprofilActivity.class);
                intent.putExtra("id", schueler.getId());
                intent.putExtra("vorname", schueler.getVorname());
                intent.putExtra("surname", schueler.getSurname());
                intent.putExtra("nachname", schueler.getItemType());
                intent.putExtra("geschlecht", schueler.getGeschlecht());
                intent.putExtra("geburtstag", schueler.getGeburtstag());


                startActivity(intent);
            }
        });

    }

    private void showAllListEntries() {
        List<Schueler> schuelerList = dataSource.getAllSchuelers();

        ArrayAdapter<Schueler> adapter = (ArrayAdapter<Schueler>) mSchuelersListView.getAdapter();

        adapter.clear();
        adapter.addAll(schuelerList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddSchueler = (Button) findViewById(R.id.button_add_schueler);
        final EditText editText1 = (EditText) findViewById(R.id.editText_schueler_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_schueler_22);
        final EditText editText3 = (EditText) findViewById(R.id.editText_schueler_33);
        final EditText editText4 = (EditText) findViewById(R.id.editText_schueler_44);
        final EditText editText5 = (EditText) findViewById(R.id.editText_schueler_55);
        final EditText editText6 = (EditText) findViewById(R.id.editText_schueler_66);
        final EditText editText7 = (EditText) findViewById(R.id.editText_schueler_77);
        final EditText editText8 = (EditText) findViewById(R.id.editText_schueler_88);
        buttonAddSchueler.setOnClickListener(new View.OnClickListener() {
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
                editText6.setText("");
                editText7.setText("");
                editText8.setText("");

                dataSource.createSchueler(s1, s2, s3, s4, s5, s6, s7, s8);

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
        Button buttonFillSchueler = (Button) findViewById(R.id.button_add_schuelers);


        buttonFillSchueler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Filler f = new Filler();

                f.fillSchueler(getBaseContext());






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
        Button buttonFindSchueler = (Button) findViewById(R.id.button_select_schueler);


        buttonFindSchueler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( dataSource.getSchuelerById(5)!= null){

                    Log.i("click", "Gesuchter Schueler: " + dataSource.getSchuelerById(5).toString());
                }else{

                    Log.i("click", "Gesuchter Schueler mit der id 5 nicht gefunden" );
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView schuelersListView = (ListView) findViewById(R.id.listview_schuelers);
        schuelersListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        schuelersListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedSchuelersPositions = schuelersListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedSchuelersPositions.size(); i++) {
                            boolean isChecked = touchedSchuelersPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSchuelersPositions.keyAt(i);
                                Schueler schueler = (Schueler) schuelersListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + schueler.toString());
                                dataSource.deleteSchueler(schueler);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedSchuelersPositions.size(); i++) {
                            boolean isChecked = touchedSchuelersPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSchuelersPositions.keyAt(i);
                                Schueler schueler = (Schueler) schuelersListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + schueler.toString());

                                AlertDialog editSchuelerDialog = createEditSchuelerDialog(schueler);
                                editSchuelerDialog.show();
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

    private AlertDialog createEditSchuelerDialog(final Schueler schueler) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_schueler_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_schueler_1);
        editText1.setText(schueler.getVorname());

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_schueler_2);
        editText2.setText(schueler.getSurname());

        final EditText editText3 = (EditText) dialogsView.findViewById(R.id.editText_schueler_3);
        editText3.setText(schueler.getItemType());

        final EditText editText4 = (EditText) dialogsView.findViewById(R.id.editText_schueler_4);
        editText4.setText(schueler.getRufname());

        final EditText editText5 = (EditText) dialogsView.findViewById(R.id.editText_schueler_5);
        editText5.setText(schueler.getGeschlecht());

        final EditText editText6 = (EditText) dialogsView.findViewById(R.id.editText_schueler_6);
        editText6.setText(schueler.getStatus());


        final EditText editText7 = (EditText) dialogsView.findViewById(R.id.editText_schueler_7);
        editText7.setText(schueler.getGeburtstag());

        final EditText editText8 = (EditText) dialogsView.findViewById(R.id.editText_schueler_8);
        editText8.setText(schueler.getGeburtsort());




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


                       /* if ((TextUtils.isEmpty(s1)) || (TextUtils.isEmpty(s2)) || (TextUtils.isEmpty(s3)) || (TextUtils.isEmpty(s4)) || (TextUtils.isEmpty(s5))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }*/


                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        Schueler updatedSchueler = dataSource.updateSchueler(schueler.getId(), s1, s2, s3, s4, s5, s6, s7, s8);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + schueler.getId() + " Inhalt: " + schueler.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedSchueler.getId() + " Inhalt: " + updatedSchueler.toString());

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