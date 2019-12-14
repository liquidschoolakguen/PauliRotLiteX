package akguen.liquidschool.paulirotlite.rest;
/*

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.db.model.Kollege;

public class DatenbankTask extends AsyncTask<String, Void, String> {

    Connection connection = null;
    Statement statement;
    ResultSet resultSet = null;
    String sql;
    String sqltest;
    String sqltest2;

    String dbDaten = "";
    String dbId, dbName, dbpasswort;


    String tvname;
    String tvpassword;

    String kollegename;
    private TextView textView;

    List<Kollege> kollegeList;


    boolean kollegeExistiert;


    List<Kollege> list = new ArrayList<>();

    public DatenbankTask() {

    }

    @Override
    protected String doInBackground(String... params) {
        Log.i("ReadWebpageAsyncTa: ", "----------: " + params[0]);

        String url = "jdbc:mysql://192.168.178.20/pauli_test"; //Zu Hause
        //String url = "jdbc:mysql://10.0.3.246/pauli_test"; //Schule
        //10.0.3.246
        // Für das Device OK ZU HAUSE!
        //String url = "jdbc:mysql://192.168.1.5/pauli_test";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Class.forName("com.mysql.jdbc.Driver");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, "Akgün", "unavejun93947");

        } catch (SQLException e) {

            e.printStackTrace();
        }


        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (params[0].equals("getAlleKollegen")) {
            case_getAlleKollegen();
            return null;
        }
        if (params[0].equals("existiertKollege")) {
            Log.i("DatenbankTask: ", "value_schulname: " + params[1]);
            case_existiertKollege(params[1], params[2]);
            return null;
        }
        if (params[0].equals("getAlleSchulen")) {
            case_getAlleSchulen();
            return null;
        }
        if (params[0].equals("speichernSchule")) {

            try {
                case_speichernSchule();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        if (params[0].equals("löschenAlleSchule")) {

            case_löschenAlleSchule();
            //Log.i("DatenbankTask: ", " ok: ");
            return null;
        }


        Log.i("DatenbankTask: ", " Datenbankzugriff Problem Mithat: ");

        return null;

    }

    private void case_löschenAlleSchule() {

        try {
            Statement stmt = connection.createStatement();


            // Use TRUNCATE
            String sql = "TRUNCATE schule";
            // Execute deletion
            stmt.executeUpdate(sql);
            // Use DELETE
            sql = "DELETE FROM schule";
            // Execute deletion
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void case_speichernSchule() throws SQLException {
        */
