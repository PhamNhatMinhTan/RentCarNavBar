package edu.fu.rentcarnavbar.ui.Invoice;

import android.os.Build;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import edu.fu.rentcarnavbar.Model.FuelDAO;
import edu.fu.rentcarnavbar.Model.GearDAO;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.R;

public class DetailInvoiceFragment extends Fragment {

    VehicleDAO vehicleDAO;
    InvoiceDAO invoiceDAO;
    FuelDAO fuelDAO;
    GearDAO gearDAO;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_invoice, container, false);
        Bundle bundle = getArguments();
        int value = bundle.getInt("invoiceId");

        vehicleDAO = new VehicleDAO(getActivity().getBaseContext());
        invoiceDAO = new InvoiceDAO(getActivity().getBaseContext());
        fuelDAO = new FuelDAO(getActivity().getBaseContext());
        gearDAO = new GearDAO(getActivity().getBaseContext());

        int v_id = invoiceDAO.GetInvoiceById(value).getV_id();
        TextView txtName = view.findViewById(R.id.txtNameCar_iv_emp);
        TextView txtGear = view.findViewById(R.id.txtGear_iv);
        TextView txtFuel = view.findViewById(R.id.txtFuel_iv);
        TextView txtlicensePlate = view.findViewById(R.id.txtlicensePlate_iv);
        TextView txtSeat = view.findViewById(R.id.txtSeat_iv);
        TextView txtPhone = view.findViewById(R.id.txtPhone_iv);
        TextView txtCus = view.findViewById(R.id.txtName_iv);
        TextView txtdateStart = view.findViewById(R.id.txtDateStart_iv);
        TextView txtdateEnd = view.findViewById(R.id.txtDateEnd_iv);
        TextView txtTotal = view.findViewById(R.id.txtTotal_iv);
        TextView txtIdentity = view.findViewById(R.id.txtIdentity_iv);
        ImageView imageView = view.findViewById(R.id.imgInvoiceDetail);

        txtName.setText(vehicleDAO.GetVehicleById(v_id).getV_name());
        txtlicensePlate.setText(vehicleDAO.GetVehicleById(v_id).getV_licensePlate());
        txtSeat.setText(String.valueOf(vehicleDAO.GetVehicleById(v_id).getV_seat()) + " Seat");
        txtCus.setText(invoiceDAO.GetInvoiceById(value).getName());
        txtPhone.setText(invoiceDAO.GetInvoiceById(value).getPhone());
        txtdateEnd.setText(invoiceDAO.GetInvoiceById(value).getDateEnd());
        txtdateStart.setText(invoiceDAO.GetInvoiceById(value).getDateStart());
        txtTotal.setText(String.valueOf("$"+invoiceDAO.GetInvoiceById(value).getTotal()));
        txtIdentity.setText(invoiceDAO.GetInvoiceById(value).getIdentity());
        txtFuel.setText(fuelDAO.GetFuelById(vehicleDAO.GetVehicleById(v_id).getFuel()).getFuel());
        txtGear.setText(gearDAO.GetGearById(vehicleDAO.GetVehicleById(v_id).getGear()).getGear());

        String imageName = vehicleDAO.GetVehicleById(v_id).getV_image();
        int image = getContext().getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());

        imageView.setImageResource(image);
        //TextView txt = view.findViewById(R.id.txtnameInvoice);
        //txt.setText(value);
        return view;
    }
}