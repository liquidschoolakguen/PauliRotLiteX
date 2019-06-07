package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



import model.SP_Fach;
import model.Thema;

public class DataSource_Thema {

    private static final String LOG_TAG = DataSource_Thema.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.THEMA_COLUMN_ID,
            MyDbHelper.THEMA_COLUMN_NAME

    };

    private String[] sp_fachColumns = {
            MyDbHelper.TABLE_SP_FACH + "." + MyDbHelper.SP_FACH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_TESTSTRING,
            MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_RAUM_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID,
            MyDbHelper.SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID
    };




    public DataSource_Thema(Context context) {
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

    public Thema createThema(String v1) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.THEMA_COLUMN_NAME, v1);



        long insertId = database.insert(MyDbHelper.TABLE_THEMA, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_THEMA,
                columns, MyDbHelper.THEMA_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Thema thema = cursorToThema(cursor);
        cursor.close();

        return thema;
    }

    public void deleteThema(Thema thema) {
        long id = thema.getId();

        database.delete(MyDbHelper.TABLE_THEMA,
                MyDbHelper.THEMA_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + thema.toString());
    }

    public Thema updateThema(int id, String v1) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.THEMA_COLUMN_NAME, v1);



        database.update(MyDbHelper.TABLE_THEMA,
                values,
                MyDbHelper.THEMA_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_THEMA,
                columns, MyDbHelper.THEMA_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Thema thema = cursorToThema(cursor);
        cursor.close();

        return thema;
    }
    public Thema getThemaById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_THEMA,
                columns, MyDbHelper.THEMA_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Thema thema = cursorToThema(cursor);
        cursor.close();

        return thema;


    }
    private Thema cursorToThema(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.THEMA_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.THEMA_COLUMN_NAME);



        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);




       // Thema thema = new Thema(id,vorname,nachname,passwort,kuerzel,status);

        return new Thema(id,q1);
    }

    public List<Thema> getAllThemas() {
        List<Thema> themaList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_THEMA,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Thema thema;

        while(!cursor.isAfterLast()) {
            thema = cursorToThema(cursor);
            themaList.add(thema);
            Log.d(LOG_TAG, "ID: " + thema.getId() + ", Inhalt: " + thema.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return themaList;
    }




                            //Child     //Parent
    public List<SP_Fach> getSP_FachsFromThemaById(int i) {

        List<SP_Fach> sp_fachList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FACH+ " LEFT JOIN " + MyDbHelper.TABLE_THEMA + " ON " + MyDbHelper.TABLE_THEMA + "."
                        + MyDbHelper.THEMA_COLUMN_ID + " = " + MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID,
                sp_fachColumns,
                MyDbHelper.TABLE_SP_FACH + "." + MyDbHelper.SP_FACH_COLUMN_THEMA_COLUMN_ID+ " = " +i,
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