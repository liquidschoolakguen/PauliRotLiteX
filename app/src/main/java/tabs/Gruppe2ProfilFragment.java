package tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import akguen.liquidschool.coredata.db.DataSource_Gruppe2;
import akguen.liquidschool.coredata.db.DataSource_Radio;
import akguen.liquidschool.coredata.db.DataSource_Separator;
import akguen.liquidschool.coredata.db.DataSource_Subjekt;
import akguen.liquidschool.coredata.db.DataSource_Subjekt_Gruppe;
import akguen.liquidschool.paulirotlite.R;
import zz_test.MyDividerItemDecorator;
import zz_test.RecyclerViewAdapter;
import zz_test.SpeedyLinearLayoutManager;

public class Gruppe2ProfilFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {







    public Gruppe2ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gruppe2_profil_fragment, container, false);




        return view;
    }

    public static Gruppe2ProfilFragment newInstance(int sectionNumber) {
        Gruppe2ProfilFragment fragment = new Gruppe2ProfilFragment();

        return fragment;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "Click on item: " + position, Toast.LENGTH_SHORT).show();
    }
}