package akguen.liquidschool.db.model;

public class Lerngruppe {
    int id;
    private String name;
    private int lernform_id;


    public Lerngruppe (int id, String name, int lernform_id){

        this.id = id;
        this.name = name;
        this.lernform_id = lernform_id;


    }


    public Lerngruppe(){

    }

    public int getLernform_id() {
        return lernform_id;
    }

    public void setLernform_id(int lernform_id) {
        this.lernform_id = lernform_id;
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



    @Override
    public String toString() {


        return id + "] "+name+" ("+lernform_id+")";

    }




}
