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

public class E_StammgruppenwahlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stammgruppenwahl);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.hamburger);
        //Datenbanksachen db = new Datenbanksachen("a");

        String [] stammgruppenArray = {
                "Lerngruppe A",
                "Lerngruppe B",
                "Lerngruppe C",
                "Lerngruppe D",
                "Lerngruppe E",
                "Lerngruppe F",
                "Lerngruppe G",
                "Lerngruppe H",
                "Lerngruppe H",
                "Lerngruppe H",
                "Lerngruppe H",


        };

        List<String>   stammgruppenListe = new ArrayList<>(Arrays.asList(stammgruppenArray));

        ArrayAdapter<String> stammgruppenListeAdapter =
                new ArrayAdapter<>(
                        this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_item_stammgruppen, // ID der XML-Layout Datei
                        R.id.tv_list_item_stammgruppenliste, // ID des TextViews
                        stammgruppenListe); // Beispieldaten in einer ArrayList



        ListView stammgruppenlisteListView = findViewById(R.id.listview_stammgruppen);
        stammgruppenlisteListView.setAdapter(stammgruppenListeAdapter);

        stammgruppenlisteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Log.i("click","" + id);
                String vergehenTitle = (String) adapterView.getItemAtPosition(position);
                Long clickId = adapterView.getSelectedItemId();

                    Log.i("click","2 nicht geklickt " + id);
                    // Intent erzeugen und Starten der KlasseFragment mit explizitem Intent
                    Intent klasseActivity = new Intent(E_StammgruppenwahlActivity.this, E_UebersichtActivity.class);
                   // klasseActivity.putExtra(Intent.EXTRA_TEXT, vergehenTitle);
                    startActivity(klasseActivity);


            }
        });

    }


}



