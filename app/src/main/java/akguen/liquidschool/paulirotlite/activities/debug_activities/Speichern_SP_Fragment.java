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

import akguen.liquidschool.mylib2.db.DataSource_SP_Fragment;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.SP_Fragment;

public class Speichern_SP_Fragment extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_SP_Fragment.class.getSimpleName();

    private DataSource_SP_Fragment dataSource;

    private ListView mSP_FragmentsListView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sp_fragment_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_SP_Fragment(this);

        initializeSP_FragmentsListView();

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

    private void initializeSP_FragmentsListView() {
        List<SP_Fragment> emptyListForInitialization = new ArrayList<>();

        mSP_FragmentsListView = (ListView) findViewById(R.id.listview_sp_fragments);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<SP_Fragment> sp_fragmentArrayAdapter = new ArrayAdapter<SP_Fragment>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                SP_Fragment memo = (SP_Fragment) mSP_FragmentsListView.getItemAtPosition(position);

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

        mSP_FragmentsListView.setAdapter(sp_fragmentArrayAdapter);

        mSP_FragmentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SP_Fragment sp_fragment = (SP_Fragment) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                SP_Fragment updatedSP_Fragment = dataSource.updateSP_Fragment(sp_fragment.getId(), sp_fragment.getVorname(), sp_fragment.getItemType(), sp_fragment.getPasswort(), sp_fragment.getKuerzel(), sp_fragment.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedSP_Fragment.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<SP_Fragment> sp_fragmentList = dataSource.getAllSP_Fragments();

        ArrayAdapter<SP_Fragment> adapter = (ArrayAdapter<SP_Fragment>) mSP_FragmentsListView.getAdapter();

        adapter.clear();
        adapter.addAll(sp_fragmentList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddSP_Fragment = (Button) findViewById(R.id.button_add_sp_fragment);
        final EditText editText1 = (EditText) findViewById(R.id.editText_sp_fragment_11);





        buttonAddSP_Fragment.setOnClickListener(new View.OnClickListener() {
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




                dataSource.createSP_Fragment(s1);

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
        Button buttonFillSP_Fragment = (Button) findViewById(R.id.button_add_sp_fragments);


        buttonFillSP_Fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Filler f = new Filler();
                String[] tempArray;


                String delimiter = "--";

                tempArray = f.alleSP_FragmentString.split(delimiter);

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




                    dataSource.createSP_Fragment(s2, s1, s3, s4, s5);







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
        Button buttonFindSP_Fragment = (Button) findViewById(R.id.button_select_sp_fragment);


        buttonFindSP_Fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( dataSource.getSP_FragmentById(2)!= null){

                    Log.i("click", "Gesuchter SP_Fragment: " + dataSource.getSP_FragmentById(5).toString());
                }else{

                    Log.i("click", "Gesuchter SP_Fragment mit der id 5 nicht gefunden" );
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView sp_fragmentsListView = (ListView) findViewById(R.id.listview_sp_fragments);
        sp_fragmentsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        sp_fragmentsListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedSP_FragmentsPositions = sp_fragmentsListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedSP_FragmentsPositions.size(); i++) {
                            boolean isChecked = touchedSP_FragmentsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSP_FragmentsPositions.keyAt(i);
                                SP_Fragment sp_fragment = (SP_Fragment) sp_fragmentsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + sp_fragment.toString());
                                dataSource.deleteSP_Fragment(sp_fragment);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedSP_FragmentsPositions.size(); i++) {
                            boolean isChecked = touchedSP_FragmentsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSP_FragmentsPositions.keyAt(i);
                                SP_Fragment sp_fragment = (SP_Fragment) sp_fragmentsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + sp_fragment.toString());

                                AlertDialog editSP_FragmentDialog = createEditSP_FragmentDialog(sp_fragment);
                                editSP_FragmentDialog.show();
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

    private AlertDialog createEditSP_FragmentDialog(final SP_Fragment sp_fragment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_sp_fragment_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_sp_fragment_1);
        editText1.setText(sp_fragment.getNummer());








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
                        SP_Fragment updatedSP_Fragment = dataSource.updateSP_Fragment(sp_fragment.getId(), s1);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + sp_fragment.getId() + " Inhalt: " + sp_fragment.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedSP_Fragment.getId() + " Inhalt: " + updatedSP_Fragment.toString());

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