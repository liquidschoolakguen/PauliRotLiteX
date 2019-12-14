package akguen.liquidschool.paulirotlite.activities.in_use;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import akguen.liquidschool.mylib2.db.DataSource_Schueler;
import akguen.liquidschool.mylib2.db.DataSource_Vergehen;

import akguen.liquidschool.mylib2.model.Schueler;
import akguen.liquidschool.mylib2.model.Vergehen;
import akguen.liquidschool.paulirotlite.activities.old_activities.E_FullScreenActivity;
import akguen.liquidschool.paulirotlite.activities.old_activities.E_FullScreenActivity2;
import akguen.liquidschool.paulirotlite.activities.old_activities.E_FullScreenActivity3;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.activities.old_activities.S3_ErstelleLerngruppe;
import akguen.liquidschool.paulirotlite.activities.old_activities.S5_LerngruppeWechseln;
import akguen.liquidschool.paulirotlite.activities.debug_activities.Debug_Main;

public class S4_WaehleVergehen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DataSource_Vergehen dS_Vergehen;
    private DataSource_Schueler dS_Schueler;
    private ListView mVergehensListView;
    public static AppCompatActivity fa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_navigation2);

        fa = this;


        dS_Vergehen = new DataSource_Vergehen(this);
        dS_Schueler = new DataSource_Schueler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        initializeVergehensListView();


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

        if (id == R.id.nav_lg_wechseln) {
            Intent speichernS1 = new Intent(S4_WaehleVergehen.this, S5_LerngruppeWechseln.class);
            startActivity(speichernS1);
            finish();
        } else if (id == R.id.nav_lg_neu) {
            Intent speichernS1 = new Intent(S4_WaehleVergehen.this, S3_ErstelleLerngruppe.class);
            startActivity(speichernS1);
            finish();
        } else if (id == R.id.nav_lg_bearbeiten) {

        } else if (id == R.id.nav_max_st) {


            List<Schueler> listZ = dS_Schueler.getAllSchuelers();

            for(Schueler s:listZ){

                String old = s.getGeburtstag();
                int oldI = Integer.parseInt(old);
                int newI;
                if(oldI>0){
                    newI = oldI-1;

                }else{
                    newI = oldI;

                }



                s.setGeburtstag(Integer.toString(newI));

                dS_Schueler.updateSchueler(s.getId(),s.getVorname(),s.getSurname(),null,null,null,null,s.getGeburtstag(),null);



            }


            Intent speichernS2 = new Intent(S4_WaehleVergehen.this, E_FullScreenActivity2.class);
            startActivity(speichernS2);
            finish();


        }else if (id == R.id.nav_max_st2) {
            Intent speichernS2 = new Intent(S4_WaehleVergehen.this, Debug_Main.class);
            startActivity(speichernS2);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initializeVergehensListView() {
        List<Vergehen> emptyListForInitialization = new ArrayList<>();

        mVergehensListView = (ListView) findViewById(R.id.s4_listview_vergehens);

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


                //intent.putExtra("id", schueler.getId());

               // Log.i("click","xxx " + getIntent().getExtras().getString("schueler_id"));
                String gg =  getIntent().getExtras().getString("schueler_id");
                Schueler s = dS_Schueler.getSchuelerById(Integer.parseInt(gg));

                s.setGeburtstag(Integer.toString(Integer.parseInt(s.getGeburtstag())+Integer.parseInt(vergehen.getGewicht())));

                if(Integer.parseInt(s.getGeburtstag())<10){
                    dS_Schueler.updateSchueler(s.getId(),s.getVorname(),s.getSurname(),s.getItemType(),s.getRufname(),s.getGeschlecht(),s.getStatus(),s.getGeburtstag(),s.getGeburtsort());
                    Intent intent = new Intent(S4_WaehleVergehen.this, E_FullScreenActivity.class);
                    intent.putExtra("schueler_name",s.getVorname());
                    intent.putExtra("strafpunkte", s.getGeburtstag());

                    startActivity(intent);


                }else{

                    s.setGeburtstag("0");

                    dS_Schueler.updateSchueler(s.getId(),s.getVorname(),s.getSurname(),s.getItemType(),s.getRufname(),s.getGeschlecht(),s.getStatus(),s.getGeburtstag(),s.getGeburtsort());
                    Intent intent = new Intent(S4_WaehleVergehen.this, E_FullScreenActivity3.class);
                    intent.putExtra("schueler_name",s.getVorname());


                    startActivity(intent);
                }


















            }
        });

    }


    private void showAllListEntries() {

        List<Vergehen> vergehenList = dS_Vergehen.getAllVergehens();
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


    @Override
    protected void onResume() {
        super.onResume();


        dS_Vergehen.open();
        dS_Schueler.open();
        showAllListEntries();

    }

    @Override
    protected void onPause() {
        super.onPause();

        dS_Vergehen.close();
        dS_Schueler.close();

    }


}
