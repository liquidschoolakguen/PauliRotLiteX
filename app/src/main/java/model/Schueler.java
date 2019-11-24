package model;

public class Schueler {

    private int id;
    private String vorname;
    private String nachname;
    private String rufname;
    private String geschlecht;
    private String status;
    private String geburtstag;
    private String geburtsort;


    public Schueler(int id, String vorname, String nachname, String rufname, String geschlecht, String status, String geburtstag, String geburtsort) {

    this.id = id;
    this.vorname = vorname;
    this.nachname = nachname;
    this.rufname = rufname;
    this.geschlecht = geschlecht;
    this.status = status;
    this.geburtstag = geburtstag;
    this.geburtsort = geburtsort;


    }

    public Schueler(){


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getRufname() {
        return rufname;
    }

    public void setRufname(String rufname) {
        this.rufname = rufname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    public String getGeburtsort() {
        return geburtsort;
    }

    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }

    @Override
    public String toString() {
        String output = id+ " "+vorname+" ["+ geburtstag+"] "+geschlecht;

        return output;

    }


}
