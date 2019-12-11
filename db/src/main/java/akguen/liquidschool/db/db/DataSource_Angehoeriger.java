package akguen.liquidschool.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Angehoeriger;


public class DataSource_Angehoeriger {

    private static final String LOG_TAG = DataSource_Angehoeriger.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.ANGEHOERIGER_COLUMN_ID,
            MyDbHelper.ANGEHOERIGER_COLUMN_VORNAME,
            MyDbHelper.ANGEHOERIGER_COLUMN_NACHNAME,
            MyDbHelper.ANGEHOERIGER_COLUMN_INSTITUTION,
            MyDbHelper.ANGEHOERIGER_COLUMN_BEZIEHUNG,
            MyDbHelper.ANGEHOERIGER_COLUMN_GESCHLECHT

    };

    public DataSource_Angehoeriger(Context context) {
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

    public Angehoeriger createAngehoeriger(String v1, String v2, String v3, String v4, String v5) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_INSTITUTION, v3);
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_BEZIEHUNG, v4);
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_GESCHLECHT, v5);


        long insertId = database.insert(MyDbHelper.TABLE_ANGEHOERIGER, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_ANGEHOERIGER,
                columns, MyDbHelper.ANGEHOERIGER_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Angehoeriger angehoeriger = cursorToAngehoeriger(cursor);
        cursor.close();

        return angehoeriger;
    }

    public void deleteAngehoeriger(Angehoeriger angehoeriger) {
        long id = angehoeriger.getId();

        database.delete(MyDbHelper.TABLE_ANGEHOERIGER,
                MyDbHelper.ANGEHOERIGER_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + angehoeriger.toString());
    }

    public Angehoeriger updateAngehoeriger(int id, String v1, String v2, String v3, String v4, String v5) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_INSTITUTION, v3);
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_BEZIEHUNG, v4);
        values.put(MyDbHelper.ANGEHOERIGER_COLUMN_GESCHLECHT, v5);

        database.update(MyDbHelper.TABLE_ANGEHOERIGER,
                values,
                MyDbHelper.ANGEHOERIGER_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_ANGEHOERIGER,
                columns, MyDbHelper.ANGEHOERIGER_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Angehoeriger angehoeriger = cursorToAngehoeriger(cursor);
        cursor.close();

        return angehoeriger;
    }
    public Angehoeriger getAngehoerigerById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_ANGEHOERIGER,
                columns, MyDbHelper.ANGEHOERIGER_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Angehoeriger angehoeriger = cursorToAngehoeriger(cursor);
        cursor.close();

        return angehoeriger;


    }
    private Angehoeriger cursorToAngehoeriger(Cursor cursor) {


        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_VORNAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_NACHNAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_INSTITUTION);
        int id4 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_BEZIEHUNG);
        int id5 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_GESCHLECHT);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);


       // Angehoeriger angehoeriger = new Angehoeriger(id,vorname,nachname,passwort,kuerzel,status);

        return new Angehoeriger(id,q1,q2,q3,q4,q5);
    }

    public List<Angehoeriger> getAllAngehoerigers() {
        List<Angehoeriger> angehoerigerList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_ANGEHOERIGER,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Angehoeriger angehoeriger;

        while(!cursor.isAfterLast()) {
            angehoeriger = cursorToAngehoeriger(cursor);
            angehoerigerList.add(angehoeriger);
            Log.d(LOG_TAG, "ID: " + angehoeriger.getId() + ", Inhalt: " + angehoeriger.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return angehoerigerList;
    }

}