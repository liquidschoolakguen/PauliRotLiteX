package akguen.liquidschool.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Schueler;

public class DataSource_Schueler {

    private static final String LOG_TAG = DataSource_Schueler.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SCHUELER_COLUMN_ID,
            MyDbHelper.SCHUELER_COLUMN_VORNAME,
            MyDbHelper.SCHUELER_COLUMN_SURNAME,
            MyDbHelper.SCHUELER_COLUMN_ITEMTYPE,
            MyDbHelper.SCHUELER_COLUMN_RUFNAME,
            MyDbHelper.SCHUELER_COLUMN_GESCHLECHT,
            MyDbHelper.SCHUELER_COLUMN_STATUS,
            MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG,
            MyDbHelper.SCHUELER_COLUMN_GEBURTSORT
    };

    public DataSource_Schueler(Context context) {
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

    public Schueler createSchueler(String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8) {
        if (v6==null){
            v6 = "0";
        }


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SCHUELER_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.SCHUELER_COLUMN_SURNAME, v2);
        values.put(MyDbHelper.SCHUELER_COLUMN_ITEMTYPE, v3);
        values.put(MyDbHelper.SCHUELER_COLUMN_RUFNAME, v4);
        values.put(MyDbHelper.SCHUELER_COLUMN_GESCHLECHT, v5);
        values.put(MyDbHelper.SCHUELER_COLUMN_STATUS, v6);
        values.put(MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG, v7);
        values.put(MyDbHelper.SCHUELER_COLUMN_GEBURTSORT, v8);

        long insertId = database.insert(MyDbHelper.TABLE_SCHUELER, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, MyDbHelper.SCHUELER_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler = cursorToSchueler(cursor);
        cursor.close();

        return schueler;
    }

    public void deleteSchueler(Schueler schueler) {
        long id = schueler.getId();

        database.delete(MyDbHelper.TABLE_SCHUELER,
                MyDbHelper.SCHUELER_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + schueler.toString());
    }

    public Schueler updateSchueler(int id, String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8) {

        //private DataSource_Schueler dS_Schueler;

          /*dS_Schueler = new DataSource_Schueler(context);

        dS_Schueler.open();*/

        //dS_Schueler.updateSchueler(g.getId(),g.getVorname(),g.getItemType(),g.getRufname(),g.getGeschlecht(),g.getStatus(),g.getGeburtstag(),g.getGeburtsort());


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SCHUELER_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.SCHUELER_COLUMN_SURNAME, v2);
        values.put(MyDbHelper.SCHUELER_COLUMN_ITEMTYPE, v3);
        values.put(MyDbHelper.SCHUELER_COLUMN_RUFNAME, v4);
        values.put(MyDbHelper.SCHUELER_COLUMN_GESCHLECHT, v5);
        values.put(MyDbHelper.SCHUELER_COLUMN_STATUS, v6);
        values.put(MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG, v7);
        values.put(MyDbHelper.SCHUELER_COLUMN_GEBURTSORT, v8);


        database.update(MyDbHelper.TABLE_SCHUELER,
                values,
                MyDbHelper.SCHUELER_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, MyDbHelper.SCHUELER_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler = cursorToSchueler(cursor);
        cursor.close();

        return schueler;
    }
    public Schueler getSchuelerById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, MyDbHelper.SCHUELER_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler = cursorToSchueler(cursor);
        cursor.close();

        return schueler;


    }

    public Schueler getSchuelerByVorname(String vorname){
        System.out.println("---nnn---: "+vorname);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, MyDbHelper.SCHUELER_COLUMN_VORNAME + "='" + vorname + "'",
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler = cursorToSchueler(cursor);
        cursor.close();
        if(schueler==null){
            System.out.println("---NULL---: xxx");
        }
        return schueler;


    }
    public Schueler getSchuelerByVornameSurname(String vorname, String surname){
        System.out.println("---nnn---: "+vorname);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, MyDbHelper.SCHUELER_COLUMN_VORNAME + "='" + vorname + "'"+ " AND " + MyDbHelper.SCHUELER_COLUMN_SURNAME + "='" + surname + "'",
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler = cursorToSchueler(cursor);
        cursor.close();
        if(schueler==null){
            System.out.println("---NULL---: xxx");
        }
        return schueler;


    }

    private Schueler cursorToSchueler(Cursor cursor) {


        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_VORNAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_SURNAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_ITEMTYPE);
        int id4 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_RUFNAME);
        int id5 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GESCHLECHT);
        int id6 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_STATUS);
        int id7 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG);
        int id8 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GEBURTSORT);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        Log.d(LOG_TAG, "ID: " + q1+ "."+q1.length());
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);
        String q6 = cursor.getString(id6);
        String q7 = cursor.getString(id7);
        String q8 = cursor.getString(id8);


        return new Schueler(id,q1,q2,q3,q4,q5,q6,q7,q8);
    }

    public List<Schueler> getAllSchuelers() {
        List<Schueler> schuelerList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler;

        while(!cursor.isAfterLast()) {
            schueler = cursorToSchueler(cursor);
            schuelerList.add(schueler);
            Log.d(LOG_TAG, "ID: " + schueler.getId() + ", Inhalt: " + schueler.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return schuelerList;
    }

}