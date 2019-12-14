package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.mylib2.model.Lerngruppe;
import akguen.liquidschool.paulirotlite.R;

public class S6_LerngruppeBearbeitenLoeschen extends AppCompatActivity {

    public static final String LOG_TAG = S6_LerngruppeBearbeitenLoeschen.class.getSimpleName();

    private DataSource_Lerngruppe dataSource;

    private ListView mLerngruppesListView;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static AppCompatActivity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s6_lerngruppe_bearbeiten_loeschen);
        fa = this;



        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Lerngruppe(this);

        initializeLerngruppesListView();
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

    private void initializeLerngruppesListView() {
        List<Lerngruppe> emptyListForInitialization = new ArrayList<>();

        mLerngruppesListView = (ListView) findViewById(R.id.listview_lerngruppe_bearbeiten_loeschen);

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


                return view;
            }
        };

        mLerngruppesListView.setAdapter(lerngruppeArrayAdapter);

        mLerngruppesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Lerngruppe lerngruppe = (Lerngruppe) adapterView.getItemAtPosition(position);

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Log.d(LOG_TAG, "mmmmmmmmmmmmmmmmmmmmm."+Integer.toString(lerngruppe.getId()));
                editor.putString("selectedLerngruppeId", Integer.toString(lerngruppe.getId()));
                editor.commit();


               Intent speichernS1 = new Intent(S6_LerngruppeBearbeitenLoeschen.this, S7_LerngruppeUpdaten.class);
                startActivity(speichernS1);
               // finish();
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


    private void initializeContextualActionBar() {
        final ListView lerngruppesListView = (ListView) findViewById(R.id.listview_lerngruppe_bearbeiten_loeschen);
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
                MenuItem item = menu.findItem(R.id.cab_change);
                item.setVisible(false);
                return true;
            }

            // In dieser Callback-Methode reagieren wir auf den invalidate() Aufruf
            // Wir lassen das Edit-Symbol verschwinden, wenn mehr als 1 Eintrag ausgewählt ist
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                MenuItem item = menu.findItem(R.id.cab_change);


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




}