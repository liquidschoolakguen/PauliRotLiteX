package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import akguen.liquidschool.mylib2.Adressse;
import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.activities.in_use.S4_WaehleVergehen;
import tabs.WaehleSchuelerDynamicTabsActivity;

//import akguen.liquidschool.

public class E_FullScreenActivity extends Activity {
    private TextView name;
    private TextView points;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Adressse z;
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen);

        name = (TextView) findViewById(R.id.textView8);
        points = (TextView) findViewById(R.id.textView9);

        name.setText(getIntent().getExtras().getString("schueler_name"));
        points.setText(getIntent().getExtras().getString("strafpunkte"));








    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        WaehleSchuelerDynamicTabsActivity.fa.finish();
        S4_WaehleVergehen.fa.finish();
        Intent speichernS3= new Intent(E_FullScreenActivity.this, WaehleSchuelerDynamicTabsActivity.class);
        startActivity(speichernS3);
    }



}
