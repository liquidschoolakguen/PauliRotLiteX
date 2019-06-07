package akguen.liquidschool.paulirotlite;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VergehenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vergehen);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.hamburger);
        //Datenbanksachen db = new Datenbanksachen("a");

        String [] vergehensArray = {
                "Verspätung",
                "Mit geholfen",
                "Diskustion mit .... ",
                "Hausaufgabe gemacht",
                "Gesprech mit ...",
        };

        List<String> vergehenListe = new ArrayList<>(Arrays.asList(vergehensArray));

        //new Datenbanksachen(vergehenListe).execute("someParams");

        ArrayAdapter<String> aktienlisteAdapter =
                new ArrayAdapter<>(
                        this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_item_vergehensliste, // ID der XML-Layout Datei
                        R.id.tv_list_item_vergehensliste, // ID des TextViews
                        vergehenListe); // Beispieldaten in einer ArrayList

        ListView vergehenlisteListView = findViewById(R.id.listview_vergehens);
        vergehenlisteListView.setAdapter(aktienlisteAdapter);

        vergehenlisteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Log.i("click","" + id);
                String vergehenTitle = (String) adapterView.getItemAtPosition(position);
                Long clickId = adapterView.getSelectedItemId();

                if(id==2){
                    Log.i("click","2 geklickt " + id);

                    // Intent erzeugen und Starten der KlasseFragment mit explizitem Intent
                    Intent klasseActivityMitCBox = new Intent(VergehenActivity.this, KlasseActivityMitCBox.class);
                    klasseActivityMitCBox.putExtra(Intent.EXTRA_TEXT, vergehenTitle);
                    //klasseActivityMitCBox.putExtra("position", clickId);
                    startActivity(klasseActivityMitCBox);

                } else{
                    Log.i("click","2 nicht geklickt " + id);
                    // Intent erzeugen und Starten der KlasseFragment mit explizitem Intent
                    Intent klasseActivity = new Intent(VergehenActivity.this, KlasseActivity.class);
                    klasseActivity.putExtra(Intent.EXTRA_TEXT, vergehenTitle);
                    startActivity(klasseActivity);

                }
            }
        });

    }


}
