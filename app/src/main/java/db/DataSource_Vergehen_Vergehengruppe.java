package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Vergehengruppe;
import model.Vergehen;


public class DataSource_Vergehen_Vergehengruppe {

    private static final String LOG_TAG = DataSource_Vergehen_Vergehengruppe.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SL_VERGEHEN_ID,
            MyDbHelper.SL_VERGEHENGRUPPE_ID,
    };


    private String[] vergehengruppeColumns = {
            MyDbHelper.VERGEHENGRUPPE_COLUMN_ID,
            MyDbHelper.VERGEHENGRUPPE_COLUMN_NAME
    };

    private String[] vergehenColumns = {
            MyDbHelper.VERGEHEN_COLUMN_ID,
            MyDbHelper.VERGEHEN_COLUMN_NAME,
            MyDbHelper.VERGEHEN_COLUMN_TEXT,
            MyDbHelper.VERGEHEN_COLUMN_GEWICHT,
            MyDbHelper.VERGEHEN_COLUMN_KATEGORIE
    };




    public DataSource_Vergehen_Vergehengruppe(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new MyDbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public void createVergehen_Vergehengruppe(ContentValues v) {


        database.insert(MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE, null, v);


    }

    public void deleteVergehen_Vergehengruppe(ContentValues v) {


        database.delete(MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE,
                MyDbHelper.SL_VERGEHEN_ID + " = " + v.getAsString("vergehen_id") + " AND " + MyDbHelper.SL_VERGEHENGRUPPE_ID + " = " + v.getAsString("vergehengruppe_id"),
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + v.getAsString("vergehen_id") + "---" + v.getAsString("vergehengruppe_id"));
    }

    public ContentValues updateVergehen_Vergehengruppe(ContentValues old, ContentValues nu) {

        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SL_VERGEHEN_ID, nu.getAsString("vergehen_id"));
        values.put(MyDbHelper.SL_VERGEHENGRUPPE_ID, nu.getAsString("vergehengruppe_id"));


        database.update(MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE,
                values,
                MyDbHelper.SL_VERGEHEN_ID + " = " + old.getAsString("vergehen_id") + " AND " + MyDbHelper.SL_VERGEHENGRUPPE_ID + " = " + old.getAsString("vergehengruppe_id"),
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE,
                columns, MyDbHelper.SL_VERGEHEN_ID + " = " + nu.getAsString("vergehen_id") + " AND " + MyDbHelper.SL_VERGEHENGRUPPE_ID + " = " + nu.getAsString("vergehengruppe_id"),
                null, null, null, null);

        cursor.moveToFirst();
        ContentValues coco = cursorToVergehen_VergehengruppeAsContentValue(cursor);
        cursor.close();

        return coco;

    }


    private ContentValues cursorToVergehen_VergehengruppeAsContentValue(Cursor cursor) {

        ContentValues values = new ContentValues();


        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id1 = cursor.getColumnIndex(MyDbHelper.SL_VERGEHEN_ID);
        int id2 = cursor.getColumnIndex(MyDbHelper.SL_VERGEHENGRUPPE_ID);


        ;
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);

        values.put("vergehen_id", q1);
        values.put("vergehengruppe_id", q2);

        // Vergehen_Vergehengruppe vergehen_vergehengruppe = new Vergehen_Vergehengruppe(id,vorname,nachname,passwort,kuerzel,status);

        return values;
    }

    public List<ContentValues> getAllVergehen_Vergehengruppes() {
        List<ContentValues> vergehen_vergehengruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        ContentValues vergehen_vergehengruppe;

        while (!cursor.isAfterLast()) {
            vergehen_vergehengruppe = cursorToVergehen_VergehengruppeAsContentValue(cursor);
            vergehen_vergehengruppeList.add(vergehen_vergehengruppe);
            Log.d(LOG_TAG, "ID: " + vergehen_vergehengruppe.toString());
            cursor.moveToNext();
        }


        cursor.close();

        return vergehen_vergehengruppeList;
    }

    private Vergehengruppe cursorToVergehengruppe(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.VERGEHENGRUPPE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.VERGEHENGRUPPE_COLUMN_NAME);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);


        // Vergehengruppe vergehengruppe = new Vergehengruppe(id,vorname,nachname,passwort,kuerzel,status);

        return new Vergehengruppe(id,q1);
    }



    private Vergehen cursorToVergehen(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_NAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_TEXT);
        int id3 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_GEWICHT);
        int id4 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_KATEGORIE);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);



      //  int id, String vorname, String nachname, String rufname, String geschlecht, String status, String geburtstag, String geburtsort
        return new Vergehen(id,q1,q2,q3,q4);
    }








    public List<Vergehengruppe> getVergehengruppesFromVergehenById(int i) {

        List<Vergehengruppe> vergehengruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHENGRUPPE+ " LEFT JOIN " + MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE + " ON " + MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE + "."
                        + MyDbHelper.SL_VERGEHENGRUPPE_ID + " = " + MyDbHelper.VERGEHENGRUPPE_COLUMN_ID,
                vergehengruppeColumns,
                 MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE + "." + MyDbHelper.SL_VERGEHEN_ID + " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        Vergehengruppe vergehengruppe;

        while (!cursor.isAfterLast()) {
            vergehengruppe = cursorToVergehengruppe(cursor);
            vergehengruppeList.add(vergehengruppe);
            //Log.d(LOG_TAG, "ID: " + vergehengruppe);
            cursor.moveToNext();
        }


        cursor.close();


        return vergehengruppeList;
    }




    public List<Vergehen> getVergehensFromVergehengruppeById(int i) {
        Log.d(LOG_TAG, "ID::::::::::::::::::: " + i);
        List<Vergehen> vergehenList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHEN+ " LEFT JOIN " + MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE + " ON " + MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE + "."
                        + MyDbHelper.SL_VERGEHEN_ID + " = " + MyDbHelper.VERGEHEN_COLUMN_ID,
                vergehenColumns,
                MyDbHelper.TABLE_VERGEHEN_VERGEHENGRUPPE + "." + MyDbHelper.SL_VERGEHENGRUPPE_ID + " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        Vergehen vergehen;

        while (!cursor.isAfterLast()) {
            vergehen = cursorToVergehen(cursor);
            vergehenList.add(vergehen);
            //Log.d(LOG_TAG, "ID: " + vergehengruppe);
            cursor.moveToNext();
        }

        cursor.close();

        return vergehenList;
    }







}