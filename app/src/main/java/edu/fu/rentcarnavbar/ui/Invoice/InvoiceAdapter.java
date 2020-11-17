package edu.fu.rentcarnavbar.ui.Invoice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import java.io.File;
import java.util.List;

import edu.fu.rentcarnavbar.MainActivity;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.Object.Invoice;
import edu.fu.rentcarnavbar.Object.InvoiceList;
import edu.fu.rentcarnavbar.R;

public class InvoiceAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<InvoiceList> list;

    public InvoiceAdapter(Context context, int layout, List<InvoiceList> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.invoke_item, null);


        //
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtDateStart = convertView.findViewById(R.id.txtDateStart);
        TextView txtDateEnd = convertView.findViewById(R.id.txtDateEnd);
        TextView txtTotal = convertView.findViewById(R.id.txtTotal);
        ImageView image = convertView.findViewById(R.id.imageView);


//        Invoice invoice = list.get(position);
        final InvoiceList invoiceList = list.get(position);

        txtName.setText(invoiceList.getName());
//        txtPrice.setText(String.valueOf(invoice.getPrice()));
        txtDateStart.setText(invoiceList.getDateStart());
        txtDateEnd.setText(invoiceList.getDateEnd());
        txtTotal.setText(String.valueOf(invoiceList.getTotal()));

        image.setImageResource(Integer.parseInt(invoiceList.getImage()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.change(invoiceList.getId());
            }
        });
        return convertView;
    }
}
