package model;
/*

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vorfall {

    private int id;
    private String zeitpunkt;
    private String info;
    private int schueler_id;
    private int vergehen_id;

    */
/*StringBuilder query=new StringBuilder();
query.append("CREATE TABLE "+TABLE_NAME+ " (");
query.append(COLUMN_ID+"int primary key autoincrement,");
query.append(COLUMN_DATETIME+" int)");

//And inserting the data includes this:
values.put(COLUMN_DATETIME, System.currentTimeMillis());*//*


    public Vorfall(int id, String zeitpunkt, String info, int schueler_id, int vergehen_id) {

        this.id = id;
        this.zeitpunkt = zeitpunkt;
        this.info = info;
        this.schueler_id = schueler_id;
        this.vergehen_id = vergehen_id;

    }

    */
/*  public Vorfall (int id, String zeitpunkt, String info){

          this.id = id;
          this.zeitpunkt = zeitpunkt;
          this.info = info;

      }*//*

    public Vorfall() {


    }

    public int getSchueler_id() {
        return schueler_id;
    }

    public void setSchueler_id(int schueler_id) {
        this.schueler_id = schueler_id;
    }

    public int getVergehen_id() {
        return vergehen_id;
    }

    public void setVergehen_id(int vergehen_id) {
        this.vergehen_id = vergehen_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(String zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {

        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(yourmilliseconds);
        //System.out.println(sdf.format(resultdate));


        return sdf.format(resultdate) + " s: " + getSchueler_id() + "; v: " + getVergehen_id() + ";";

    }


}
*/
