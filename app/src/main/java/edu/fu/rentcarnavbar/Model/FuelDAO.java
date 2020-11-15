package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import edu.fu.rentcarnavbar.Object.Fuel;

public class FuelDAO extends DBOpenHepler{

    SQLiteDatabase db;
    private String TABLE_NAME = "fuel";
    private String id = "f_id";
    private String fuel = "fuel";

    public FuelDAO(@Nullable Context context) {
        super(context);
    }

    public void InsertFuel(Fuel fue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(fuel, fue.getFuel());

        db.insert(TABLE_NAME, null, values);
    }
}
