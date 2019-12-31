package tabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import akguen.liquidschool.coredata.db.DataSource_Lerngruppe;
import akguen.liquidschool.coredata.db.DataSource_Schueler;
import akguen.liquidschool.coredata.db.DataSource_Schueler_Lerngruppe;
import akguen.liquidschool.coredata.model.Lerngruppe;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.activities.debug_activities.Debug_Main;

public class Gruppe2TabsActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Gruppe2PagerAdapter viewPagerAdapter;
    public static AppCompatActivity fa;


    private TextView tw;


    ListView listView;

    int idd;

    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waehle_gruppe2_activity_dynamic_tabs);

        mTopToolbar = (Toolbar) findViewById(R.id.dynamic_toolbar_gruppe2);


        fa = this;


        viewPagerAdapter = new Gruppe2PagerAdapter(getSupportFragmentManager(), 1, this);


        int sessionId = 0;
        viewPager = findViewById(R.id.viewpager_gruppe2);



        viewPager.setAdapter(viewPagerAdapter);






        tabLayout = findViewById(R.id.tabs_gruppe2);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.getTabAt(i-1).setIcon(tabIcon);








    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.waehle_schueler_dynamic_menu_main, menu);

//        menu.add("Add Contacts");
//        menu.getItem(0).setIcon(R.drawable.blume);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

     /*   //noinspection SimplifiableIfStatement
        if (id == R.id.dynamic_add) {
            //Toast.makeText(WaehleSchuelerDynamicTabsActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }*/

        if (id == R.id.dynamic_debug) {
            Intent speichernS1 = new Intent(Gruppe2TabsActivity.this, Debug_Main.class);
            startActivity(speichernS1);
            finish();
            return true;
        }

        if (id == R.id.dynamic_loeschen_aendern) {


            return true;
        }

        if (id == R.id.dynamic_statistik) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();


        dS_L_S.open();
        dS_L.open();
        dS_Schueler.open();
        //showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        dS_L_S.close();
        dS_L.close();
        dS_Schueler.close();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);

    }
}