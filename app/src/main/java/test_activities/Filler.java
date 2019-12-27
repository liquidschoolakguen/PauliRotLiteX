package test_activities;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Iterator;

import akguen.liquidschool.coredata.db.*;
import akguen.liquidschool.coredata.model.*;

public class Filler {


    public Filler() {

    }



    public void fillAll(Context c) throws GeneralSecurityException, IOException {
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



    public void fillKollege(Context c) throws GeneralSecurityException, IOException {
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

            String vorname = null;
            String nachname = null;
            String personaltyp = null;
            String geburtstag = null;
            String strasse = null;
            String plz = null;
            String telefon = null;
            String email = null;
            String standort = null;
            String passwort = null;

            String[] tempTempArray;
            String deDelimiter = ",";

            tempTempArray = tempArray[i].split(deDelimiter);


            for (int ii = 0; ii < tempTempArray.length; ii++) {
                Log.i("click", "+++++++++++++++++++++++++++++++++++++++++++++");
                String newString = tempTempArray[ii].replaceAll("[\u0000-\u001f]", "");

                if (ii == 0) {// Nachname
                    Log.i("click", "Nachname: " + newString);
                    nachname = newString;
                }
                if (ii == 1) {//Vorname
                    Log.i("click", "Vorname: " + newString);
                    vorname = newString;
                }
                if (ii == 2) {//Personaltyp
                    Log.i("click", "personaltyp: " + newString);
                    personaltyp = newString;
                }
                if (ii == 3) {//Standort
                    Log.i("click", "standort: " + newString);

                    standort = newString;
                }

                if (ii == 4) {//passwort
                    Log.i("click", "passwort: " + newString);

                    passwort = newString;
                }

                if (ii == 5) {//strasse
                    Log.i("click", "strasse: " + newString);

                    strasse = newString;
                }
                if (ii == 6) {//plz
                    Log.i("click", "plz: " + newString);

                    plz = newString;
                }

                if (ii == 7) {//geburtstag
                    Log.i("click", "geburtstag: " + newString);

                    geburtstag = newString;
                }

                if (ii == 8) {//telefon
                    Log.i("click", "telefon: " + newString);

                    telefon = newString;
                }

                if (ii == 9) {//email
                    Log.i("click", "email: " + newString);

                    email = newString;
                }



            }

            Log.i("click", "----------------------------------");


            d.createKollege(vorname, nachname, personaltyp, geburtstag, strasse, plz, telefon, email, standort, passwort);

           /* values.put(MyDbHelper.KOLLEGE_COLUMN_VORNAME, v1);
            values.put(MyDbHelper.KOLLEGE_COLUMN_NACHNAME, v2);
            values.put(MyDbHelper.KOLLEGE_COLUMN_PERSONALTYP, v3);
            values.put(MyDbHelper.KOLLEGE_COLUMN_GEBURTSTAG, v4);
            values.put(MyDbHelper.KOLLEGE_COLUMN_STRASSE, v5);*/


        }

        d.close();
    }



