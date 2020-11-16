package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Object.Branch;
import edu.fu.rentcarnavbar.Object.Color;
import edu.fu.rentcarnavbar.Object.Invoice;
import edu.fu.rentcarnavbar.Object.User;
import edu.fu.rentcarnavbar.Object.Vehicle;
import edu.fu.rentcarnavbar.Object.Version;

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

    /**
     * Write into DB
     * @param sqlString
     */
    public void writeData(String sqlString)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sqlString);
    }

    /**
     * read into DB
     * @param sqlString
     * @return
     */
    public Cursor getData(String sqlString)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sqlString, null);
    }

    /**
     * list of car
     * @return
     */
    public List<Vehicle> getCarList(){
        List<Vehicle> lst = new ArrayList<>();
        Cursor cursor = getData("SELECT * FROM vehicle");
        while (cursor.moveToNext())
        {
            int v_id=cursor.getInt(0);
            String v_name=cursor.getString(1);
            String v_licensePlate=cursor.getString(2);
            int v_seat=cursor.getInt(3);
            float v_costPerDate=cursor.getFloat(4);
            float v_costPerKm=cursor.getFloat(5);
            String v_image=cursor.getString(6);
            int v_status=cursor.getInt(7);
            int version=cursor.getInt(8);
            int branch=cursor.getInt(9);
            int color=cursor.getInt(10);
            int fuel=cursor.getInt(11);
            int gear=cursor.getInt(12);;
            lst.add(new Vehicle(v_id,v_name ,v_licensePlate,v_seat,v_costPerDate,v_costPerKm,v_image,v_status,version,branch,color,fuel,gear));
        }
        //adapter.notifyDataSetChanged();
        return lst;
    }

    /**
     * list of version
     * @return
     */
    public List<Version> getVesionList(){
        List<Version> lst = new ArrayList<>();
        Cursor cursor = getData("SELECT * FROM version");
        while (cursor.moveToNext())
        {
            int vs_id=cursor.getInt(0);
            String version = cursor.getString(1);

            lst.add(new Version(vs_id,version));
        }
        //adapter.notifyDataSetChanged();
        return lst;
    }
    /**
     * list of color
     * @return
     */
    public List<Color> getColorList(){
        List<Color> lst = new ArrayList<>();
        Cursor cursor = getData("SELECT * FROM color");
        while (cursor.moveToNext())
        {
            int color_id=cursor.getInt(0);
            String color = cursor.getString(1);

            lst.add(new Color(color_id,color));
        }
        //adapter.notifyDataSetChanged();
        return lst;
    }
    /**
     * list of branch
     * @return
     */
    public List<Branch> getBranchList(){
        List<Branch> lst = new ArrayList<>();
        Cursor cursor = getData("SELECT * FROM branch");
        while (cursor.moveToNext())
        {
            int br_id=cursor.getInt(0);
            String br_name = cursor.getString(1);
            String br_logo = cursor.getString(2);

            lst.add(new Branch(br_id,br_name,br_logo));
        }
        //adapter.notifyDataSetChanged();
        return lst;
    }

}