/*
        linkHref: http://www.schulliste.eu/schule/21243-grundschule-ellerstadt/
        linkHref: http://www.schulliste.eu/schule/36947-robert-schuman-mittelschule-kempten-allgau-sankt-mang/
        linkHref: http://www.schulliste.eu/schule/4287-ernst-reuter-schule-ii/
        linkHref: http://www.schulliste.eu/schule/2071-eduard-presser-grundschule-riedheim/
        linkHref: http://www.schulliste.eu/schule/4896-h_da-hochschule-darmstadt/
        linkHref: http://www.schulliste.eu/schule/17724-konrad-adenauer-schule-gem-hauptschule/
        linkHref: http://www.schulliste.eu/schule/36264-grundschule-feuchtwangen-stadt/
        linkHref: http://www.schulliste.eu/schule/939-gartenschule-grundschule/
        mailKlau: http://application_v2.just4web.cz/templates/box_email.php?email=352@bildung.bremen.de&amp;id_lang=10&amp;hide_email=0
        *//*


        int xxxx =0;                            // Eine Zählvariable x

        double cccc = 0;
        int ziel = 26860;// Anfangswert für x
        while (xxxx <= ziel) {//26860


            Document doc = new Document("");
            String html = null;

            String url = "http://www.schulliste.eu/type/?bundesland=&t=offentliche-schule&start="+xxxx;


            try {
                html = Jsoup.connect(url).get().html();
                doc = Jsoup.parse(html);

            } catch (IOException e) {
                Log.e("ReadWebpageAsyncTa: ", "url: " + url);
                e.printStackTrace();
            }


            Elements boo = doc.getElementsByAttributeValue("class", "doc_entry_desc");
            for (Element v : boo) {

                String linkHref = v.html();
                String value_schulname = "";

                int dd1 = linkHref.indexOf("class=\"red\">");
                int dd2 = linkHref.indexOf("</a>");

                //Log.i("ReadWebpageAsyncTa: ", "jsoup: " + dd1+ "  "+dd2);

                value_schulname = linkHref.substring(linkHref.indexOf("class=\"red\">") + 12, linkHref.indexOf("</a>")).trim();


                //Log.i("ReadWebpageAsyncTa: ", "jsoup: " + value_schulname);


                linkHref = linkHref.substring(linkHref.indexOf("href=") + 6, linkHref.indexOf("class=\"red\"") - 2);


                //Log.i("ReadWebpageAsyncTa: ", "jsoup: " + linkHref );
                // Log.i("ReadWebpageAsyncTa: ", "jsoup: "+v.html());
                Document docInner = new Document("");
                String htmlInner = null;
                try {
                    htmlInner = Jsoup.connect(linkHref).get().html();
                    docInner = Jsoup.parse(htmlInner);

                } catch (IOException e) {
                    Log.e("ReadWebpageAsyncTa: ", "linkHref: " + linkHref);
                    e.printStackTrace();
                }

                Elements booInner = docInner.getElementsByAttributeValue("class", "profil_basic_info");


                for (Element vv : booInner) {
                    //Log.i("ReadWebpageAsyncTa: ", "jsoup: " + vv.html());


                    String value_bundesland = "";
                    String value_schultyp = "";
                    String value_kategorien = "";
                    String value_anschrift = "";
                    String value_telefon = "";
                    String value_fax = "";
                    String value_email = "";
                    String value_www = "";
                    String value_direktorin = "";
                    String value_ansprechpartner = "";
                    String value_an_tel = "";


                    Elements puh = vv.getElementsByAttributeValue("class", "c1");
                    Elements c2 = vv.getElementsByAttributeValue("class", "c2");
                    for (Element vvv : puh) {
                        if (vvv.text().equals("Bundesland:")) {
                            value_bundesland = c2.get(puh.indexOf(vvv)).text();
                        } else if (vvv.text().equals("Schultyp:")) {
                            value_schultyp = c2.get(puh.indexOf(vvv)).text();
                        } else if (vvv.text().equals("Kategorien:")) {
                            value_kategorien = c2.get(puh.indexOf(vvv)).text();
                        } else if (vvv.text().equals("Anschrift:")) {
                            value_anschrift = c2.get(puh.indexOf(vvv)).text();
                        } else if (vvv.text().equals("Telefon:")) {
                            if (value_telefon.equals("")) {
                                value_telefon = c2.get(puh.indexOf(vvv)).text();

                            } else {
                                value_an_tel = c2.get(puh.indexOf(vvv)).text();
                            }
                        } else if (vvv.text().equals("Fax:")) {
                            value_fax = c2.get(puh.indexOf(vvv)).text();
                        } else if (vvv.text().equals("E-Mail:")) {

                            String mailKlau = vv.html();
                            //mailKlau = mailKlau.substring(mailKlau.indexOf("href=") + 6, mailKlau.indexOf("class=\"red\"") - 2)

                            //Log.i("ReadWebpageAsyncTa: ", "EMAIL-----------------------------: " + mailKlau);

                            int qq = mailKlau.indexOf("<a href=\"http://application_v2") + 9;
                            int ww = mailKlau.indexOf("class=\"my_modal_open\"") - 2;

                            mailKlau = mailKlau.substring(qq, ww);

                            // Log.i("ReadWebpageAsyncTa: ", "EMAIL LINK-----------------------------: " + mailKlau);

                            Document docInnerInner = new Document("");
                            String htmlInnerInner = null;
                            try {
                                htmlInnerInner = Jsoup.connect(mailKlau).get().html();
                                docInnerInner = Jsoup.parse(htmlInnerInner);

                            } catch (IOException e) {
                                Log.e("ReadWebpageAsyncTa: ", "mailKlau: " + mailKlau);
                                e.printStackTrace();
                            }

                            Elements booInnerInner = docInnerInner.getElementsByAttributeValue("name", "email_recipient");
                            String zzz = String.valueOf(booInnerInner.eachText());


                            value_email = zzz.substring(1, zzz.length() - 1);
                            //Log.i("ReadWebpageAsyncTa: ", "EMAIL-----------------------------: " + value_email);

                        } else if (vvv.text().equals("WWW:")) {
                            value_www = c2.get(puh.indexOf(vvv)).text();
                        } else if (vvv.text().equals("Direktor/in:") || vvv.text().equals("Rektor/in:")) {
                            value_direktorin = c2.get(puh.indexOf(vvv)).text();
                        } else if (vvv.text().equals("Name:")) {
                            value_ansprechpartner = c2.get(puh.indexOf(vvv)).text();
                        } else {


                           // Log.i("ReadWebpageAsyncTa: ", "jsoup: " + "+++++++++PROBLEM++++++++ "+vvv.text()+ " "+value_schulname);
                        }
                        // Log.i("ReadWebpageAsyncTa: ", "jsoup: " + vvv.text());

                    }

                    //Log.i("ReadWebpageAsyncTa: ", "jsoup: " + "drinmmm");
                    DatenbankTask datenbankTask = new DatenbankTask();

                    //Log.i("ReadWebpageAsyncTa: ", "jsoup: " + "drinkkk");


                    //Log.i("ReadWebpageAsyncTa: ", "jsoup: " + "drin2");


                    sql = "INSERT INTO schule " +
                            "(name, bundesland, schultyp, kategorien, anschrift, " +
                            "telefon, fax, email, www, direktor, ansprechpartner, " +
                            "a_tel, status) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setString(1, value_schulname);
                    stmt.setString(2, value_bundesland);
                    stmt.setString(3, value_schultyp);
                    stmt.setString(4, value_kategorien);
                    stmt.setString(5, value_anschrift);
                    stmt.setString(6, value_telefon);
                    stmt.setString(7, value_fax);  // , ,
                    stmt.setString(8, value_email);
                    stmt.setString(9, value_www);
                    stmt.setString(10, value_direktorin);
                    stmt.setString(11, value_ansprechpartner);
                    stmt.setString(12, value_an_tel);
                    stmt.setString(13, "0");

                    stmt.executeUpdate();
                    //Log.i("ReadWebpageAsyncTa: ", "status: " + cccc+ "/26863 ("+cccc/26863*100+"%)");
                    cccc= cccc+1;

                    //I/ReadWebpageAsyncTa:: status: 14416.0/26863 (53.66489223094963%)
                    //E/ReadWebpageAsyncTa:: Schulen kopiert: 14400/26860
                    //I/ReadWebpageAsyncTa:: jsoup: http://www.schulliste.eu/type/?bundesland=&t=offentliche-schule&start=14420


                    */
