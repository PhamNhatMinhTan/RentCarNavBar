package edu.fu.rentcarnavbar.Object;

public class vehicle {


    //v_id INTEGER PRIMARY KEY AUTOINCREMENT, v_name TEXT ,v_licensePlate TEXT, " +
    //                " v_seat INTEGER, v_costPerDate FLOAT, v_costPerKm FLOAT, v_image TEXT, v_status INTEGER, version INTEGER, " +
    //                " branch INTEGER, color INTEGER, fuel INTEGER, gear INTEGER
    int v_id, v_seat, v_status, version,branch, color, fuel, gear;
    float v_costPerDate, v_costPerKm;
    String v_name, v_licensePlate, v_image;

    public vehicle(int v_id,String v_name ,String v_licensePlate,int v_seat,float v_costPerDate,float v_costPerKm,String v_image,int v_status,int version,int branch,int color,int fuel,int gear)
    {
        this.v_id=v_id;
        this.v_name=v_name;
        this.v_licensePlate=v_licensePlate;
        this.v_seat=v_seat;
        this.v_costPerDate=v_costPerDate;
        this.v_costPerKm=v_costPerKm;
        this.v_image=v_image;
        this.v_status=v_status;
        this.version=version;
        this.branch=branch;
        this.color=color;
        this.fuel=fuel;
        this.gear=gear;
    }

    public int getV_id() {
        return v_id;
    }

    public int getV_seat() {
        return v_seat;
    }

    public int getV_status() {
        return v_status;
    }

    public int getVersion() {
        return version;
    }

    public int getBranch() {
        return branch;
    }

    public int getColor() {
        return color;
    }

    public int getFuel() {
        return fuel;
    }

    public int getGear() {
        return gear;
    }

    public float getV_costPerDate() {
        return v_costPerDate;
    }

    public float getV_costPerKm() {
        return v_costPerKm;
    }

    public String getV_name() {
        return v_name;
    }

    public String getV_licensePlate() {
        return v_licensePlate;
    }

    public String getV_image() {
        return v_image;
    }


}
