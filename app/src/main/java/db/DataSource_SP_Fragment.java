package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.SP_Fragment;

public class DataSource_SP_Fragment {

    private static final String LOG_TAG = DataSource_SP_Fragment.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SP_FRAGMENT_COLUMN_ID,
            MyDbHelper.SP_FRAGMENT_COLUMN_NUMMER

    };

    public DataSource_SP_Fragment(Context context) {
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

    public SP_Fragment createSP_Fragment(String v1) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SP_FRAGMENT_COLUMN_NUMMER, v1);


        long insertId = database.insert(MyDbHelper.TABLE_SP_FRAGMENT, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FRAGMENT,
                columns, MyDbHelper.SP_FRAGMENT_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        SP_Fragment sp_Fragment = cursorToSP_Fragment(cursor);
        cursor.close();

        return sp_Fragment;
    }

    public void deleteSP_Fragment(SP_Fragment sp_Fragment) {
        long id = sp_Fragment.getId();

        database.delete(MyDbHelper.TABLE_SP_FRAGMENT,
                MyDbHelper.SP_FRAGMENT_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + sp_Fragment.toString());
    }

    public SP_Fragment updateSP_Fragment(int id, String v1) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SP_FRAGMENT_COLUMN_NUMMER, v1);


        database.update(MyDbHelper.TABLE_SP_FRAGMENT,
                values,
                MyDbHelper.SP_FRAGMENT_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FRAGMENT,
                columns, MyDbHelper.SP_FRAGMENT_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        SP_Fragment sp_Fragment = cursorToSP_Fragment(cursor);
        cursor.close();

        return sp_Fragment;
    }

    public SP_Fragment getSP_FragmentById(int id) {


        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FRAGMENT,
                columns, MyDbHelper.SP_FRAGMENT_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        SP_Fragment sp_Fragment = cursorToSP_Fragment(cursor);
        cursor.close();

        return sp_Fragment;


    }

    private SP_Fragment cursorToSP_Fragment(Cursor cursor) {


        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }
        int id0 = cursor.getColumnIndex(MyDbHelper.SP_FRAGMENT_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.SP_FRAGMENT_COLUMN_NUMMER);


        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);


        // SP_Fragment sp_Fragment = new SP_Fragment(id,vorname,nachname,passwort,kuerzel,status);

        return new SP_Fragment(id, q1);
    }

    public List<SP_Fragment> getAllSP_Fragments() {
        List<SP_Fragment> sp_FragmentList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SP_FRAGMENT,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        SP_Fragment sp_Fragment;

        while (!cursor.isAfterLast()) {
            sp_Fragment = cursorToSP_Fragment(cursor);
            sp_FragmentList.add(sp_Fragment);
            Log.d(LOG_TAG, "ID: " + sp_Fragment.getId() + ", Inhalt: " + sp_Fragment.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return sp_FragmentList;
    }

}