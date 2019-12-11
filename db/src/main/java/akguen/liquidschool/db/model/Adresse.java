package akguen.liquidschool.db.model;

public class Adresse {

    int id;
    private String strasse;
    private String plz;
    private String ort;
    private String tel;
    private String fax;



public Adresse(int id, String straße, String plz, String ort, String tel, String fax){

    this.id =id;
    this.strasse = straße;
    this.plz = plz;
    this.ort = ort;
    this.tel = tel;
    this.fax = fax;



}

    public Adresse(){



    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }



    @Override
    public String toString() {
        String output = strasse;

        return output;

    }


}
