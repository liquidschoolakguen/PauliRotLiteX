package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Vorfall;

public class DataSource_Vorfall {

    private static final String LOG_TAG = DataSource_Vorfall.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.VORFALL_COLUMN_ID,
            MyDbHelper.VORFALL_COLUMN_ZEITPUNKT,
            MyDbHelper.VORFALL_COLUMN_INFO
    };

    public DataSource_Vorfall(Context context) {
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

    public Vorfall createVorfall(String v1, String v2) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.VORFALL_COLUMN_ZEITPUNKT, v1);
        values.put(MyDbHelper.VORFALL_COLUMN_INFO, v2);


        long insertId = database.insert(MyDbHelper.TABLE_VORFALL, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_VORFALL,
                columns, MyDbHelper.VORFALL_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Vorfall vorfall = cursorToVorfall(cursor);
        cursor.close();

        return vorfall;
    }

    public void deleteVorfall(Vorfall vorfall) {
        long id = vorfall.getId();

        database.delete(MyDbHelper.TABLE_VORFALL,
                MyDbHelper.VORFALL_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + vorfall.toString());
    }

    public Vorfall updateVorfall(int id, String v1, String v2) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.VORFALL_COLUMN_ZEITPUNKT, v1);
        values.put(MyDbHelper.VORFALL_COLUMN_INFO, v2);


        database.update(MyDbHelper.TABLE_VORFALL,
                values,
                MyDbHelper.VORFALL_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_VORFALL,
                columns, MyDbHelper.VORFALL_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Vorfall vorfall = cursorToVorfall(cursor);
        cursor.close();

        return vorfall;
    }
    public Vorfall getVorfallById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_VORFALL,
                columns, MyDbHelper.VORFALL_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Vorfall vorfall = cursorToVorfall(cursor);
        cursor.close();

        return vorfall;


    }
    private Vorfall cursorToVorfall(Cursor cursor) {



        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.VORFALL_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.VORFALL_COLUMN_ZEITPUNKT);
        int id2 = cursor.getColumnIndex(MyDbHelper.VORFALL_COLUMN_INFO);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);



       // Vorfall vorfall = new Vorfall(id,vorname,nachname,passwort,kuerzel,status);

        return new Vorfall(id,q1,q2);
    }

    public List<Vorfall> getAllVorfalls() {
        List<Vorfall> vorfallList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_VORFALL,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Vorfall vorfall;

        while(!cursor.isAfterLast()) {
            vorfall = cursorToVorfall(cursor);
            vorfallList.add(vorfall);
            Log.d(LOG_TAG, "ID: " + vorfall.getId() + ", Inhalt: " + vorfall.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return vorfallList;
    }

}