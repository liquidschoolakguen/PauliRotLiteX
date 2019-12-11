package akguen.liquidschool.db.model;

public class Angehoeriger {

    private int id;
    private String vorname;
    private String nachname;
    private String institution;
    private String beziehung;
    private String geschlecht;



    public Angehoeriger (int id, String vorname, String nachname, String institution, String beziehung, String geschlecht){

       this.id = id;
       this.vorname = vorname;
       this.nachname = nachname;
       this.institution = institution;
       this.beziehung = beziehung;
       this.geschlecht = geschlecht;


    }



    public Angehoeriger(){


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getBeziehung() {
        return beziehung;
    }

    public void setBeziehung(String beziehung) {
        this.beziehung = beziehung;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    @Override
    public String toString() {


        if (geschlecht.equals("1")) {
            return "Herr " + nachname;
        } else {

            return "Frau " + nachname;
        }

    }

}
