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

import java.util.List;

import akguen.liquidschool.coredata.db.DataSource_Gruppe2;
import akguen.liquidschool.coredata.db.DataSource_Radio;
import akguen.liquidschool.coredata.db.DataSource_Separator;
import akguen.liquidschool.coredata.db.DataSource_Subjekt;
import akguen.liquidschool.coredata.db.DataSource_Subjekt_Gruppe;
import akguen.liquidschool.coredata.model.Gruppe2;
import akguen.liquidschool.coredata.model.Radio;
import akguen.liquidschool.coredata.model.Separator;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.activities.debug_activities.Debug_Main;

public class Gruppe2TabsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Gruppe2PagerAdapter viewPagerAdapter;
    public static AppCompatActivity fa;

    private DataSource_Gruppe2 ds_g2;




    private Gruppe2 selectedGruppe2;


    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waehle_gruppe2_activity_dynamic_tabs);

        mTopToolbar = (Toolbar) findViewById(R.id.dynamic_toolbar_gruppe2);
        fa = this;

        viewPagerAdapter = new Gruppe2PagerAdapter(getSupportFragmentManager(), 6, this);
        viewPager = findViewById(R.id.viewpager_gruppe2);
        viewPager.setAdapter(viewPagerAdapter);


        ds_g2 = new DataSource_Gruppe2(this);

        ds_g2.open();




        selectedGruppe2 = getSelectedGruppe2();


        tabLayout = findViewById(R.id.tabs_gruppe2);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.getTabAt(i-1).setIcon(tabIcon);


                tabLayout.getTabAt(0).setText("Allgemoin");

    }

    private Gruppe2 getSelectedGruppe2() {

        Intent intent = getIntent();
        String stringId = intent.getStringExtra("stringId");

        if(stringId!=null && !stringId.equals("")){

        return ds_g2.getGruppe2ByStringId(stringId);

        } else {

            Gruppe2 g = ds_g2.getGruppe2ByStringId("main");

            if(g!=null){

                return g;
            }else{

                return buildMainGruppe2();
            }

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.gruppe2_menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.dynamic_debug) {
            Intent speichernS1 = new Intent(Gruppe2TabsActivity.this, Debug_Main.class);
            startActivity(speichernS1);
            finish();
            return true;
        }

        if (id == R.id.dynamic_loeschen) {





            Intent speichernS1 = new Intent(Gruppe2TabsActivity.this, Gruppe2TabsActivity.class);
            startActivity(speichernS1);
            finish();




            return true;
        }

        if (id == R.id.dynamic_statistik) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }





    public Gruppe2 buildMainGruppe2() {


        Gruppe2 n = new Gruppe2();





            // erste Gruppe
            n.setStringId("main");
            n.setName("main");
            n.setExternName("main");

            n.setVaterStringId("");



            return  ds_g2.createGruppe2(n.getStringId(), n.getName(), n.getExternName(), n.getVaterStringId());








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