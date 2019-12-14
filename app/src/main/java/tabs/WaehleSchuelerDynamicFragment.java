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

import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.mylib2.db.DataSource_Schueler;
import akguen.liquidschool.mylib2.db.DataSource_Schueler_Lerngruppe;
import akguen.liquidschool.mylib2.db.MyDbHelper;
import akguen.liquidschool.mylib2.model.Lerngruppe;
import akguen.liquidschool.mylib2.model.Schueler;
import zz_test.MyDividerItemDecorator;
import zz_test.RecyclerViewAdapter;
import zz_test.SpeedyLinearLayoutManager;

public class WaehleSchuelerDynamicFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int sectionNumber;
    ListView listView;
    // private static WaehleSchuelerCustomAdapter adapter;
    RecyclerViewAdapter adapter;
    private DataSource_Schueler_Lerngruppe dS_L_S;
    private DataSource_Lerngruppe dS_L;
    private DataSource_Schueler dS_Schueler;
    Button respet_lgSpeichern;
    boolean newLG = false;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";


    public WaehleSchuelerDynamicFragment() {
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
        View view = inflater.inflate(R.layout.waehle_schueler_fragment_dynamic, container, false);


        dS_L = new DataSource_Lerngruppe(getActivity());
        dS_Schueler = new DataSource_Schueler(getActivity());
        dS_L_S = new DataSource_Schueler_Lerngruppe(getActivity());

        dS_L.open();
        dS_Schueler.open();
        dS_L_S.open();


        List hhh = dS_L.getAllLerngruppes();
        //System.out.println("..............................." + h.size());

        Lerngruppe selectedLerngruppe;
        ArrayList<Schueler> ff = new ArrayList<Schueler>();

        int idToSend=0;
        if ((sectionNumber) <= hhh.size()) {

            selectedLerngruppe = ((Lerngruppe) hhh.get(sectionNumber-1));
            ff.addAll(dS_L_S.getSchuelersFromLerngruppeById(selectedLerngruppe.getId()));
            idToSend = selectedLerngruppe.getId();
        } else {

            selectedLerngruppe = null;
        }


        System.out.println("   dS_L.getAllLerngruppes()   ..............................    "+ hhh.size());

        System.out.println("       "+sectionNumber+"..............................    "+ ff.size());
       // System.out.println("       "+sectionNumber+"..............................."+ selectedLerngruppe.getName()+ "    "+ ff.size());


        respet_lgSpeichern = (Button) view.findViewById(R.id.bnt_nrespekt);


        if (selectedLerngruppe == null) {

            newLG = true;


            Schueler mmmm = new Schueler();
            mmmm.setItemType("6");
            mmmm.setGeschlecht("M");
            mmmm.setGeburtstag("0");
            mmmm.setVorname("");
            ff.add(mmmm);

            for (int i = 0; i < 30; i++) {
                Schueler kenni = new Schueler();
                kenni.setItemType("1");
                kenni.setGeschlecht("M");
                kenni.setGeburtstag("0");
                kenni.setVorname("");
                ff.add(kenni);
            }

          Schueler endRow = new Schueler();
            endRow.setItemType("9");
            endRow.setVorname("");
            ff.add(endRow);

            respet_lgSpeichern.setText("Lerngruppe speichern");
            respet_lgSpeichern.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDisableDarker));

            respet_lgSpeichern.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String lg_name = adapter.getDataSet().get(0).getVorname();

                    if (lg_name.trim().length() == 0) {

                        Snackbar.make(view, "Du hast der Lerngruppe keinen Namen gegeben.", Snackbar.LENGTH_LONG)
                                .setAction("No action", null).show();
                    } else {


                        if (dS_L.getLerngruppeByName(lg_name) != null) {
                            Snackbar.make(view, "Dieser Lerngruppenname existiert bereits. Wähle einen anderen", Snackbar.LENGTH_LONG)
                                    .setAction("No action", null).show();
                        } else {
                            dS_L.createLerngruppe(lg_name,"0");
                            Lerngruppe lg = dS_L.getLerngruppeByName(lg_name);

                            adapter.getDataSet().remove(0);
                            for (Schueler s : adapter.getDataSet()) {


                                if (s.getVorname().trim().length() != 0) {
                                    if (dS_Schueler.getSchuelerByVorname(s.getVorname()) == null) {


                                        System.out.println("Schüler mit dem Namen " + s.getVorname() + "wird erstellt.");
                                        dS_Schueler.createSchueler(s.getVorname(),s.getSurname(), null, null, s.getGeschlecht(), null, "0", null);
                                    } else {

                                        System.out.println("Schüler mit dem Namen " + s.getVorname() + "wird NICHT erstellt.");
                                    }

                                    Schueler schue = dS_Schueler.getSchuelerByVorname(s.getVorname());


                                    ContentValues values = new ContentValues();
                                    values.put(MyDbHelper.SL_SCHUELER_ID, schue.getId());
                                    values.put(MyDbHelper.SL_LERNGRUPPE_ID, lg.getId());


                                    dS_L_S.createSchueler_Lerngruppe(values);

                                }


                            }




                            Intent speichernS1 = new Intent(getContext(), WaehleSchuelerDynamicTabsActivity.class);
                            //speichernS1.putExtra("EXTRA_SESSION_ID", Integer.toString(lg.getId()));
                            startActivity(speichernS1);
                            getActivity().finish();


                        }


                    }


                }
            });


        } else {
            respet_lgSpeichern.setText("Respekt!");
            respet_lgSpeichern.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
            for (Schueler b : ff) {

                b.setItemType("1");

            }


            Schueler h = new Schueler();
            h.setItemType("3");
            ff.add(h);

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

    public static WaehleSchuelerDynamicFragment newInstance(int sectionNumber) {
        WaehleSchuelerDynamicFragment fragment = new WaehleSchuelerDynamicFragment();
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