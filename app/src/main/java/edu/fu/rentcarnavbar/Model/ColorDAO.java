package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import edu.fu.rentcarnavbar.Object.Color;

public class ColorDAO extends DBOpenHepler{

    SQLiteDatabase db;
    private String TABLE_NAME = "color";
    private String id = "col_id";
    private String color = "color";

    public ColorDAO(@Nullable Context context) {
        super(context);
    }

    public void InsertColor(Color col){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(color, col.getColor());

        db.insert(TABLE_NAME, null, values);
    }
}
