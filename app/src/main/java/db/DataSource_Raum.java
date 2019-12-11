package db;
/*

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Raum;
import model.SP_Fach;

public class DataSource_Raum {

    private static final String LOG_TAG = DataSource_Raum.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.RAUM_COLUMN_ID,
            MyDbHelper.RAUM_COLUMN_HAUS,
            MyDbHelper.RAUM_COLUMN_NAME
    };


    private String[] sp_fachColumns = {
            MyDbHelper.TABLE_SP_FACH + "." + MyDbHelper.SP_FACH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_TESTSTRING,
            MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID
    };




    public DataSource_Raum(Context context) {
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

    public Raum createRaum(String v1, String v2) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.RAUM_COLUMN_HAUS, v1);
        values.put(MyDbHelper.RAUM_COLUMN_NAME, v2);


        long insertId = database.insert(MyDbHelper.TABLE_RAUM, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_RAUM,
                columns, MyDbHelper.RAUM_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Raum raum = cursorToRaum(cursor);
        cursor.close();

        return raum;
    }

    public void deleteRaum(Raum raum) {
        long id = raum.getId();

        database.delete(MyDbHelper.TABLE_RAUM,
                MyDbHelper.RAUM_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + raum.toString());
    }

    public Raum updateRaum(int id, String v1, String v2) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.RAUM_COLUMN_HAUS, v1);
        values.put(MyDbHelper.RAUM_COLUMN_NAME, v2);


        database.update(MyDbHelper.TABLE_RAUM,
                values,
                MyDbHelper.RAUM_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_RAUM,
                columns, MyDbHelper.RAUM_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Raum raum = cursorToRaum(cursor);
        cursor.close();

        return raum;
    }
    public Raum getRaumById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_RAUM,
                columns, MyDbHelper.RAUM_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Raum raum = cursorToRaum(cursor);
        cursor.close();

        return raum;


    }
    private Raum cursorToRaum(Cursor cursor) {


        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.RAUM_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.RAUM_COLUMN_HAUS);
        int id2 = cursor.getColumnIndex(MyDbHelper.RAUM_COLUMN_NAME);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);



       // Raum raum = new Raum(id,vorname,nachname,passwort,kuerzel,status);

        return new Raum(id,q1,q2);
    }

    public List<Raum> getAllRaums() {
        List<Raum> raumList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_RAUM,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Raum raum;

        while(!cursor.isAfterLast()) {
            raum = cursorToRaum(cursor);
            raumList.add(raum);
            Log.d(LOG_TAG, "ID: " + raum.getId() + ", Inhalt: " + raum.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return raumList;
    }











    //Child     //Parent
    public List<SP_Fach> getSP_FachsFromRaumById(int i) {

        List<SP_Fach> sp_fachList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FACH + " LEFT JOIN " + MyDbHelper.TABLE_RAUM + " ON " + MyDbHelper.TABLE_RAUM + "."
                        + MyDbHelper.RAUM_COLUMN_ID + " = " + MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID,
                sp_fachColumns,
                MyDbHelper.TABLE_SP_FACH + "." + MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID+ " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        SP_Fach sp_fach;

        while (!cursor.isAfterLast()) {
            sp_fach = cursorToSP_Fach(cursor);
            sp_fachList.add(sp_fach);
            //Log.d(LOG_TAG, "ID: " + angehoeriger);
            cursor.moveToNext();
        }


        cursor.close();


        return sp_fachList;
    }



    private SP_Fach cursorToSP_Fach(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.SP_FACH_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.SP_FACH_COLUMN_TESTSTRING);
        int id2 = cursor.getColumnIndex(MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID);
        int id3 = cursor.getColumnIndex(MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID);
        int id4 = cursor.getColumnIndex(MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID);
        int id5 = cursor.getColumnIndex(MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);


        // Angehoeriger angehoeriger = new Angehoeriger(id,vorname,nachname,passwort,kuerzel,status);

        return new SP_Fach(id,q1,Integer.parseInt(q2),Integer.parseInt(q3),Integer.parseInt(q4),Integer.parseInt(q5));
    }











}*/