/*Log.i("ReadWebpageAsyncTa: ", "value_schulname: " + value_schulname);
                    Log.i("ReadWebpageAsyncTa: ", "value_bundesland: " + value_bundesland);
                    Log.i("ReadWebpageAsyncTa: ", "value_schultyp: " + value_schultyp);
                    Log.i("ReadWebpageAsyncTa: ", "value_kategorien: " + value_kategorien);
                    Log.i("ReadWebpageAsyncTa: ", "value_anschrift: " + value_anschrift);
                    Log.i("ReadWebpageAsyncTa: ", "value_telefon: " + value_telefon);
                    Log.i("ReadWebpageAsyncTa: ", "value_fax: " + value_fax);
                    Log.i("ReadWebpageAsyncTa: ", "value_email: " + value_email);
                    Log.i("ReadWebpageAsyncTa: ", "value_www: " + value_www);
                    Log.i("ReadWebpageAsyncTa: ", "value_direktorin: " + value_direktorin);
                    Log.i("ReadWebpageAsyncTa: ", "value_ansprechpartner: " + value_ansprechpartner);
                    Log.i("ReadWebpageAsyncTa: ", "value_an_tel: " + value_an_tel);
                    Log.i("ReadWebpageAsyncTa: ", "-------------------------------------------");*//*



                }

                //Log.i("ReadWebpageAsyncTa: ", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " );
            }
            xxxx = xxxx + 20;
            Log.i("ReadWebpageAsyncTa: ", "status: " + xxxx+ "/26863 ("+(double)xxxx/26863*100+"%)");


        }


    }

    private void case_getAlleSchulen() {


    }

    private void case_getAlleKollegen() {

        sql = "SELECT * from kollege";
        kollegeList = new ArrayList<>();


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id_ = resultSet.getString(1);
                String name_ = resultSet.getString(2);
                String passwort_ = resultSet.getString(3);

                Kollege kollege = new Kollege();
                kollege.setId(Integer.parseInt(id_));
              //  kollege.setName(name_);
                kollege.setPasswort(passwort_);

                kollegeList.add(kollege);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!kollegeList.isEmpty()) {

            for (Kollege s : kollegeList) {
               // Log.i("DatenbankTask: ", " drin: " + s.getName());

            }
        } else {

            Log.i("DatenbankTask: ", " KollegenListe ist leer: ");

        }


    }

    private void case_existiertKollege(String arg0, String arg1) {

        Log.i("DatenbankTask: ", " drin: " + arg0 + arg1);

        sql = "SELECT id from kollege WHERE name='" + arg0 + "' AND passwort='" + arg1 + "' ";


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                dbId = resultSet.getString(1);
            }

            Log.i("DatenbankTask: ", " gefundener Kollege (Id): " + dbId);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onPostExecute(String result) {


    }


}
*/
