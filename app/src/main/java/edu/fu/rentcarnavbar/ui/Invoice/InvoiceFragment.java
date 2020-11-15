package edu.fu.rentcarnavbar.ui.Invoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edu.fu.rentcarnavbar.MainActivity;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.Object.InvoiceList;
import edu.fu.rentcarnavbar.R;

public class InvoiceFragment extends Fragment {

    ListView listView;
    ArrayList<InvoiceList> arrayList;
    InvoiceAdapter adapter;
    InvoiceDAO invoiceDAO;
    VehicleDAO vehicleDAO;
    String userID;
    int invoiceId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invoke, container, false);
        invoiceDAO = new InvoiceDAO(getActivity().getBaseContext());
        vehicleDAO = new VehicleDAO(getActivity().getBaseContext());
        userID = "106846390997285914983";
        listView = root.findViewById(R.id.listViewInvoke);
        arrayList = new ArrayList<>();
        //String image =  String.valueOf(R.drawable.bmw_740_black);

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                invoiceId = position;
                //MainActivity.change(invoiceId);
            }
        });

        return root;
    }

}