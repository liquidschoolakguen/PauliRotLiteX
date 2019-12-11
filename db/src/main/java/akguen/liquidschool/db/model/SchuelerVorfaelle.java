package akguen.liquidschool.db.model;

import java.util.Date;

public class SchuelerVorfaelle {



    public SchuelerVorfallData[] getInvoices() {

        //TODO Daten aus der DB

        SchuelerVorfallData[] data = new SchuelerVorfallData[20];

        for(int i = 0; i < 20; i ++) {

            SchuelerVorfallData row = new SchuelerVorfallData();

            //row.id = (i+1);
            row.id = "1";
            row.datum = new Date();
            row.uhrzeit = "12:12";
            row.info = "entschuldigung";

            Kollege kollege = new Kollege();
            //kollege.setName("mitat");   name ist jetzt vorname !!!!!!!
            //row.kollegeName = kollege.getName();


            Vergehen vergehen = new Vergehen();
            //vergehen.setTitel("Aufgabe gemacht");
            //row.vergehenTitel = vergehen.getTitel();
            data[i] = row;

        }

        return data;



    }

}
