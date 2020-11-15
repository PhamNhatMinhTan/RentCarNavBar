package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import edu.fu.rentcarnavbar.Object.Branch;
import edu.fu.rentcarnavbar.Object.Fuel;

public class BranchDAO extends DBOpenHepler{

    SQLiteDatabase db;
    private String TABLE_NAME = "branch";
    private String id = "br_id";
    private String name = "br_name";
    private String logo = "br_logo";

    public BranchDAO(@Nullable Context context) {
        super(context);
    }


    public void InsertColor(Branch br){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(name, br.getBr_name());
        values.put(logo, br.getBr_logo());
        db.insert(TABLE_NAME, null, values);
    }
}
