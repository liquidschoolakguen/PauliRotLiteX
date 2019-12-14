package akguen.liquidschool.paulirotlite.activities.old_activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.mylib2.db.DataSource_Schueler;
import akguen.liquidschool.mylib2.db.DataSource_Schueler_Lerngruppe;
import akguen.liquidschool.mylib2.db.MyDbHelper;
import akguen.liquidschool.mylib2.model.Lerngruppe;
import akguen.liquidschool.mylib2.model.Schueler;
import akguen.liquidschool.paulirotlite.R;

public class S7_LerngruppeUpdaten extends AppCompatActivity {
    private static final String LOG_TAG = S7_LerngruppeUpdaten.class.getSimpleName();
    private DataSource_Schueler dataSourceSchueler;
    private DataSource_Lerngruppe dataSourceLerngruppe;
    private DataSource_Schueler_Lerngruppe dS_L_S;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    Button btnA;
    EditText eName;

    //Geschlecht
    private RadioGroup g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20, g21, g22, g23, g24, g25, g26, g27, g28, g29, g30;

    //Schülername
    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30;

    //Strafpunkte + -
    TextView e1s, e2s, e3s, e4s, e5s, e6s, e7s, e8s, e9s, e10s, e11s, e12s, e13s, e14s, e15s, e16s, e17s, e18s, e19s, e20s, e21s, e22s, e23s, e24s, e25s, e26s, e27s, e28s, e29s, e30s;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30;
    Button b1p, b2p, b3p, b4p, b5p, b6p, b7p, b8p, b9p, b10p, b11p, b12p, b13p, b14p, b15p, b16p, b17p, b18p, b19p, b20p, b21p, b22p, b23p, b24p, b25p, b26p, b27p, b28p, b29p, b30p;


    List<Schueler> schuelersList;

    Lerngruppe selectedLerngruppe;

