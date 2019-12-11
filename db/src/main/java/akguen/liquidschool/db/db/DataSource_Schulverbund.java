package akguen.liquidschool.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Schulverbund;

public class DataSource_Schulverbund {

    private static final String LOG_TAG = DataSource_Schulverbund.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SCHULVERBUND_COLUMN_ID,
            MyDbHelper.SCHULVERBUND_COLUMN_NAME,
            MyDbHelper.SCHULVERBUND_COLUMN_TRAEGER,
            MyDbHelper.SCHULVERBUND_COLUMN_BUNDESLAND,
            MyDbHelper.SCHULVERBUND_COLUMN_BEZIRK,
            MyDbHelper.SCHULVERBUND_COLUMN_TYP,
            MyDbHelper.SCHULVERBUND_COLUMN_EMAIL,
            MyDbHelper.SCHULVERBUND_COLUMN_WWW,
            MyDbHelper.SCHULVERBUND_COLUMN_SCHUELERZAHL,
            MyDbHelper.SCHULVERBUND_COLUMN_LEITUNG,
            MyDbHelper.SCHULVERBUND_COLUMN_STATUS,
            MyDbHelper.SCHULVERBUND_COLUMN_MAINSERVERADRESS


    };

    public DataSource_Schulverbund(Context context) {
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

    public Schulverbund createSchulverbund(String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8, String v9, String v10, String v11) {
        ContentValues values = new ContentValues();


        values.put(MyDbHelper.SCHULVERBUND_COLUMN_NAME, v1);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_TRAEGER, v2);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_BUNDESLAND, v3);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_BEZIRK, v4);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_TYP, v5);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_EMAIL, v6);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_WWW, v7);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_SCHUELERZAHL, v8);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_LEITUNG, v9);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_STATUS, v10);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_MAINSERVERADRESS, v11);

        long insertId = database.insert(MyDbHelper.TABLE_SCHULVERBUND, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHULVERBUND,
                columns, MyDbHelper.SCHULVERBUND_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Schulverbund schulverbund = cursorToSchulverbund(cursor);
        cursor.close();

        return schulverbund;
    }

    public void deleteSchulverbund(Schulverbund schulverbund) {
        long id = schulverbund.getId();

        database.delete(MyDbHelper.TABLE_SCHULVERBUND,
                MyDbHelper.SCHULVERBUND_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + schulverbund.toString());
    }

    public Schulverbund updateSchulverbund(int id, String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8, String v9, String v10, String v11) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_NAME, v1);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_TRAEGER, v2);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_BUNDESLAND, v3);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_BEZIRK, v4);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_TYP, v5);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_EMAIL, v6);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_WWW, v7);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_SCHUELERZAHL, v8);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_LEITUNG, v9);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_STATUS, v10);
        values.put(MyDbHelper.SCHULVERBUND_COLUMN_MAINSERVERADRESS, v11);

        database.update(MyDbHelper.TABLE_SCHULVERBUND,
                values,
                MyDbHelper.SCHULVERBUND_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHULVERBUND,
                columns, MyDbHelper.SCHULVERBUND_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Schulverbund schulverbund = cursorToSchulverbund(cursor);
        cursor.close();

        return schulverbund;
    }
    public Schulverbund getSchulverbundById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_SCHULVERBUND,
                columns, MyDbHelper.SCHULVERBUND_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Schulverbund schulverbund = cursorToSchulverbund(cursor);
        cursor.close();

        return schulverbund;


    }
    private Schulverbund cursorToSchulverbund(Cursor cursor) {


        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_NAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_TRAEGER);
        int id3 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_BUNDESLAND);
        int id4 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_BEZIRK);
        int id5 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_TYP);
        int id6 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_EMAIL);
        int id7 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_WWW);
        int id8 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_SCHUELERZAHL);
        int id9 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_LEITUNG);
        int id10 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_STATUS);
        int id11 = cursor.getColumnIndex(MyDbHelper.SCHULVERBUND_COLUMN_MAINSERVERADRESS);



        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);
        String q6 = cursor.getString(id6);
        String q7 = cursor.getString(id7);
        String q8 = cursor.getString(id8);
        String q9 = cursor.getString(id9);
        String q10 = cursor.getString(id10);
        String q11 = cursor.getString(id11);



       // Schulverbund schulverbund = new Schulverbund(id,vorname,nachname,passwort,kuerzel,status);

        return new Schulverbund(id, q1, q2, q3 ,q4, q5, q6, q7, q8, q9, q10, q11);
    }

    public List<Schulverbund> getAllSchulverbunds() {
        List<Schulverbund> schulverbundList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHULVERBUND,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Schulverbund schulverbund;

        while(!cursor.isAfterLast()) {
            schulverbund = cursorToSchulverbund(cursor);
            schulverbundList.add(schulverbund);
            Log.d(LOG_TAG, "ID: " + schulverbund.getId() + ", Inhalt: " + schulverbund.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return schulverbundList;
    }

}