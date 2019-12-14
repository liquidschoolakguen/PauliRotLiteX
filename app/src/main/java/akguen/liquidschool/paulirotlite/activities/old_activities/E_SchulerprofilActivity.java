package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import akguen.liquidschool.paulirotlite.rest.ExpandableListData;
import akguen.liquidschool.paulirotlite.R;

public class E_SchulerprofilActivity extends AppCompatActivity {



    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    TextView a;
    TextView b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht);

        a = (TextView) findViewById(R.id.profil_schueler_a);

        b = (TextView) findViewById(R.id.profil_schueler_b);

        String schuelerVorname = getIntent().getExtras().getString("vorname");
        String schuelerNachname = getIntent().getExtras().getString("nachname");

        a.setText(","+schuelerVorname+","+schuelerNachname+",");

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);


        expandableListDetail = ExpandableListData.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        //expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();

           /*     Intent schuelerMerkblattInfo = new Intent(E_SchulerprofilActivity.this, SchuelerMerkblattInfo.class);
                schuelerMerkblattInfo.putExtra("name",expandableListTitle.get(groupPosition) );
                schuelerMerkblattInfo.putExtra("merkblatt",expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) );

                startActivity(schuelerMerkblattInfo);*/

                return false;
            }
        });
    }

}





