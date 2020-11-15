package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import edu.fu.rentcarnavbar.Object.Gear;
import edu.fu.rentcarnavbar.Object.Version;

public class VersionDAO extends DBOpenHepler{

    SQLiteDatabase db;
    private String TABLE_NAME = "version";
    private String id = "vs_id";
    private String name = "version";

    public VersionDAO(@Nullable Context context) {
        super(context);
    }

    public void InsertVersion(Version vs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(name, vs.getVersion());

        db.insert(TABLE_NAME, null, values);
    }
}
