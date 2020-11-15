package edu.fu.rentcarnavbar.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Object.Invoice;
import edu.fu.rentcarnavbar.Object.User;

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
}
