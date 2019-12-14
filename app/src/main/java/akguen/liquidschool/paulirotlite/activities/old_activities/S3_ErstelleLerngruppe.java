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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class S3_ErstelleLerngruppe extends AppCompatActivity {
    private static final String LOG_TAG = S3_ErstelleLerngruppe.class.getSimpleName();
    private DataSource_Schueler dataSourceSchueler;
    private DataSource_Lerngruppe dataSourceLerngruppe;
    private DataSource_Schueler_Lerngruppe dS_L_S;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    Button btnA;
    EditText eName;
    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30;
    private RadioGroup g1,g2,g3,g4,g5,g6,g7,g8,g9,g10,g11,g12,g13,g14,g15,g16,g17,g18,g19,g20,g21,g22,g23,g24,g25,g26,g27,g28,g29,g30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erstelle_schuelergruppe_fragment);

        dataSourceSchueler = new DataSource_Schueler(this);
        dataSourceLerngruppe = new DataSource_Lerngruppe(this);
        dS_L_S = new DataSource_Schueler_Lerngruppe(this);


        btnA = findViewById(R.id.bnt_lg_speichern);

        eName = findViewById(R.id.editText_lg_name);

        e1 = findViewById(R.id.editText_lg_erstellen_1);
        e2 = findViewById(R.id.editText_lg_erstellen_2);
        e3 = findViewById(R.id.editText_lg_erstellen_3);
        e4 = findViewById(R.id.editText_lg_erstellen_4);
        e5 = findViewById(R.id.editText_lg_erstellen_5);
        e6 = findViewById(R.id.editText_lg_erstellen_6);
        e7 = findViewById(R.id.editText_lg_erstellen_7);
        e8 = findViewById(R.id.editText_lg_erstellen_8);
        e9 = findViewById(R.id.editText_lg_erstellen_9);
        e10 = findViewById(R.id.editText_lg_erstellen_10);
        e11 = findViewById(R.id.editText_lg_erstellen_11);
        e12 = findViewById(R.id.editText_lg_erstellen_12);
        e13 = findViewById(R.id.editText_lg_erstellen_13);
        e14 = findViewById(R.id.editText_lg_erstellen_14);
        e15 = findViewById(R.id.editText_lg_erstellen_15);
        e16 = findViewById(R.id.editText_lg_erstellen_16);
        e17 = findViewById(R.id.editText_lg_erstellen_17);
        e18 = findViewById(R.id.editText_lg_erstellen_18);
        e19 = findViewById(R.id.editText_lg_erstellen_19);
        e20 = findViewById(R.id.editText_lg_erstellen_20);
        e21 = findViewById(R.id.editText_lg_erstellen_21);
        e22 = findViewById(R.id.editText_lg_erstellen_22);
        e23 = findViewById(R.id.editText_lg_erstellen_23);
        e24 = findViewById(R.id.editText_lg_erstellen_24);
        e25 = findViewById(R.id.editText_lg_erstellen_25);
        e26 = findViewById(R.id.editText_lg_erstellen_26);
        e27 = findViewById(R.id.editText_lg_erstellen_27);
        e28 = findViewById(R.id.editText_lg_erstellen_28);
        e29 = findViewById(R.id.editText_lg_erstellen_29);
        e30 = findViewById(R.id.editText_lg_erstellen_30);


        List<EditText> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        list.add(e8);
        list.add(e9);
        list.add(e10);
        list.add(e11);
        list.add(e12);
        list.add(e13);
        list.add(e14);
        list.add(e15);
        list.add(e16);
        list.add(e17);
        list.add(e18);
        list.add(e19);
        list.add(e20);
        list.add(e21);
        list.add(e22);
        list.add(e23);
        list.add(e24);
        list.add(e25);
        list.add(e26);
        list.add(e27);
        list.add(e28);
        list.add(e29);
        list.add(e30);


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

        List<RadioGroup> listS = new ArrayList<>();
        listS.add(g1);
        listS.add(g2);
        listS.add(g3);
        listS.add(g4);
        listS.add(g5);
        listS.add(g6);
        listS.add(g7);
        listS.add(g8);
        listS.add(g9);
        listS.add(g10);
        listS.add(g11);
        listS.add(g12);
        listS.add(g13);
        listS.add(g14);
        listS.add(g15);
        listS.add(g16);
        listS.add(g17);
        listS.add(g18);
        listS.add(g19);
        listS.add(g20);
        listS.add(g21);
        listS.add(g22);
        listS.add(g23);
        listS.add(g24);
        listS.add(g25);
        listS.add(g26);
        listS.add(g27);
        listS.add(g28);
        listS.add(g29);
        listS.add(g30);


        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (eName.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(),"Du hast der Lerngruppe keinen Namen gegeben.",Toast.LENGTH_LONG);

                }else{


                    String s1 = eName.getText().toString();
                    eName.setText("");

                    if(dataSourceLerngruppe.getLerngruppeByName(s1)!=null){
                        Toast.makeText(getApplicationContext(),"Dieser Lerngruppenname existiert bereits. Wähle einen anderen Namen.",Toast.LENGTH_LONG);

                    }else{
                        dataSourceLerngruppe.createLerngruppe(s1,"0");
                        Lerngruppe lg = dataSourceLerngruppe.getLerngruppeByName(s1);

                        List<Schueler> sList = new ArrayList<>();
                        for (EditText s : list){
                            int pos = list.indexOf(s);
                            String sX = s.getText().toString();
                            s.setText("");
                            int selectedSex= listS.get(pos).getCheckedRadioButtonId();
                            RadioButton radioSexButton = (RadioButton) findViewById(selectedSex);

                            if(sX.trim().length() != 0) {
                                if (dataSourceSchueler.getSchuelerByVorname(sX) == null) {


                                    System.out.println("Schüler mit dem Namen "+sX+ "wird erstellt.");
                                    dataSourceSchueler.createSchueler(sX, null,null, null, radioSexButton.getText().toString(), null, null, null);
                                }else{

                                    System.out.println("Schüler mit dem Namen "+sX+ "wird NICHT erstellt.");
                                }

                                Schueler schue = dataSourceSchueler.getSchuelerByVorname(sX);


                                ContentValues values = new ContentValues();
                                values.put(MyDbHelper.SL_SCHUELER_ID, schue.getId());
                                values.put(MyDbHelper.SL_LERNGRUPPE_ID, lg.getId());


                                dS_L_S.createSchueler_Lerngruppe(values);

                            }


                        }

                        Log.d(LOG_TAG, "lg.getId(): "+Integer.toString(lg.getId()));


                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("selectedLerngruppeId", Integer.toString(lg.getId()));
                        editor.commit();

                        Intent speichernS1 = new Intent(S3_ErstelleLerngruppe.this, S1_WaehleSchueler.class);
                        startActivity(speichernS1);
                        finish();

                    }



                }




            }
        });


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
