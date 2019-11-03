package akguen.liquidschool.paulirotlite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import db.DataSource_Schueler_Lerngruppe;
import db.Speichern_Schueler;
import model.Schueler;

public class S1_WaehleSchueler extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DataSource_Schueler_Lerngruppe dS_L_S;
    private ListView mSchuelersListView;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;


    int idd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        dS_L_S = new DataSource_Schueler_Lerngruppe(this);


        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String lastDay = prefs.getString("lastDay", null);


        if (lastDay != null) {


        } else {

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(date);
            System.out.println(date);
            System.out.println("Converted String: " + strDate);

            try {
                Date date2 = new SimpleDateFormat("yyyy-mm-dd").parse(strDate);
                System.out.println(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = sharedpreferences.getString("selectedLerngruppeId", null);
        if (restoredText != null) {

            initializeSchuelersListView();



        } else {

            Intent speichernS1 = new Intent(S1_WaehleSchueler.this, S3_ErstelleLerngruppe.class);
            startActivity(speichernS1);
            finish();

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.s1__waehle_schueler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initializeSchuelersListView() {
        List<Schueler> emptyListForInitialization = new ArrayList<>();

        mSchuelersListView = (ListView) findViewById(R.id.s1_listview_schuelers);

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

                Intent intent = new Intent(S1_WaehleSchueler.this, S4_WaehleVergehen.class);
                intent.putExtra("id", schueler.getId());
                intent.putExtra("vorname", schueler.getVorname());
                intent.putExtra("nachname", schueler.getNachname());
                intent.putExtra("geschlecht", schueler.getGeschlecht());
                intent.putExtra("geburtstag", schueler.getGeburtstag());


                startActivity(intent);
            }
        });

    }


    private void showAllListEntries() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = sharedpreferences.getString("selectedLerngruppeId", null);

        idd = Integer.parseInt(restoredText);

        List<Schueler> schuelerList = dS_L_S.getSchuelersFromLerngruppeById(idd);

        ArrayAdapter<Schueler> adapter = (ArrayAdapter<Schueler>) mSchuelersListView.getAdapter();

        adapter.clear();
        adapter.addAll(schuelerList);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();


        dS_L_S.open();

        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        dS_L_S.close();


    }


}
