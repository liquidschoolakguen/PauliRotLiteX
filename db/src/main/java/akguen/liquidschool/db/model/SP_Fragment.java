package akguen.liquidschool.db.model;

public class SP_Fragment {


    private int id;
    private String nummer;



    public SP_Fragment(int id, String nummer){

        this.id = id;
        this.nummer = nummer;

    }

    public SP_Fragment(){


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    @Override
    public String toString() {

        return "Fragment Nummer: "+nummer;

    }







}
