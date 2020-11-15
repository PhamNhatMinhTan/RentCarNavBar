package edu.fu.rentcarnavbar.Object;

public class Branch {
    int br_id;
    String br_name, br_logo;

    public Branch(int br_id, String br_name, String br_logo) {
        this.br_id = br_id;
        this.br_name = br_name;
        this.br_logo = br_logo;
    }

    public int getBr_id() {
        return br_id;
    }

    public String getBr_name() {
        return br_name;
    }

    public String getBr_logo() {
        return br_logo;
    }
}
