package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import akguen.liquidschool.paulirotlite.R;

public class E_FullScreenActivity2 extends Activity {
    private TextView name;
    private TextView points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen2);

        name = (TextView) findViewById(R.id.textView8);


        name.setText(getIntent().getExtras().getString("lerngruppe_name"));









    }
}
