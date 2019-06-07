package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Schueler;

public class DataSource_Schueler {

    private static final String LOG_TAG = DataSource_Schueler.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SCHUELER_COLUMN_ID,
            MyDbHelper.SCHUELER_COLUMN_VORNAME,
            MyDbHelper.SCHUELER_COLUMN_NACHNAME,
            MyDbHelper.SCHUELER_COLUMN_RUFNAME,
            MyDbHelper.SCHUELER_COLUMN_GESCHLECHT,
            MyDbHelper.SCHUELER_COLUMN_STATUS,
            MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG,
            MyDbHelper.SCHUELER_COLUMN_GEBURTSORT
    };

    public DataSource_Schueler(Context context) {
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

    public Schueler createSchueler(String v1, String v2, String v3, String v4, String v5, String v6, String v7) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SCHUELER_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.SCHUELER_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.SCHUELER_COLUMN_RUFNAME, v3);
        values.put(MyDbHelper.SCHUELER_COLUMN_GESCHLECHT, v4);
        values.put(MyDbHelper.SCHUELER_COLUMN_STATUS, v5);
        values.put(MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG, v6);
        values.put(MyDbHelper.SCHUELER_COLUMN_GEBURTSORT, v7);

        long insertId = database.insert(MyDbHelper.TABLE_SCHUELER, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, MyDbHelper.SCHUELER_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler = cursorToSchueler(cursor);
        cursor.close();

        return schueler;
    }

    public void deleteSchueler(Schueler schueler) {
        long id = schueler.getId();

        database.delete(MyDbHelper.TABLE_SCHUELER,
                MyDbHelper.SCHUELER_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + schueler.toString());
    }

    public Schueler updateSchueler(int id, String v1, String v2, String v3, String v4, String v5, String v6, String v7) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SCHUELER_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.SCHUELER_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.SCHUELER_COLUMN_RUFNAME, v3);
        values.put(MyDbHelper.SCHUELER_COLUMN_GESCHLECHT, v4);
        values.put(MyDbHelper.SCHUELER_COLUMN_STATUS, v5);
        values.put(MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG, v6);
        values.put(MyDbHelper.SCHUELER_COLUMN_GEBURTSORT, v7);


        database.update(MyDbHelper.TABLE_SCHUELER,
                values,
                MyDbHelper.SCHUELER_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, MyDbHelper.SCHUELER_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler = cursorToSchueler(cursor);
        cursor.close();

        return schueler;
    }
    public Schueler getSchuelerById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, MyDbHelper.SCHUELER_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler = cursorToSchueler(cursor);
        cursor.close();

        return schueler;


    }
    private Schueler cursorToSchueler(Cursor cursor) {


        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_VORNAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_NACHNAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_RUFNAME);
        int id4 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GESCHLECHT);
        int id5 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_STATUS);
        int id6 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG);
        int id7 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GEBURTSORT);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        Log.d(LOG_TAG, "ID: " + q1+ "."+q1.length());
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);
        String q6 = cursor.getString(id6);
        String q7 = cursor.getString(id7);



        return new Schueler(id,q1,q2,q3,q4,q5,q6,q7);
    }

    public List<Schueler> getAllSchuelers() {
        List<Schueler> schuelerList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler;

        while(!cursor.isAfterLast()) {
            schueler = cursorToSchueler(cursor);
            schuelerList.add(schueler);
            Log.d(LOG_TAG, "ID: " + schueler.getId() + ", Inhalt: " + schueler.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return schuelerList;
    }

}