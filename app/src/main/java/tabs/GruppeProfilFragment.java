package tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import akguen.liquidschool.coredata.db.Controller;
import akguen.liquidschool.coredata.db.DataSource_Gruppe;
import akguen.liquidschool.coredata.db.DataSource_Radio;
import akguen.liquidschool.coredata.db.DataSource_Separator;
import akguen.liquidschool.coredata.db.DataSource_Subjekt;
import akguen.liquidschool.coredata.db.DataSource_Subjekt_Gruppe;
import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.paulirotlite.R;

public class GruppeProfilFragment extends Fragment {

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


        Intent g = getActivity().getIntent();
        String stringIdOfselectedGruppe = g.getStringExtra("stringId");

        Gruppe gg = ds_g2.getGruppeByStringId(stringIdOfselectedGruppe);




        stringId.setText(gg.getStringId());
        name.setText(gg.getName());
        externname.setText(gg.getExternName());
        anzahlSubjekts.setText(ds_g2_su.getSubjektsFromGruppeByStringId(gg.getStringId()).size());
        anzahlVäter.setText(con.holeAlleVäter(gg).size());
        anzahlBrüder.setText(ds_g2.getAllGruppesByVaterStringId(gg.getVaterStringId()).size());

        anzahlSöhne.setText(ds_g2.getAllGruppesByVaterStringId(gg.getStringId()).size());
        anzahlSeparatoren.setText(con.getVisibleSeparatorsOfGruppe(gg).size());


        return view;
    }

    public static GruppeProfilFragment newInstance() {
        GruppeProfilFragment fragment = new GruppeProfilFragment();

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