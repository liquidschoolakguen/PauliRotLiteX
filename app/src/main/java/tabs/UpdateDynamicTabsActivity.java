package tabs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import akguen.liquidschool.paulirotlite.R;
import db.DataSource_Lerngruppe;
import db.DataSource_Schueler;
import db.DataSource_Schueler_Lerngruppe;

public class UpdateDynamicTabsActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private UpdateViewPagerAdapter viewPagerAdapter;

    private int noOfTabs = 10;




    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    private DataSource_Schueler_Lerngruppe dS_L_S;
    private DataSource_Lerngruppe dS_L;
    private DataSource_Schueler dS_Schueler;

    private TextView tw;

    public static AppCompatActivity fa;
    ListView listView;
    private static WaehleSchuelerCustomAdapter adapter;
    int idd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity_dynamic_tabs);


        dS_L_S = new DataSource_Schueler_Lerngruppe(this);
        dS_L = new DataSource_Lerngruppe(this);
        dS_Schueler = new DataSource_Schueler(this);

        dS_L.open();

        int i = dS_L.getAllLerngruppes().size();
        viewPagerAdapter = new UpdateViewPagerAdapter(getSupportFragmentManager(), i, this);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

       /* tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(UpdateDynamicTabsActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            }

        });*/


    }







    @Override
    protected void onResume() {
        super.onResume();


        dS_L_S.open();

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

}