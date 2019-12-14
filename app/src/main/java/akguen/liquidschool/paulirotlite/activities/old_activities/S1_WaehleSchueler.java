package akguen.liquidschool.paulirotlite.activities.old_activities;

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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.mylib2.db.DataSource_Schueler;
import akguen.liquidschool.mylib2.db.DataSource_Schueler_Lerngruppe;
import akguen.liquidschool.mylib2.model.Lerngruppe;
import akguen.liquidschool.mylib2.model.Schueler;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.activities.debug_activities.Debug_Main;
import akguen.liquidschool.paulirotlite.activities.in_use.S4_WaehleVergehen;
import tabs.WaehleSchuelerCustomAdapter;

public class S1_WaehleSchueler extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DataSource_Schueler_Lerngruppe dS_L_S;
    private DataSource_Lerngruppe dS_L;
    private DataSource_Schueler dS_Schueler;

    private TextView tw;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static AppCompatActivity fa;
    ListView listView;
    private static WaehleSchuelerCustomAdapter adapter;
    int idd;
    Lerngruppe selectedLG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fa = this;


        setContentView(R.layout.my_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.my_list_schueler);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Schueler schueler = (Schueler) parent.getItemAtPosition(position);
                //Schueler schu= ff.get(position);

                // Snackbar.make(view, schu.getVorname()+"\n"+schu.getGeburtstag(), Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();


                Intent intent = new Intent(S1_WaehleSchueler.this, S4_WaehleVergehen.class);
                intent.putExtra("schueler_id", Integer.toString(schueler.getId()));
                startActivity(intent);





            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        dS_L_S = new DataSource_Schueler_Lerngruppe(this);
        dS_L = new DataSource_Lerngruppe(this);
        dS_Schueler = new DataSource_Schueler(this);

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
        //
        // sharedpreferences.edit().remove("selectedLerngruppeId").commit();

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
        finish();
        System.exit(0);

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
            Intent speichernS1 = new Intent(S1_WaehleSchueler.this, S5_LerngruppeWechseln.class);
            startActivity(speichernS1);
            finish();
        } else if (id == R.id.nav_lg_neu) {
            Intent speichernS2 = new Intent(S1_WaehleSchueler.this, S3_ErstelleLerngruppe.class);
            startActivity(speichernS2);
            finish();
        } else if (id == R.id.nav_lg_bearbeiten) {
            Intent speichernS2 = new Intent(S1_WaehleSchueler.this, S6_LerngruppeBearbeitenLoeschen.class);
            startActivity(speichernS2);
            finish();
        } else if (id == R.id.nav_max_st) {
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String restoredText = sharedpreferences.getString("selectedLerngruppeId", null);

            idd = Integer.parseInt(restoredText);
            List<Schueler> listZ = dS_L_S.getSchuelersFromLerngruppeById(idd);

            for(Schueler s:listZ){

                String old = s.getGeburtstag();
                if(old.equals("")){
                    old="0";
                }
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


            Intent speichernS2 = new Intent(S1_WaehleSchueler.this, E_FullScreenActivity2.class);
            speichernS2.putExtra("lerngruppe_name", dS_L.getLerngruppeById(idd).getName());

            startActivity(speichernS2);
            finish();
        }else if (id == R.id.nav_max_st2) {
            Intent speichernS2 = new Intent(S1_WaehleSchueler.this, Debug_Main.class);
            startActivity(speichernS2);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initializeSchuelersListView() {















       /* List<Schueler> emptyListForInitialization = new ArrayList<>();

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
                Log.i("click","--- " + schueler.getId());
                Intent intent = new Intent(S1_WaehleSchueler.this, S4_WaehleVergehen.class);

                intent.putExtra("schueler_id", Integer.toString(schueler.getId()));




                startActivity(intent);
            }
        });*/

    }

    private void showAllListEntries() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = sharedpreferences.getString("selectedLerngruppeId", null);

        idd = Integer.parseInt(restoredText);

        tw = (TextView)findViewById(R.id.s1_textView_lg_name);

        tw.setText(dS_L.getLerngruppeById(idd).getName());


        List<Schueler> schuelerList = dS_L_S.getSchuelersFromLerngruppeById(idd);
        ArrayList<Schueler> ff= new ArrayList<Schueler>(schuelerList);
        adapter= new WaehleSchuelerCustomAdapter(ff,getApplicationContext());

        listView.setAdapter(adapter);



    }


    @Override
    protected void onResume() {
        super.onResume();


        dS_L_S.open();
        dS_L.open();
        dS_Schueler.open();
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        dS_L_S.close();
        dS_L.close();
        dS_Schueler.close();
    }


}
