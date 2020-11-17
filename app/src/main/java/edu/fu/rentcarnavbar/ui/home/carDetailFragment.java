package edu.fu.rentcarnavbar.ui.home;

import androidx.annotation.RequiresApi;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.fu.rentcarnavbar.MainActivity;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.Object.Branch;
import edu.fu.rentcarnavbar.Object.Color;
import edu.fu.rentcarnavbar.Object.Fuel;
import edu.fu.rentcarnavbar.Object.Gear;
import edu.fu.rentcarnavbar.Object.Vehicle;
import edu.fu.rentcarnavbar.Object.Version;
import edu.fu.rentcarnavbar.R;

public class carDetailFragment extends Fragment {
    ImageView carImg;
    TextView branch;
    TextView version;
    TextView color;
    TextView gear;
    TextView fuel;
    TextView seat;
    TextView licensePlate;
    TextView costByKm;
    TextView costByDate;
    Button btnHire;
    VehicleDAO vehicleDAO;
    TextView carName;

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_detail, container, false);

        Bundle bundle = getArguments();
        final int value = bundle.getInt("id");

        vehicleDAO = new VehicleDAO(getActivity().getBaseContext());
        Vehicle c = vehicleDAO.GetVehicleById(value);

        carImg = view.findViewById(R.id.imgCarDetail);
        int iconResource = getActivity().getBaseContext().getResources().getIdentifier(c.getV_image(), "drawable", getActivity().getBaseContext().getPackageName());
        carImg.setImageResource(iconResource);

        branch = view.findViewById(R.id.txtBranchCDS);
        List<Branch> lstBranch= vehicleDAO.getBranchList();
        for(Branch b:lstBranch)
        {
            if(b.getBr_id() == c.getBranch())
            {
                branch.setText(b.getBr_name());
                break;
            }
        }

        carName = view.findViewById(R.id.txtNameCarDetail);
        carName.setText(c.getV_name());
        version = view.findViewById(R.id.txtVersionCDS);
        List<Version> lstVersion= vehicleDAO.getVesionList();
        for(Version b:lstVersion)
        {
            if(b.getVs_id() == c.getVersion())
            {
                version.setText(b.getVersion());
                break;
            }
        }

        color = view.findViewById(R.id.txtColorCDS);
        List<Color> lstColor= vehicleDAO.getColorList();
        for(Color b:lstColor)
        {
            if(b.getColor_id() == c.getColor())
            {
                color.setText(b.getColor());
                break;
            }
        }

        gear = view.findViewById(R.id.txtGearCDS);
        List<Gear> lstGear= vehicleDAO.getGreaList();
        for(Gear b:lstGear)
        {
            if(b.getG_id() == c.getGear())
            {
                gear.setText(b.getGear());
                break;
            }
        }
        fuel = view.findViewById(R.id.txtFuelCDS);
        List<Fuel> lstFuel= vehicleDAO.getFuelList();
        for(Fuel b:lstFuel)
        {
            if(b.getF_id() == c.getFuel())
            {
                fuel.setText(b.getFuel());
                break;
            }
        }
        seat = view.findViewById(R.id.txtSeatCDS);
        seat.setText(c.getV_seat()+"");

        licensePlate = view.findViewById(R.id.txtLisenceCDS);
        licensePlate.setText(c.getV_licensePlate());

        costByKm = view.findViewById(R.id.txtCostByKmCDS);
        costByKm.setText(c.getV_costPerKm()+"");

        costByDate = view.findViewById(R.id.txtCostByDateCDS);
        costByDate.setText(c.getV_costPerDate()+"");
        btnHire = (Button) view.findViewById(R.id.btnHireCar);



        btnHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.rentalCar(value);
            }
        });
        return view;
    }


}