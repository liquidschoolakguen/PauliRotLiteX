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

import akguen.liquidschool.paulirotlite.activities.debug_activities.Debug_Main;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.mylib2.db.DataSource_Schueler;
import akguen.liquidschool.mylib2.db.DataSource_Schueler_Lerngruppe;
import akguen.liquidschool.mylib2.model.Lerngruppe;

public class WaehleSchuelerDynamicTabsActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private WaehleSchuelerViewPagerAdapter viewPagerAdapter;
    public static AppCompatActivity fa;
    private int noOfTabs = 10;
    private int tabIcon = R.drawable.baseline;
    private int tabIconActive = R.drawable.baseline_active;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    private DataSource_Schueler_Lerngruppe dS_L_S;
    private DataSource_Lerngruppe dS_L;
    private DataSource_Schueler dS_Schueler;

    private TextView tw;


    ListView listView;
    private static WaehleSchuelerCustomAdapter adapter;
    int idd;

    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waehle_schueler_activity_dynamic_tabs);

        mTopToolbar = (Toolbar) findViewById(R.id.dynamic_toolbar);
        dS_L_S = new DataSource_Schueler_Lerngruppe(this);
        dS_L = new DataSource_Lerngruppe(this);
        dS_Schueler = new DataSource_Schueler(this);
        fa = this;
        dS_L.open();

        int i = dS_L.getAllLerngruppes().size() + 1;
        viewPagerAdapter = new WaehleSchuelerViewPagerAdapter(getSupportFragmentManager(), i, this);


        int sessionId = 0;
        viewPager = findViewById(R.id.viewpager);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //
        // sharedpreferences.edit().remove("selectedLerngruppeId").commit();

        String sessionString = sharedpreferences.getString("selectedLerngruppeId", null);


        System.out.println("sessionString " + sessionString + ".");
        if (sessionString != null && !sessionString.equals("")) {


            sessionId = Integer.parseInt(sessionString);
        }


        viewPager.setAdapter(viewPagerAdapter);


        viewPager.setCurrentItem(sessionId);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {


                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("selectedLerngruppeId", Integer.toString(position));
                editor.commit();

                System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkk" + i);


                if (i - 1 == position) {
                    tabLayout.getTabAt(i - 1).setIcon(tabIconActive);
                } else {

                    tabLayout.getTabAt(i - 1).setIcon(tabIcon);
                }

                //System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" + position);

                // Check if this is the page you want.
            }
        });

/*

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = sharedpreferences.getString("selectedLerngruppeId", null);
        if (restoredText != null) {
            if(restoredText.equals("-1")) {
                viewPager.setCurrentItem(Integer.parseInt(restoredText));
            }else{
                viewPager.setCurrentItem(1);
            }

            SharedPreferences preferences = getSharedPreferences(MyPREFERENCES, 0);
            preferences.edit().remove("selectedLerngruppeId").commit();

        }
*/

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.getTabAt(i-1).setIcon(tabIcon);


        if (i != 1) {
            if (i - 1 == sessionId) {
                tabLayout.getTabAt(i - 1).setIcon(tabIconActive);
            } else {

                tabLayout.getTabAt(i - 1).setIcon(tabIcon);
            }
        }

        LinearLayout tabStrip = (LinearLayout) tabLayout.getChildAt(0);

        for (int ff = 0; ff < tabStrip.getChildCount(); ff++) {

            // Set LongClick listener to each Tab
            List h = dS_L.getAllLerngruppes();
            int finalFf = ff;
            tabStrip.getChildAt(ff).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {


                    if (finalFf <= h.size() - 1) {

                        Toast.makeText(getApplicationContext(), "Tab clicked " + ((Lerngruppe) h.get(finalFf)).getName(), Toast.LENGTH_SHORT).show();
                    }


                    return true;
                }
            });
        }


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
            Intent speichernS1 = new Intent(WaehleSchuelerDynamicTabsActivity.this, Debug_Main.class);
            startActivity(speichernS1);
            finish();
            return true;
        }

        if (id == R.id.dynamic_loeschen_aendern) {


            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("selectedLerngruppeId", Integer.toString(0));
            editor.commit();
            Intent speichernS1 = new Intent(WaehleSchuelerDynamicTabsActivity.this, LerngruppeLöschenÄndern.class);
            startActivity(speichernS1);
            finish();
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