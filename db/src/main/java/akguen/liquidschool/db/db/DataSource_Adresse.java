package akguen.liquidschool.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Adresse;


public class DataSource_Adresse {

    private static final String LOG_TAG = DataSource_Adresse.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.ADRESSE_COLUMN_ID,
            MyDbHelper.ADRESSE_COLUMN_STRASSE,
            MyDbHelper.ADRESSE_COLUMN_PLZ,
            MyDbHelper.ADRESSE_COLUMN_ORT,
            MyDbHelper.ADRESSE_COLUMN_TEL,
            MyDbHelper.ADRESSE_COLUMN_FAX
    };

    public DataSource_Adresse(Context context) {
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

    public Adresse createAdresse(String v1, String v2, String v3, String v4, String v5) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.ADRESSE_COLUMN_STRASSE, v1);
        values.put(MyDbHelper.ADRESSE_COLUMN_PLZ, v2);
        values.put(MyDbHelper.ADRESSE_COLUMN_ORT, v3);
        values.put(MyDbHelper.ADRESSE_COLUMN_TEL, v4);
        values.put(MyDbHelper.ADRESSE_COLUMN_FAX, v5);


        long insertId = database.insert(MyDbHelper.TABLE_ADRESSE, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_ADRESSE,
                columns, MyDbHelper.ADRESSE_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Adresse adresse = cursorToAdresse(cursor);
        cursor.close();

        return adresse;
    }

    public void deleteAdresse(Adresse adresse) {
        long id = adresse.getId();

        database.delete(MyDbHelper.TABLE_ADRESSE,
                MyDbHelper.ADRESSE_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + adresse.toString());
    }

    public Adresse updateAdresse(int id, String v1, String v2, String v3, String v4, String v5) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.ADRESSE_COLUMN_STRASSE, v1);
        values.put(MyDbHelper.ADRESSE_COLUMN_PLZ, v2);
        values.put(MyDbHelper.ADRESSE_COLUMN_ORT, v3);
        values.put(MyDbHelper.ADRESSE_COLUMN_TEL, v4);
        values.put(MyDbHelper.ADRESSE_COLUMN_FAX, v5);


        database.update(MyDbHelper.TABLE_ADRESSE,
                values,
                MyDbHelper.ADRESSE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_ADRESSE,
                columns, MyDbHelper.ADRESSE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Adresse adresse = cursorToAdresse(cursor);
        cursor.close();

        return adresse;
    }
    public Adresse getAdresseById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_ADRESSE,
                columns, MyDbHelper.ADRESSE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Adresse adresse = cursorToAdresse(cursor);
        cursor.close();

        return adresse;


    }
    private Adresse cursorToAdresse(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.ADRESSE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.ADRESSE_COLUMN_STRASSE);
        int id2 = cursor.getColumnIndex(MyDbHelper.ADRESSE_COLUMN_PLZ);
        int id3 = cursor.getColumnIndex(MyDbHelper.ADRESSE_COLUMN_ORT);
        int id4 = cursor.getColumnIndex(MyDbHelper.ADRESSE_COLUMN_TEL);
        int id5 = cursor.getColumnIndex(MyDbHelper.ADRESSE_COLUMN_FAX);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);




        return new Adresse(id,q1,q2,q3,q4,q5);
    }

    public List<Adresse> getAllAdresses() {
        List<Adresse> adresseList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_ADRESSE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Adresse adresse;

        while(!cursor.isAfterLast()) {
            adresse = cursorToAdresse(cursor);
            adresseList.add(adresse);
            Log.d(LOG_TAG, "ID: " + adresse.getId() + ", Inhalt: " + adresse.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return adresseList;
    }

}