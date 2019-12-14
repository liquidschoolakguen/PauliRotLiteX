package test_activities;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import akguen.liquidschool.mylib2.db.DataSource_Kollege;
import akguen.liquidschool.mylib2.db.DataSource_Lerngruppe;
import akguen.liquidschool.mylib2.db.DataSource_Raum;
import akguen.liquidschool.mylib2.db.DataSource_Schueler;
import akguen.liquidschool.mylib2.db.DataSource_Thema;
import akguen.liquidschool.mylib2.db.DataSource_Vergehen;
import akguen.liquidschool.mylib2.model.Schueler;

public class Filler {


    public Filler() {

    }


    public void fillAll(Context c) {
        //hallo 1
        fillSchueler(c);
        fillKollege(c);
        fillLerngruppe(c);
        fillSchueler_Lerngruppe(c);
        fillRaum(c);
        fillThema(c);


    }


    public void fillSchueler_Lerngruppe(Context c) {


    }


    public void fillSchueler(Context c) {
        DataSource_Schueler d = new DataSource_Schueler(c);
        d.open();


        String nnn;
        String[] tempArray;
        Log.i("click", " ---0");
        InputStream is = null;
        AssetManager am = c.getAssets();
        try {
            is = am.open("schueler.txt");
            //Log.i("click", " ---1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.i("click", " ---2");
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        //Log.i("click", " ---3");
        if (s.hasNext()) {
            nnn = s.next();
            //Log.i("click", " ---4");
        } else {
            nnn = "";
            //Log.i("click", " ---5");
        }

        //Log.i("click", " ---555 "+ nnn);


        String delimiter = "\n";

        tempArray = nnn.split(delimiter);


        for (int i = 0; i < tempArray.length; i++) {


            if (Character.isWhitespace(tempArray[i].length() - 1)) {

                tempArray[i] = tempArray[i].substring(0, tempArray[i].length() - 1);
            }


        }


        for (int i = 0; i < tempArray.length; i++) {
            //Log.i("click", tempArray[i]);

            //Log.i("click", i+" --- "+tempArray[i]);
            if (tempArray[i].replaceAll("[\u0000-\u001f]", "").equals("1")) {

                String vor = tempArray[i + 1].substring(0, tempArray[i + 1].length());
                String nach = tempArray[i + 3].substring(0, tempArray[i + 3].length());
                String gesch = tempArray[i + 7];
                String gebTa = tempArray[i + 5];

                /*if(Character.isWhitespace(vor.length()-1)){
                    Log.i("click", " ---. JAA");
                    vor=vor.substring(0,vor.length()-1);
                }
                if(Character.isWhitespace(nach.length()-1)){
                    Log.i("click", " ---. JAAAA");
                    nach=nach.substring(0,nach.length()-1);
                }
*/
                //Log.i("click", " ---."+tempArray[i+1]+ "."+tempArray[i+3]+ "."+tempArray[i+5]+ "."+tempArray[i+7]+".");
                Schueler u = d.createSchueler(vor, nach,null, null, gesch, "1", gebTa, null);
                // Log.i("click", " ---."+u.getVorname()+ "."+u.getItemType()+ "."+u.getGeburtstag()+ "."+u.getGeschlecht()+".");

            }


        }

        d.close();
    }


    public void fillKollege(Context c) {
        DataSource_Kollege d = new DataSource_Kollege(c);
        d.open();

        String nnn;
        String[] tempArray;

        InputStream is = null;
        AssetManager am = c.getAssets();
        try {
            is = am.open("kollege.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");

        if (s.hasNext()) {
            nnn = s.next();
        } else {
            nnn = "";
        }


        String delimiter = "--";

        tempArray = nnn.split(delimiter);

        for (int i = 0; i < tempArray.length; i++) {
            //Log.i("click", tempArray[i]);

            String s1 = null;
            String s2 = null;
            String s3 = null;
            String s4 = null;
            String s5 = null;


            String[] tempTempArray;
            String deDelimiter = ",";

            tempTempArray = tempArray[i].split(deDelimiter);


            for (int ii = 0; ii < tempTempArray.length; ii++) {

                String newString = tempTempArray[ii].replaceAll("[\u0000-\u001f]", "");

                if (ii == 0) {
                    Log.i("click", "s1: " + newString);
                    s1 = newString;
                }
                if (ii == 1) {
                    Log.i("click", "s2: " + newString);
                    s2 = newString;
                }
                if (ii == 2) {
                    Log.i("click", "s3: " + newString);
                    s3 = newString;
                }
                if (ii == 4) {
                    Log.i("click", "s4: " + newString);

                    s4 = newString;
                }


            }

            Log.i("click", "----------------------------------");


            d.createKollege(s2, s1, s3, s4, s5);


        }

        d.close();
    }


    public void fillLerngruppe(Context c) {
        DataSource_Lerngruppe d = new DataSource_Lerngruppe(c);
        d.open();

        String nnn;
        String[] tempArray;

        InputStream is = null;
        AssetManager am = c.getAssets();
        try {
            is = am.open("lerngruppe.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");

        if (s.hasNext()) {
            nnn = s.next();
        } else {
            nnn = "";
        }

        //Log.i("click", "###################### " + nnn);


        String delimiter = "\n";

        tempArray = nnn.split(delimiter);

        for (int i = 0; i < tempArray.length; i++) {
            //Log.i("click", tempArray[i]);

            String s1 = tempArray[i];


            Log.i("click", "---------------------------------- " + s1);


            ;
            d.createLerngruppe(s1,"0");


        }
        d.close();
    }


    public void fillThema(Context c) {
        DataSource_Thema d = new DataSource_Thema(c);
        d.open();

        String nnn;
        String[] tempArray;

        InputStream is = null;
        AssetManager am = c.getAssets();
        try {
            is = am.open("thema.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");

        if (s.hasNext()) {
            nnn = s.next();
        } else {
            nnn = "";
        }

        //Log.i("click", "###################### " + nnn);


        String delimiter = "\n";

        tempArray = nnn.split(delimiter);

        for (int i = 0; i < tempArray.length; i++) {
            //Log.i("click", tempArray[i]);

            String s1 = tempArray[i];


            Log.i("click", "---------------------------------- " + s1);


            ;


            d.createThema(s1);


        }

        d.close();
    }

    public void fillRaum(Context c) {
        DataSource_Raum d = new DataSource_Raum(c);
        d.open();


        String nnn;
        String[] tempArray;

        InputStream is = null;
        AssetManager am = c.getAssets();
        try {
            is = am.open("raum.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");

        if (s.hasNext()) {
            nnn = s.next();
        } else {
            nnn = "";
        }


        String delimiter = "--";

        tempArray = nnn.split(delimiter);

        for (int i = 0; i < tempArray.length; i++) {
            //Log.i("click", tempArray[i]);

            String s1 = null;
            String s2 = null;


            String[] tempTempArray;
            String deDelimiter = ",";

            tempTempArray = tempArray[i].split(deDelimiter);


            for (int ii = 0; ii < tempTempArray.length; ii++) {

                String newString = tempTempArray[ii].replaceAll("[\u0000-\u001f]", "");

                if (ii == 0) {
                    Log.i("click", "s1: " + newString);
                    s2 = newString;
                }
                if (ii == 1) {
                    Log.i("click", "s2: " + newString);
                    s1 = newString;
                }


            }

            Log.i("click", "----------------------------------");


            d.createRaum(s1, s2);


        }


        d.close();


    }




    public void fillVergehen(Context c) {
        DataSource_Vergehen d = new DataSource_Vergehen(c);
        d.open();

        String nnn;
        String[] tempArray;

        InputStream is = null;
        AssetManager am = c.getAssets();
        try {
            is = am.open("vergehen.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");

        if (s.hasNext()) {
            nnn = s.next();
        } else {
            nnn = "";
        }


        String delimiter = "--";

        tempArray = nnn.split(delimiter);

        for (int i = 0; i < tempArray.length; i++) {
            //Log.i("click", tempArray[i]);

            String s1 = null;
            String s2 = null;
            String s3 = null;
            String s4 = null;
            String s5 = null;


            String[] tempTempArray;
            String deDelimiter = ",";

            tempTempArray = tempArray[i].split(deDelimiter);


            for (int ii = 0; ii < tempTempArray.length; ii++) {

                String newString = tempTempArray[ii].replaceAll("[\u0000-\u001f]", "");

                if (ii == 0) {
                    Log.i("click", "s1: " + newString);
                    s1 = newString;
                }
                if (ii == 1) {
                    Log.i("click", "s2: " + newString);
                    s2 = newString;
                }
                if (ii == 2) {
                    Log.i("click", "s3: " + newString);
                    s3 = newString;
                }



            }

            Log.i("click", "----------------------------------");


            d.createVergehen(s1, null, s3, s2);


        }

        d.close();
    }



}