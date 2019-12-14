package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.activities.in_use.S4_WaehleVergehen;

public class E_FullScreenActivity3 extends Activity {
    private TextView name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen3);

        name = (TextView) findViewById(R.id.textView8);


        name.setText(getIntent().getExtras().getString("schueler_name"));



        S1_WaehleSchueler.fa.finish();
        S4_WaehleVergehen.fa.finish();





    }
}
