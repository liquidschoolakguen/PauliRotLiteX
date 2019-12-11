package akguen.liquidschool.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Vergehengruppe;


public class DataSource_Vergehengruppe {

    private static final String LOG_TAG = DataSource_Vergehengruppe.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.VERGEHENGRUPPE_COLUMN_ID,
            MyDbHelper.VERGEHENGRUPPE_COLUMN_NAME,
    };



    public DataSource_Vergehengruppe(Context context) {
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

    public Vergehengruppe createVergehengruppe(String v1) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.VERGEHENGRUPPE_COLUMN_NAME, v1);



        long insertId = database.insert(MyDbHelper.TABLE_VERGEHENGRUPPE, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHENGRUPPE,
                columns, MyDbHelper.VERGEHENGRUPPE_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Vergehengruppe vergehengruppe = cursorToVergehengruppe(cursor);
        cursor.close();

        return vergehengruppe;
    }

    public void deleteVergehengruppe(Vergehengruppe vergehengruppe) {
        long id = vergehengruppe.getId();

        database.delete(MyDbHelper.TABLE_VERGEHENGRUPPE,
                MyDbHelper.VERGEHENGRUPPE_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + vergehengruppe.toString());
    }

    public Vergehengruppe updateVergehengruppe(int id, String v1) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.VERGEHENGRUPPE_COLUMN_NAME, v1);


        database.update(MyDbHelper.TABLE_VERGEHENGRUPPE,
                values,
                MyDbHelper.VERGEHENGRUPPE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHENGRUPPE,
                columns, MyDbHelper.VERGEHENGRUPPE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Vergehengruppe vergehengruppe = cursorToVergehengruppe(cursor);
        cursor.close();

        return vergehengruppe;
    }
    public Vergehengruppe getVergehengruppeById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHENGRUPPE,
                columns, MyDbHelper.VERGEHENGRUPPE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Vergehengruppe vergehengruppe = cursorToVergehengruppe(cursor);
        cursor.close();

        return vergehengruppe;


    }

    public Vergehengruppe getVergehengruppeByName(String name){

        Log.d(LOG_TAG, "schrubbi: "+name);
        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHENGRUPPE,
                columns, MyDbHelper.VERGEHENGRUPPE_COLUMN_NAME+ "='" + name + "'",
                null, null, null, null);

        cursor.moveToFirst();
        Vergehengruppe vergehengruppe = cursorToVergehengruppe(cursor);
        cursor.close();

        return vergehengruppe;


    }

    private Vergehengruppe cursorToVergehengruppe(Cursor cursor) {

        if(cursor.getCount()==0){
            Log.d(LOG_TAG, "cursorToVergehengruppe");
            return null;
        }
        Log.d(LOG_TAG, "cursorToVergehengruppe33");
        int id0 = cursor.getColumnIndex(MyDbHelper.VERGEHENGRUPPE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.VERGEHENGRUPPE_COLUMN_NAME);



        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);




        // Vergehengruppe vergehengruppe = new Vergehengruppe(id,vorname,nachname,passwort,kuerzel,status);

        return new Vergehengruppe(id,q1);
    }

    public List<Vergehengruppe> getAllVergehengruppes() {
        List<Vergehengruppe> vergehengruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHENGRUPPE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Vergehengruppe vergehengruppe;

        while(!cursor.isAfterLast()) {
            vergehengruppe = cursorToVergehengruppe(cursor);
            vergehengruppeList.add(vergehengruppe);
            //Log.d(LOG_TAG, "ID: " + vergehengruppe.getId() + ", Inhalt: " + vergehengruppe.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return vergehengruppeList;
    }













}