package akguen.liquidschool.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Kollege;
import akguen.liquidschool.db.model.Standort;


public class DataSource_Kollege_Standort {

    private static final String LOG_TAG = DataSource_Kollege_Standort.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.KSTA_KOLLEGE_ID,
            MyDbHelper.KSTA_STANDORT_ID,
    };


    private String[] kollegeColumns = {
            MyDbHelper.KOLLEGE_COLUMN_ID,
            MyDbHelper.KOLLEGE_COLUMN_VORNAME,
            MyDbHelper.KOLLEGE_COLUMN_NACHNAME,
            MyDbHelper.KOLLEGE_COLUMN_PASSWORT,
            MyDbHelper.KOLLEGE_COLUMN_KUERZEL,
            MyDbHelper.KOLLEGE_COLUMN_STATUS
            
            
    };

    private String[] standortColumns = {
            MyDbHelper.STANDORT_COLUMN_ID,
            MyDbHelper.STANDORT_COLUMN_NAME,
            MyDbHelper.STANDORT_COLUMN_KONTAKTPERSON,
            MyDbHelper.STANDORT_COLUMN_STANDORTEMAIL,
            MyDbHelper.STANDORT_COLUMN_HAUPTSTANDORT,
            MyDbHelper.STANDORT_COLUMN_STADTTEIL,
            MyDbHelper.STANDORT_COLUMN_STATUS,
            MyDbHelper.STANDORT_COLUMN_SERVERADRESSE
    };




    public DataSource_Kollege_Standort(Context context) {
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

    public void createKollege_Standort(ContentValues v) {


        database.insert(MyDbHelper.TABLE_KOLLEGE_STANDORT, null, v);


    }

    public void deleteKollege_Standort(ContentValues v) {


        database.delete(MyDbHelper.TABLE_KOLLEGE_STANDORT,
                MyDbHelper.KSTA_KOLLEGE_ID + " = " + v.getAsString(MyDbHelper.KSTA_KOLLEGE_ID) + " AND " + MyDbHelper.KSTA_STANDORT_ID + " = " + v.getAsString(MyDbHelper.KSTA_STANDORT_ID),
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + v.getAsString(MyDbHelper.KSTA_KOLLEGE_ID) + "---" + v.getAsString(MyDbHelper.KSTA_STANDORT_ID));
    }

    public ContentValues updateKollege_Standort(ContentValues old, ContentValues nu) {

        ContentValues values = new ContentValues();
        values.put(MyDbHelper.KSTA_KOLLEGE_ID, nu.getAsString(MyDbHelper.KSTA_KOLLEGE_ID));
        values.put(MyDbHelper.KSTA_STANDORT_ID, nu.getAsString(MyDbHelper.KSTA_STANDORT_ID));


        database.update(MyDbHelper.TABLE_KOLLEGE_STANDORT,
                values,
                MyDbHelper.KSTA_KOLLEGE_ID + " = " + old.getAsString(MyDbHelper.KSTA_KOLLEGE_ID) + " AND " + MyDbHelper.KSTA_STANDORT_ID + " = " + old.getAsString(MyDbHelper.KSTA_STANDORT_ID),
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE_STANDORT,
                columns, MyDbHelper.KSTA_KOLLEGE_ID  + " = " + nu.getAsString(MyDbHelper.KSTA_KOLLEGE_ID ) + " AND " + MyDbHelper.KSTA_STANDORT_ID+ " = " + nu.getAsString(MyDbHelper.KSTA_STANDORT_ID),
                null, null, null, null);

        cursor.moveToFirst();
        ContentValues coco = cursorToKollege_StandortAsContentValue(cursor);
        cursor.close();

        return coco;

    }


    private ContentValues cursorToKollege_StandortAsContentValue(Cursor cursor) {

        ContentValues values = new ContentValues();


        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id1 = cursor.getColumnIndex(MyDbHelper.KSTA_KOLLEGE_ID);
        int id2 = cursor.getColumnIndex(MyDbHelper.KSTA_STANDORT_ID);


        ;
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);

        values.put(MyDbHelper.KSTA_KOLLEGE_ID, q1);
        values.put(MyDbHelper.KSTA_STANDORT_ID, q2);

        // Kollege_Standort kollege_standort = new Kollege_Standort(id,vorname,nachname,passwort,kuerzel,status);

        return values;
    }

    public List<ContentValues> getAllKollege_Standorts() {
        List<ContentValues> kollege_standortList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE_STANDORT,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        ContentValues kollege_standort;

        while (!cursor.isAfterLast()) {
            kollege_standort = cursorToKollege_StandortAsContentValue(cursor);
            kollege_standortList.add(kollege_standort);
            Log.d(LOG_TAG, "ID: " + kollege_standort.toString());
            cursor.moveToNext();
        }


        cursor.close();

        return kollege_standortList;
    }

    private Standort cursorToStandort(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.STANDORT_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.STANDORT_COLUMN_NAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.STANDORT_COLUMN_KONTAKTPERSON);
        int id3 = cursor.getColumnIndex(MyDbHelper.STANDORT_COLUMN_STANDORTEMAIL);
        int id4 = cursor.getColumnIndex(MyDbHelper.STANDORT_COLUMN_HAUPTSTANDORT);
        int id5 = cursor.getColumnIndex(MyDbHelper.STANDORT_COLUMN_STADTTEIL);
        int id6 = cursor.getColumnIndex(MyDbHelper.STANDORT_COLUMN_STATUS);
        int id7 = cursor.getColumnIndex(MyDbHelper.STANDORT_COLUMN_SERVERADRESSE);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);
        String q6 = cursor.getString(id6);
        String q7 = cursor.getString(id7);

        // Standort standort = new Standort(id,vorname,nachname,passwort,kuerzel,status);

        return new Standort(id,q1,q2,q3,q4,q5,q6,q7);
    }



    private Kollege cursorToKollege(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_VORNAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_NACHNAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_PASSWORT);
        int id4 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_KUERZEL);
        int id5 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_STATUS);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);

        // Standort standort = new Standort(id,vorname,nachname,passwort,kuerzel,status);
      //  int id, String vorname, String nachname, String rufname, String geschlecht, String status, String geburtstag, String geburtsort
        return new Kollege(id,q1,q2,q3,q4,q5);
    }








    public List<Standort> getStandortsFromKollegeById(int i) {

        List<Standort> standortList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_STANDORT+ " LEFT JOIN " + MyDbHelper.TABLE_KOLLEGE_STANDORT + " ON " + MyDbHelper.TABLE_KOLLEGE_STANDORT + "."
                        + MyDbHelper.KSTA_STANDORT_ID + " = " + MyDbHelper.STANDORT_COLUMN_ID,
                standortColumns,
                 MyDbHelper.TABLE_KOLLEGE_STANDORT + "." + MyDbHelper.KSTA_KOLLEGE_ID + " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        Standort standort;

        while (!cursor.isAfterLast()) {
            standort = cursorToStandort(cursor);
            standortList.add(standort);
            //Log.d(LOG_TAG, "ID: " + standort);
            cursor.moveToNext();
        }


        cursor.close();


        return standortList;
    }




    public List<Kollege> getKollegesFromStandortById(int i) {

        List<Kollege> kollegeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE+ " LEFT JOIN " + MyDbHelper.TABLE_KOLLEGE_STANDORT + " ON " + MyDbHelper.TABLE_KOLLEGE_STANDORT + "."
                        + MyDbHelper.KSTA_KOLLEGE_ID + " = " + MyDbHelper.KOLLEGE_COLUMN_ID,
                kollegeColumns,
                MyDbHelper.TABLE_KOLLEGE_STANDORT + "." + MyDbHelper.KSTA_STANDORT_ID + " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        Kollege kollege;

        while (!cursor.isAfterLast()) {
            kollege = cursorToKollege(cursor);
            kollegeList.add(kollege);
            //Log.d(LOG_TAG, "ID: " + standort);
            cursor.moveToNext();
        }

        cursor.close();

        return kollegeList;
    }







}