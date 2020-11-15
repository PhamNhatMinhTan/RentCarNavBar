package edu.fu.rentcarnavbar.Object;

public class Fuel {
    int f_id;
    String fiel;

    public Fuel(int f_id, String fiel) {
        this.f_id = f_id;
        this.fiel = fiel;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getFiel() {
        return fiel;
    }

    public void setFiel(String fiel) {
        this.fiel = fiel;
    }
}
