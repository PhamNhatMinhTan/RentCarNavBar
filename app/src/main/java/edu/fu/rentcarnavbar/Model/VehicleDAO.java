package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Object.Invoice;
import edu.fu.rentcarnavbar.Object.User;
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

    public Vehicle GetVehicleById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query("vehicle", null, "v_id = ?", new String[] {String.valueOf(id)}, null, null, null);
            cursor.moveToFirst();
            Vehicle vehicle = new Vehicle(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getFloat(4), cursor.getFloat(5),
                    cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10),
                    cursor.getInt(11), cursor.getInt(12));
            return vehicle;
        } finally {
            cursor.close();
        }
    }
}
