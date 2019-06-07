package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Angehoeriger;
import model.Schueler;


public class DataSource_Schueler_Angehoeriger {

    private static final String LOG_TAG = DataSource_Schueler_Angehoeriger.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SA_SCHUELER_ID,
            MyDbHelper.SA_ANGEHOERIGER_ID,
    };


    private String[] angehoerigerColumns = {
            MyDbHelper.ANGEHOERIGER_COLUMN_ID,
            MyDbHelper.ANGEHOERIGER_COLUMN_VORNAME,
            MyDbHelper.ANGEHOERIGER_COLUMN_NACHNAME,
            MyDbHelper.ANGEHOERIGER_COLUMN_INSTITUTION,
            MyDbHelper.ANGEHOERIGER_COLUMN_BEZIEHUNG,
            MyDbHelper.ANGEHOERIGER_COLUMN_GESCHLECHT

    };

    private String[] schuelerColumns = {
            MyDbHelper.SCHUELER_COLUMN_ID,
            MyDbHelper.SCHUELER_COLUMN_VORNAME,
            MyDbHelper.SCHUELER_COLUMN_NACHNAME,
            MyDbHelper.SCHUELER_COLUMN_GESCHLECHT,
            MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG
    };




    public DataSource_Schueler_Angehoeriger(Context context) {
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

    public void createSchueler_Angehoeriger(ContentValues v) {


        database.insert(MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER, null, v);


    }

    public void deleteSchueler_Angehoeriger(ContentValues v) {


        database.delete(MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER,
                MyDbHelper.SL_SCHUELER_ID + " = " + v.getAsString("schueler_id") + " AND " + MyDbHelper.SA_ANGEHOERIGER_ID + " = " + v.getAsString("angehoeriger_id"),
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + v.getAsString("schueler_id") + "---" + v.getAsString("angehoeriger_id"));
    }

    public ContentValues updateSchueler_Angehoeriger(ContentValues old, ContentValues nu) {

        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SA_SCHUELER_ID, nu.getAsString(MyDbHelper.SA_SCHUELER_ID));
        values.put(MyDbHelper.SA_ANGEHOERIGER_ID, nu.getAsString(MyDbHelper.SA_ANGEHOERIGER_ID));


        database.update(MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER,
                values,
                MyDbHelper.SA_SCHUELER_ID + " = " + old.getAsString(MyDbHelper.SA_SCHUELER_ID) + " AND " + MyDbHelper.SA_ANGEHOERIGER_ID + " = " + old.getAsString(MyDbHelper.SA_ANGEHOERIGER_ID),
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER,
                columns, MyDbHelper.SA_SCHUELER_ID + " = " + nu.getAsString(MyDbHelper.SA_SCHUELER_ID) + " AND " + MyDbHelper.SA_ANGEHOERIGER_ID + " = " + nu.getAsString(MyDbHelper.SA_ANGEHOERIGER_ID),
                null, null, null, null);

        cursor.moveToFirst();
        ContentValues coco = cursorToSchueler_AngehoerigerAsContentValue(cursor);
        cursor.close();

        return coco;

    }


    private ContentValues cursorToSchueler_AngehoerigerAsContentValue(Cursor cursor) {

        ContentValues values = new ContentValues();


        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id1 = cursor.getColumnIndex(MyDbHelper.SA_SCHUELER_ID);
        int id2 = cursor.getColumnIndex(MyDbHelper.SA_ANGEHOERIGER_ID);


        ;
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);

        values.put("schueler_id", q1);
        values.put("angehoeriger_id", q2);

        // Schueler_Angehoeriger schueler_angehoeriger = new Schueler_Angehoeriger(id,vorname,nachname,passwort,kuerzel,status);

        return values;
    }

    public List<ContentValues> getAllSchueler_Angehoerigers() {
        List<ContentValues> schueler_angehoerigerList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        ContentValues schueler_angehoeriger;

        while (!cursor.isAfterLast()) {
            schueler_angehoeriger = cursorToSchueler_AngehoerigerAsContentValue(cursor);
            schueler_angehoerigerList.add(schueler_angehoeriger);
            Log.d(LOG_TAG, "ID: " + schueler_angehoeriger.toString());
            cursor.moveToNext();
        }


        cursor.close();

        return schueler_angehoerigerList;
    }

    private Angehoeriger cursorToAngehoeriger(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_VORNAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_NACHNAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_INSTITUTION);
        int id4 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_BEZIEHUNG);
        int id5 = cursor.getColumnIndex(MyDbHelper.ANGEHOERIGER_COLUMN_GESCHLECHT);

        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);


        // Angehoeriger angehoeriger = new Angehoeriger(id,vorname,nachname,passwort,kuerzel,status);

        return new Angehoeriger(id,q1,q2,q3,q4,q5);
    }



    private Schueler cursorToSchueler(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_VORNAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_NACHNAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GESCHLECHT);
        int id4 = cursor.getColumnIndex(MyDbHelper.SCHUELER_COLUMN_GEBURTSTAG);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);


        // Angehoeriger angehoeriger = new Angehoeriger(id,vorname,nachname,passwort,kuerzel,status);
      //  int id, String vorname, String nachname, String rufname, String geschlecht, String status, String geburtstag, String geburtsort
        return new Schueler(id,q1,q2,null,q3,"1",q4,null);
    }








    public List<Angehoeriger> getAngehoerigersFromSchuelerById(int i) {

        List<Angehoeriger> angehoerigerList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_ANGEHOERIGER+ " LEFT JOIN " + MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER + " ON " + MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER + "."
                        + MyDbHelper.SA_ANGEHOERIGER_ID + " = " + MyDbHelper.ANGEHOERIGER_COLUMN_ID,
                angehoerigerColumns,
                 MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER + "." + MyDbHelper.SA_SCHUELER_ID + " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        Angehoeriger angehoeriger;

        while (!cursor.isAfterLast()) {
            angehoeriger = cursorToAngehoeriger(cursor);
            angehoerigerList.add(angehoeriger);
            //Log.d(LOG_TAG, "ID: " + angehoeriger);
            cursor.moveToNext();
        }


        cursor.close();


        return angehoerigerList;
    }




    public List<Schueler> getSchuelersFromAngehoerigerById(int i) {

        List<Schueler> schuelerList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SCHUELER+ " LEFT JOIN " + MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER + " ON " + MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER + "."
                        + MyDbHelper.SA_SCHUELER_ID + " = " + MyDbHelper.SCHUELER_COLUMN_ID,
                schuelerColumns,
                MyDbHelper.TABLE_SCHUELER_ANGEHOERIGER + "." + MyDbHelper.SA_ANGEHOERIGER_ID + " = " +i,
                null, null, null, null);

        cursor.moveToFirst();
        Schueler schueler;

        while (!cursor.isAfterLast()) {
            schueler = cursorToSchueler(cursor);
            schuelerList.add(schueler);
            //Log.d(LOG_TAG, "ID: " + angehoeriger);
            cursor.moveToNext();
        }

        cursor.close();

        return schuelerList;
    }







}