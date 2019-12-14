package akguen.liquidschool.paulirotlite.activities.in_use;


import java.util.ArrayList;
import java.util.List;

public class UnterrichtPlan {

    List<String> stundeListe;
    List<String> montagListe;
    List<String> dienstagListe;
    List<String> mittwochListe;
    List<String> donnerstagListe;
    List<String> freitagListe;


    public StundenPlanRow[] getPlan() {

        StundenPlanRow[] stundenPlanRows = new StundenPlanRow[20];
        for (int i = 0; i < getStunde().size(); i++) {

            StundenPlanRow stundenPlanRow = new StundenPlanRow();
            stundenPlanRow.stunde = (String) getStunde().get(i);
            stundenPlanRow.montag = (String) getMontagPlan().get(i);
            stundenPlanRow.dienstag = (String) getDienstagPlan().get(i);
            stundenPlanRow.mittwoch = (String) getMittwochPlan().get(i);
            stundenPlanRow.donnerstag = (String) getDonnertagPlan().get(i);


            stundenPlanRows[i] = stundenPlanRow;
        }
        return stundenPlanRows;
    }

    public List getStunde() {

        stundeListe = new ArrayList<>();
        stundeListe.add("8:30 - 9:15");
        stundeListe.add("9:15 - 10:00");
        stundeListe.add("10:00 - 10:25"); // Pause
        stundeListe.add("10:25 - 11:10");
        stundeListe.add("11:10 - 11:55");
        stundeListe.add("11:55 - 12:15"); // Pause
        stundeListe.add("12:15 - 13:00");
        stundeListe.add("13:00 - 14:15"); // Pause
        stundeListe.add("14:15 - 13:00");
        stundeListe.add("15:00 - 15:45");
        stundeListe.add("x");
        stundeListe.add("x");
        stundeListe.add("x");
        stundeListe.add("x");
        stundeListe.add("x");
        stundeListe.add("x");
        stundeListe.add("x");
        stundeListe.add("x");
        stundeListe.add("x");
        stundeListe.add("x");
        return stundeListe;

    }

    public List getMontagPlan() {

        montagListe = new ArrayList<>();
        montagListe.add("Mathematik");
        montagListe.add("Kunst");
        montagListe.add("Pause"); // Pause
        montagListe.add("Literatur");
        montagListe.add("Literatur");
        montagListe.add("Pause"); // Pause
        montagListe.add("Technik");
        montagListe.add("Pause"); // Pause
        montagListe.add("Physik");
        montagListe.add("Sport");
        montagListe.add("x");
        montagListe.add("x");
        montagListe.add("x");
        montagListe.add("x");
        montagListe.add("x");
        montagListe.add("x");
        montagListe.add("x");
        montagListe.add("x");
        montagListe.add("x");
        montagListe.add("x");


        return montagListe;

    }

    public List getDienstagPlan() {

        dienstagListe = new ArrayList<>();
        dienstagListe.add("Sport");
        dienstagListe.add("Mathematik");
        dienstagListe.add("Pause"); // Pause
        dienstagListe.add("Philosophie");
        dienstagListe.add("Literatur");
        dienstagListe.add("Pause"); // Pause
        dienstagListe.add("Technik");
        dienstagListe.add("Pause"); // Pause
        dienstagListe.add("Physik");
        dienstagListe.add("Kunst");
        dienstagListe.add("x");
        dienstagListe.add("x");
        dienstagListe.add("x");
        dienstagListe.add("x");
        dienstagListe.add("x");
        dienstagListe.add("x");
        dienstagListe.add("x");
        dienstagListe.add("x");
        dienstagListe.add("x");
        dienstagListe.add("x");
        return dienstagListe;

    }


    public List getMittwochPlan() {

        mittwochListe = new ArrayList<>();
        mittwochListe.add("Kunst");
        mittwochListe.add("Technik");
        mittwochListe.add("Pause"); // Pause
        mittwochListe.add("Philosophie");
        mittwochListe.add("Technik");
        mittwochListe.add("Pause"); // Pause
        mittwochListe.add("Chemie");
        mittwochListe.add("Pause"); // Pause
        mittwochListe.add("Physik");
        mittwochListe.add("Physik");
        mittwochListe.add("x");
        mittwochListe.add("x");
        mittwochListe.add("x");
        mittwochListe.add("x");
        mittwochListe.add("x");
        mittwochListe.add("x");
        mittwochListe.add("x");
        mittwochListe.add("x");
        mittwochListe.add("x");
        mittwochListe.add("x");
        return mittwochListe;
    }

    public List getDonnertagPlan() {

        donnerstagListe = new ArrayList<>();
        donnerstagListe.add("Kunst");
        donnerstagListe.add("Technik");
        donnerstagListe.add("Pause"); // Pause
        donnerstagListe.add("Philosophie");
        donnerstagListe.add("Technik");
        donnerstagListe.add("Pause"); // Pause
        donnerstagListe.add("Chemie");
        donnerstagListe.add("Pause"); // Pause
        donnerstagListe.add("Physik");
        donnerstagListe.add("Physik");
        donnerstagListe.add("x");
        donnerstagListe.add("x");
        donnerstagListe.add("x");
        donnerstagListe.add("x");
        donnerstagListe.add("x");
        donnerstagListe.add("x");
        donnerstagListe.add("x");
        donnerstagListe.add("x");
        donnerstagListe.add("x");
        donnerstagListe.add("x");
        return donnerstagListe;
    }

    public StundenPlanRow[] getVorfaelle() {


        StundenPlanRow[] vorfaelle = new StundenPlanRow[20];

        for (int i = 0; i < 10; i++) {
            StundenPlanRow row = new StundenPlanRow();

           /* //row.id = (i+1);
            row.id = "8:30 - 9:15 ";
            //row.datum = new Date();
            row.fach = "Philosophie";
            row.uhrzeit = "Literatur";
            row.info = "Mathematik";
            Kollege kollege = new Kollege();
            kollege.setNachname("Sprache");
            row.kollegeName = kollege.getNachname();
            Vergehen vergehen = new Vergehen("testErstmal");
            vergehen.setTitel("Sport");
            row.vergehenTitel = vergehen.getTitel();
            vorfaelle[i] = row;*/
        }
        return vorfaelle;

    }

}