    public void fillKollege2(Context context) {
        String fileName = "hallo-mein-benno.xls";
        InputStream is = null;
        AssetManager am = context.getAssets();
        try {
            is = am.open(fileName);


            File file = new File(context.getExternalFilesDir(null), fileName);


            // commons-io
       // FileUtils.copyInputStreamToFile(file);
            FileUtils.copyInputStreamToFile(is, file);

            // Creating Input Stream

            FileInputStream myInput = new FileInputStream(file);

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            /** We now need something to iterate through the cells.**/
            Iterator rowIter = mySheet.rowIterator();

            while(rowIter.hasNext()){
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                while(cellIter.hasNext()){
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    Log.d("benno", "Cell Value: " +  myCell.getColumnIndex()+ " "+myCell.getRowIndex());
                    Toast.makeText(context, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){e.printStackTrace(); }

        return;







    }

    public void convertKollege(Context context) {




        String nnn;
        String[] tempArray;

        InputStream is = null;
        AssetManager am = context.getAssets();
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




        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("kollege");
        Row row = sheet1.createRow(0);

        c = row.createCell(0);
        c.setCellValue("id");

        c = row.createCell(1);
        c.setCellValue("vorname");

        c = row.createCell(2);
        c.setCellValue("nachname");

        c = row.createCell(3);
        c.setCellValue("personaltyp");

        c = row.createCell(4);
        c.setCellValue("geburtstag");

        c = row.createCell(5);
        c.setCellValue("strasse");

        c = row.createCell(6);
        c.setCellValue("plz");

        c = row.createCell(7);
        c.setCellValue("telefon");

        c = row.createCell(8);
        c.setCellValue("email");

        c = row.createCell(9);
        c.setCellValue("standort");

        c = row.createCell(10);
        c.setCellValue("passwort");

        for (int i = 0; i < tempArray.length; i++) {
            //Log.i("click", tempArray[i]);

            String vorname1 = null;
            String nachname2 = null;
            String personaltyp3 = null;
            String geburtstag4 = null;
            String strasse5 = null;
            String plz6 = null;
            String telefon7 = null;
            String email8 = null;
            String standort9 = null;
            String passwort10 = null;

            String[] tempTempArray;
            String deDelimiter = ",";

            tempTempArray = tempArray[i].split(deDelimiter);


            // Generate column headings
            row = sheet1.createRow(i+1);




            for (int ii = 0; ii < tempTempArray.length; ii++) {

                String newString = tempTempArray[ii].replaceAll("[\u0000-\u001f]", "");

                if (ii == 0) {// Nachname
                    Log.i("click", "Nachname: " + newString);
                    nachname2 = newString;


                }
                if (ii == 1) {//Vorname
                    Log.i("click", "Vorname: " + newString);
                    vorname1 = newString;


                }
                if (ii == 2) {//Personaltyp
                    Log.i("click", "personaltyp: " + newString);
                    personaltyp3 = newString;


                }
                if (ii == 3) {//Standort
                    Log.i("click", "standort: " + newString);
                    standort9 = newString;


                }

                if (ii == 4) {//passwort
                    Log.i("click", "passwort: " + newString);
                    passwort10 = newString;


                }

                if (ii == 5) {//strasse
                    Log.i("click", "strasse: " + newString);
                    strasse5 = newString;


                }
                if (ii == 6) {//plz
                    Log.i("click", "plz: " + newString);
                    plz6 = newString;


                }

                if (ii == 7) {//geburtstag
                    Log.i("click", "geburtstag: " + newString);
                    geburtstag4 = newString;


                }

                if (ii == 8) {//telefon
                    Log.i("click", "telefon: " + newString);
                    telefon7 = newString;


                }

                if (ii == 9) {//email
                    Log.i("click", "email: " + newString);
                    email8 = newString;


                }


            }





            c = row.createCell(0);
            c.setCellValue(Integer.toString(i+1));

            c = row.createCell(1);
            c.setCellValue(vorname1);

            c = row.createCell(2);
            c.setCellValue(nachname2);

            c = row.createCell(3);
            c.setCellValue(personaltyp3);

            c = row.createCell(4);
            c.setCellValue(geburtstag4);

            c = row.createCell(5);
            c.setCellValue(strasse5);

            c = row.createCell(6);
            c.setCellValue(plz6);

            c = row.createCell(7);
            c.setCellValue(telefon7);

            c = row.createCell(8);
            c.setCellValue(email8);

            c = row.createCell(9);
            c.setCellValue(standort9);

            c = row.createCell(10);
            c.setCellValue(passwort10);




        }


        sheet1.setColumnWidth(0, (15 * 150));
        sheet1.setColumnWidth(1, (15 * 350));
        sheet1.setColumnWidth(2, (15 * 350));
        sheet1.setColumnWidth(3, (15 * 350));
        sheet1.setColumnWidth(4, (15 * 350));
        sheet1.setColumnWidth(5, (15 * 350));
        sheet1.setColumnWidth(6, (15 * 350));
        sheet1.setColumnWidth(7, (15 * 350));
        sheet1.setColumnWidth(8, (15 * 350));
        sheet1.setColumnWidth(9, (15 * 350));
        sheet1.setColumnWidth(10, (15 * 350));
        sheet1.setColumnWidth(11, (15 * 350));


        File file = new File(context.getExternalFilesDir(null), "kollege.xls");
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Writing file" + file);

        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }





    }



    public static boolean saveExcelFile(Context context, String fileName) {

        // check if available and not read only


        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        //CellStyle cs = wb.createCellStyle();
        //cs.setFillForegroundColor(HSSFColor.LIME.index);
       // cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("myOrder");

        // Generate column headings
        Row row = sheet1.createRow(0);

        c = row.createCell(0);
        c.setCellValue("Item Number");
        //c.setCellStyle(cs);

        c = row.createCell(1);
        c.setCellValue("Quantity");
        //c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("Price");
       // c.setCellStyle(cs);

       // sheet1.setColumnWidth(0, (15 * 500));
        //sheet1.setColumnWidth(1, (15 * 500));
       // sheet1.setColumnWidth(2, (15 * 500));

        // Create a path where we will place our List of objects on external storage
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Writing file" + file);
            success = true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }
        return success;
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