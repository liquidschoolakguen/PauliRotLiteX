package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Rhythmuszelle;

public class DataSource_Rhythmuszelle {

    private static final String LOG_TAG = DataSource_Rhythmuszelle.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.RHYTHMUSZELLE_COLUMN_ID,
            MyDbHelper.RHYTHMUSZELLE_COLUMN_WOCHENTAG,
            MyDbHelper.RHYTHMUSZELLE_COLUMN_TYP,
            MyDbHelper.RHYTHMUSZELLE_COLUMN_NUMMER,
            MyDbHelper.RHYTHMUSZELLE_COLUMN_VON,
            MyDbHelper.RHYTHMUSZELLE_COLUMN_BIS
    };

    public DataSource_Rhythmuszelle(Context context) {
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

    public Rhythmuszelle createRhythmuszelle(String v1, String v2, String v3, String v4, String v5) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_WOCHENTAG, v1);
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_TYP, v2);
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_NUMMER, v3);
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_VON, v4);
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_BIS, v5);


        long insertId = database.insert(MyDbHelper.TABLE_RHYTHMUSZELLE, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_RHYTHMUSZELLE,
                columns, MyDbHelper.RHYTHMUSZELLE_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Rhythmuszelle rhythmuszelle = cursorToRhythmuszelle(cursor);
        cursor.close();

        return rhythmuszelle;
    }

    public void deleteRhythmuszelle(Rhythmuszelle rhythmuszelle) {
        long id = rhythmuszelle.getId();

        database.delete(MyDbHelper.TABLE_RHYTHMUSZELLE,
                MyDbHelper.RHYTHMUSZELLE_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + rhythmuszelle.toString());
    }

    public Rhythmuszelle updateRhythmuszelle(int id, String v1, String v2, String v3, String v4, String v5) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_WOCHENTAG, v1);
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_TYP, v2);
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_NUMMER, v3);
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_VON, v4);
        values.put(MyDbHelper.RHYTHMUSZELLE_COLUMN_BIS, v5);


        database.update(MyDbHelper.TABLE_RHYTHMUSZELLE,
                values,
                MyDbHelper.RHYTHMUSZELLE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_RHYTHMUSZELLE,
                columns, MyDbHelper.RHYTHMUSZELLE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Rhythmuszelle rhythmuszelle = cursorToRhythmuszelle(cursor);
        cursor.close();

        return rhythmuszelle;
    }
    public Rhythmuszelle getRhythmuszelleById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_RHYTHMUSZELLE,
                columns, MyDbHelper.RHYTHMUSZELLE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Rhythmuszelle rhythmuszelle = cursorToRhythmuszelle(cursor);
        cursor.close();

        return rhythmuszelle;


    }
    private Rhythmuszelle cursorToRhythmuszelle(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.RHYTHMUSZELLE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.RHYTHMUSZELLE_COLUMN_WOCHENTAG);
        int id2 = cursor.getColumnIndex(MyDbHelper.RHYTHMUSZELLE_COLUMN_TYP);
        int id3 = cursor.getColumnIndex(MyDbHelper.RHYTHMUSZELLE_COLUMN_NUMMER);
        int id4 = cursor.getColumnIndex(MyDbHelper.RHYTHMUSZELLE_COLUMN_VON);
        int id5 = cursor.getColumnIndex(MyDbHelper.RHYTHMUSZELLE_COLUMN_BIS);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);





        return new Rhythmuszelle(id,q1,q2,q3,q4,q5);
    }

    public List<Rhythmuszelle> getAllRhythmuszelles() {
        List<Rhythmuszelle> rhythmuszelleList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_RHYTHMUSZELLE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Rhythmuszelle rhythmuszelle;

        while(!cursor.isAfterLast()) {
            rhythmuszelle = cursorToRhythmuszelle(cursor);
            rhythmuszelleList.add(rhythmuszelle);
            Log.d(LOG_TAG, "ID: " + rhythmuszelle.getId() + ", Inhalt: " + rhythmuszelle.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return rhythmuszelleList;
    }

}