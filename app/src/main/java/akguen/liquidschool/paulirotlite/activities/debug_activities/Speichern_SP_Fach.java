package akguen.liquidschool.paulirotlite.activities.debug_activities;

import android.content.DialogInterface;
import android.graphics.Color;
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
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import akguen.liquidschool.mylib2.db.DataSource_Gueltigkeitsbereich;
import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.mylib2.db.DataSource_Raum;
import akguen.liquidschool.mylib2.db.DataSource_SP_Fach;
import akguen.liquidschool.mylib2.db.DataSource_Thema;
import akguen.liquidschool.paulirotlite.adapters.old_adapters.CustomExpandableListAdapter;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.model.Gueltigkeitsbereich;
import akguen.liquidschool.mylib2.model.Lerngruppe;
import akguen.liquidschool.mylib2.model.Raum;
import akguen.liquidschool.mylib2.model.SP_Fach;
import akguen.liquidschool.mylib2.model.Thema;

public class Speichern_SP_Fach extends AppCompatActivity {

    public static final String LOG_TAG = Speichern_SP_Fach.class.getSimpleName();

    private DataSource_SP_Fach dataSource;
    private DataSource_Thema dataSource_thema;
    private DataSource_Raum dataSource_raum;
    private DataSource_Gueltigkeitsbereich dataSource_gueltigkeit;
    private DataSource_Lerngruppe dataSource_lerngruppe;


    private ListView mSP_FachsListView;

    ExpandableListView expandableListView;
    CustomExpandableListAdapter myAdapter;
    List<String> expandableListTitle;
    //HashMap<String, List<String>> expandableListDetail;

    Thema selectedThema;
    Raum selectedRaum;
    Gueltigkeitsbereich selectedGueltig;
    Lerngruppe selectedLerngruppe;

