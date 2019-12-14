package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.mylib2.model.Lerngruppe;
import akguen.liquidschool.paulirotlite.R;


public class S5_LerngruppeWechseln extends AppCompatActivity {

    public static final String LOG_TAG = S5_LerngruppeWechseln.class.getSimpleName();

    private DataSource_Lerngruppe dataSource;

    private ListView mLerngruppesListView;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s5_lerngruppe_wechseln);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource_Lerngruppe(this);

        initializeLerngruppesListView();


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

        mLerngruppesListView = (ListView) findViewById(R.id.listview_lerngruppe_wechseln);

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

                editor.putString("selectedLerngruppeId", Integer.toString(lerngruppe.getId()));
                editor.commit();


                Intent speichernS1 = new Intent(S5_LerngruppeWechseln.this, S1_WaehleSchueler.class);
                startActivity(speichernS1);
                finish();
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







}