package akguen.liquidschool.db.model;

public class Vergehen {

    private int id;
    private String name;
    private String text;
    private String gewicht;
    private String kategorie;


    public Vergehen(int id, String name, String text, String gewicht, String kategorie) {

        this.id = id;
        this.name = name;
        this.text = text;
        this.gewicht = gewicht;
        this.kategorie = kategorie;

    }

    public Vergehen() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getGewicht() {
        return gewicht;
    }

    public void setGewicht(String gewicht) {
        this.gewicht = gewicht;
    }


    @Override
    public String toString() {
        String output = name;

        return output;

    }



}
