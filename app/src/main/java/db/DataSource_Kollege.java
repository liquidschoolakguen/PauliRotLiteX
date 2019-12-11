package db;
/*

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Kollege;

public class DataSource_Kollege {

    private static final String LOG_TAG = DataSource_Kollege.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.KOLLEGE_COLUMN_ID,
            MyDbHelper.KOLLEGE_COLUMN_VORNAME,
            MyDbHelper.KOLLEGE_COLUMN_NACHNAME,
            MyDbHelper.KOLLEGE_COLUMN_PASSWORT,
            MyDbHelper.KOLLEGE_COLUMN_KUERZEL,
            MyDbHelper.KOLLEGE_COLUMN_STATUS
    };

    public DataSource_Kollege(Context context) {
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

    public Kollege createKollege(String v1, String v2, String v3, String v4, String v5) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.KOLLEGE_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.KOLLEGE_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.KOLLEGE_COLUMN_PASSWORT, v3);
        values.put(MyDbHelper.KOLLEGE_COLUMN_KUERZEL, v4);
        values.put(MyDbHelper.KOLLEGE_COLUMN_STATUS, v5);

        long insertId = database.insert(MyDbHelper.TABLE_KOLLEGE, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE,
                columns, MyDbHelper.KOLLEGE_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Kollege kollege = cursorToKollege(cursor);
        cursor.close();

        return kollege;
    }

    public void deleteKollege(Kollege kollege) {
        long id = kollege.getId();

        database.delete(MyDbHelper.TABLE_KOLLEGE,
                MyDbHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + kollege.toString());
    }

    public Kollege updateKollege(int id, String v1, String v2, String v3, String v4, String v5) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.KOLLEGE_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.KOLLEGE_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.KOLLEGE_COLUMN_PASSWORT, v3);
        values.put(MyDbHelper.KOLLEGE_COLUMN_KUERZEL, v4);
        values.put(MyDbHelper.KOLLEGE_COLUMN_STATUS, v5);

        database.update(MyDbHelper.TABLE_KOLLEGE,
                values,
                MyDbHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE,
                columns, MyDbHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Kollege kollege = cursorToKollege(cursor);
        cursor.close();

        return kollege;
    }

    public Kollege getKollegeById(int id) {


        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE,
                columns, MyDbHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Kollege kollege = cursorToKollege(cursor);
        cursor.close();

        return kollege;


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




            //Log.d(LOG_TAG, "cursor index: "+ id0);
            int id = cursor.getInt(id0);
            String q1 = cursor.getString(id1);
            String q2 = cursor.getString(id2);
            String q3 = cursor.getString(id3);
            String q4 = cursor.getString(id4);
            String q5 = cursor.getString(id5);


            // Kollege kollege = new Kollege(id,vorname,nachname,passwort,kuerzel,status);

            return new Kollege(id, q1, q2, q3, q4, q5);



    }

    public List<Kollege> getAllKolleges() {
        List<Kollege> kollegeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Kollege kollege;

        while (!cursor.isAfterLast()) {
            kollege = cursorToKollege(cursor);
            kollegeList.add(kollege);
            Log.d(LOG_TAG, "ID: " + kollege.getId() + ", Inhalt: " + kollege.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return kollegeList;
    }

}*/
