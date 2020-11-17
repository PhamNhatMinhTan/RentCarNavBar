package edu.fu.rentcarnavbar.ui.Invoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edu.fu.rentcarnavbar.R;

class ApproveInvoiceFragment extends Fragment {

    Spinner spinnerStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_approve_invoice, container, false);

        //int v_id = invoiceDAO.GetInvoiceById(value).getV_id();
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

        ArrayList<String> arrayStatus = new ArrayList<String>();
        arrayStatus.add("Approve");
        arrayStatus.add("Delete");
        arrayStatus.add("Not Approve");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, arrayStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);

        return view;
    }
}
