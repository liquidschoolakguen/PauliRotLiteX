package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Gueltigkeitsbereich;
import model.SP_Fach;

public class DataSource_Gueltigkeitsbereich {

    private static final String LOG_TAG = DataSource_Gueltigkeitsbereich.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_ID,
            MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_SCHULJAHR,
            MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_TYP,
            MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_NUMMER,
            MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_VON,
            MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_BIS
    };


    private String[] sp_fachColumns = {
            MyDbHelper.TABLE_SP_FACH + "." + MyDbHelper.SP_FACH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_TESTSTRING,
            MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID
    };

    public DataSource_Gueltigkeitsbereich(Context context) {
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

    public Gueltigkeitsbereich createGueltigkeitsbereich(String v1, String v2, String v3, long v4, long v5) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_SCHULJAHR, v1);
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_TYP, v2);
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_NUMMER, v3);
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_VON, v4);
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_BIS, v5);

        long insertId = database.insert(MyDbHelper.TABLE_GUELTIGKEITSBEREICH, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_GUELTIGKEITSBEREICH,
                columns, MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Gueltigkeitsbereich gueltigkeitsbereich = cursorToGueltigkeitsbereich(cursor);
        cursor.close();

        return gueltigkeitsbereich;
    }

    public void deleteGueltigkeitsbereich(Gueltigkeitsbereich gueltigkeitsbereich) {
        long id = gueltigkeitsbereich.getId();

        database.delete(MyDbHelper.TABLE_GUELTIGKEITSBEREICH,
                MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + gueltigkeitsbereich.toString());
    }

    public Gueltigkeitsbereich updateGueltigkeitsbereich(int id, String v1, String v2, String v3, long v4, long v5) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_SCHULJAHR, v1);
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_TYP, v2);
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_NUMMER, v3);
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_VON, v4);
        values.put(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_BIS, v5);

        database.update(MyDbHelper.TABLE_GUELTIGKEITSBEREICH,
                values,
                MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_GUELTIGKEITSBEREICH,
                columns, MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Gueltigkeitsbereich gueltigkeitsbereich = cursorToGueltigkeitsbereich(cursor);
        cursor.close();

        return gueltigkeitsbereich;
    }
    public Gueltigkeitsbereich getGueltigkeitsbereichById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_GUELTIGKEITSBEREICH,
                columns, MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Gueltigkeitsbereich gueltigkeitsbereich = cursorToGueltigkeitsbereich(cursor);
        cursor.close();

        return gueltigkeitsbereich;


    }
    private Gueltigkeitsbereich cursorToGueltigkeitsbereich(Cursor cursor) {
        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }



        int id0 = cursor.getColumnIndex(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_SCHULJAHR);
        int id2 = cursor.getColumnIndex(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_TYP);
        int id3 = cursor.getColumnIndex(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_NUMMER);
        int id4 = cursor.getColumnIndex(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_VON);
        int id5 = cursor.getColumnIndex(MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_BIS);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        long q4 = cursor.getLong(id4);
        long q5 = cursor.getLong(id5);


       // Gueltigkeitsbereich gueltigkeitsbereich = new Gueltigkeitsbereich(id,vorname,nachname,passwort,kuerzel,status);

        return new Gueltigkeitsbereich(id,q1,q2,q3,q4,q5);
    }

    public List<Gueltigkeitsbereich> getAllGueltigkeitsbereichs() {
        List<Gueltigkeitsbereich> gueltigkeitsbereichList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_GUELTIGKEITSBEREICH,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Gueltigkeitsbereich gueltigkeitsbereich;

        while(!cursor.isAfterLast()) {
            gueltigkeitsbereich = cursorToGueltigkeitsbereich(cursor);
            gueltigkeitsbereichList.add(gueltigkeitsbereich);
           // Log.d(LOG_TAG, "ID: " + gueltigkeitsbereich.getId() + ", Inhalt: " + gueltigkeitsbereich.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return gueltigkeitsbereichList;
    }






                                //Child     //Parent
    public List<SP_Fach> getSP_FachsFromGueltigkeitsbereichById(int i) {

        List<SP_Fach> sp_fachList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FACH+ " LEFT JOIN " + MyDbHelper.TABLE_GUELTIGKEITSBEREICH + " ON " + MyDbHelper.TABLE_GUELTIGKEITSBEREICH + "."
                        + MyDbHelper.GUELTIGKEITSBEREICH_COLUMN_ID + " = " + MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID,
                sp_fachColumns,
                MyDbHelper.TABLE_SP_FACH + "." + MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID+ " = " +i,
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