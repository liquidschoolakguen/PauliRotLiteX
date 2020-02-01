package tabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import akguen.liquidschool.coredata.db.DataSource_Gruppe;
import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.activities.debug_activities.Debug_Main;

public class GruppeTabsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private GruppePagerAdapter viewPagerAdapter;
    public static AppCompatActivity fa;

    private DataSource_Gruppe ds_g2;


    private Gruppe selectedGruppe;


    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gruppe_activity);

        mTopToolbar = (Toolbar) findViewById(R.id.dynamic_toolbar_gruppe);




        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.titel_rechtsbuendig);

        TextView customTitle = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.mumu);

        fa = this;




        ds_g2 = new DataSource_Gruppe(this);
        ds_g2.open();


        //pref löschen wenn datenbank leer ist
        if(ds_g2.getAllGruppes().size()==0){

               SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();
        }





        selectedGruppe = getSelectedGruppe();


        viewPagerAdapter = new GruppePagerAdapter(getSupportFragmentManager(), 5, this);
        viewPager = findViewById(R.id.viewpager_gruppe);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.tabs_gruppe);
        tabLayout.setupWithViewPager(viewPager);

        //this.setTitle(selectedGruppe.getExternName());


        String showableName="";
        if(selectedGruppe.getExternName().length()<=20){
            showableName =   selectedGruppe.getExternName();

        }else{
            showableName = "..."+selectedGruppe.getExternName().substring(selectedGruppe.getExternName().length()-20,selectedGruppe.getExternName().length());

        }


        customTitle.setText(showableName);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String separatorErzeugt = prefs.getString("separatorErzeugt", null);
        if(separatorErzeugt!=null){

            viewPager.setCurrentItem(4);

            prefs.edit().remove("separatorErzeugt").commit();

        }

    }

    private Gruppe getSelectedGruppe() {

      /*  SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("selectedGruppe", null);
        editor.commit();
        */


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String stringIdOfselectedGruppe = prefs.getString("selectedGruppe", null);
        Log.d("GruppeTest6", "222 "+stringIdOfselectedGruppe);

        if (stringIdOfselectedGruppe != null) {
            ds_g2.open();
            Gruppe pos = ds_g2.getGruppeByStringId(stringIdOfselectedGruppe);
            if(pos==null){
                Log.d("GruppeTest6", "null "+stringIdOfselectedGruppe);
                pos = ds_g2.createGruppe(stringIdOfselectedGruppe,null,null,null);
                if(pos==null){
                    Log.d("GruppeTest", "fehler ");

                }else{

                   // Log.d("GruppeTest", "ok ");
                }


            }


            return ds_g2.getGruppeByStringId(stringIdOfselectedGruppe);

        } else {


            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("selectedGruppe", "main#1");
            editor.commit();


            Gruppe g = ds_g2.getGruppeByStringId("main#1");

            if (g != null) {

                return g;
            } else {

                return buildMainGruppe();
            }

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.gruppe_menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.dynamic_debug) {
            Intent speichernS1 = new Intent(GruppeTabsActivity.this, Debug_Main.class);
            startActivity(speichernS1);
            finish();
            return true;
        }

        if (id == R.id.dynamic_loeschen) {


            Intent speichernS1 = new Intent(GruppeTabsActivity.this, GruppeTabsActivity.class);
            startActivity(speichernS1);
            finish();


            return true;
        }

        if (id == R.id.dynamic_statistik) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public Gruppe buildMainGruppe() {


        Gruppe n = new Gruppe();


        // erste Gruppe
        n.setStringId("main#1");
        n.setName("main#1");
        n.setExternName("Wurzelgruppe");

        n.setVaterStringId("");


        return ds_g2.createGruppe(n.getStringId(), n.getName(), n.getExternName(), n.getVaterStringId());


    }


    @Override
    protected void onResume() {
        super.onResume();
        ds_g2.open();


    }

    @Override
    protected void onPause() {
        super.onPause();
        ds_g2.close();


    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);

    }
}