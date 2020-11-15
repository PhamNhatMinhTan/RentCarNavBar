package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import edu.fu.rentcarnavbar.Object.Fuel;
import edu.fu.rentcarnavbar.Object.Gear;


public class GearDAO extends DBOpenHepler{

    SQLiteDatabase db;
    private String TABLE_NAME = "gear";
    private String id = "g_id";
    private String name = "gear";

    public GearDAO(@Nullable Context context) {
        super(context);
    }


    public void InsertGear(Gear gear){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(name, gear.getGear());

        db.insert(TABLE_NAME, null, values);
    }

    public Gear GetGearById (int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        cursor = db.query(TABLE_NAME, null, "g_id = ?", new String[] {String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();
        Gear gear = new Gear(cursor.getInt(0), cursor.getString(1));

        return gear;
    }
}
