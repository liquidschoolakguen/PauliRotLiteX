package akguen.liquidschool.paulirotlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import model.CustomAdapter;

import model.VorfallModel;
import utils.CustomAdapterVorfaelle;

public class VorfaelleActivity extends AppCompatActivity {

    ArrayList<VorfallModel> modelArrayList;
    private CustomAdapterVorfaelle customAdapterVorfaelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorfaelle);


        modelArrayList = getModel();

        customAdapterVorfaelle = new CustomAdapterVorfaelle(getBaseContext(), modelArrayList);


        ListView vergehenlisteListView = findViewById(R.id.lvVorfaelle);

        vergehenlisteListView.setAdapter(customAdapterVorfaelle);
    }


    private ArrayList<VorfallModel> getModel(){



        ArrayList<VorfallModel> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            VorfallModel model = new VorfallModel();
            model.setSchuelerName("Sila Fein");
            model.setStrafpunkt("88");
            model.setVergehenName("zu spät");
            model.setZeit("heute 8:30");
            list.add(model);

            VorfallModel model2 = new VorfallModel();
            model2.setSchuelerName("Ayse Can");
            model2.setStrafpunkt("8");
            model2.setVergehenName("zu spät");
            model2.setZeit("heute 8:30");
            list.add(model2);
            VorfallModel model3 = new VorfallModel();
            model3.setSchuelerName("Zeynep Aygül");
            model3.setStrafpunkt("8");
            model3.setVergehenName("zu spät");
            model3.setZeit("heute 8:30");
            list.add(model3);

        }
        return list;
    }
}
