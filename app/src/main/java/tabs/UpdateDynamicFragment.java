package tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.paulirotlite.R;
import akguen.liquidschool.paulirotlite.S4_WaehleVergehen;
import db.DataSource_Lerngruppe;
import db.DataSource_Schueler;
import db.DataSource_Schueler_Lerngruppe;
import model.Schueler;
import zz_test.UpdateCustomAdapter2;


public class UpdateDynamicFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int sectionNumber;
    ListView listView;
    private static UpdateCustomAdapter2 adapter;

    private DataSource_Schueler_Lerngruppe dS_L_S;
    private DataSource_Lerngruppe dS_L;
    private DataSource_Schueler dS_Schueler;

    Button buttonUp;

    public UpdateDynamicFragment() {
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
        View view = inflater.inflate(R.layout.update_fragment_dynamic, container, false);


        dS_L_S = new DataSource_Schueler_Lerngruppe(getActivity());
        dS_L = new DataSource_Lerngruppe(getActivity());
        dS_Schueler = new DataSource_Schueler(getActivity());


        dS_L_S.open();

        buttonUp = (Button) view.findViewById(R.id.bnt_lg_update);
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Schueler p = new Schueler();
                p.setNachname("3");
                adapter.addItem(p);


            }
        });



        listView=(ListView)view.findViewById(R.id.dynamic_list_schueler_update);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* Schueler schueler = (Schueler) parent.getItemAtPosition(position);
                //Schueler schu= ff.get(position);

                // Snackbar.make(view, schu.getVorname()+"\n"+schu.getGeburtstag(), Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();


                Intent intent = new Intent(getActivity(), S4_WaehleVergehen.class);
                intent.putExtra("schueler_id", Integer.toString(schueler.getId()));
                startActivity(intent);*/



              /*  Schueler schu= new Schueler();
                Schueler gg = adapter.getItem(pos);
                gg.setNachname("1");
                gg.setVorname("XXXXX");
                //adapter.replaceItem2(gg,pos);
                adapter.addItem(schu);
                adapter.notifyDataSetChanged();*/


            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

              Schueler schu = (Schueler) arg0.getItemAtPosition(pos);
               // Schueler = ff.get(position);

               /* Snackbar.make(view, schu.getVorname()+"\n"+schu.getGeburtstag(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/

               // Schueler schu= adapter.getItem(pos);
             /* Schueler gg = new Schueler();
                gg.setNachname("1");
                gg.setVorname("XXXXX");
                //adapter.replaceItem2(gg,pos);
                adapter.addItem(gg);
                adapter.notifyDataSetChanged();
*/
                adapter.gogo(view,schu);






               /* List<Schueler> schuelerList = dS_L_S.getSchuelersFromLerngruppeById(sectionNumber);
                 ArrayList<Schueler> ff= new ArrayList<Schueler>(schuelerList);
                 Schueler neu = new Schueler();
                 ff.add(neu);
                adapter= new WaehleSchuelerCustomAdapter(ff,getContext());
                listView.setAdapter(adapter);
                listView.setSelection(ff.indexOf(neu));*/


                return true;
            }
        });

        List<Schueler> schuelerList = dS_L_S.getSchuelersFromLerngruppeById(sectionNumber);
        ArrayList<Schueler> ff= new ArrayList<Schueler>(schuelerList);

        for(Schueler z : ff){


            z.setNachname("2");
        }
   /*     for(int i=0; i<30; i++)
        {
            Schueler s = new Schueler();
            s.setNachname("2");
            s.setGeburtstag(Integer.toString(i));
            s.setVorname("Fritz"+s.getGeburtstag());
            s.setGeschlecht("M");
            ff.add(s);
        }*/
        Schueler k = new Schueler();
        k.setNachname("3");
        ff.add(k);
        /*Schueler gggg = new Schueler();
        gggg.setNachname("2");
        ff.add(gggg);
        Schueler ggg = new Schueler();
        ggg.setNachname("3");
        ff.add(ggg);
        Schueler gggg2 = new Schueler();
        gggg2.setNachname("2");
        ff.add(gggg2);
        Schueler ggg3 = new Schueler();
        ggg3.setNachname("3");
        ff.add(ggg3);
        Schueler gggg4 = new Schueler();
        gggg4.setNachname("2");
        ff.add(gggg4);
        Schueler ggg5 = new Schueler();
        ggg5.setNachname("1");
        ff.add(ggg5);
        Schueler gggg6 = new Schueler();
        gggg6.setNachname("1");
        ff.add(gggg6);
        Schueler ggg7 = new Schueler();
        ggg7.setNachname("3");
        ff.add(ggg7);
        Schueler gggg8 = new Schueler();
        gggg8.setNachname("2");
        ff.add(gggg8);
        Schueler ggg9 = new Schueler();
        ggg9.setNachname("3");
        ff.add(ggg9);*/
        adapter= new UpdateCustomAdapter2(ff, getContext());

        listView.setAdapter(adapter);








        return  view;
    }

    public static UpdateDynamicFragment newInstance(int sectionNumber) {
        UpdateDynamicFragment fragment = new UpdateDynamicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}