package akguen.liquidschool.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Lerngruppe;
import akguen.liquidschool.db.model.Schueler;


public class DataSource_Schueler_Lerngruppe {

    private static final String LOG_TAG = DataSource_Schueler_Lerngruppe.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SL_SCHUELER_ID,
            MyDbHelper.SL_LERNGRUPPE_ID,
    };


    private String[] lerngruppeColumns = {
            MyDbHelper.LERNGRUPPE_COLUMN_ID,
            MyDbHelper.LERNGRUPPE_COLUMN_NAME
    };

    private String[] schuelerColumns = {
            MyDbHelper.SCHUELER_COLUMN_ID,
            MyDbHelper.SCHUELER_COLUMN_VORNAME,
            MyDbHelper.SCHUELER_COLUMN_SURNAME,
            MyDbHelper.SCHUELER_COLUMN_ITEMTYPE,
            MyDbHelper.SCHUELER_COLUMN_GESCHLECHT,
            MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG
    };




    public DataSource_Schueler_Lerngruppe(Context context) {
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

    public void createSchueler_Lerngruppe(ContentValues v) {


        database.insert(MyDbHelper.TABLE_SCHUELER_LERNGRUPPE, null, v);


    }

    public void deleteSchueler_Lerngruppe(ContentValues v) {


        database.delete(MyDbHelper.TABLE_SCHUELER_LERNGRUPPE,
                MyDbHelper.SL_SCHUELER_ID + " = " + v.getAsString("schueler_id") + " AND " + MyDbHelper.SL_LERNGRUPPE_ID + " = " + v.getAsString("lerngruppe_id"),
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + v.getAsString("schueler_id") + "---" + v.getAsString("lerngruppe_id"));
    }

    public ContentValues updateSchueler_Lerngruppe(ContentValues old, ContentValues nu) {

        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SL_SCHUELER_ID, nu.getAsString("schueler_id"));
        values.put(MyDbHelper.SL_LERNGRUPPE_ID, nu.getAsString("lerngruppe_id"));


        database.update(MyDbHelper.TABLE_SCHUELER_LERNGRUPPE,
                values,
                MyDbHelper.SL_SCHUELER_ID + " = " + old.getAsString("schueler_id") + " AND " + MyDbHelper.SL_LERNGRUPPE_ID + " = " + old.getAsString("lerngruppe_id"),
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER_LERNGRUPPE,
                columns, MyDbHelper.SL_SCHUELER_ID + " = " + nu.getAsString("schueler_id") + " AND " + MyDbHelper.SL_LERNGRUPPE_ID + " = " + nu.getAsString("lerngruppe_id"),
                null, null, null, null);

        cursor.moveToFirst();
        ContentValues coco = cursorToSchueler_LerngruppeAsContentValue(cursor);
        cursor.close();

        return coco;

    }


    private ContentValues cursorToSchueler_LerngruppeAsContentValue(Cursor cursor) {

        ContentValues values = new ContentValues();


        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id1 = cursor.getColumnIndex(MyDbHelper.SL_SCHUELER_ID);
        int id2 = cursor.getColumnIndex(MyDbHelper.SL_LERNGRUPPE_ID);


        ;
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);

        values.put("schueler_id", q1);
        values.put("lerngruppe_id", q2);

        // Schueler_Lerngruppe schueler_lerngruppe = new Schueler_Lerngruppe(id,vorname,nachname,passwort,kuerzel,status);

        return values;
    }

    public List<ContentValues> getAllSchueler_Lerngruppes() {
        List<ContentValues> schueler_lerngruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER_LERNGRUPPE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        ContentValues schueler_lerngruppe;

        while (!cursor.isAfterLast()) {
            schueler_lerngruppe = cursorToSchueler_LerngruppeAsContentValue(cursor);
            schueler_lerngruppeList.add(schueler_lerngruppe);
            Log.d(LOG_TAG, "ID: " + schueler_lerngruppe.toString());
            cursor.moveToNext();
        }


        cursor.close();

        return schueler_lerngruppeList;
    }

    private Lerngruppe cursorToLerngruppe(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.LERNGRUPPE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.LERNGRUPPE_COLUMN_NAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.LERNGRUPPE_COLUMN_LERNFORM_COLUMN_ID);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        int q2 = cursor.getInt(id2);

        // Lerngruppe lerngruppe = new Lerngruppe(id,vorname,nachname,passwort,kuerzel,status);

        return new Lerngruppe(id,q1,q2);
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
        int id4 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GESCHLECHT);
        int id5 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);

        // Lerngruppe lerngruppe = new Lerngruppe(id,vorname,nachname,passwort,kuerzel,status);
      //  int id, String vorname, String nachname, String rufname, String geschlecht, String status, String geburtstag, String geburtsort
        return new Schueler(id,q1,q2,q3,null,q4,"1",q5,null);
    }








    public List<Lerngruppe> getLerngruppesFromSchuelerById(int i) {

        List<Lerngruppe> lerngruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_LERNGRUPPE+ " LEFT JOIN " + MyDbHelper.TABLE_SCHUELER_LERNGRUPPE + " ON " + MyDbHelper.TABLE_SCHUELER_LERNGRUPPE + "."
                        + MyDbHelper.SL_LERNGRUPPE_ID + " = " + MyDbHelper.LERNGRUPPE_COLUMN_ID,
                lerngruppeColumns,
                 MyDbHelper.TABLE_SCHUELER_LERNGRUPPE + "." + MyDbHelper.SL_SCHUELER_ID + " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        Lerngruppe lerngruppe;

        while (!cursor.isAfterLast()) {
            lerngruppe = cursorToLerngruppe(cursor);
            lerngruppeList.add(lerngruppe);
            //Log.d(LOG_TAG, "ID: " + lerngruppe);
            cursor.moveToNext();
        }


        cursor.close();


        return lerngruppeList;
    }




    public List<Schueler> getSchuelersFromLerngruppeById(int i) {
        Log.d(LOG_TAG, "ID::::::::::::::::::: " + i);
        List<Schueler> schuelerList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER+ " LEFT JOIN " + MyDbHelper.TABLE_SCHUELER_LERNGRUPPE + " ON " + MyDbHelper.TABLE_SCHUELER_LERNGRUPPE + "."
                        + MyDbHelper.SL_SCHUELER_ID + " = " + MyDbHelper.SCHUELER_COLUMN_ID,
                schuelerColumns,
                MyDbHelper.TABLE_SCHUELER_LERNGRUPPE + "." + MyDbHelper.SL_LERNGRUPPE_ID + " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler;

        while (!cursor.isAfterLast()) {
            schueler = cursorToSchueler(cursor);
            schuelerList.add(schueler);
            //Log.d(LOG_TAG, "ID: " + lerngruppe);
            cursor.moveToNext();
        }

        cursor.close();

        return schuelerList;
    }







}