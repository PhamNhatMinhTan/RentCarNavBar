package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import edu.fu.rentcarnavbar.Object.Vehicle;

public class VehicleDAO extends DBOpenHepler{

    private String TABLE_NAME = "vehicle";
    private String id = "v_id";
    private String name = "v_name";
    private String licensePlate = "v_licensePlate";
    private String seat = "v_seat";
    private String costPdate = "v_costPerDate";
    private String costPdkm = "v_costPerKm";
    private String image = "v_image";
    private String status = "v_status";
    private String version = "version";
    private String branch = "branch";
    private String color = "color";
    private String fuel = "fuel";
    private String gear = "gear";
    SQLiteDatabase db;

    public VehicleDAO(@Nullable Context context) {
        super(context);
    }

    public void InsertCar(Vehicle vehicle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(name, vehicle.getV_name());
        values.put(licensePlate, vehicle.getV_licensePlate());
        values.put(seat, vehicle.getV_seat());
        values.put(costPdate, vehicle.getV_costPerDate());
        values.put(costPdkm, vehicle.getV_costPerKm());
        values.put(image, vehicle.getV_image());
        values.put(status, vehicle.getV_status());
        values.put(version, vehicle.getVersion());
        values.put(branch, vehicle.getBranch());
        values.put(color, vehicle.getColor());
        values.put(fuel, vehicle.getFuel());
        values.put(gear, vehicle.getGear());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
