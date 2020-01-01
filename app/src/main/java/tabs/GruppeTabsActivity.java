package tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
        fa = this;

        viewPagerAdapter = new GruppePagerAdapter(getSupportFragmentManager(), 6, this);
        viewPager = findViewById(R.id.viewpager_gruppe);
        viewPager.setAdapter(viewPagerAdapter);


        ds_g2 = new DataSource_Gruppe(this);

        ds_g2.open();




        selectedGruppe = getSelectedGruppe();


        tabLayout = findViewById(R.id.tabs_gruppe);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.getTabAt(i-1).setIcon(tabIcon);


                tabLayout.getTabAt(0).setText("Allgemoin");

    }

    private Gruppe getSelectedGruppe() {

        Intent intent = getIntent();
        String stringId = intent.getStringExtra("stringId");

        if(stringId!=null && !stringId.equals("")){

        return ds_g2.getGruppeByStringId(stringId);

        } else {

            intent.putExtra("stringId", "main");


            Gruppe g = ds_g2.getGruppeByStringId("main");

            if(g!=null){

                return g;
            }else{

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
            n.setStringId("main");
            n.setName("main");
            n.setExternName("main");

            n.setVaterStringId("");



            return  ds_g2.createGruppe(n.getStringId(), n.getName(), n.getExternName(), n.getVaterStringId());








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