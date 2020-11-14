package edu.fu.rentcarnavbar.ui.Invoke;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.fu.rentcarnavbar.Object.Invoke;
import edu.fu.rentcarnavbar.R;

public class InvokeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Invoke> list;

    public InvokeAdapter(Context context, int layout, List<Invoke> list) {
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

        Invoke invoke = list.get(position);
        txtName.setText(invoke.getName());
        txtPrice.setText(String.valueOf(invoke.getPrice()));
        img.setImageResource(invoke.getImg());

        return convertView;
    }
}
