package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import akguen.liquidschool.mylib2.model.Schueler;
import tabs.WaehleSchuelerCustomAdapter;

public class TestMainActivity2 extends AppCompatActivity {
/*
    ArrayList<Schueler> dataModels;
    ListView listView;
    private static WaehleSchuelerCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main2);


        listView=(ListView)findViewById(R.id.listi);

        dataModels= new ArrayList<>();

        dataModels.add(new Schueler(100, "Harun", null,null,null,null, "3",null));
        dataModels.add(new Schueler(101, "Kemal", null, null,null,null, "2",null));
        dataModels.add(new Schueler(102, "Tim", null, null,null,null, "1",null));
        dataModels.add(new Schueler(103, "Struppi", null, null,null,null, "0",null));


        adapter= new WaehleSchuelerCustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Schueler dataModel= dataModels.get(position);

                Snackbar.make(view, dataModel.getVorname()+"\n"+dataModel.getGeburtstag(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contextual_action_bar_sq, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}