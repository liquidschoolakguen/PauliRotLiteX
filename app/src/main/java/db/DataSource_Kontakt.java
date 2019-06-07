package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Kontakt;

public class DataSource_Kontakt {

    private static final String LOG_TAG = DataSource_Kontakt.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.KONTAKT_COLUMN_ID,
            MyDbHelper.KONTAKT_COLUMN_MOBIL,
            MyDbHelper.KONTAKT_COLUMN_MAIL
    };

    public DataSource_Kontakt(Context context) {
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

    public Kontakt createKontakt(String v1, String v2) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.KONTAKT_COLUMN_MOBIL, v1);
        values.put(MyDbHelper.KONTAKT_COLUMN_MAIL, v2);


        long insertId = database.insert(MyDbHelper.TABLE_KONTAKT, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_KONTAKT,
                columns, MyDbHelper.KONTAKT_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Kontakt kontakt = cursorToKontakt(cursor);
        cursor.close();

        return kontakt;
    }

    public void deleteKontakt(Kontakt kontakt) {
        long id = kontakt.getId();

        database.delete(MyDbHelper.TABLE_KONTAKT,
                MyDbHelper.KONTAKT_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + kontakt.toString());
    }

    public Kontakt updateKontakt(int id, String v1, String v2) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.KONTAKT_COLUMN_MOBIL, v1);
        values.put(MyDbHelper.KONTAKT_COLUMN_MAIL, v2);


        database.update(MyDbHelper.TABLE_KONTAKT,
                values,
                MyDbHelper.KONTAKT_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_KONTAKT,
                columns, MyDbHelper.KONTAKT_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Kontakt kontakt = cursorToKontakt(cursor);
        cursor.close();

        return kontakt;
    }
    public Kontakt getKontaktById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_KONTAKT,
                columns, MyDbHelper.KONTAKT_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Kontakt kontakt = cursorToKontakt(cursor);
        cursor.close();

        return kontakt;


    }
    private Kontakt cursorToKontakt(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.KONTAKT_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.KONTAKT_COLUMN_MOBIL);
        int id2 = cursor.getColumnIndex(MyDbHelper.KONTAKT_COLUMN_MAIL);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);



       // Kontakt kontakt = new Kontakt(id,vorname,nachname,passwort,kuerzel,status);

        return new Kontakt(id,q1,q2);
    }

    public List<Kontakt> getAllKontakts() {
        List<Kontakt> kontaktList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_KONTAKT,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Kontakt kontakt;

        while(!cursor.isAfterLast()) {
            kontakt = cursorToKontakt(cursor);
            kontaktList.add(kontakt);
            Log.d(LOG_TAG, "ID: " + kontakt.getId() + ", Inhalt: " + kontakt.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return kontaktList;
    }

}