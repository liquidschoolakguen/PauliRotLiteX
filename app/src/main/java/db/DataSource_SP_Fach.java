package db;
/*

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.SP_Fach;

public class DataSource_SP_Fach {

    private static final String LOG_TAG = DataSource_SP_Fach.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SP_FACH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_TESTSTRING,
            MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID

    };

    public DataSource_SP_Fach(Context context) {
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

    public SP_Fach createSP_Fach(String v1, int v2, int v3, int v4, int v5) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SP_FACH_COLUMN_TESTSTRING, v1);
        values.put(MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID, v2);
        values.put(MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID, v3);
        values.put(MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID, v4);
        values.put(MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID, v5);

        long insertId = database.insert(MyDbHelper.TABLE_SP_FACH, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FACH,
                columns, MyDbHelper.SP_FACH_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        SP_Fach sp_Fach = cursorToSP_Fach(cursor);
        cursor.close();

        return sp_Fach;
    }

    public void deleteSP_Fach(SP_Fach sp_Fach) {
        long id = sp_Fach.getId();

        database.delete(MyDbHelper.TABLE_SP_FACH,
                MyDbHelper.SP_FACH_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + sp_Fach.toString());
    }

    public SP_Fach updateSP_Fach(int id, String v1, String v2, String v3, String v4, String v5) {

        Cursor cursorX2 = database.query(MyDbHelper.TABLE_THEMA,
                null, MyDbHelper.THEMA_COLUMN_ID + "=" + v2,
                null, null, null, null);

        Cursor cursorX3 = database.query(MyDbHelper.TABLE_RAUM,
                null, MyDbHelper.RAUM_COLUMN_ID + "=" + v3,
                null, null, null, null);


        Cursor cursorX4 = database.query(MyDbHelper.TABLE_GUELTIGKEITSBEREICH,
                null, MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_ID + "=" + v4,
                null, null, null, null);


        Cursor cursorX5 = database.query(MyDbHelper.TABLE_LERNGRUPPE,
                null, MyDbHelper.LERNGRUPPE_COLUMN_ID + "=" + v5,
                null, null, null, null);


        if (cursorX2.getCount() == 0 || cursorX3.getCount() == 0 || cursorX4.getCount() == 0 || cursorX5.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SP_FACH_COLUMN_TESTSTRING, v1);
        values.put(MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID, v2);
        values.put(MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID, v3);
        values.put(MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID, v4);
        values.put(MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID, v5);


        database.update(MyDbHelper.TABLE_SP_FACH,
                values,
                MyDbHelper.SP_FACH_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FACH,
                columns, MyDbHelper.SP_FACH_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        SP_Fach sp_Fach = cursorToSP_Fach(cursor);
        cursor.close();

        return sp_Fach;
    }

    public SP_Fach getSP_FachById(int id) {


        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FACH,
                columns, MyDbHelper.SP_FACH_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        SP_Fach sp_Fach = cursorToSP_Fach(cursor);
        cursor.close();

        return sp_Fach;


    }

    private SP_Fach cursorToSP_Fach(Cursor cursor) {


        if (cursor.getCount() == 0) {
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


        // SP_Fach sp_Fach = new SP_Fach(id,vorname,nachname,passwort,kuerzel,status);

        return new SP_Fach(id, q1, Integer.parseInt(q2),Integer.parseInt(q3),Integer.parseInt(q4),Integer.parseInt(q5));
    }

    public List<SP_Fach> getAllSP_Fachs() {
        List<SP_Fach> sp_FachList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FACH,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        SP_Fach sp_Fach;

        while (!cursor.isAfterLast()) {
            sp_Fach = cursorToSP_Fach(cursor);
            sp_FachList.add(sp_Fach);
            Log.d(LOG_TAG, "ID: " + sp_Fach.getId() + ", Inhalt: " + sp_Fach.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return sp_FachList;
    }

}*/
