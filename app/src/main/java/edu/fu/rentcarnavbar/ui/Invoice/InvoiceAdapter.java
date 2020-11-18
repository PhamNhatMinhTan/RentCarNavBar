package edu.fu.rentcarnavbar.ui.Invoice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import org.w3c.dom.Text;

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
    private InvoiceDAO invoiceDAO;

    public InvoiceAdapter(Context context, int layout, List<InvoiceList> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        invoiceDAO = new InvoiceDAO(context);
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
        TextView txtStatus = convertView.findViewById(R.id.txtStatus_detail_user);
        final InvoiceList invoiceList = list.get(position);
        String status = "";
        if(invoiceDAO.GetInvoiceById(invoiceList.getId()).getStatus() == 0)
        {
            status = "Processing";
//            txtStatus.setTextColor(Color.DKGRAY);
            txtStatus.setTextColor(Color.RED);
        }
        else if(invoiceDAO.GetInvoiceById(invoiceList.getId()).getStatus() == 1){
            status = "Approved";
//            txtStatus.setTextColor(Color.GREEN);
            txtStatus.setTextColor(Color.BLUE);
        }
        else if(invoiceDAO.GetInvoiceById(invoiceList.getId()).getStatus() == 2){
            status = "Returned";
  //          txtStatus.setTextColor(Color.LTGRAY);
            txtStatus.setTextColor(Color.GREEN);
        } else if (invoiceDAO.GetInvoiceById(invoiceList.getId()).getStatus() == 3) {
            status = "Rejected";
    //        txtStatus.setTextColor(Color.RED);
            txtStatus.setTextColor(Color.RED);
        }

        if(list.size() == 0){
            return null;
        }
        txtName.setText(invoiceList.getName().toString());
        txtDateStart.setText(invoiceList.getDateStart().toString());
        txtDateEnd.setText(invoiceList.getDateEnd().toString());
        txtTotal.setText(String.valueOf(invoiceList.getTotal()).toString());
        image.setImageResource(Integer.parseInt(invoiceList.getImage()));
        txtStatus.setText(status);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.change(invoiceList.getId());
            }
        });
        return convertView;
    }
}
