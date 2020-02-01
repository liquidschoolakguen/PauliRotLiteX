package tabs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import akguen.liquidschool.coredata.db.Controller;
import akguen.liquidschool.coredata.db.DataSource_Gruppe;
import akguen.liquidschool.coredata.db.DataSource_Radio;
import akguen.liquidschool.coredata.db.DataSource_Separator;
import akguen.liquidschool.coredata.db.DataSource_Subjekt;
import akguen.liquidschool.coredata.db.DataSource_Subjekt_Gruppe;
import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.paulirotlite.R;
import zz_test.MyDividerItemDecorator;
import zz_test.SpeedyLinearLayoutManager;

public class GruppeDynamicSoehneFragment extends Fragment implements GruppeViewAdapter.ItemClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int sectionNumber;


    List<Gruppe> ff;
    GruppeViewAdapter adapter;
    private DataSource_Gruppe ds_g2;
    private DataSource_Subjekt ds_su;
    private DataSource_Subjekt_Gruppe ds_g2_su;
    private DataSource_Separator ds_sep;
    private DataSource_Radio ds_ra;
    private Controller con;




    public GruppeDynamicSoehneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sectionNumber = getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1;


    }

    @Override
    public void onPause() {
        super.onPause();

        ds_g2.close();
        ds_su.close();
        ds_g2_su.close();
        ds_sep.close();
        ds_ra.close();
        con.closeAll();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gruppe_fragment_dynamic, container, false);


        ds_g2 = new DataSource_Gruppe(getActivity());
        ds_su = new DataSource_Subjekt(getActivity());
        ds_g2_su = new DataSource_Subjekt_Gruppe(getActivity());
        ds_sep = new DataSource_Separator(getActivity());
        ds_ra = new DataSource_Radio(getActivity());
        con = new Controller(getActivity());


        ds_g2.open();
        ds_su.open();
        ds_g2_su.open();
        ds_sep.open();
        ds_ra.open();
        con.openAll();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String stringIdOfselectedGruppe = prefs.getString("selectedGruppe", null);






        Gruppe gg = ds_g2.getGruppeByStringId(stringIdOfselectedGruppe);






  /*      if ((sectionNumber)==2) {



            //Toast.makeText(getContext(), "dynamic: " + sectionNumber, Toast.LENGTH_LONG).show();
            // Toast.makeText(getContext(), "sectionNumber Fehler: " + sectionNumber, Toast.LENGTH_LONG).show();
            ff = con.holeAlleVäter(gg);

            Log.d("GruppeTest", "sectionNumber "+sectionNumber);
            RecyclerView recyclerView = view.findViewById(R.id.dynamic_list_gruppe);
            RecyclerView.LayoutManager kkk = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(kkk);
            recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(getActivity(), SpeedyLinearLayoutManager.VERTICAL, false));
            adapter = new GruppeViewAdapter(getActivity(), ff, recyclerView);
            adapter.addItemClickListener(this);
            RecyclerView.ItemDecoration dividerItemDecoration = new MyDividerItemDecorator(ContextCompat.getDrawable(getActivity(), R.drawable.divider));
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setAdapter(adapter);

        }*/

       /* if ((sectionNumber)==3) {


//Brüder
            ff = ds_g2.getAllGruppesByVaterStringId(gg.getVaterStringId());

            Log.d("GruppeTest", "sectionNumber ausbBrüder "+sectionNumber);
            RecyclerView recyclerView = view.findViewById(R.id.dynamic_list_gruppe);
            RecyclerView.LayoutManager kkk = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(kkk);
            recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(getActivity(), SpeedyLinearLayoutManager.VERTICAL, false));
            adapter = new GruppeViewAdapter(getActivity(), ff, recyclerView);
            adapter.addItemClickListener(this);
            RecyclerView.ItemDecoration dividerItemDecoration = new MyDividerItemDecorator(ContextCompat.getDrawable(getActivity(), R.drawable.divider));
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setAdapter(adapter);



        }*/

        if ((sectionNumber)==3) {
            //Söhne

            ff = ds_g2.getAllGruppesByVaterStringId(gg.getStringId());

            Log.d("GruppeTest", "sectionNumber aus Söhne"+sectionNumber);
            RecyclerView recyclerView = view.findViewById(R.id.dynamic_list_gruppe);
            RecyclerView.LayoutManager kkk = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(kkk);
            recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(getActivity(), SpeedyLinearLayoutManager.VERTICAL, false));
            adapter = new GruppeViewAdapter(getActivity(), ff, recyclerView);
            adapter.addItemClickListener(this);
            RecyclerView.ItemDecoration dividerItemDecoration = new MyDividerItemDecorator(ContextCompat.getDrawable(getActivity(), R.drawable.divider));
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setAdapter(adapter);



        }







        return view;
    }

    public static GruppeDynamicSoehneFragment newInstance(int sectionNumber) {
        GruppeDynamicSoehneFragment fragment = new GruppeDynamicSoehneFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);






        return fragment;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "Click on item: " + position, Toast.LENGTH_SHORT).show();
    }
}