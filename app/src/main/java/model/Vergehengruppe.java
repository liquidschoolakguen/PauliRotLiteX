package model;

public class Vergehengruppe {
    int id;
    private String name;



    public Vergehengruppe(int id, String name){

        this.id = id;
        this.name = name;


    }


    public Vergehengruppe(){

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


        return id + "] "+name;

    }




}
