package edu.fu.rentcarnavbar.Object;

public class Gear {
    int g_id;
    String gear;

    public Gear(int g_id, String gear) {
        this.g_id = g_id;
        this.gear = gear;
    }

    public Gear(String gear) {
        this.gear = gear;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }
}
