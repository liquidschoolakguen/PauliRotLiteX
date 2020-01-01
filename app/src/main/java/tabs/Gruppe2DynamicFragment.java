package tabs;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.db.DataSource_Gruppe2;
import akguen.liquidschool.coredata.db.DataSource_Lerngruppe;
import akguen.liquidschool.coredata.db.DataSource_Radio;
import akguen.liquidschool.coredata.db.DataSource_Schueler;
import akguen.liquidschool.coredata.db.DataSource_Schueler_Lerngruppe;
import akguen.liquidschool.coredata.db.DataSource_Separator;
import akguen.liquidschool.coredata.db.DataSource_Subjekt;
import akguen.liquidschool.coredata.db.DataSource_Subjekt_Gruppe;
import akguen.liquidschool.coredata.db.MyDbHelper;
import akguen.liquidschool.coredata.model.Lerngruppe;
import akguen.liquidschool.coredata.model.Schueler;
import akguen.liquidschool.paulirotlite.R;
import zz_test.MyDividerItemDecorator;
import zz_test.RecyclerViewAdapter;
import zz_test.SpeedyLinearLayoutManager;

public class Gruppe2DynamicFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int sectionNumber;



    RecyclerViewAdapter adapter;
    private DataSource_Gruppe2 ds_g2;
    private DataSource_Subjekt ds_su;
    private DataSource_Subjekt_Gruppe ds_g2_su;
    private DataSource_Separator ds_sep;
    private DataSource_Radio ds_ra;





    public Gruppe2DynamicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sectionNumber = getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1;


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gruppe2_fragment_dynamic, container, false);


        ds_g2 = new DataSource_Gruppe2(getActivity());
        ds_su = new DataSource_Subjekt(getActivity());
        ds_g2_su = new DataSource_Subjekt_Gruppe(getActivity());
        ds_sep = new DataSource_Separator(getActivity());
        ds_ra = new DataSource_Radio(getActivity());
        ds_g2.open();
        ds_su.open();
        ds_g2_su.open();
        ds_sep.open();
        ds_ra.open();


        if ((sectionNumber)==0) {


        }
        if ((sectionNumber)==1) {


        }

        if ((sectionNumber)==2) {


        }

        if ((sectionNumber)==3) {


        }




        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.dynamic_list_schueler);
        RecyclerView.LayoutManager kkk = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(kkk);


        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(getActivity(), SpeedyLinearLayoutManager.VERTICAL, false));


        adapter = new RecyclerViewAdapter(getActivity(), ff, recyclerView, idToSend, newLG, getActivity());
        adapter.addItemClickListener(this);

        //MyDividerItemDecorator dividerItemDecoration = new MyDividerItemDecorator();
        //recyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.ItemDecoration dividerItemDecoration = new MyDividerItemDecorator(ContextCompat.getDrawable(getActivity(), R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);
        // recyclerView.addOnItemTouchListener();


        return view;
    }

    public static Gruppe2DynamicFragment newInstance(int sectionNumber) {
        Gruppe2DynamicFragment fragment = new Gruppe2DynamicFragment();
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