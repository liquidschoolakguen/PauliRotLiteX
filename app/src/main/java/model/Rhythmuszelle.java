package model;

public class Rhythmuszelle {

    private int id;
    private String wochentag; // Mittwoch
    private String typ; //Unterricht/Pause
    private String nummer; //19
    private String von; //0830
    private String bis; //0915

    public Rhythmuszelle(int id, String wochentag, String typ, String nummer, String von, String bis) {

        this.id = id;
        this.wochentag = wochentag;
        this.typ = typ;
        this.nummer = nummer;
        this.von = von;
        this.bis = bis;

    }

    public Rhythmuszelle() {


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWochentag() {
        return wochentag;
    }

    public void setWochentag(String wochentag) {
        this.wochentag = wochentag;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }



    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getVon() {
        return von;
    }

    public void setVon(String von) {
        this.von = von;
    }

    public String getBis() {
        return bis;
    }

    public void setBis(String bis) {
        this.bis = bis;
    }

    @Override
    public String toString() {
        String output = wochentag + ", " + nummer+". "+typ;

        return output;

    }


}
