package tabs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.db.Controller;
import akguen.liquidschool.coredata.db.DataSource_Gruppe;
import akguen.liquidschool.coredata.db.DataSource_Subjekt_Gruppe;
import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.coredata.model.Radio;
import akguen.liquidschool.paulirotlite.R;
import zz_test.MyDividerItemDecorator;
import zz_test.SpeedyLinearLayoutManager;

public class GruppeSeparatorErstellenFragment extends Fragment implements GruppeViewAdapterForSeparatorErstellen.ItemClickListener{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int sectionNumber;


    private DataSource_Gruppe ds_g2;
    private DataSource_Subjekt_Gruppe ds_g2_su;
    private Controller con;
    GruppeViewAdapterForSeparatorErstellen adapter;
    private Gruppe selectedGruppe;

    public GruppeSeparatorErstellenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gruppe_fragment_separator_erstellen, container, false);

        final EditText sepName = view.findViewById(R.id.simpleEditText);



        ds_g2 = new DataSource_Gruppe(getActivity());
        ds_g2_su = new DataSource_Subjekt_Gruppe(getActivity());
        con = new Controller(getActivity());

        ds_g2.open();
        ds_g2_su.open();
        con.openAll();



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String stringIdOfselectedGruppe = prefs.getString("selectedGruppe", null);



        Gruppe gg = ds_g2.getGruppeByStringId(stringIdOfselectedGruppe);

        List<Radio> list = new ArrayList<Radio>();
        list.add(new Radio());
        list.add(new Radio());
        list.add(new Radio());


        RecyclerView recyclerView = view.findViewById(R.id.dynamic_list_radios_for_separator_erstellen);
        RecyclerView.LayoutManager kkk = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(kkk);
        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(getActivity(), SpeedyLinearLayoutManager.VERTICAL, false));
        adapter = new GruppeViewAdapterForSeparatorErstellen(getActivity(), list, recyclerView);
        adapter.addItemClickListener(this);
        RecyclerView.ItemDecoration dividerItemDecoration = new MyDividerItemDecorator(ContextCompat.getDrawable(getActivity(), R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);







        final ImageView button = (ImageView) view.findViewById(R.id.multi_save_super);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Radio> nulist = new ArrayList<Radio>();

                for(Radio r : adapter.getDataSet()){

                    if(r.getName()!=null && !r.getName().trim().equals("")){

                        nulist.add(r);
                        Log.d("GruppeTest", "da stimmt was nicht :::"+r.getName());

                    }

                }

            if(nulist==null||nulist.isEmpty()){

                Log.d("GruppeTest", "da stimmt was nicht ");

            }
                con.buildSeparatorWithRadios(nulist,sepName.getText().toString(),gg);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("selectedGruppe", gg.getStringId());
                editor.putString("separatorErzeugt", "ja");
                editor.commit();



                Intent intent = new Intent(v.getContext(), GruppeTabsActivity.class);
                v.getContext().startActivity(intent);
                Activity activity = (Activity) v.getContext();
                activity.finish();


            }
        });






        if ((sectionNumber)==1) {

        }
        if ((sectionNumber)==2) {
            ;
        }
        if ((sectionNumber)==3) {

        }
        if ((sectionNumber)==4) {

        }
        if ((sectionNumber)==5) {

        }
        if ((sectionNumber)==6) {
            Toast.makeText(getContext(), "Separator erzeugen: " + sectionNumber, Toast.LENGTH_LONG).show();
        }









        return view;
    }

    public static GruppeSeparatorErstellenFragment newInstance(int sectionNumber) {
        GruppeSeparatorErstellenFragment fragment = new GruppeSeparatorErstellenFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onPause() {
        super.onPause();

        ds_g2.close();
        ds_g2_su.close();
        con.closeAll();

    }


    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "Click on item: " + position, Toast.LENGTH_SHORT).show();
    }
}