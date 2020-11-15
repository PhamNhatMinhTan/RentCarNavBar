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
import edu.fu.rentcarnavbar.Object.InvoiceList;
import edu.fu.rentcarnavbar.R;

public class InvoiceFragment extends Fragment {

    private InvoiceViewModel invoiceViewModel;
    ListView listView;
    ArrayList<InvoiceList> arrayList;
    InvoiceAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invoke, container, false);

        listView = root.findViewById(R.id.listViewInvoke);
        arrayList = new ArrayList<>();
        String image =  String.valueOf(R.drawable.bmw_740_black);
        arrayList.add(new InvoiceList(1,"Porsche 720 2020", "10/1150/2020", "12/11/2020", image ,150));
//
        adapter = new InvoiceAdapter(getActivity().getBaseContext(), R.layout.invoke_item, arrayList);
        listView.setAdapter(adapter);

        return root;
    }

}