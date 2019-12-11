package db;
/*

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Lernform;
import model.Lerngruppe;
import model.Lerngruppe;

public class DataSource_Lernform {

    private static final String LOG_TAG = DataSource_Lernform.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.LERNFORM_COLUMN_ID,
            MyDbHelper.LERNFORM_COLUMN_NAME,
            MyDbHelper.LERNFORM_COLUMN_ZYKLUS
    };


    private String[] lerngruppeColumns = {
            MyDbHelper.TABLE_LERNGRUPPE + "." + MyDbHelper.LERNGRUPPE_COLUMN_ID,
            MyDbHelper.LERNGRUPPE_COLUMN_NAME,
            MyDbHelper.LERNGRUPPE_COLUMN_LERNFORM_COLUMN_ID
    };



    public DataSource_Lernform(Context context) {
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

    public Lernform createLernform(String v1, String v2) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.LERNFORM_COLUMN_NAME, v1);
        values.put(MyDbHelper.LERNFORM_COLUMN_ZYKLUS, v2);


        long insertId = database.insert(MyDbHelper.TABLE_LERNFORM, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_LERNFORM,
                columns, MyDbHelper.LERNFORM_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Lernform lernform = cursorToLernform(cursor);
        cursor.close();

        return lernform;
    }

    public void deleteLernform(Lernform lernform) {
        long id = lernform.getId();

        database.delete(MyDbHelper.TABLE_LERNFORM,
                MyDbHelper.LERNFORM_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + lernform.toString());
    }

    public Lernform updateLernform(int id, String v1, String v2) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.LERNFORM_COLUMN_NAME, v1);
        values.put(MyDbHelper.LERNFORM_COLUMN_ZYKLUS, v2);


        database.update(MyDbHelper.TABLE_LERNFORM,
                values,
                MyDbHelper.LERNFORM_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_LERNFORM,
                columns, MyDbHelper.LERNFORM_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Lernform lernform = cursorToLernform(cursor);
        cursor.close();

        return lernform;
    }
    public Lernform getLernformById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_LERNFORM,
                columns, MyDbHelper.LERNFORM_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Lernform lernform = cursorToLernform(cursor);
        cursor.close();

        return lernform;


    }





    //Child     //Parent
    public List<Lerngruppe> getLerngruppesFromLernformById(int i) {

        List<Lerngruppe> lerngruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_LERNGRUPPE+ " LEFT JOIN " + MyDbHelper.TABLE_LERNFORM + " ON " + MyDbHelper.TABLE_LERNFORM + "."
                        + MyDbHelper.LERNFORM_COLUMN_ID + " = " + MyDbHelper.LERNGRUPPE_COLUMN_LERNFORM_COLUMN_ID,
                lerngruppeColumns,
                MyDbHelper.TABLE_LERNGRUPPE + "." + MyDbHelper.LERNGRUPPE_COLUMN_LERNFORM_COLUMN_ID+ " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        Lerngruppe lerngruppe;

        while (!cursor.isAfterLast()) {
            lerngruppe = cursorToLerngruppe(cursor);
            lerngruppeList.add(lerngruppe);
            //Log.d(LOG_TAG, "ID: " + angehoeriger);
            cursor.moveToNext();
        }


        cursor.close();


        return lerngruppeList;
    }


    private Lerngruppe cursorToLerngruppe(Cursor cursor) {

        if(cursor.getCount()==0){
            Log.d(LOG_TAG, "cursorToLerngruppe");
            return null;
        }
        Log.d(LOG_TAG, "cursorToLerngruppe33");
        int id0 = cursor.getColumnIndex(MyDbHelper.LERNGRUPPE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.LERNGRUPPE_COLUMN_NAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.LERNGRUPPE_COLUMN_LERNFORM_COLUMN_ID);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        int q2 =cursor.getInt(id2);




        // Lerngruppe lerngruppe = new Lerngruppe(id,vorname,nachname,passwort,kuerzel,status);

        return new Lerngruppe(id,q1,q2);
    }




    private Lernform cursorToLernform(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.LERNFORM_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.LERNFORM_COLUMN_NAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.LERNFORM_COLUMN_ZYKLUS);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);



       // Lernform lernform = new Lernform(id,vorname,nachname,passwort,kuerzel,status);

        return new Lernform(id,q1,q2);
    }

    public List<Lernform> getAllLernforms() {
        List<Lernform> lernformList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_LERNFORM,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Lernform lernform;

        while(!cursor.isAfterLast()) {
            lernform = cursorToLernform(cursor);
            lernformList.add(lernform);
            Log.d(LOG_TAG, "ID: " + lernform.getId() + ", Inhalt: " + lernform.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return lernformList;
    }

}*/
