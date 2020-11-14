package edu.fu.rentcarnavbar.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHepler extends SQLiteOpenHelper {

    //declare Const variable reference to Database
    private static final String DATABASE_NAME = "rental_car.db";

    private Context context;
    
    public DBOpenHepler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        InitializeTable(db);
    }

    //region Generate table and data into database

    protected void InitializeTable(SQLiteDatabase db) {
        //Create table User
        String queryCreateUser = " CREATE TABLE IF NOT EXISTS user(u_id TEXT PRIMARY KEY, u_phone TEXT, " +
                " u_name TEXT, u_email TEXT, u_address TEXT, u_identity TEXT, " +
                " u_status INTEGER); ";
        db.execSQL(queryCreateUser);

        //Create table vehicle
        String queryCreateVehicle = " CREATE TABLE IF NOT EXISTS vehicle(v_id INTEGER PRIMARY KEY AUTOINCREMENT, v_licensePlate TEXT, " +
                " v_seat INTEGER, v_costPerDate FLOAT, v_costPerKm FLOAT, v_image TEXT, v_status INTEGER, version INTEGER, " +
                " branch INTEGER, color INTEGER, fuel INTEGER, gear INTEGER); ";
        db.execSQL(queryCreateVehicle);

        //Create table branch
        String queryCreateBranch = " CREATE TABLE IF NOT EXISTS branch(br_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " br_name TEXT, br_logo TEXT); ";
        db.execSQL(queryCreateBranch);

        //Create table version
        String queryCreateVersion = " CREATE TABLE IF NOT EXISTS version(vs_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " version TEXT); ";
        db.execSQL(queryCreateVersion);

        //Create table color
        String queryCreateColor = " CREATE TABLE IF NOT EXISTS color(col_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " color TEXT); ";
        db.execSQL(queryCreateColor);

        //Create table fuel
        String queryCreateFuel = " CREATE TABLE IF NOT EXISTS fuel(f_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " fuel TEXT); ";
        db.execSQL(queryCreateFuel);

        //Create table gear
        String queryCreateGear = " CREATE TABLE IF NOT EXISTS gear(g_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " gear TEXT); ";
        db.execSQL(queryCreateGear);

        //Create table invoice
        String queryCreateInvoice = " CREATE TABLE IF NOT EXISTS invoice(i_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " i_dateStart TEXT, i_dateEnd TEXT, i_total FLOAT, i_name TEXT, i_phone TEXT, i_identity TEXT," +
                " i_status INTEGER, u_id INTEGER, v_id INTEGER); ";
        db.execSQL(queryCreateInvoice);
    }

    //endregion

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Do nothing
    }
}
