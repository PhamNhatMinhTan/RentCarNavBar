package edu.fu.rentcarnavbar.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.MainActivity;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.Object.Vehicle;
import edu.fu.rentcarnavbar.R;

public class CarAdapter extends BaseAdapter {
    private Context context;
    List<Vehicle> lst;
    int layout;

    public CarAdapter(Context context, int layout,List<Vehicle> lst) {
        this.context = context;
        this.lst = lst;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lst.size();
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
        final View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);    //Khởi tạo giao diện cho row với câu lệnh LayoutInflater
            view = inflater.inflate(layout, null);
        } else
            view = convertView;

        TextView tvnameCar = (TextView) view.findViewById(R.id.txtName);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView seat = (TextView) view.findViewById(R.id.txtDateStart);
        TextView km = (TextView) view.findViewById(R.id.txtDateEnd);
        TextView date = (TextView) view.findViewById(R.id.txtTotal);
        //int i = R.drawable.;
        final Vehicle c = lst.get(position);
        tvnameCar.setText(c.getV_name());
        km.setText("$"+c.getV_costPerKm()+"/km");
        date.setText("$"+c.getV_costPerDate()+"/day");
        seat.setText("Seat: "+c.getV_seat());

        int iconResource = context.getResources().getIdentifier(c.getV_image(), "drawable", context.getPackageName());

        imageView.setImageResource(iconResource);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.deatailCar(c.getV_id());
            }
        });

        return  view;
    }
}
