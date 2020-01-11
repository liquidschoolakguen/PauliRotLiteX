package tabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import akguen.liquidschool.coredata.db.Controller;
import akguen.liquidschool.coredata.db.DataSource_Gruppe;
import akguen.liquidschool.coredata.db.DataSource_Radio;
import akguen.liquidschool.coredata.db.DataSource_Separator;
import akguen.liquidschool.coredata.db.DataSource_Subjekt;
import akguen.liquidschool.coredata.db.DataSource_Subjekt_Gruppe;
import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.paulirotlite.R;

public class GruppeProfilFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private int sectionNumber;


    private DataSource_Gruppe ds_g2;
    private DataSource_Subjekt_Gruppe ds_g2_su;
    private Controller con;

    private Gruppe selectedGruppe;

    public GruppeProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gruppe_fragment_profil, container, false);

        final TextView stringId = view.findViewById(R.id.tv_item_stringid2);
        final TextView name = view.findViewById(R.id.tv_item_name2);
        final TextView externname = view.findViewById(R.id.tv_item_externname2);

        final TextView anzahlSubjekts = view.findViewById(R.id.tv_item_anzahl_subjekts2);
        final TextView anzahlVäter = view.findViewById(R.id.tv_item_anzahl_vaeter2);
        final TextView anzahlBrüder = view.findViewById(R.id.tv_item_anzahl_brueder2);
        final TextView anzahlSöhne = view.findViewById(R.id.tv_item_anzahl_soehne2);

        final TextView anzahlSeparatoren = view.findViewById(R.id.tv_item_anzahl_separatoren2);


        ds_g2 = new DataSource_Gruppe(getActivity());
        ds_g2_su = new DataSource_Subjekt_Gruppe(getActivity());
        con = new Controller(getActivity());

        ds_g2.open();
        ds_g2_su.open();
        con.openAll();
        Log.d("GruppeTest", "drin? ");
        if ((sectionNumber)==0) {
            Log.d("GruppeTest", "drin! ");
            //Toast.makeText(getContext(), "ok: " + sectionNumber, Toast.LENGTH_LONG).show();
        }
        if ((sectionNumber)==1) {
             //Toast.makeText(getContext(), "ok: " + sectionNumber, Toast.LENGTH_LONG).show();
        }
        if ((sectionNumber)==2) {

        }
        if ((sectionNumber)==3) {

        }
        if ((sectionNumber)==4) {

        }
        if ((sectionNumber)==5) {

        }
        if ((sectionNumber)==6) {

        }







        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String stringIdOfselectedGruppe = prefs.getString("selectedGruppe", null);



        //Log.d("GruppeTest", stringIdOfselectedGruppe);


        Gruppe gg = ds_g2.getGruppeByStringId(stringIdOfselectedGruppe);
        Log.d("GruppeTest", "ttt "+gg.getStringId());
        Log.d("GruppeTest", "ttt "+gg.getName());
        Log.d("GruppeTest", "ttt "+gg.getExternName());


        stringId.setText(gg.getStringId());
        name.setText(gg.getName());
        externname.setText(gg.getExternName());

        anzahlSubjekts.setText(Integer.toString(ds_g2_su.getSubjektsFromGruppeByStringId(gg.getStringId()).size()));
        anzahlVäter.setText(Integer.toString(con.holeAlleVäter(gg).size()));
        anzahlBrüder.setText(Integer.toString(ds_g2.getAllGruppesByVaterStringId(gg.getVaterStringId()).size()-1));

        anzahlSöhne.setText(Integer.toString(ds_g2.getAllGruppesByVaterStringId(gg.getStringId()).size()));
        anzahlSeparatoren.setText(Integer.toString(con.getVisibleSeparatorsOfGruppe(gg).size()));


        return view;
    }

    public static GruppeProfilFragment newInstance(int sectionNumber) {
        GruppeProfilFragment fragment = new GruppeProfilFragment();
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


}