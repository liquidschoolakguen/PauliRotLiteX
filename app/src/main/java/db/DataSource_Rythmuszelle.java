package db;

/*
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Rythmuszelle;

public class DataSource_Rythmuszelle {

    private static final String LOG_TAG = DataSource_Rythmuszelle.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.RYTHMUSZELLE_COLUMN_ID,
            MyDbHelper.RYTHMUSZELLE_COLUMN_WOCHENTAG,
            MyDbHelper.RYTHMUSZELLE_COLUMN_TYP,
            MyDbHelper.RYTHMUSZELLE_COLUMN_NUMMER,
            MyDbHelper.RYTHMUSZELLE_COLUMN_VON,
            MyDbHelper.RYTHMUSZELLE_COLUMN_BIS
    };

    public DataSource_Rythmuszelle(Context context) {
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

    public Rythmuszelle createRythmuszelle(String v1, String v2, String v3, String v4, String v5) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_WOCHENTAG, v1);
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_TYP, v2);
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_NUMMER, v3);
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_VON, v4);
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_BIS, v5);


        long insertId = database.insert(MyDbHelper.TABLE_RYTHMUSZELLE, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_RYTHMUSZELLE,
                columns, MyDbHelper.RYTHMUSZELLE_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Rythmuszelle rythmuszelle = cursorToRythmuszelle(cursor);
        cursor.close();

        return rythmuszelle;
    }

    public void deleteRythmuszelle(Rythmuszelle rythmuszelle) {
        long id = rythmuszelle.getId();

        database.delete(MyDbHelper.TABLE_RYTHMUSZELLE,
                MyDbHelper.RYTHMUSZELLE_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + rythmuszelle.toString());
    }

    public Rythmuszelle updateRythmuszelle(int id, String v1, String v2, String v3, String v4, String v5) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_WOCHENTAG, v1);
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_TYP, v2);
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_NUMMER, v3);
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_VON, v4);
        values.put(MyDbHelper.RYTHMUSZELLE_COLUMN_BIS, v5);


        database.update(MyDbHelper.TABLE_RYTHMUSZELLE,
                values,
                MyDbHelper.RYTHMUSZELLE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_RYTHMUSZELLE,
                columns, MyDbHelper.RYTHMUSZELLE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Rythmuszelle rythmuszelle = cursorToRythmuszelle(cursor);
        cursor.close();

        return rythmuszelle;
    }
    public Rythmuszelle getRythmuszelleById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_RYTHMUSZELLE,
                columns, MyDbHelper.RYTHMUSZELLE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Rythmuszelle rythmuszelle = cursorToRythmuszelle(cursor);
        cursor.close();

        return rythmuszelle;


    }
    private Rythmuszelle cursorToRythmuszelle(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.RYTHMUSZELLE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.RYTHMUSZELLE_COLUMN_WOCHENTAG);
        int id2 = cursor.getColumnIndex(MyDbHelper.RYTHMUSZELLE_COLUMN_TYP);
        int id3 = cursor.getColumnIndex(MyDbHelper.RYTHMUSZELLE_COLUMN_NUMMER);
        int id4 = cursor.getColumnIndex(MyDbHelper.RYTHMUSZELLE_COLUMN_VON);
        int id5 = cursor.getColumnIndex(MyDbHelper.RYTHMUSZELLE_COLUMN_BIS);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);





        return new Rythmuszelle(id,q1,q2,q3,q4,q5);
    }

    public List<Rythmuszelle> getAllRythmuszelles() {
        List<Rythmuszelle> rythmuszelleList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_RYTHMUSZELLE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Rythmuszelle rythmuszelle;

        while(!cursor.isAfterLast()) {
            rythmuszelle = cursorToRythmuszelle(cursor);
            rythmuszelleList.add(rythmuszelle);
            Log.d(LOG_TAG, "ID: " + rythmuszelle.getId() + ", Inhalt: " + rythmuszelle.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return rythmuszelleList;
    }

}*/