    int pos_thema = -1;
    int pos_raum = -1;
    int pos_gueltig = -1;
    int pos_lerngruppe = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sp_fach_sq);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_SP_Fach(this);
        dataSource_thema = new DataSource_Thema(this);

        dataSource_raum = new DataSource_Raum(this);
        dataSource_gueltigkeit = new DataSource_Gueltigkeitsbereich(this);
        dataSource_lerngruppe = new DataSource_Lerngruppe(this);

        dataSource_thema.open();
        dataSource_raum.open();
        dataSource_gueltigkeit.open();
        dataSource_lerngruppe.open();


        initializeSP_FachsListView();

        activateAddButton();
        activateFillButton();
        activateFindButton();
        initializeContextualActionBar();
        activateExpandableListView();
    }

    private int save = -1;

    private void activateExpandableListView() {


        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView_sp_fach);

        Map<String, List<?>> expandableListDetail = new TreeMap<String, List<?>>();

        List<Thema> themas = new ArrayList<Thema>();
        themas = dataSource_thema.getAllThemas();


        List<Raum> raums = new ArrayList<Raum>();
        raums = dataSource_raum.getAllRaums();


        List<Gueltigkeitsbereich> gueltigkeits = new ArrayList<Gueltigkeitsbereich>();
        gueltigkeits = dataSource_gueltigkeit.getAllGueltigkeitsbereichs();


        List<Lerngruppe> lerngruppes = new ArrayList<Lerngruppe>();
        lerngruppes = dataSource_lerngruppe.getAllLerngruppes();


        expandableListDetail.put("Wähle ein Schulfach", themas);
        expandableListDetail.put("Wähle einen Raum", raums);
        expandableListDetail.put("Wähle eine Unterrichtsart", gueltigkeits);
        expandableListDetail.put("Wähle eine Lerngruppe", lerngruppes);


        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        myAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(myAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();


            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();


            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();


                Object o = expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition);

                if (o instanceof Thema) {
                    myAdapter.setT((Thema) o);
                    selectedThema = (Thema) o;

                    if (myAdapter.lastViewThema != null) {
                        myAdapter.lastViewThema.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                    v.setBackgroundResource(R.color.colorPrimaryDisable);
                    myAdapter.lastViewThema = v;


                } else if (o instanceof Raum) {
                    myAdapter.setR((Raum) o);
                    selectedRaum = (Raum) o;

                    if (myAdapter.lastViewRaum != null) {
                        myAdapter.lastViewRaum.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                    v.setBackgroundResource(R.color.colorPrimaryDisable);

                    myAdapter.lastViewRaum = v;


                } else if (o instanceof Gueltigkeitsbereich) {
                    myAdapter.setG((Gueltigkeitsbereich) o);
                    selectedGueltig = (Gueltigkeitsbereich) o;

                    if (myAdapter.lastViewGueltig != null) {
                        myAdapter.lastViewGueltig.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                    v.setBackgroundResource(R.color.colorPrimaryDisable);

                    myAdapter.lastViewGueltig = v;


                } else if (o instanceof Lerngruppe) {
                    myAdapter.setL((Lerngruppe) o);
                    selectedLerngruppe = (Lerngruppe) o;

                    if (myAdapter.lastViewLerngruppe != null) {
                        myAdapter.lastViewLerngruppe.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                    v.setBackgroundResource(R.color.colorPrimaryDisable);

                    myAdapter.lastViewLerngruppe = v;

                }


                return false;
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();
        dataSource_thema.open();
        dataSource_raum.open();
        dataSource_gueltigkeit.open();
        dataSource_lerngruppe.open();


        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
        dataSource_thema.close();
        dataSource_raum.close();
        dataSource_gueltigkeit.close();
        dataSource_lerngruppe.close();
    }

    private void initializeSP_FachsListView() {
        List<SP_Fach> emptyListForInitialization = new ArrayList<>();

        mSP_FachsListView = (ListView) findViewById(R.id.listview_sp_fachs);

        // Erstellen des ArrayAdapters für unseren ListView
        ArrayAdapter<SP_Fach> sp_fachArrayAdapter = new ArrayAdapter<SP_Fach>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                emptyListForInitialization) {

            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                SP_Fach memo = (SP_Fach) mSP_FachsListView.getItemAtPosition(position);

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

        mSP_FachsListView.setAdapter(sp_fachArrayAdapter);

        mSP_FachsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SP_Fach sp_fach = (SP_Fach) adapterView.getItemAtPosition(position);

                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
                // Dann ListView neu zeichnen mit showAllListEntries()
/*                SP_Fach updatedSP_Fach = dataSource.updateSP_Fach(sp_fach.getId(), sp_fach.getVorname(), sp_fach.getItemType(), sp_fach.getPasswort(), sp_fach.getKuerzel(), sp_fach.getStatus());
                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedSP_Fach.toString() + " ist: ");
                showAllListEntries();*/
            }
        });

    }

    private void showAllListEntries() {
        List<SP_Fach> sp_fachList = dataSource.getAllSP_Fachs();

        ArrayAdapter<SP_Fach> adapter = (ArrayAdapter<SP_Fach>) mSP_FachsListView.getAdapter();

        adapter.clear();
        adapter.addAll(sp_fachList);
        adapter.notifyDataSetChanged();
    }

    private void activateAddButton() {
        Button buttonAddSP_Fach = (Button) findViewById(R.id.button_add_sp_fach);
        final EditText editText1 = (EditText) findViewById(R.id.editText_sp_fach_11);
       /* final EditText editText2 = (EditText) findViewById(R.id.editText_sp_fach_22);
        final EditText editText3 = (EditText) findViewById(R.id.editText_sp_fach_33);
        final EditText editText4 = (EditText) findViewById(R.id.editText_sp_fach_44);
        final EditText editText5 = (EditText) findViewById(R.id.editText_sp_fach_55);*/

        buttonAddSP_Fach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = editText1.getText().toString();
                int s2 = selectedThema.getId();
                int s3 = selectedRaum.getId();
                int s4 = selectedGueltig.getId();
                int s5 = selectedLerngruppe.getId();

   /*             if (TextUtils.isEmpty(s2)) {
                    editText2.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if (TextUtils.isEmpty(s3)) {
                    editText3.setError(getString(R.string.editText_errorMessage));
                    return;
                }*/


                editText1.setText("");
             /*   editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText5.setText("");*/


                dataSource.createSP_Fach(s1,s2,s3,s4,s5);
                myAdapter.lastViewThema=null;
                myAdapter.lastViewRaum=null;
                myAdapter.lastViewGueltig=null;
                myAdapter.lastViewLerngruppe=null;

                myAdapter.setT(null);
                myAdapter.setR(null);
                myAdapter.setG(null);
                myAdapter.setL(null);

                selectedThema = null;
                selectedRaum = null;
                selectedGueltig = null;
                selectedLerngruppe = null;

                myAdapter.notifyDataSetChanged();

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
        Button buttonFillSP_Fach = (Button) findViewById(R.id.button_add_sp_fachs);


        buttonFillSP_Fach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Filler f = new Filler();
                String[] tempArray;






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
        Button buttonFindSP_Fach = (Button) findViewById(R.id.button_select_sp_fach);


        buttonFindSP_Fach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dataSource.getSP_FachById(2) != null) {

                    Log.i("click", "Gesuchter SP_Fach: " + dataSource.getSP_FachById(2).toString());
                } else {

                    Log.i("click", "Gesuchter SP_Fach mit der id 5 nicht gefunden");
                }

            }

        });


    }


    private void initializeContextualActionBar() {
        final ListView sp_fachsListView = (ListView) findViewById(R.id.listview_sp_fachs);
        sp_fachsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        sp_fachsListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

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
                SparseBooleanArray touchedSP_FachsPositions = sp_fachsListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedSP_FachsPositions.size(); i++) {
                            boolean isChecked = touchedSP_FachsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSP_FachsPositions.keyAt(i);
                                SP_Fach sp_fach = (SP_Fach) sp_fachsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + sp_fach.toString());
                                dataSource.deleteSP_Fach(sp_fach);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedSP_FachsPositions.size(); i++) {
                            boolean isChecked = touchedSP_FachsPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedSP_FachsPositions.keyAt(i);
                                SP_Fach sp_fach = (SP_Fach) sp_fachsListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + sp_fach.toString());

                                AlertDialog editSP_FachDialog = createEditSP_FachDialog(sp_fach);
                                editSP_FachDialog.show();
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

    private AlertDialog createEditSP_FachDialog(final SP_Fach sp_fach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_sp_fach_sq, null);

        final EditText editText1 = (EditText) dialogsView.findViewById(R.id.editText_sp_fach_1);
        final EditText editText2 = (EditText) dialogsView.findViewById(R.id.editText_sp_fach_2);
        final EditText editText3 = (EditText) dialogsView.findViewById(R.id.editText_sp_fach_3);
        final EditText editText4 = (EditText) dialogsView.findViewById(R.id.editText_sp_fach_4);
        final EditText editText5 = (EditText) dialogsView.findViewById(R.id.editText_sp_fach_5);

        Log.d(LOG_TAG, "Position im ListViewXXX: " + sp_fach.getThema_id());

        editText1.setText(sp_fach.getTeststring());
        editText2.setText(String.valueOf(sp_fach.getThema_id()));
        editText3.setText(String.valueOf(sp_fach.getRaum_id()));
        editText4.setText(String.valueOf(sp_fach.getGueltigkeitsbereich_id()));
        editText5.setText(String.valueOf(sp_fach.getLerngruppe_id()));


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
                        SP_Fach updatedSP_Fach = dataSource.updateSP_Fach(sp_fach.getId(), s1, s2, s3, s4, s5);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + sp_fach.getId() + " Inhalt: " + sp_fach.toString());
                        if (updatedSP_Fach != null) {
                            Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedSP_Fach.getId() + " Inhalt: " + updatedSP_Fach.toString());
                        } else {
                            Log.d(LOG_TAG, "Alter Eintrag wurde nicht geändert - ID: " + sp_fach.getId() + " Inhalt: " + sp_fach.toString());

                        }
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