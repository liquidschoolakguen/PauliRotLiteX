package akguen.liquidschool.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Standort;

public class DataSource_Standort {

    private static final String LOG_TAG = DataSource_Standort.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.STANDORT_COLUMN_ID,
            MyDbHelper.STANDORT_COLUMN_NAME,
            MyDbHelper.STANDORT_COLUMN_KONTAKTPERSON,
            MyDbHelper.STANDORT_COLUMN_STANDORTEMAIL,
            MyDbHelper.STANDORT_COLUMN_HAUPTSTANDORT,
            MyDbHelper.STANDORT_COLUMN_STADTTEIL,
            MyDbHelper.STANDORT_COLUMN_STATUS,
            MyDbHelper.STANDORT_COLUMN_SERVERADRESSE


    };

    public DataSource_Standort(Context context) {
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

    public Standort createStandort(String v1, String v2, String v3, String v4, String v5, String v6, String v7) {
        ContentValues values = new ContentValues();

        values.put(MyDbHelper.STANDORT_COLUMN_NAME, v1);
        values.put(MyDbHelper.STANDORT_COLUMN_KONTAKTPERSON, v2);
        values.put(MyDbHelper.STANDORT_COLUMN_STANDORTEMAIL, v3);
        values.put(MyDbHelper.STANDORT_COLUMN_HAUPTSTANDORT, v4);
        values.put(MyDbHelper.STANDORT_COLUMN_STADTTEIL, v5);
        values.put(MyDbHelper.STANDORT_COLUMN_STATUS, v6);
        values.put(MyDbHelper.STANDORT_COLUMN_SERVERADRESSE, v7);


        long insertId = database.insert(MyDbHelper.TABLE_STANDORT, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_STANDORT,
                columns, MyDbHelper.STANDORT_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Standort standort = cursorToStandort(cursor);
        cursor.close();

        return standort;
    }

    public void deleteStandort(Standort standort) {
        long id = standort.getId();

        database.delete(MyDbHelper.TABLE_STANDORT,
                MyDbHelper.STANDORT_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + standort.toString());
    }

    public Standort updateStandort(int id, String v1, String v2, String v3, String v4, String v5, String v6, String v7) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.STANDORT_COLUMN_NAME, v1);
        values.put(MyDbHelper.STANDORT_COLUMN_KONTAKTPERSON, v2);
        values.put(MyDbHelper.STANDORT_COLUMN_STANDORTEMAIL, v3);
        values.put(MyDbHelper.STANDORT_COLUMN_HAUPTSTANDORT, v4);
        values.put(MyDbHelper.STANDORT_COLUMN_STADTTEIL, v5);
        values.put(MyDbHelper.STANDORT_COLUMN_STATUS, v6);
        values.put(MyDbHelper.STANDORT_COLUMN_SERVERADRESSE, v7);

        database.update(MyDbHelper.TABLE_STANDORT,
                values,
                MyDbHelper.STANDORT_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_STANDORT,
                columns, MyDbHelper.STANDORT_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Standort standort = cursorToStandort(cursor);
        cursor.close();

        return standort;
    }
    public Standort getStandortById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_STANDORT,
                columns, MyDbHelper.STANDORT_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Standort standort = cursorToStandort(cursor);
        cursor.close();

        return standort;


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

        return new Standort(id, q1, q2, q3 ,q4, q5, q6, q7);
    }

    public List<Standort> getAllStandorts() {
        List<Standort> standortList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_STANDORT,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Standort standort;

        while(!cursor.isAfterLast()) {
            standort = cursorToStandort(cursor);
            standortList.add(standort);
            Log.d(LOG_TAG, "ID: " + standort.getId() + ", Inhalt: " + standort.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return standortList;
    }

}