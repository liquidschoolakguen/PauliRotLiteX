package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import akguen.liquidschool.paulirotlite.R;

public class PauseActivity extends AppCompatActivity implements View.OnTouchListener {

    ImageView ivSwipe;
    Button bntClear, bntGet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        bntClear = findViewById(R.id.bntSharedClear);
        bntGet = findViewById(R.id.bntSharedGet);
        bntClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             //  new PrefManager(PauseActivity.this).clear();
            }
        });


        bntGet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               // new PrefManager(PauseActivity.this).saveLoginDetails("esin", "1234" );
            }
        });




    }


    // counter funktioniert, aber auskommentiert wegen der Test SharedPref clear !!!

      /*  CountDownTimer yourCountDownTimer = new CountDownTimer(6000, 1000) {                     //geriye sayma


            @Override
            public void onTick(long millisUntilFinished) {

                Log.e("seconds remaining : ", "seconds remaining : " + millisUntilFinished / 1000);
                //in 10 sekunde wird app stopp 10000/1000 : 10 sekunde
                //soll bitte in 6 sekunde wird app stopp 6000/1000 : 6 sekunde
            }

            public void onFinish() {

                Log.i("countdown", "---");
                //this.finish();
                //System.exit(0);
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);

            }
        }.start();*/






    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
