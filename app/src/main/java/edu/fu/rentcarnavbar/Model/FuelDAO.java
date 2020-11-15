package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Object.Fuel;
import edu.fu.rentcarnavbar.Object.Invoice;

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

    public Fuel GetFuelById (int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        cursor = db.query(TABLE_NAME, null, "f_id = ?", new String[] {String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();
        Fuel fuel = new Fuel(cursor.getInt(0), cursor.getString(1));

        return fuel;
    }
}
