package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Lerngruppe;
import model.SP_Fach;

public class DataSource_Lerngruppe {

    private static final String LOG_TAG = DataSource_Lerngruppe.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.LERNGRUPPE_COLUMN_ID,
            MyDbHelper.LERNGRUPPE_COLUMN_NAME,
    };

    private String[] sp_fachColumns = {
            MyDbHelper.TABLE_SP_FACH + "." + MyDbHelper.SP_FACH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_TESTSTRING,
            MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID
    };

    public DataSource_Lerngruppe(Context context) {
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

    public Lerngruppe createLerngruppe(String v1) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.LERNGRUPPE_COLUMN_NAME, v1);



        long insertId = database.insert(MyDbHelper.TABLE_LERNGRUPPE, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_LERNGRUPPE,
                columns, MyDbHelper.LERNGRUPPE_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Lerngruppe lerngruppe = cursorToLerngruppe(cursor);
        cursor.close();

        return lerngruppe;
    }

    public void deleteLerngruppe(Lerngruppe lerngruppe) {
        long id = lerngruppe.getId();

        database.delete(MyDbHelper.TABLE_LERNGRUPPE,
                MyDbHelper.LERNGRUPPE_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + lerngruppe.toString());
    }

    public Lerngruppe updateLerngruppe(int id, String v1) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.LERNGRUPPE_COLUMN_NAME, v1);


        database.update(MyDbHelper.TABLE_LERNGRUPPE,
                values,
                MyDbHelper.LERNGRUPPE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_LERNGRUPPE,
                columns, MyDbHelper.LERNGRUPPE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Lerngruppe lerngruppe = cursorToLerngruppe(cursor);
        cursor.close();

        return lerngruppe;
    }
    public Lerngruppe getLerngruppeById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_LERNGRUPPE,
                columns, MyDbHelper.LERNGRUPPE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Lerngruppe lerngruppe = cursorToLerngruppe(cursor);
        cursor.close();

        return lerngruppe;


    }
    private Lerngruppe cursorToLerngruppe(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.LERNGRUPPE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.LERNGRUPPE_COLUMN_NAME);



        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);




        // Lerngruppe lerngruppe = new Lerngruppe(id,vorname,nachname,passwort,kuerzel,status);

        return new Lerngruppe(id,q1);
    }

    public List<Lerngruppe> getAllLerngruppes() {
        List<Lerngruppe> lerngruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_LERNGRUPPE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Lerngruppe lerngruppe;

        while(!cursor.isAfterLast()) {
            lerngruppe = cursorToLerngruppe(cursor);
            lerngruppeList.add(lerngruppe);
            Log.d(LOG_TAG, "ID: " + lerngruppe.getId() + ", Inhalt: " + lerngruppe.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return lerngruppeList;
    }









    public List<SP_Fach> getSP_FachsFromLerngruppeById(int i) {

        List<SP_Fach> sp_fachList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FACH+ " LEFT JOIN " + MyDbHelper.TABLE_LERNGRUPPE + " ON " + MyDbHelper.TABLE_LERNGRUPPE + "."
                        + MyDbHelper.LERNGRUPPE_COLUMN_ID + " = " + MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID,
                sp_fachColumns,
                MyDbHelper.TABLE_SP_FACH + "." + MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID+ " = " +i,
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









}