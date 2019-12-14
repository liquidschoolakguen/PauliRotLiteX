package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import akguen.liquidschool.paulirotlite.R;

public class KlasseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klasse);
        Log.i("click","KlasseActivity" );
        Intent intent = new Intent();
        String vergehensTitle = intent.getStringExtra(Intent.EXTRA_TEXT);


        //new Datenbanksachen(vergehensTitle).execute("someParams");

    }
}