    List<RadioGroup> list_radioGeschlecht;
    List<EditText> list_editText_Name;
    List<TextView> listZ;
    List<Button> listB;
    List<Button> listBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s7_lerngruppe_updaten);

        dataSourceSchueler = new DataSource_Schueler(this);
        dataSourceLerngruppe = new DataSource_Lerngruppe(this);
        dS_L_S = new DataSource_Schueler_Lerngruppe(this);

        dataSourceSchueler.open();
        dataSourceLerngruppe.open();
        dS_L_S.open();


        btnA = findViewById(R.id.bnt_lg_speichern);

        eName = findViewById(R.id.editText_lg_name);


        list_radioGeschlecht = new ArrayList<>();
        list_editText_Name = new ArrayList<>();
        listZ = new ArrayList<>();
        listB = new ArrayList<>();
        listBP = new ArrayList<>();

        initializeThings();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = sharedpreferences.getString("selectedLerngruppeId", null);
        Log.d(LOG_TAG, "---------------restoredText(): " + restoredText);
        int idd = Integer.parseInt(restoredText);

        schuelersList = new ArrayList<>();

        schuelersList = dS_L_S.getSchuelersFromLerngruppeById(idd);

        for (int i = 0; i < schuelersList.size(); i++) {
            //listZ.get(i).setInputType(InputType.TYPE_CLASS_NUMBER);
            list_editText_Name.get(i).setText(schuelersList.get(i).getVorname());
            //listZ.get(i).setText(schuelersList.get(i).getGeburtstag());
        }

        selectedLerngruppe = dataSourceLerngruppe.getLerngruppeById(idd);

        eName.setText(selectedLerngruppe.getName());


        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (eName.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Du hast der Lerngruppe keinen Namen gegeben.", Toast.LENGTH_LONG);

                } else {


                    String s1 = eName.getText().toString();
                    eName.setText("");


                    dataSourceLerngruppe.updateLerngruppe(selectedLerngruppe.getId(), s1);
                    Lerngruppe lg = dataSourceLerngruppe.getLerngruppeByName(s1);

                    for (int i = 0; i < list_editText_Name.size(); i++) {

                        if (list_editText_Name.get(i).getText().toString().trim().length() != 0) {

                            if (i <= schuelersList.size() - 1) {
                                Schueler d = schuelersList.get(i);
                                d.setVorname(list_editText_Name.get(i).getText().toString());
                                d.setGeburtstag(listZ.get(i).getText().toString());

                                list_editText_Name.get(i).setText("");
                                listZ.get(i).setText("");

                                dataSourceSchueler.updateSchueler(d.getId(), d.getVorname(), null,null, null, null, null, d.getGeburtstag(), null);
                            } else {

                                String g = listZ.get(i).getText().toString();
                                if (!g.equals("0") && !g.equals("")) {


                                } else {

                                    g = "0";
                                }


                                String sss = list_editText_Name.get(i).getText().toString();
                                if (dataSourceSchueler.getSchuelerByVorname(sss) == null) {
                                    System.out.println("XXXSchüler mit dem Namen " + sss + "wird erstellt.");
                                    dataSourceSchueler.createSchueler(sss, null,null, null, null, null, g, null);
                                } else {

                                    System.out.println("XXXSchüler mit dem Namen " + sss + " wird NICHT erstellt.");
                                }


                                int iii = dataSourceSchueler.getSchuelerByVorname(sss).getId();

                                ContentValues values = new ContentValues();
                                values.put(MyDbHelper.SL_SCHUELER_ID, iii);
                                values.put(MyDbHelper.SL_LERNGRUPPE_ID, lg.getId());


                                dS_L_S.createSchueler_Lerngruppe(values);


                            }

                        } else {
                            if (i <= schuelersList.size() - 1) {
                                dataSourceSchueler.deleteSchueler(schuelersList.get(i));
                            }


                        }


                    }


                    Log.d(LOG_TAG, "lg.getId(): " + Integer.toString(lg.getId()));


                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("selectedLerngruppeId", Integer.toString(lg.getId()));
                    editor.commit();

                    Intent speichernS1 = new Intent(S7_LerngruppeUpdaten.this, S1_WaehleSchueler.class);
                    startActivity(speichernS1);
                    S6_LerngruppeBearbeitenLoeschen.fa.finish();
                    finish();
                }


            }
        });


    }

    private void initializeThings() {

        g1 = findViewById(R.id.toggle1);
        g2 = findViewById(R.id.toggle2);
        g3 = findViewById(R.id.toggle3);
        g4 = findViewById(R.id.toggle4);
        g5 = findViewById(R.id.toggle5);
        g6 = findViewById(R.id.toggle6);
        g7 = findViewById(R.id.toggle7);
        g8 = findViewById(R.id.toggle8);
        g9 = findViewById(R.id.toggle9);
        g10 = findViewById(R.id.toggle10);
        g11 = findViewById(R.id.toggle11);
        g12 = findViewById(R.id.toggle12);
        g13 = findViewById(R.id.toggle13);
        g14 = findViewById(R.id.toggle14);
        g15 = findViewById(R.id.toggle15);
        g16 = findViewById(R.id.toggle16);
        g17 = findViewById(R.id.toggle17);
        g18 = findViewById(R.id.toggle18);
        g19 = findViewById(R.id.toggle19);
        g20 = findViewById(R.id.toggle20);
        g21 = findViewById(R.id.toggle21);
        g22 = findViewById(R.id.toggle22);
        g23 = findViewById(R.id.toggle23);
        g24 = findViewById(R.id.toggle24);
        g25 = findViewById(R.id.toggle25);
        g26 = findViewById(R.id.toggle26);
        g27 = findViewById(R.id.toggle27);
        g28 = findViewById(R.id.toggle28);
        g29 = findViewById(R.id.toggle29);
        g30 = findViewById(R.id.toggle30);

        list_radioGeschlecht.add(g1);
        list_radioGeschlecht.add(g2);
        list_radioGeschlecht.add(g3);
        list_radioGeschlecht.add(g4);
        list_radioGeschlecht.add(g5);
        list_radioGeschlecht.add(g6);
        list_radioGeschlecht.add(g7);
        list_radioGeschlecht.add(g8);
        list_radioGeschlecht.add(g9);
        list_radioGeschlecht.add(g10);
        list_radioGeschlecht.add(g11);
        list_radioGeschlecht.add(g12);
        list_radioGeschlecht.add(g13);
        list_radioGeschlecht.add(g14);
        list_radioGeschlecht.add(g15);
        list_radioGeschlecht.add(g16);
        list_radioGeschlecht.add(g17);
        list_radioGeschlecht.add(g18);
        list_radioGeschlecht.add(g19);
        list_radioGeschlecht.add(g20);
        list_radioGeschlecht.add(g21);
        list_radioGeschlecht.add(g22);
        list_radioGeschlecht.add(g23);
        list_radioGeschlecht.add(g24);
        list_radioGeschlecht.add(g25);
        list_radioGeschlecht.add(g26);
        list_radioGeschlecht.add(g27);
        list_radioGeschlecht.add(g28);
        list_radioGeschlecht.add(g29);
        list_radioGeschlecht.add(g30);


        e1 = findViewById(R.id.editText_lg_updaten_1);
        e2 = findViewById(R.id.editText_lg_updaten_2);
        e3 = findViewById(R.id.editText_lg_updaten_3);
        e4 = findViewById(R.id.editText_lg_updaten_4);
        e5 = findViewById(R.id.editText_lg_updaten_5);
        e6 = findViewById(R.id.editText_lg_updaten_6);
        e7 = findViewById(R.id.editText_lg_updaten_7);
        e8 = findViewById(R.id.editText_lg_updaten_8);
        e9 = findViewById(R.id.editText_lg_updaten_9);
        e10 = findViewById(R.id.editText_lg_updaten_10);
        e11 = findViewById(R.id.editText_lg_updaten_11);
        e12 = findViewById(R.id.editText_lg_updaten_12);
        e13 = findViewById(R.id.editText_lg_updaten_13);
        e14 = findViewById(R.id.editText_lg_updaten_14);
        e15 = findViewById(R.id.editText_lg_updaten_15);
        e16 = findViewById(R.id.editText_lg_updaten_16);
        e17 = findViewById(R.id.editText_lg_updaten_17);
        e18 = findViewById(R.id.editText_lg_updaten_18);
        e19 = findViewById(R.id.editText_lg_updaten_19);
        e20 = findViewById(R.id.editText_lg_updaten_20);
        e21 = findViewById(R.id.editText_lg_updaten_21);
        e22 = findViewById(R.id.editText_lg_updaten_22);
        e23 = findViewById(R.id.editText_lg_updaten_23);
        e24 = findViewById(R.id.editText_lg_updaten_24);
        e25 = findViewById(R.id.editText_lg_updaten_25);
        e26 = findViewById(R.id.editText_lg_updaten_26);
        e27 = findViewById(R.id.editText_lg_updaten_27);
        e28 = findViewById(R.id.editText_lg_updaten_28);
        e29 = findViewById(R.id.editText_lg_updaten_29);
        e30 = findViewById(R.id.editText_lg_updaten_30);

        list_editText_Name.add(e1);
        list_editText_Name.add(e2);
        list_editText_Name.add(e3);
        list_editText_Name.add(e4);
        list_editText_Name.add(e5);
        list_editText_Name.add(e6);
        list_editText_Name.add(e7);
        list_editText_Name.add(e8);
        list_editText_Name.add(e9);
        list_editText_Name.add(e10);
        list_editText_Name.add(e11);
        list_editText_Name.add(e12);
        list_editText_Name.add(e13);
        list_editText_Name.add(e14);
        list_editText_Name.add(e15);
        list_editText_Name.add(e16);
        list_editText_Name.add(e17);
        list_editText_Name.add(e18);
        list_editText_Name.add(e19);
        list_editText_Name.add(e20);
        list_editText_Name.add(e21);
        list_editText_Name.add(e22);
        list_editText_Name.add(e23);
        list_editText_Name.add(e24);
        list_editText_Name.add(e25);
        list_editText_Name.add(e26);
        list_editText_Name.add(e27);
        list_editText_Name.add(e28);
        list_editText_Name.add(e29);
        list_editText_Name.add(e30);


        e1s = findViewById(R.id.tv_strafpunkt_1);
        e2s = findViewById(R.id.tv_strafpunkt_2);
        e3s = findViewById(R.id.tv_strafpunkt_3);
        e4s = findViewById(R.id.tv_strafpunkt_4);
        e5s = findViewById(R.id.tv_strafpunkt_5);
        e6s = findViewById(R.id.tv_strafpunkt_6);
        e7s = findViewById(R.id.tv_strafpunkt_7);
        e8s = findViewById(R.id.tv_strafpunkt_8);
        e9s = findViewById(R.id.tv_strafpunkt_9);
        e10s = findViewById(R.id.tv_strafpunkt_10);
        e11s = findViewById(R.id.tv_strafpunkt_11);
        e12s = findViewById(R.id.tv_strafpunkt_12);
        e13s = findViewById(R.id.tv_strafpunkt_13);
        e14s = findViewById(R.id.tv_strafpunkt_14);
        e15s = findViewById(R.id.tv_strafpunkt_15);
        e16s = findViewById(R.id.tv_strafpunkt_16);
        e17s = findViewById(R.id.tv_strafpunkt_17);
        e18s = findViewById(R.id.tv_strafpunkt_18);
        e19s = findViewById(R.id.tv_strafpunkt_19);
        e20s = findViewById(R.id.tv_strafpunkt_20);
        e21s = findViewById(R.id.tv_strafpunkt_21);
        e22s = findViewById(R.id.tv_strafpunkt_22);
        e23s = findViewById(R.id.tv_strafpunkt_23);
        e24s = findViewById(R.id.tv_strafpunkt_24);
        e25s = findViewById(R.id.tv_strafpunkt_25);
        e26s = findViewById(R.id.tv_strafpunkt_26);
        e27s = findViewById(R.id.tv_strafpunkt_27);
        e28s = findViewById(R.id.tv_strafpunkt_28);
        e29s = findViewById(R.id.tv_strafpunkt_29);
        e30s = findViewById(R.id.tv_strafpunkt_30);

        listZ.add(e1s);
        listZ.add(e2s);
        listZ.add(e3s);
        listZ.add(e4s);
        listZ.add(e5s);
        listZ.add(e6s);
        listZ.add(e7s);
        listZ.add(e8s);
        listZ.add(e9s);
        listZ.add(e10s);
        listZ.add(e11s);
        listZ.add(e12s);
        listZ.add(e13s);
        listZ.add(e14s);
        listZ.add(e15s);
        listZ.add(e16s);
        listZ.add(e17s);
        listZ.add(e18s);
        listZ.add(e19s);
        listZ.add(e20s);
        listZ.add(e21s);
        listZ.add(e22s);
        listZ.add(e23s);
        listZ.add(e24s);
        listZ.add(e25s);
        listZ.add(e26s);
        listZ.add(e27s);
        listZ.add(e28s);
        listZ.add(e29s);
        listZ.add(e30s);


        b1 = findViewById(R.id.btn_strafpunkt_minus_1);
        b2 = findViewById(R.id.btn_strafpunkt_minus_2);
        b3 = findViewById(R.id.btn_strafpunkt_minus_3);
        b4 = findViewById(R.id.btn_strafpunkt_minus_4);
        b5 = findViewById(R.id.btn_strafpunkt_minus_5);
        b6 = findViewById(R.id.btn_strafpunkt_minus_6);
        b7 = findViewById(R.id.btn_strafpunkt_minus_7);
        b8 = findViewById(R.id.btn_strafpunkt_minus_8);
        b9 = findViewById(R.id.btn_strafpunkt_minus_9);
        b10 = findViewById(R.id.btn_strafpunkt_minus_10);
        b11 = findViewById(R.id.btn_strafpunkt_minus_11);
        b12 = findViewById(R.id.btn_strafpunkt_minus_12);
        b13 = findViewById(R.id.btn_strafpunkt_minus_13);
        b14 = findViewById(R.id.btn_strafpunkt_minus_14);
        b15 = findViewById(R.id.btn_strafpunkt_minus_15);
        b16 = findViewById(R.id.btn_strafpunkt_minus_16);
        b17 = findViewById(R.id.btn_strafpunkt_minus_17);
        b18 = findViewById(R.id.btn_strafpunkt_minus_18);
        b19 = findViewById(R.id.btn_strafpunkt_minus_19);
        b20 = findViewById(R.id.btn_strafpunkt_minus_20);
        b21 = findViewById(R.id.btn_strafpunkt_minus_21);
        b22 = findViewById(R.id.btn_strafpunkt_minus_22);
        b23 = findViewById(R.id.btn_strafpunkt_minus_23);
        b24 = findViewById(R.id.btn_strafpunkt_minus_24);
        b25 = findViewById(R.id.btn_strafpunkt_minus_25);
        b26 = findViewById(R.id.btn_strafpunkt_minus_26);
        b27 = findViewById(R.id.btn_strafpunkt_minus_27);
        b28 = findViewById(R.id.btn_strafpunkt_minus_28);
        b29 = findViewById(R.id.btn_strafpunkt_minus_29);
        b30 = findViewById(R.id.btn_strafpunkt_minus_30);

        listB.add(b1);
        listB.add(b2);
        listB.add(b3);
        listB.add(b4);
        listB.add(b5);
        listB.add(b6);
        listB.add(b7);
        listB.add(b8);
        listB.add(b9);
        listB.add(b10);
        listB.add(b11);
        listB.add(b12);
        listB.add(b13);
        listB.add(b14);
        listB.add(b15);
        listB.add(b16);
        listB.add(b17);
        listB.add(b18);
        listB.add(b19);
        listB.add(b20);
        listB.add(b21);
        listB.add(b22);
        listB.add(b23);
        listB.add(b24);
        listB.add(b25);
        listB.add(b26);
        listB.add(b27);
        listB.add(b28);
        listB.add(b29);
        listB.add(b30);

        for(Button b: listB){
            b.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = listB.indexOf(b);

                    int strafpunkt = Integer.parseInt(listZ.get(pos).getText().toString());
                    if(strafpunkt<9) {
                        listZ.get(pos).setText(Integer.toString(strafpunkt + 1));
                    }
                }
            });
        }



        b1p = findViewById(R.id.btn_strafpunkt_plus_1);
        b2p = findViewById(R.id.btn_strafpunkt_plus_2);
        b3p = findViewById(R.id.btn_strafpunkt_plus_3);
        b4p = findViewById(R.id.btn_strafpunkt_plus_4);
        b5p = findViewById(R.id.btn_strafpunkt_plus_5);
        b6p = findViewById(R.id.btn_strafpunkt_plus_6);
        b7p = findViewById(R.id.btn_strafpunkt_plus_7);
        b8p = findViewById(R.id.btn_strafpunkt_plus_8);
        b9p = findViewById(R.id.btn_strafpunkt_plus_9);
        b10p = findViewById(R.id.btn_strafpunkt_plus_10);
        b11p = findViewById(R.id.btn_strafpunkt_plus_11);
        b12p = findViewById(R.id.btn_strafpunkt_plus_12);
        b13p = findViewById(R.id.btn_strafpunkt_plus_13);
        b14p = findViewById(R.id.btn_strafpunkt_plus_14);
        b15p = findViewById(R.id.btn_strafpunkt_plus_15);
        b16p = findViewById(R.id.btn_strafpunkt_plus_16);
        b17p = findViewById(R.id.btn_strafpunkt_plus_17);
        b18p = findViewById(R.id.btn_strafpunkt_plus_18);
        b19p = findViewById(R.id.btn_strafpunkt_plus_19);
        b20p = findViewById(R.id.btn_strafpunkt_plus_20);
        b21p = findViewById(R.id.btn_strafpunkt_plus_21);
        b22p = findViewById(R.id.btn_strafpunkt_plus_22);
        b23p = findViewById(R.id.btn_strafpunkt_plus_23);
        b24p = findViewById(R.id.btn_strafpunkt_plus_24);
        b25p = findViewById(R.id.btn_strafpunkt_plus_25);
        b26p = findViewById(R.id.btn_strafpunkt_plus_26);
        b27p = findViewById(R.id.btn_strafpunkt_plus_27);
        b28p = findViewById(R.id.btn_strafpunkt_plus_28);
        b29p = findViewById(R.id.btn_strafpunkt_plus_29);
        b30p = findViewById(R.id.btn_strafpunkt_plus_30);


        listBP.add(b1p);
        listBP.add(b2p);
        listBP.add(b3p);
        listBP.add(b4p);
        listBP.add(b5p);
        listBP.add(b6p);
        listBP.add(b7p);
        listBP.add(b8p);
        listBP.add(b9p);
        listBP.add(b10p);
        listBP.add(b11p);
        listBP.add(b12p);
        listBP.add(b13p);
        listBP.add(b14p);
        listBP.add(b15p);
        listBP.add(b16p);
        listBP.add(b17p);
        listBP.add(b18p);
        listBP.add(b19p);
        listBP.add(b20p);
        listBP.add(b21p);
        listBP.add(b22p);
        listBP.add(b23p);
        listBP.add(b24p);
        listBP.add(b25p);
        listBP.add(b26p);
        listBP.add(b27p);
        listBP.add(b28p);
        listBP.add(b29p);
        listBP.add(b30p);
        for(Button bp: listBP){
            bp.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = listBP.indexOf(bp);

                    int strafpunkt = Integer.parseInt(listZ.get(pos).getText().toString());
                    if(strafpunkt>0) {
                        listZ.get(pos).setText(Integer.toString(strafpunkt - 1));
                    }
                }
            });
        }






    }


    @Override
    protected void onResume() {
        super.onResume();

        dataSourceSchueler.open();
        dataSourceLerngruppe.open();
        dS_L_S.open();


    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSourceSchueler.close();
        dataSourceLerngruppe.close();
        dS_L_S.close();


    }
}
