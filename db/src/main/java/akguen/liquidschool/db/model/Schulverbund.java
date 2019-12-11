package akguen.liquidschool.db.model;

public class Schulverbund {


    private int id;
    private String name;
    private String traeger;
    private String bundesland;
    private String bezirk;
    private String typ;
    private String email;
    private String www;
    private String schuelerzahl;
    private String leitung;
    private String status;
    private String mainserveradress;



    public Schulverbund (int id ,String name,String traeger,String bundesland,String bezirk,String typ,String email,String www,String schuelerzahl,String leitung,String status,String mainserveradress){
       this.id= id;
       this.name = name;
       this.traeger = traeger;
       this.bundesland = bundesland;
       this.bezirk = bezirk;
       this.typ = typ;
       this.email = email;
       this.www = www;
       this.schuelerzahl = schuelerzahl;
       this.leitung = leitung;
       this.status = status;
       this.mainserveradress = mainserveradress;


    }


    public Schulverbund(){


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

    public String getTraeger() {
        return traeger;
    }

    public void setTraeger(String traeger) {
        this.traeger = traeger;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public String getBezirk() {
        return bezirk;
    }

    public void setBezirk(String bezirk) {
        this.bezirk = bezirk;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getSchuelerzahl() {
        return schuelerzahl;
    }

    public void setSchuelerzahl(String schuelerzahl) {
        this.schuelerzahl = schuelerzahl;
    }

    public String getLeitung() {
        return leitung;
    }

    public void setLeitung(String leitung) {
        this.leitung = leitung;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMainserveradress() {
        return mainserveradress;
    }

    public void setMainserveradress(String mainserveradress) {
        this.mainserveradress = mainserveradress;
    }


    @Override
    public String toString() {

        return name + " ("+ typ +") ";
    }



}
