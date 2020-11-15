package edu.fu.rentcarnavbar.Object;

public class Fuel {
    int f_id;
    String fuel;

    public Fuel(int f_id, String fuel) {
        this.f_id = f_id;
        this.fuel = fuel;
    }

    public Fuel() {
    }

    public Fuel(String fuel) {
        this.fuel = fuel;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
