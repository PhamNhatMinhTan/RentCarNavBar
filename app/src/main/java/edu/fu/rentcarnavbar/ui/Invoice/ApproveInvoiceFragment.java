package edu.fu.rentcarnavbar.ui.Invoice;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.app.Fragment;
import android.widget.Toast;

import java.util.ArrayList;

import edu.fu.rentcarnavbar.MainActivity;
import edu.fu.rentcarnavbar.Model.FuelDAO;
import edu.fu.rentcarnavbar.Model.GearDAO;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.R;

public class ApproveInvoiceFragment extends Fragment {

    //declare class DAO
    VehicleDAO vehicleDAO;
    InvoiceDAO invoiceDAO;
    FuelDAO fuelDAO;
    GearDAO gearDAO;

    //declare variable
    String status = "";

    //declare widget
    Spinner spinnerStatus;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_approve_invoice, container, false);

        //get widget
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
        spinnerStatus = view.findViewById(R.id.spinnerStatus);
        Button btnConfirm = view.findViewById(R.id.btnConfirmInvoice);

        vehicleDAO = new VehicleDAO(getActivity().getBaseContext());
        invoiceDAO = new InvoiceDAO(getActivity().getBaseContext());
        fuelDAO = new FuelDAO(getActivity().getBaseContext());
        gearDAO = new GearDAO(getActivity().getBaseContext());

        Bundle bundle = getArguments();
        final int value = bundle.getInt("invoiceId");

        int v_id = invoiceDAO.GetInvoiceById(value).getV_id();

        final ArrayList<String> arrayStatus = new ArrayList<String>();
        arrayStatus.add("APPROVE");
        arrayStatus.add("REJECT");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, arrayStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);

        //set text for edit text
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

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status = arrayStatus.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("APPROVE".equals(status)) {
                    invoiceDAO.updateInvoice(value, "Approved");
                } else if("REJECT".equals(status)) {
                    invoiceDAO.updateInvoice(value, "Rejected");
                }
                Toast.makeText(getActivity(), "Confirm Successful", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
