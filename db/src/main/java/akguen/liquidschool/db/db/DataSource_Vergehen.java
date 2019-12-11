package akguen.liquidschool.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Vergehen;

public class DataSource_Vergehen {

    private static final String LOG_TAG = DataSource_Vergehen.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.VERGEHEN_COLUMN_ID,
            MyDbHelper.VERGEHEN_COLUMN_NAME,
            MyDbHelper.VERGEHEN_COLUMN_TEXT,
            MyDbHelper.VERGEHEN_COLUMN_GEWICHT,
            MyDbHelper.VERGEHEN_COLUMN_KATEGORIE,
 
    };

    public DataSource_Vergehen(Context context) {
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

    public Vergehen createVergehen(String v1, String v2, String v3, String v4) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.VERGEHEN_COLUMN_NAME, v1);
        values.put(MyDbHelper.VERGEHEN_COLUMN_TEXT, v2);
        values.put(MyDbHelper.VERGEHEN_COLUMN_GEWICHT, v3);
        values.put(MyDbHelper.VERGEHEN_COLUMN_KATEGORIE, v4);



        long insertId = database.insert(MyDbHelper.TABLE_VERGEHEN, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHEN,
                columns, MyDbHelper.VERGEHEN_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Vergehen vergehen = cursorToVergehen(cursor);
        cursor.close();

        return vergehen;
    }

    public void deleteVergehen(Vergehen vergehen) {
        long id = vergehen.getId();

        database.delete(MyDbHelper.TABLE_VERGEHEN,
                MyDbHelper.VERGEHEN_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + vergehen.toString());
    }

    public Vergehen updateVergehen(int id, String v1, String v2, String v3, String v4) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.VERGEHEN_COLUMN_NAME, v1);
        values.put(MyDbHelper.VERGEHEN_COLUMN_TEXT, v2);
        values.put(MyDbHelper.VERGEHEN_COLUMN_GEWICHT, v3);
        values.put(MyDbHelper.VERGEHEN_COLUMN_KATEGORIE, v4);



        database.update(MyDbHelper.TABLE_VERGEHEN,
                values,
                MyDbHelper.VERGEHEN_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHEN,
                columns, MyDbHelper.VERGEHEN_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Vergehen vergehen = cursorToVergehen(cursor);
        cursor.close();

        return vergehen;
    }
    public Vergehen getVergehenById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHEN,
                columns, MyDbHelper.VERGEHEN_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Vergehen vergehen = cursorToVergehen(cursor);
        cursor.close();

        return vergehen;


    }
    private Vergehen cursorToVergehen(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_NAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_TEXT);
        int id3 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_GEWICHT);
        int id4 = cursor.getColumnIndex(MyDbHelper.VERGEHEN_COLUMN_KATEGORIE);



        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);





        return new Vergehen(id,q1,q2,q3,q4);
    }

    public List<Vergehen> getAllVergehens() {
        List<Vergehen> vergehenList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_VERGEHEN,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Vergehen vergehen;

        while(!cursor.isAfterLast()) {
            vergehen = cursorToVergehen(cursor);
            vergehenList.add(vergehen);
            Log.d(LOG_TAG, "ID: " + vergehen.getId() + ", Inhalt: " + vergehen.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return vergehenList;
    }

}