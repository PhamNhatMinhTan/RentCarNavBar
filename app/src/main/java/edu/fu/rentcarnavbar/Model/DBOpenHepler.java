package edu.fu.rentcarnavbar.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHepler extends SQLiteOpenHelper {

    //declare Const variable reference to Database
    private static final String DATABASE_NAME = "rental_car.sqlite";

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
        String queryCreateVehicle = " CREATE TABLE IF NOT EXISTS vehicle(v_id INTEGER PRIMARY KEY AUTOINCREMENT,v_name TEXT,v_licensePlate TEXT, " +
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
                " i_status INTEGER, u_id TEXT, v_id INTEGER); ";
        db.execSQL(queryCreateInvoice);


    }

    //endregion

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
     * Data of table
     */
    public void insert(){
        writeData("INSERT INTO branch VALUES(NULL,'BMW', 'bmw')");
        writeData("INSERT INTO branch VALUES(NULL,'Porsche', 'porsche')");
        writeData("INSERT INTO branch VALUES(NULL,'Toyota', 'toyota')");
        writeData("INSERT INTO branch VALUES(NULL,'Honda', 'honda')");

        writeData("INSERT INTO version VALUES(NULL,'2015')");
        writeData("INSERT INTO version VALUES(NULL,'2016')");
        writeData("INSERT INTO version VALUES(NULL,'2017')");
        writeData("INSERT INTO version VALUES(NULL,'2018')");
        writeData("INSERT INTO version VALUES(NULL,'2019')");
        writeData("INSERT INTO version VALUES(NULL,'2020')");

        writeData("INSERT INTO color VALUES(NULL,'Black')");
        writeData("INSERT INTO color VALUES(NULL,'White')");
        writeData("INSERT INTO color VALUES(NULL,'Silver')");
        writeData("INSERT INTO color VALUES(NULL,'Red')");
        writeData("INSERT INTO color VALUES(NULL,'Darkblue')");
        writeData("INSERT INTO color VALUES(NULL,'Blue')");
        writeData("INSERT INTO color VALUES(NULL,'Grey')");

        writeData("INSERT INTO fuel VALUES(NULL,'Gasoline')");
        writeData("INSERT INTO fuel VALUES(NULL,'Oil')");

        writeData("INSERT INTO gear VALUES(NULL,'Manual')");
        writeData("INSERT INTO gear VALUES(NULL,'Automatic')");

        //vehicle(v_id INTEGER PRIMARY KEY AUTOINCREMENT,v_name TEXT,v_licensePlate TEXT, " +
        //                " v_seat INTEGER, v_costPerDate FLOAT, v_costPerKm FLOAT, v_image TEXT, v_status INTEGER, version INTEGER, " +
        //                " branch INTEGER, color INTEGER, fuel INTEGER, gear INTEGER); ";
//        writeData("INSERT INTO vehicle VALUES(NULL,'Toyota Altis 1.8G CVT','65A1-262.95',4,50,3,'toyota_altis_black',1,6,3,1,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Totota Altis 1.8G CVT','65A1-662.85',4,50,3,'toyota_altis_red',1,6,3,2,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Toyota Hiace','65A1-656.36',16,70,5,'toyota_hiace_silver',1,3,3,3,2,1)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Toyota Hiace','65A1-215.61',16,70,5,'toyota_hiace_white',1,3,3,3,2,1)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Honda City','65B1-621.21',4,40,3.5,'honda_city_darkblue',1,5,4,5,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Honda City','65B1-356.27',4,40,3.5,'honda_city_silver',1,5,4,3,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Honda Civic','65B1-624.65',4,45,3.5,'honda_civic_white',1,5,4,2,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Honda Civic','65B1-294.82',4,45,3.5,'honda_civic_blue',1,5,4,6,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'BMW X7','65B1-354.64',4,120,7.5,'bmw_x7_blue',1,4,1,6,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'BMW X7','65B1-854.66',4,120,7.5,'bmw_x7_white',1,4,1,2,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'BMW 740','65B1-455.22',4,130,8.5,'bmw_740_white',1,5,1,2,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'BMW 740','65B1-665.342',4,130,8.5,'bmw_740_black',1,5,1,1,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Porscher 718','65B1-112.94',4,150,9.5,'porscher_718_blue',1,5,2,6,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Porscher 718','65B1-632.24',4,150,9.5,'porscher_718_grey',1,5,2,7,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Porscher J1 Taycan','65B1-647.24',4,140,8.5,'j1_taycan_blue',1,4,2,6,1,2)");
//        writeData("INSERT INTO vehicle VALUES(NULL,'Porscher J1 Taycan','65B1-632.88',4,140,8.5,'j1_taycan_white',1,4,2,2,1,2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Do nothing
    }
}
