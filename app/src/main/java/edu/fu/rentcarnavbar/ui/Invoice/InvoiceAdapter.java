package edu.fu.rentcarnavbar.ui.Invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.fu.rentcarnavbar.Object.Invoice;
import edu.fu.rentcarnavbar.R;

public class InvoiceAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Invoice> list;

    public InvoiceAdapter(Context context, int layout, List<Invoice> list) {
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
        TextView txtPrice = convertView.findViewById(R.id.txtprice);
        ImageView img = convertView.findViewById(R.id.imageView);

        Invoice invoice = list.get(position);
        txtName.setText(invoice.getName());
        txtPrice.setText(String.valueOf(invoice.getPrice()));
        img.setImageResource(invoice.getImg());

        return convertView;
    }
}
