package edu.fu.rentcarnavbar.ui.Invoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edu.fu.rentcarnavbar.Object.Invoice;
import edu.fu.rentcarnavbar.R;

public class InvoiceFragment extends Fragment {

    private InvoiceViewModel invoiceViewModel;
    ListView listView;
    ArrayList<Invoice> arrayList;
    InvoiceAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invoke, container, false);

        listView = root.findViewById(R.id.listViewInvoke);
        arrayList = new ArrayList<>();
/*        arrayList.add(new Invoice(R.drawable.ic_baseline_person_24, "Porsche Cayman 720 2019", 20));
        arrayList.add(new Invoice(R.drawable.ic_dashboard_black_24dp, "Porsche Cayman 720 2020", 30));
        arrayList.add(new Invoice(R.drawable.ic_baseline_person_24, "BMW X7 2020", 32));
        arrayList.add(new Invoice(R.drawable.ic_dashboard_black_24dp, "Honda Civic 2020", 15));
        arrayList.add(new Invoice(R.drawable.ic_dashboard_black_24dp, "Honda City 2020", 12));*/
//
//        adapter = new InvoiceAdapter(getActivity().getBaseContext(), R.layout.invoke_item, arrayList);
//        listView.setAdapter(adapter);

        return root;
    }

}