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

import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Lerngruppe;
import akguen.liquidschool.mylib2.model.SP_Fach;
import test_activities.Filler;

public class Speichern_Lerngruppe extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Lerngruppe.class.getSimpleName();

    private DataSource_Lerngruppe dataSource;

    private ListView mLerngruppesListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lerngruppe_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Lerngruppe(this);

        initializeLerngruppesListView();

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

    private void initializeLerngruppesListView() {
        List<Lerngruppe> emptyListForInitialization = new ArrayList<>();

        mLerngruppesListView = (ListView) findViewById(R.id.listview_lerngruppes);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<Lerngruppe> lerngruppeArrayAdapter = new ArrayAdapter<Lerngruppe>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                Lerngruppe memo = (Lerngruppe) mLerngruppesListView.getItemAtPosition(position);

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

        mLerngruppesListView.setAdapter(lerngruppeArrayAdapter);

        mLerngruppesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Lerngruppe lerngruppe = (Lerngruppe) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Lerngruppe updatedLerngruppe = dataSource.updateLerngruppe(lerngruppe.getId(), lerngruppe.getVorname(), lerngruppe.getItemType(), lerngruppe.getPasswort(), lerngruppe.getKuerzel(), lerngruppe.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedLerngruppe.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<Lerngruppe> lerngruppeList = dataSource.getAllLerngruppes();

        ArrayAdapter<Lerngruppe> adapter = (ArrayAdapter<Lerngruppe>) mLerngruppesListView.getAdapter();

        adapter.clear();
        adapter.addAll(lerngruppeList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddLerngruppe = (Button) findViewById(R.id.button_add_lerngruppe);
        final EditText editText1 = (EditText) findViewById(R.id.editText_lerngruppe_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_lerngruppe_11_lf_id);

        buttonAddLerngruppe.setOnClickListener(new View.OnClickListener() {
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

                dataSource.createLerngruppe(s1,s2);

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
        Button buttonFillLerngruppe = (Button) findViewById(R.id.button_add_lerngruppes);


        buttonFillLerngruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Filler f = new Filler();

               f.fillLerngruppe(getBaseContext());




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
        Button buttonFindLerngruppe = (Button) findViewById(R.id.button_select_lerngruppe);


        buttonFindLerngruppe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<SP_Fach> s =dataSource.getSP_FachsFromLerngruppeById(2);

                if(s!= null && !s.isEmpty()){


                    for(int i=0; i<s.size(); i++)
                    {
                        Log.i("click", "Gesuchter SP_Fach: " + s.get(i).getTeststring()+ " " +s.get(i).getLerngruppe_id());
                    }

                }else{

                    Log.i("click", "keine SP_Fach gefunden" );
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView lerngruppesListView = (ListView) findViewById(R.id.listview_lerngruppes);
        lerngruppesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        lerngruppesListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedLerngruppesPositions = lerngruppesListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedLerngruppesPositions.size(); i++) {
                            boolean isChecked = touchedLerngruppesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedLerngruppesPositions.keyAt(i);
                                Lerngruppe lerngruppe = (Lerngruppe) lerngruppesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + lerngruppe.toString());
                                dataSource.deleteLerngruppe(lerngruppe);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedLerngruppesPositions.size(); i++) {
                            boolean isChecked = touchedLerngruppesPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedLerngruppesPositions.keyAt(i);
                                Lerngruppe lerngruppe = (Lerngruppe) lerngruppesListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + lerngruppe.toString());

                                AlertDialog editLerngruppeDialog = createEditLerngruppeDialog(lerngruppe);
                                editLerngruppeDialog.show();
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

    private AlertDialog createEditLerngruppeDialog(final Lerngruppe lerngruppe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_lerngruppe_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_lerngruppe_1);
        editText1.setText(lerngruppe.getName());


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
                        Lerngruppe updatedLerngruppe = dataSource.updateLerngruppe(lerngruppe.getId(), s1);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + lerngruppe.getId() + " Inhalt: " + lerngruppe.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedLerngruppe.getId() + " Inhalt: " + updatedLerngruppe.toString());

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