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

import akguen.liquidschool.mylib2.db.DataSource_Vergehen;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Vergehen;
import test_activities.Filler;

public class Speichern_Vergehen extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_Vergehen.class.getSimpleName();

    private DataSource_Vergehen dataSource;

    private ListView mVergehensListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vergehen_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Vergehen(this);

        initializeVergehensListView();

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

    private void initializeVergehensListView() {
        List<Vergehen> emptyListForInitialization = new ArrayList<>();

        mVergehensListView = (ListView) findViewById(R.id.listview_vergehens);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<Vergehen> vergehenArrayAdapter = new ArrayAdapter<Vergehen>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                Vergehen memo = (Vergehen) mVergehensListView.getItemAtPosition(position);

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

        mVergehensListView.setAdapter(vergehenArrayAdapter);

        mVergehensListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Vergehen vergehen = (Vergehen) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                Vergehen updatedVergehen = dataSource.updateVergehen(vergehen.getId(), vergehen.getVorname(), vergehen.getItemType(), vergehen.getPasswort(), vergehen.getKuerzel(), vergehen.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedVergehen.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<Vergehen> vergehenList = dataSource.getAllVergehens();
        Collections.sort(vergehenList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Vergehen p1 = (Vergehen) o1;
                Vergehen p2 = (Vergehen) o2;
                return p1.getKategorie().compareToIgnoreCase(p2.getKategorie());
            }
        });
        ArrayAdapter<Vergehen> adapter = (ArrayAdapter<Vergehen>) mVergehensListView.getAdapter();

        adapter.clear();
        adapter.addAll(vergehenList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddVergehen = (Button) findViewById(R.id.button_add_vergehen);
        final EditText editText1 = (EditText) findViewById(R.id.editText_vergehen_11);
        final EditText editText2 = (EditText) findViewById(R.id.editText_vergehen_22);
        final EditText editText3 = (EditText) findViewById(R.id.editText_vergehen_33);
        final EditText editText4 = (EditText) findViewById(R.id.editText_vergehen_44);



        buttonAddVergehen.setOnClickListener(new View.OnClickListener() {
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



                dataSource.createVergehen(s1, s2, s3, s4);

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
        Button buttonFillVergehen = (Button) findViewById(R.id.button_add_vergehens);


        buttonFillVergehen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Filler f = new Filler();

                f.fillVergehen(getBaseContext());



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
        Button buttonFindVergehen = (Button) findViewById(R.id.button_select_vergehen);


        buttonFindVergehen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( dataSource.getVergehenById(5)!= null){

                    Log.i("click", "Gesuchter Vergehen: " + dataSource.getVergehenById(5).toString());
                }else{

                    Log.i("click", "Gesuchter Vergehen mit der id 5 nicht gefunden" );
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView vergehensListView = (ListView) findViewById(R.id.listview_vergehens);
        vergehensListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        vergehensListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedVergehensPositions = vergehensListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedVergehensPositions.size(); i++) {
                            boolean isChecked = touchedVergehensPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedVergehensPositions.keyAt(i);
                                Vergehen vergehen = (Vergehen) vergehensListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + vergehen.toString());
                                dataSource.deleteVergehen(vergehen);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedVergehensPositions.size(); i++) {
                            boolean isChecked = touchedVergehensPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedVergehensPositions.keyAt(i);
                                Vergehen vergehen = (Vergehen) vergehensListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + vergehen.toString());

                                AlertDialog editVergehenDialog = createEditVergehenDialog(vergehen);
                                editVergehenDialog.show();
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

    private AlertDialog createEditVergehenDialog(final Vergehen vergehen) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_vergehen_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_vergehen_1);
        editText1.setText(vergehen.getName());

        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_vergehen_2);
        editText2.setText(vergehen.getText());

        final EditText editText3 = (EditText) dialogsView.findViewById(R.id.editText_vergehen_3);
        editText3.setText(vergehen.getGewicht());

        final EditText editText4 = (EditText) dialogsView.findViewById(R.id.editText_vergehen_4);
        editText4.setText(vergehen.getKategorie());




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
                        Vergehen updatedVergehen = dataSource.updateVergehen(vergehen.getId(), s1, s2, s3, s4);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + vergehen.getId() + " Inhalt: " + vergehen.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedVergehen.getId() + " Inhalt: " + updatedVergehen.toString());

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