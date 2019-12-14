package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import akguen.liquidschool.paulirotlite.adapters.old_adapters.E_CustomExpandableListAdapter;
import akguen.liquidschool.paulirotlite.rest.E_ExpandableListData;
import akguen.liquidschool.paulirotlite.R;

public class E_UebersichtActivity extends AppCompatActivity {



    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);


        expandableListDetail = E_ExpandableListData.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new E_CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
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

           /*     Intent schuelerMerkblattInfo = new Intent(E_UebersichtActivity.this, SchuelerMerkblattInfo.class);
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





