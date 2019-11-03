package akguen.liquidschool.paulirotlite;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.DataSource_Lerngruppe;
import db.DataSource_Schueler;
import db.DataSource_Schueler_Lerngruppe;
import db.Filler;
import db.MyDbHelper;
import db.Speichern_Adresse;
import db.Speichern_Angehoeriger;
import db.Speichern_Gueltigkeitsbereich;
import db.Speichern_Kollege;
import db.Speichern_Kollege_Standort;
import db.Speichern_Kontakt;
import db.Speichern_Lernform;
import db.Speichern_Lerngruppe;
import db.Speichern_Raum;
import db.Speichern_SP_Fach;
import db.Speichern_SP_Fragment;
import db.Speichern_Schueler;
import db.Speichern_Schueler_Angehoeriger;
import db.Speichern_Schueler_Lerngruppe;
import db.Speichern_Schulverbund;
import db.Speichern_Standort;
import db.Speichern_Thema;
import db.Speichern_Vergehen;
import db.Speichern_Vorfall;
import model.Lerngruppe;
import model.Schueler;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3_erstelle_lerngruppe);

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
                        dataSourceLerngruppe.createLerngruppe(s1);
                        Lerngruppe lg = dataSourceLerngruppe.getLerngruppeByName(s1);

                        List<Schueler> sList = new ArrayList<>();
                        for (EditText s : list){

                            String sX = s.getText().toString();
                            s.setText("");

                            if(sX.trim().length() != 0) {
                                if (dataSourceSchueler.getSchuelerByVorname(sX) == null) {
                                    dataSourceSchueler.createSchueler(sX, null, null, null, null, null, null);
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
