package db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = MyDbHelper.class.getSimpleName();

    public static final String DB_NAME = "pauli_rot_lite.db";
    public static final int DB_VERSION = 96;


    public static final String TABLE_KOLLEGE = "kollege";

    public static final String KOLLEGE_COLUMN_ID = "_id";
    public static final String KOLLEGE_COLUMN_VORNAME = "vorname";
    public static final String KOLLEGE_COLUMN_NACHNAME = "nachname";
    public static final String KOLLEGE_COLUMN_PASSWORT = "passwort";
    public static final String KOLLEGE_COLUMN_KUERZEL = "kuerzel";
    public static final String KOLLEGE_COLUMN_STATUS = "status";

    public static final String SQL_CREATE_KOLLEGE =
            "CREATE TABLE " + TABLE_KOLLEGE +
                    "(" + KOLLEGE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    KOLLEGE_COLUMN_VORNAME + " TEXT NULL, " +
                    KOLLEGE_COLUMN_NACHNAME + " TEXT NOT NULL, " +
                    KOLLEGE_COLUMN_PASSWORT + " TEXT NOT NULL, " +
                    KOLLEGE_COLUMN_KUERZEL + " TEXT NULL, " +
                    KOLLEGE_COLUMN_STATUS + " TEXT NULL);";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_SCHULVERBUND = "schulverbund";

    public static final String SCHULVERBUND_COLUMN_ID = "_id";
    public static final String SCHULVERBUND_COLUMN_NAME = "name";
    public static final String SCHULVERBUND_COLUMN_TRAEGER = "traeger";
    public static final String SCHULVERBUND_COLUMN_BUNDESLAND = "bundesland";
    public static final String SCHULVERBUND_COLUMN_BEZIRK = "bezirk";
    public static final String SCHULVERBUND_COLUMN_TYP = "typ";
    public static final String SCHULVERBUND_COLUMN_EMAIL = "email";
    public static final String SCHULVERBUND_COLUMN_WWW = "www";
    public static final String SCHULVERBUND_COLUMN_SCHUELERZAHL = "schuelerzahl";
    public static final String SCHULVERBUND_COLUMN_LEITUNG = "leitung";
    public static final String SCHULVERBUND_COLUMN_STATUS = "status";
    public static final String SCHULVERBUND_COLUMN_MAINSERVERADRESS = "mainserveradress";


    public static final String SQL_CREATE_SCHULVERBUND =
            "CREATE TABLE " + TABLE_SCHULVERBUND +
                    "(" + SCHULVERBUND_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    SCHULVERBUND_COLUMN_NAME + " TEXT, " +
                    SCHULVERBUND_COLUMN_TRAEGER + "  TEXT, " +
                    SCHULVERBUND_COLUMN_BUNDESLAND + " TEXT, " +
                    SCHULVERBUND_COLUMN_BEZIRK + " TEXT, " +
                    SCHULVERBUND_COLUMN_TYP + " TEXT, " +
                    SCHULVERBUND_COLUMN_EMAIL + " TEXT, " +
                    SCHULVERBUND_COLUMN_WWW + " TEXT, " +
                    SCHULVERBUND_COLUMN_SCHUELERZAHL + " TEXT, " +
                    SCHULVERBUND_COLUMN_LEITUNG + " TEXT, " +
                    SCHULVERBUND_COLUMN_STATUS + " TEXT, " +
                    SCHULVERBUND_COLUMN_MAINSERVERADRESS + " TEXT);";


