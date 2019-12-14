package akguen.liquidschool.paulirotlite.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.activities.old_activities.PauseActivity;

public class KlasseFragment extends Fragment {

    public KlasseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] schuelerlisteArray = {

                "alev",
                "kaan",
                "stefan",
                "hans",
                "ebru",
                "fatma",
                "esin",
                "fazilet",
                "petek",
                "aysun",
                "nadia",
                "farouk",
                "can",
                "petek5",
                "siegfried",
                "ayse",
                "gerd",

        };

        List<String> schuelerListe = new ArrayList<>(Arrays.asList(schuelerlisteArray));
        ArrayAdapter<String> aktienlisteAdapter =
                new ArrayAdapter<>(
                        getActivity(), // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_item_schueler_in_klasse, // ID der XML-Layout Datei
                        R.id.tv_item_schuelerliste, // ID des TextViews
                        schuelerListe); // Beispieldaten in einer ArrayList


        View rootView = inflater.inflate(R.layout.fragment_klasse, container, false);

        final ListView schuelerlisteListView = (ListView) rootView.findViewById(R.id.listview_schuelerinderklasse);
        schuelerlisteListView.setAdapter(aktienlisteAdapter);


        schuelerlisteListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object vorfallObj = schuelerlisteListView.getItemAtPosition(position);
                String vorfall=(String)vorfallObj;//As you are using Default String Adapter
                Toast.makeText(getActivity().getApplicationContext(),vorfall,Toast.LENGTH_SHORT).show();


                Intent intent = new Intent();
                String vergehensTitle = intent.getStringExtra(Intent.EXTRA_TEXT);


                // RUHE MODUS von der App
               Intent pause = new Intent(getActivity(), PauseActivity.class);

                //vorfaelleListView.putExtra("name", nameExtra);
                //vorfaelleListView.putExtra("vorfall", vorfall);
                //vorfaelleListView.putExtra("merkblatt", schuelerMerkblatt);
                startActivity(pause);


            }
        });

        return rootView;
    }

}
