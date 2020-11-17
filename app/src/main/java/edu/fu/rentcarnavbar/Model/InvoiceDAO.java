package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.google.android.material.internal.ContextUtils;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Object.Invoice;
import edu.fu.rentcarnavbar.Object.User;
import edu.fu.rentcarnavbar.ui.Invoice.InvoiceFragment;

public class InvoiceDAO extends DBOpenHepler{

    private String TABLE_NAME = "invoice";
    private String id = "i_id";
    private String dateStart = "i_dateStart";
    private String dateEnd = "i_dateEnd";
    private String total = "i_total";
    private String name = "i_name";
    private String phone = "i_phone";
    private String identity = "i_identity";
    private String status = "i_status";
    private String u_id = "u_id";
    private String v_id = "v_id";
    SQLiteDatabase db;



    public InvoiceDAO(@Nullable Context context) {
        super(context);
    }


    public void InsertInvoice(Invoice invoice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dateStart, invoice.getDateStart());
        values.put(dateEnd, invoice.getDateEnd());
        values.put(total, invoice.getTotal());
        values.put(name, invoice.getName());
        values.put(phone, invoice.getPhone());
        values.put(identity, invoice.getIdentity());
        values.put(status, invoice.getStatus());
        values.put(u_id, invoice.getU_id());
        values.put(v_id, invoice.getV_id());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Invoice> GetInvoiceByUser (String id){
        List<Invoice> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;
        cursor = db.query(TABLE_NAME, null, "u_id = ?", new String[] {id}, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                Invoice invoice = new Invoice(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getFloat(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getInt(7), cursor.getString(8), cursor.getInt(9));
                list.add(invoice);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public Invoice GetInvoiceById (int id){
        List<Invoice> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;
        cursor = db.query(TABLE_NAME, null, "i_id = ?", new String[] {String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();
        Invoice invoice = new Invoice(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6),
                cursor.getInt(7), cursor.getString(8), cursor.getInt(9));

        return invoice;
    }

    public List<Invoice> getAllInvoice() {
        //khoi tao list invoice
        List<Invoice> list = new ArrayList<>();
        String sqlQuery = " SELECT * FROM " + TABLE_NAME;
        Cursor result = null;

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            result = db.rawQuery(sqlQuery, null);
            result.moveToFirst();

            if (result.moveToFirst()){
                do {
                    //add data to object
                    Invoice invoice = new Invoice();
                    invoice.setId(result.getInt(0));
                    invoice.setDateStart(result.getString(1));
                    invoice.setDateEnd(result.getString(2));
                    invoice.setTotal(result.getDouble(3));
                    invoice.setName(result.getString(4));
                    invoice.setPhone(result.getString(5));
                    invoice.setIdentity(result.getString(6));
                    invoice.setStatus(result.getInt(7));
                    invoice.setU_id(result.getString(8));
                    invoice.setV_id(result.getInt(9));
                    list.add(invoice);
                } while (result.moveToNext());
            }
        } finally {
            result.close();
        }

        return list;
    }

    public void updateInvoice(String id, String i_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int my_status = -1;
        if(i_status == "Processing")
        {
            my_status = 0;
        }
        else if(i_status == "Renting")
        {
            my_status = 1;
        }
        else if(i_status == "Rented")
        {
            my_status = 2;
        }
        //put values to context value
        values.put(status, my_status);

        db.update(TABLE_NAME, values, "i_id = ?", new String[] { id });
        db.close();
    }

}
