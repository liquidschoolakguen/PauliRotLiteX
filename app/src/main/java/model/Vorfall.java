package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vorfall {

    private int id;
    private String zeitpunkt;
    private String info;


    /*StringBuilder query=new StringBuilder();
query.append("CREATE TABLE "+TABLE_NAME+ " (");
query.append(COLUMN_ID+"int primary key autoincrement,");
query.append(COLUMN_DATETIME+" int)");

//And inserting the data includes this:
values.put(COLUMN_DATETIME, System.currentTimeMillis());*/

    public Vorfall (int id, String zeitpunkt, String info){

        this.id = id;
        this.zeitpunkt = zeitpunkt;
        this.info = info;

    }

    public Vorfall (){


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


        return sdf.format(resultdate);

    }


}
