package edu.fu.rentcarnavbar.ui.Invoice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edu.fu.rentcarnavbar.MainActivity;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.UserDAO;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.Object.InvoiceList;
import edu.fu.rentcarnavbar.R;

public class InvoiceFragment extends Fragment {

    ListView listView;
    ArrayList<InvoiceList> arrayList;
    InvoiceAdapter adapter;
    emp_InvoiceAdapter emp_Adapter;
    InvoiceDAO invoiceDAO;
    VehicleDAO vehicleDAO;
    UserDAO userDAO;
    String userID;
    int invoiceId;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invoke, container, false);
        invoiceDAO = new InvoiceDAO(getActivity().getBaseContext());
        vehicleDAO = new VehicleDAO(getActivity().getBaseContext());
        userDAO = new UserDAO(getActivity().getBaseContext());

        //userID = "106846390997285914983";
        //userID = "1312asd";
        Intent intent = getActivity().getIntent();
        userID = intent.getStringExtra("id");
        listView = root.findViewById(R.id.listViewInvoke);
        arrayList = new ArrayList<>();
        int stt = userDAO.getUserById(userID).getStatus();
        if(userDAO.getUserById(userID).getStatus() == 1)
        {
            for(int i = 0; i < invoiceDAO.GetInvoiceByUser(userID).size(); i++)
            {
                String name = vehicleDAO.GetVehicleById(invoiceDAO.GetInvoiceByUser(userID).get(i).getV_id()).getV_name();
                String imageName = vehicleDAO.GetVehicleById(invoiceDAO.GetInvoiceByUser(userID).get(i).getV_id()).getV_image();
                int image = getContext().getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());

                arrayList.add(new InvoiceList(invoiceDAO.GetInvoiceByUser(userID).get(i).getId(), name
                        , invoiceDAO.GetInvoiceByUser(userID).get(i).getDateStart(), invoiceDAO.GetInvoiceByUser(userID).get(i).getDateEnd()
                        , String.valueOf(image), invoiceDAO.GetInvoiceByUser(userID).get(i).getTotal()));

            }
            adapter = new InvoiceAdapter(getActivity().getBaseContext(), R.layout.invoke_item, arrayList);
            listView.setAdapter(adapter);
        }
        else if((userDAO.getUserById(userID).getStatus() == 2)){

            for(int i = 0; i < invoiceDAO.getAllInvoice().size(); i++)
            {
                String name = vehicleDAO.GetVehicleById(invoiceDAO.getAllInvoice().get(i).getV_id()).getV_name();
                String imageName = vehicleDAO.GetVehicleById(invoiceDAO.getAllInvoice().get(i).getV_id()).getV_image();
                int image = getContext().getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());

                arrayList.add(new InvoiceList(invoiceDAO.getAllInvoice().get(i).getId(), name
                        , invoiceDAO.getAllInvoice().get(i).getDateStart(), invoiceDAO.getAllInvoice().get(i).getDateEnd()
                        , String.valueOf(image), invoiceDAO.getAllInvoice().get(i).getTotal()));

            }
            emp_Adapter = new emp_InvoiceAdapter(getActivity().getBaseContext(), R.layout.invoice_item_emp, arrayList);
            listView.setAdapter(emp_Adapter);
        }

        return root;
    }

}