//------------------------------------------------------------------------------------------

    public static final String TABLE_STANDORT = "standort";

    public static final String STANDORT_COLUMN_ID = "_id";
    public static final String STANDORT_COLUMN_NAME = "name";
    public static final String STANDORT_COLUMN_KONTAKTPERSON = "kontaktperson";
    public static final String STANDORT_COLUMN_STANDORTEMAIL = "standortemail";
    public static final String STANDORT_COLUMN_HAUPTSTANDORT = "hauptstandort";
    public static final String STANDORT_COLUMN_STADTTEIL = "stadtteil";
    public static final String STANDORT_COLUMN_STATUS = "status";
    public static final String STANDORT_COLUMN_SERVERADRESSE = "serveradresse";

    public static final String SQL_CREATE_STANDORT =
            "CREATE TABLE " + TABLE_STANDORT +
                    "(" + STANDORT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    STANDORT_COLUMN_NAME + " TEXT, " +
                    STANDORT_COLUMN_KONTAKTPERSON + "  TEXT, " +
                    STANDORT_COLUMN_STANDORTEMAIL + " TEXT, " +
                    STANDORT_COLUMN_HAUPTSTANDORT + " TEXT, " +
                    STANDORT_COLUMN_STADTTEIL + " TEXT, " +
                    STANDORT_COLUMN_STATUS + " TEXT, " +
                    STANDORT_COLUMN_SERVERADRESSE + " TEXT);";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_ADRESSE = "adresse";

    public static final String ADRESSE_COLUMN_ID = "_id";
    public static final String ADRESSE_COLUMN_STRASSE = "straße";
    public static final String ADRESSE_COLUMN_PLZ = "plz";
    public static final String ADRESSE_COLUMN_ORT = "ort";
    public static final String ADRESSE_COLUMN_TEL = "tel";
    public static final String ADRESSE_COLUMN_FAX = "fax";

    public static final String SQL_CREATE_ADRESSE =
            "CREATE TABLE " + TABLE_ADRESSE +
                    "(" + ADRESSE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    ADRESSE_COLUMN_STRASSE + " TEXT, " +
                    ADRESSE_COLUMN_PLZ + "  TEXT, " +
                    ADRESSE_COLUMN_ORT + " TEXT, " +
                    ADRESSE_COLUMN_TEL + " TEXT, " +
                    ADRESSE_COLUMN_FAX + " TEXT);";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_SCHUELER = "schueler";

    public static final String SCHUELER_COLUMN_ID = "_id";
    public static final String SCHUELER_COLUMN_VORNAME = "vorname";
    public static final String SCHUELER_COLUMN_NACHNAME = "nachname";
    public static final String SCHUELER_COLUMN_RUFNAME = "rufname";
    public static final String SCHUELER_COLUMN_GESCHLECHT = "geschlecht";
    public static final String SCHUELER_COLUMN_STATUS = "status";
    public static final String SCHUELER_COLUMN_GEBURTSTAG = "geburtstag";
    public static final String SCHUELER_COLUMN_GEBURTSORT = "geburtsort";


    public static final String SQL_CREATE_SCHUELER =
            "CREATE TABLE " + TABLE_SCHUELER +
                    "(" + SCHUELER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    SCHUELER_COLUMN_VORNAME + " TEXT, " +
                    SCHUELER_COLUMN_NACHNAME + "  TEXT, " +
                    SCHUELER_COLUMN_RUFNAME + " TEXT, " +
                    SCHUELER_COLUMN_GESCHLECHT + " TEXT, " +
                    SCHUELER_COLUMN_STATUS + " TEXT, " +
                    SCHUELER_COLUMN_GEBURTSTAG + " TEXT, " +
                    SCHUELER_COLUMN_GEBURTSORT + " TEXT);";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_KONTAKT = "kontakt";

    public static final String KONTAKT_COLUMN_ID = "_id";
    public static final String KONTAKT_COLUMN_MOBIL = "mobil";
    public static final String KONTAKT_COLUMN_MAIL = "mail";

    public static final String SQL_CREATE_KONTAKT =
            "CREATE TABLE " + TABLE_KONTAKT +
                    "(" + KONTAKT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    KONTAKT_COLUMN_MOBIL + " TEXT, " +
                    KONTAKT_COLUMN_MAIL + "  TEXT);";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_ANGEHOERIGER = "angehoeriger";

    public static final String ANGEHOERIGER_COLUMN_ID = "_id";
    public static final String ANGEHOERIGER_COLUMN_NACHNAME = "nachname";
    public static final String ANGEHOERIGER_COLUMN_VORNAME = "vorname";
    public static final String ANGEHOERIGER_COLUMN_INSTITUTION = "institution";
    public static final String ANGEHOERIGER_COLUMN_BEZIEHUNG = "beziehung";
    public static final String ANGEHOERIGER_COLUMN_GESCHLECHT = "geschlecht";

    public static final String SQL_CREATE_ANGEHOERIGER =
            "CREATE TABLE " + TABLE_ANGEHOERIGER +
                    "(" + ANGEHOERIGER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    ANGEHOERIGER_COLUMN_NACHNAME + " TEXT, " +
                    ANGEHOERIGER_COLUMN_VORNAME + "  TEXT, " +
                    ANGEHOERIGER_COLUMN_INSTITUTION + " TEXT, " +
                    ANGEHOERIGER_COLUMN_BEZIEHUNG + " TEXT, " +
                    ANGEHOERIGER_COLUMN_GESCHLECHT + " TEXT);";


    //------------------------------------------------------------------------------------------


    public static final String TABLE_VERGEHEN = "vergehen";

    public static final String VERGEHEN_COLUMN_ID = "_id";
    public static final String VERGEHEN_COLUMN_NAME = "name";
    public static final String VERGEHEN_COLUMN_TEXT = "text";
    public static final String VERGEHEN_COLUMN_GEWICHT = "gewicht";
    public static final String VERGEHEN_COLUMN_KATEGORIE = "kategorie";


    public static final String SQL_CREATE_VERGEHEN =
            "CREATE TABLE " + TABLE_VERGEHEN +
                    "(" + VERGEHEN_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    VERGEHEN_COLUMN_NAME + " TEXT, " +
                    VERGEHEN_COLUMN_TEXT + "  TEXT, " +
                    VERGEHEN_COLUMN_GEWICHT + " TEXT, " +
                    VERGEHEN_COLUMN_KATEGORIE + " TEXT);";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_THEMA = "thema";

    public static final String THEMA_COLUMN_ID = "_id";
    public static final String THEMA_COLUMN_NAME = "name";

    public static final String SQL_CREATE_THEMA =
            "CREATE TABLE " + TABLE_THEMA +
                    "(" + THEMA_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    THEMA_COLUMN_NAME + " TEXT);";




    //------------------------------------------------------------------------------------------

    public static final String TABLE_RAUM = "raum";

    public static final String RAUM_COLUMN_ID = "_id";
    public static final String RAUM_COLUMN_HAUS = "haus";
    public static final String RAUM_COLUMN_NAME = "name";


    public static final String SQL_CREATE_RAUM =
            "CREATE TABLE " + TABLE_RAUM +
                    "(" + RAUM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    RAUM_COLUMN_HAUS + " TEXT, " +
                    RAUM_COLUMN_NAME + "  TEXT);";



    //------------------------------------------------------------------------------------------

    public static final String TABLE_GUELTIGKEITSBEREICH = "gueltigkeitsbereich";

    public static final String GUELTIGKEITSBEREICH_COLUMN_ID = "_id";
    public static final String GUELTIGKEITSBEREICH_COLUMN_SCHULJAHR = "schuljahr";
    public static final String GUELTIGKEITSBEREICH_COLUMN_TYP = "typ";
    public static final String GUELTIGKEITSBEREICH_COLUMN_NUMMER = "nummer";
    public static final String GUELTIGKEITSBEREICH_COLUMN_VON = "von";
    public static final String GUELTIGKEITSBEREICH_COLUMN_BIS = "bis";

    public static final String SQL_CREATE_GUELTIGKEITSBEREICH =
            "CREATE TABLE " + TABLE_GUELTIGKEITSBEREICH +
                    "(" + GUELTIGKEITSBEREICH_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    GUELTIGKEITSBEREICH_COLUMN_SCHULJAHR + " TEXT, " +
                    GUELTIGKEITSBEREICH_COLUMN_TYP + "  TEXT, " +
                    GUELTIGKEITSBEREICH_COLUMN_NUMMER + " TEXT, " +
                    GUELTIGKEITSBEREICH_COLUMN_VON + " BIGINT, " +
                    GUELTIGKEITSBEREICH_COLUMN_BIS + " BIGINT);";





       //------------------------------------------------------------------------------------------

    public static final String TABLE_LERNGRUPPE = "lerngruppe";

    public static final String LERNGRUPPE_COLUMN_ID = "_id";
    public static final String LERNGRUPPE_COLUMN_NAME = "name";


    public static final String SQL_CREATE_LERNGRUPPE =
            "CREATE TABLE " + TABLE_LERNGRUPPE +
                    "(" + LERNGRUPPE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    LERNGRUPPE_COLUMN_NAME + " TEXT);";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_VERGEHENGRUPPE = "vergehengruppe";

    public static final String VERGEHENGRUPPE_COLUMN_ID = "_id";
    public static final String VERGEHENGRUPPE_COLUMN_NAME = "name";


    public static final String SQL_CREATE_VERGEHENGRUPPE =
            "CREATE TABLE " + TABLE_VERGEHENGRUPPE +
                    "(" + VERGEHENGRUPPE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    VERGEHENGRUPPE_COLUMN_NAME + " TEXT);";




    //------------------------------------------------------------------------------------------

    public static final String TABLE_VORFALL = "vorfall";

    public static final String VORFALL_COLUMN_ID = "_id";
    public static final String VORFALL_COLUMN_ZEITPUNKT = "zeitpunkt";
    public static final String VORFALL_COLUMN_INFO = "info";
    public static final String VORFALL_COLUMN_SCHUELER_ID = "schueler_id";
    public static final String VORFALL_COLUMN_VERGEHEN_ID = "vergehen_id";

    public static final String SQL_CREATE_VORFALL =
            "CREATE TABLE " + TABLE_VORFALL +
                    "(" +

                    VORFALL_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VORFALL_COLUMN_ZEITPUNKT + " TEXT, " +
                    VORFALL_COLUMN_INFO + "  TEXT, " +
                    VORFALL_COLUMN_SCHUELER_ID + "  INTEGER, " +
                    VORFALL_COLUMN_VERGEHEN_ID + "  INTEGER, " +

                    " FOREIGN KEY " + "(" + VORFALL_COLUMN_SCHUELER_ID + ")" + " REFERENCES " + TABLE_SCHUELER + "(" + SCHUELER_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE, " +
                    " FOREIGN KEY " + "(" + VORFALL_COLUMN_VERGEHEN_ID + ")" + " REFERENCES " + TABLE_VERGEHEN + "(" + VERGEHEN_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE " +

                    ");";
    //------------------------------------------------------------------------------------------

















    public static final String TABLE_SP_FACH = "sp_fach";

    public static final String SP_FACH_COLUMN_ID = "_id";
    public static final String SP_FACH_COLUMN_TESTSTRING = "teststring";
    public static final String SP_FACH_COLUMN_THEMA_COLUMN_ID = "thema_id";
    public static final String SP_FACH_COLUMN_RAUM_COLUMN_ID = "raum_id";
    public static final String SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID = "gueltigkeitsbereich_id";
    public static final String SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID = "lerngruppe_id";

    public static final String SQL_CREATE_SP_FACH =
            "CREATE TABLE " + TABLE_SP_FACH +
                    "(" + SP_FACH_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SP_FACH_COLUMN_TESTSTRING + " TEXT, " +
                    SP_FACH_COLUMN_THEMA_COLUMN_ID + " INTEGER, " +
                    SP_FACH_COLUMN_RAUM_COLUMN_ID + " INTEGER, " +
                    SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID + " INTEGER, " +
                    SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID + " INTEGER, " +

                    " FOREIGN KEY " + "(" + SP_FACH_COLUMN_THEMA_COLUMN_ID + ")" + " REFERENCES " + TABLE_THEMA + "(" + THEMA_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE, " +
                    " FOREIGN KEY " + "(" + SP_FACH_COLUMN_RAUM_COLUMN_ID + ")" + " REFERENCES " + TABLE_RAUM + "(" + RAUM_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE, " +
                    " FOREIGN KEY " + "(" + SP_FACH_COLUMN_GUELTIGKEITSBEREICH_COLUMN_ID + ")" + " REFERENCES " + TABLE_GUELTIGKEITSBEREICH + "(" + GUELTIGKEITSBEREICH_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE, " +
                    " FOREIGN KEY " + "(" + SP_FACH_COLUMN_LERNGRUPPE_COLUMN_ID + ")" + " REFERENCES " + TABLE_LERNGRUPPE + "(" + LERNGRUPPE_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE " +

                    ");";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_SP_FRAGMENT = "sp_fragment";

    public static final String SP_FRAGMENT_COLUMN_ID = "_id";
    public static final String SP_FRAGMENT_COLUMN_NUMMER = "nummer";

    public static final String SQL_CREATE_SP_FRAGMENT =
            "CREATE TABLE " + TABLE_SP_FRAGMENT +
                    "(" + SP_FRAGMENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    SP_FRAGMENT_COLUMN_NUMMER + " TEXT);";





    //------------------------------------------------------------------------------------------

    public static final String TABLE_RYTHMUSZELLE = "rhythmuszelle";

    public static final String RYTHMUSZELLE_COLUMN_ID = "_id";
    public static final String RYTHMUSZELLE_COLUMN_WOCHENTAG = "wochentag";
    public static final String RYTHMUSZELLE_COLUMN_TYP = "typ";
    public static final String RYTHMUSZELLE_COLUMN_NUMMER = "nummer";
    public static final String RYTHMUSZELLE_COLUMN_VON = "von";
    public static final String RYTHMUSZELLE_COLUMN_BIS = "bis";


    public static final String SQL_CREATE_RYTHMUSZELLE =
            "CREATE TABLE " + TABLE_RYTHMUSZELLE +
                    "(" + RYTHMUSZELLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    RYTHMUSZELLE_COLUMN_WOCHENTAG + " TEXT, " +
                    RYTHMUSZELLE_COLUMN_TYP + "  TEXT, " +
                    RYTHMUSZELLE_COLUMN_NUMMER + " TEXT, " +
                    RYTHMUSZELLE_COLUMN_VON + " TEXT, " +
                    RYTHMUSZELLE_COLUMN_BIS + " TEXT);";







    //------------------------------------------------------------------------------------------

    public static final String TABLE_LERNFORM = "lernform";

    public static final String LERNFORM_COLUMN_ID = "_id";
    public static final String LERNFORM_COLUMN_NAME = "name";
    public static final String LERNFORM_COLUMN_ZYKLUS = "zyklus";

    public static final String SQL_CREATE_LERNFORM =
            "CREATE TABLE " + TABLE_LERNFORM +
                    "(" + LERNFORM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    LERNFORM_COLUMN_NAME + " TEXT, " +
                    LERNFORM_COLUMN_ZYKLUS + "  TEXT);";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_SCHUELER_LERNGRUPPE = "schueler_lerngruppe";

    public static final String SL_SCHUELER_ID = "schueler_id";
    public static final String SL_LERNGRUPPE_ID = "lerngruppe_id";


    public static final String SQL_CREATE_SCHUELER_LERNGRUPPE =
            "CREATE TABLE " + TABLE_SCHUELER_LERNGRUPPE +
                    "(" + SL_SCHUELER_ID + " INTEGER NOT NULL, " +
                    SL_LERNGRUPPE_ID + " INTEGER NOT NULL," +
                    " PRIMARY KEY(" + SL_SCHUELER_ID + ", " + SL_LERNGRUPPE_ID + ")," +
                    " FOREIGN KEY " + "(" + SL_SCHUELER_ID + ")" + " REFERENCES " + TABLE_SCHUELER + "(" + SCHUELER_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE, " +
                    " FOREIGN KEY " + "(" + SL_LERNGRUPPE_ID + ")" + " REFERENCES " + TABLE_LERNGRUPPE + "(" + LERNGRUPPE_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE " +
                    ");";





    //------------------------------------------------------------------------------------------

    public static final String TABLE_VERGEHEN_VERGEHENGRUPPE = "vergehen_vergehengruppe";

    public static final String SL_VERGEHEN_ID = "vergehen_id";
    public static final String SL_VERGEHENGRUPPE_ID = "vergehengruppe_id";


    public static final String SQL_CREATE_VERGEHEN_VERGEHENGRUPPE =
            "CREATE TABLE " + TABLE_VERGEHEN_VERGEHENGRUPPE +
                    "(" + SL_VERGEHEN_ID + " INTEGER NOT NULL, " +
                    SL_VERGEHENGRUPPE_ID + " INTEGER NOT NULL," +
                    " PRIMARY KEY(" + SL_VERGEHEN_ID + ", " + SL_VERGEHENGRUPPE_ID + ")," +
                    " FOREIGN KEY " + "(" + SL_VERGEHEN_ID + ")" + " REFERENCES " + TABLE_VERGEHEN + "(" + VERGEHEN_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE, " +
                    " FOREIGN KEY " + "(" + SL_VERGEHENGRUPPE_ID + ")" + " REFERENCES " + TABLE_VERGEHENGRUPPE + "(" + VERGEHENGRUPPE_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE " +
                    ");";









    //------------------------------------------------------------------------------------------

    public static final String TABLE_KOLLEGE_SCHUELER = "kollege_schueler";

    public static final String KS_KOLLEGE_ID = "kollege_id";
    public static final String KS_SCHUELER_ID = "schueler_id";


    public static final String SQL_CREATE_KOLLEGE_SCHUELER =
            "CREATE TABLE " + TABLE_KOLLEGE_SCHUELER +
                    "(" + KS_KOLLEGE_ID + " INTEGER NOT NULL, " +
                    KS_SCHUELER_ID + " INTEGER NOT NULL," +
                    " PRIMARY KEY(" + KS_KOLLEGE_ID + ", " + KS_SCHUELER_ID + ")," +
                    " FOREIGN KEY " + "(" + KS_KOLLEGE_ID + ")" + " REFERENCES " + TABLE_KOLLEGE + "(" + KOLLEGE_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE, " +
                    " FOREIGN KEY " + "(" + KS_SCHUELER_ID + ")" + " REFERENCES " + TABLE_SCHUELER + "(" + SCHUELER_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE );";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_KOLLEGE_STANDORT = "kollege_standort";

    public static final String KSTA_KOLLEGE_ID = "kollege_id";
    public static final String KSTA_STANDORT_ID = "standort_id";


    public static final String SQL_CREATE_KOLLEGE_STANDORT =
            "CREATE TABLE " + TABLE_KOLLEGE_STANDORT +
                    "(" + KSTA_KOLLEGE_ID + " INTEGER NOT NULL, " +
                    KSTA_STANDORT_ID + " INTEGER NOT NULL," +
                    " PRIMARY KEY(" + KSTA_KOLLEGE_ID + ", " + KSTA_STANDORT_ID + ")," +
                    " FOREIGN KEY " + "(" + KSTA_KOLLEGE_ID + ")" + " REFERENCES " + TABLE_KOLLEGE + "(" + KOLLEGE_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE, " +
                    " FOREIGN KEY " + "(" + KSTA_STANDORT_ID + ")" + " REFERENCES " + TABLE_STANDORT + "(" + STANDORT_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE " +
                    ");";


    //------------------------------------------------------------------------------------------

    public static final String TABLE_SCHUELER_ANGEHOERIGER = "schueler_angehoeriger";

    public static final String SA_SCHUELER_ID = "schueler_id";
    public static final String SA_ANGEHOERIGER_ID = "angehoeriger_id";


    public static final String SQL_CREATE_SCHUELER_ANGEHOERIGER =
            "CREATE TABLE " + TABLE_SCHUELER_ANGEHOERIGER +
                    "(" + SA_SCHUELER_ID + " INTEGER NOT NULL, " +
                    SA_ANGEHOERIGER_ID + " INTEGER NOT NULL," +
                    " PRIMARY KEY(" + SA_SCHUELER_ID + ", " + SA_ANGEHOERIGER_ID + ")," +
                    " FOREIGN KEY " + "(" + SA_SCHUELER_ID + ")" + " REFERENCES " + TABLE_SCHUELER + "(" + SCHUELER_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE, " +
                    " FOREIGN KEY " + "(" + SA_ANGEHOERIGER_ID + ")" + " REFERENCES " + TABLE_ANGEHOERIGER + "(" + ANGEHOERIGER_COLUMN_ID + ") " + "ON DELETE CASCADE ON UPDATE CASCADE " +
                    ");";


    //------------------------------------------------------------------------------------------

    public static final String SQL_DROP_KOLLEGE = "DROP TABLE IF EXISTS " + TABLE_KOLLEGE;
    public static final String SQL_DROP_SCHULVERBUND = "DROP TABLE IF EXISTS " + TABLE_SCHULVERBUND;
    public static final String SQL_DROP_STANDORT = "DROP TABLE IF EXISTS " + TABLE_STANDORT;
    public static final String SQL_DROP_ADRESSE = "DROP TABLE IF EXISTS " + TABLE_ADRESSE;
    public static final String SQL_DROP_SCHUELER = "DROP TABLE IF EXISTS " + TABLE_SCHUELER;
    public static final String SQL_DROP_KONTAKT = "DROP TABLE IF EXISTS " + TABLE_KONTAKT;
    public static final String SQL_DROP_ANGEHOERIGER = "DROP TABLE IF EXISTS " + TABLE_ANGEHOERIGER;
    public static final String SQL_DROP_VORFALL = "DROP TABLE IF EXISTS " + TABLE_VORFALL;
    public static final String SQL_DROP_VERGEHEN = "DROP TABLE IF EXISTS " + TABLE_VERGEHEN;
    public static final String SQL_DROP_GUELTIGKEITSBEREICH = "DROP TABLE IF EXISTS " + TABLE_GUELTIGKEITSBEREICH;
    public static final String SQL_DROP_RAUM = "DROP TABLE IF EXISTS " + TABLE_RAUM;
    public static final String SQL_DROP_THEMA = "DROP TABLE IF EXISTS " + TABLE_THEMA;
    public static final String SQL_DROP_LERNGRUPPE = "DROP TABLE IF EXISTS " + TABLE_LERNGRUPPE;

    public static final String SQL_DROP_VERGEHENGRUPPE = "DROP TABLE IF EXISTS " + TABLE_VERGEHENGRUPPE;
    public static final String SQL_DROP_SP_FACH = "DROP TABLE IF EXISTS " + TABLE_SP_FACH;
    public static final String SQL_DROP_SP_FRAGMENT = "DROP TABLE IF EXISTS " + TABLE_SP_FRAGMENT;
    public static final String SQL_DROP_RYTHMUSZELLE = "DROP TABLE IF EXISTS " + TABLE_RYTHMUSZELLE;

    public static final String SQL_DROP_LERNFORM = "DROP TABLE IF EXISTS " + TABLE_LERNFORM;

    public static final String SQL_DROP_SCHUELER_LERNGRUPPE = "DROP TABLE IF EXISTS " + TABLE_SCHUELER_LERNGRUPPE;
    public static final String SQL_DROP_VERGEHEN_VERGEHENGRUPPE = "DROP TABLE IF EXISTS " + TABLE_VERGEHEN_VERGEHENGRUPPE;
    public static final String SQL_DROP_KOLLEGE_SCHUELER = "DROP TABLE IF EXISTS " + TABLE_KOLLEGE_SCHUELER;
    public static final String SQL_DROP_KOLLEGE_STANDORT = "DROP TABLE IF EXISTS " + TABLE_KOLLEGE_STANDORT;
    public static final String SQL_DROP_SCHUELER_ANGEHOERIGER = "DROP TABLE IF EXISTS " + TABLE_SCHUELER_ANGEHOERIGER;


    public MyDbHelper(Context context) {
        //super(context, "PLATZHALTER_DATENBANKNAME", null, 1);
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }


    // Die onCreate-Methode wird nur aufgerufen, falls die Datenbank noch nicht existiert
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("PRAGMA foreign_keys=ON");

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_KOLLEGE + " angelegt.");
            db.execSQL(SQL_CREATE_KOLLEGE);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_SCHUELER + " angelegt.");
            db.execSQL(SQL_CREATE_SCHUELER);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_SCHULVERBUND + " angelegt.");
            db.execSQL(SQL_CREATE_SCHULVERBUND);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_STANDORT + " angelegt.");
            db.execSQL(SQL_CREATE_STANDORT);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_ADRESSE + " angelegt.");
            db.execSQL(SQL_CREATE_ADRESSE);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_KONTAKT + " angelegt.");
            db.execSQL(SQL_CREATE_KONTAKT);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_ANGEHOERIGER + " angelegt.");
            db.execSQL(SQL_CREATE_ANGEHOERIGER);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_VORFALL + " angelegt.");
            db.execSQL(SQL_CREATE_VORFALL);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_VERGEHEN + " angelegt.");
            db.execSQL(SQL_CREATE_VERGEHEN);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_LERNGRUPPE + " angelegt.");
            db.execSQL(SQL_CREATE_LERNGRUPPE);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_VERGEHENGRUPPE + " angelegt.");
            db.execSQL(SQL_CREATE_VERGEHENGRUPPE);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_GUELTIGKEITSBEREICH + " angelegt.");
            db.execSQL(SQL_CREATE_GUELTIGKEITSBEREICH);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_RAUM + " angelegt.");
            db.execSQL(SQL_CREATE_RAUM);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_THEMA + " angelegt.");
            db.execSQL(SQL_CREATE_THEMA);


            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_SP_FACH + " angelegt.");
            db.execSQL(SQL_CREATE_SP_FACH);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_SP_FRAGMENT + " angelegt.");
            db.execSQL(SQL_CREATE_SP_FRAGMENT);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_RYTHMUSZELLE + " angelegt.");
            db.execSQL(SQL_CREATE_RYTHMUSZELLE);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_LERNFORM + " angelegt.");
            db.execSQL(SQL_CREATE_LERNFORM);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_SCHUELER_LERNGRUPPE + " angelegt.");
            db.execSQL(SQL_CREATE_SCHUELER_LERNGRUPPE);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_VERGEHEN_VERGEHENGRUPPE + " angelegt.");
            db.execSQL(SQL_CREATE_VERGEHEN_VERGEHENGRUPPE);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_KOLLEGE_STANDORT + " angelegt.");
            db.execSQL(SQL_CREATE_KOLLEGE_STANDORT);

            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_SCHUELER_ANGEHOERIGER + " angelegt.");
            db.execSQL(SQL_CREATE_SCHUELER_ANGEHOERIGER);

        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    // Die onUpgrade-Methode wird aufgerufen, sobald die neue Versionsnummer höher
    // als die alte Versionsnummer ist und somit ein Upgrade notwendig wird
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.d(LOG_TAG, "Die Tabelle mit Versionsnummer " + oldVersion + " wird entfernt.");
        db.execSQL(SQL_DROP_KOLLEGE);
        db.execSQL(SQL_DROP_SCHULVERBUND);
        db.execSQL(SQL_DROP_STANDORT);
        db.execSQL(SQL_DROP_ADRESSE);
        db.execSQL(SQL_DROP_SCHUELER);
        db.execSQL(SQL_DROP_KONTAKT);
        db.execSQL(SQL_DROP_ANGEHOERIGER);
        db.execSQL(SQL_DROP_VORFALL);
        db.execSQL(SQL_DROP_VERGEHEN);
        db.execSQL(SQL_DROP_SP_FACH);
        db.execSQL(SQL_DROP_SP_FRAGMENT);
        db.execSQL(SQL_DROP_GUELTIGKEITSBEREICH);
        db.execSQL(SQL_DROP_RYTHMUSZELLE);
        db.execSQL(SQL_DROP_RAUM);
        db.execSQL(SQL_DROP_THEMA);
        db.execSQL(SQL_DROP_LERNGRUPPE);
        db.execSQL(SQL_DROP_VERGEHENGRUPPE);
        db.execSQL(SQL_DROP_LERNFORM);


        db.execSQL(SQL_DROP_SCHUELER_LERNGRUPPE);
        db.execSQL(SQL_DROP_VERGEHEN_VERGEHENGRUPPE);
        db.execSQL(SQL_DROP_KOLLEGE_SCHUELER);
        db.execSQL(SQL_DROP_KOLLEGE_STANDORT);
        db.execSQL(SQL_DROP_SCHUELER_ANGEHOERIGER);


        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        db.execSQL("PRAGMA foreign_keys=ON");
    }
}