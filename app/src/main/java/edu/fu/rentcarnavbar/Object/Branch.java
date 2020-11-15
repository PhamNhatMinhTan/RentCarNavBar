package edu.fu.rentcarnavbar.Object;

public class Branch {
    int br_id;
    String br_name, br_logo;

    public Branch(int br_id, String br_name, String br_logo) {
        this.br_id = br_id;
        this.br_name = br_name;
        this.br_logo = br_logo;
    }

    public Branch(String br_name, String br_logo) {
        this.br_name = br_name;
        this.br_logo = br_logo;
    }



    public Branch() {
    }

    public int getBr_id() {
        return br_id;
    }

    public void setBr_id(int br_id) {
        this.br_id = br_id;
    }

    public String getBr_name() {
        return br_name;
    }

    public void setBr_name(String br_name) {
        this.br_name = br_name;
    }

    public String getBr_logo() {
        return br_logo;
    }

    public void setBr_logo(String br_logo) {
        this.br_logo = br_logo;
    }
}
