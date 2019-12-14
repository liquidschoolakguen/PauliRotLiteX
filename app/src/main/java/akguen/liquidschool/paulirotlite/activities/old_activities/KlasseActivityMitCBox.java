package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import akguen.liquidschool.paulirotlite.R;

public class KlasseActivityMitCBox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klasse_mit_cbox);
        Log.i("click","KlasseActivityMitCBox " );

        Intent intent = new Intent();
        String vergehenTitle = intent.getStringExtra(Intent.EXTRA_TEXT);

    }
}
