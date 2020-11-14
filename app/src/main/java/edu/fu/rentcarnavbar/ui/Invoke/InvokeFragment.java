package edu.fu.rentcarnavbar.ui.Invoke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.fu.rentcarnavbar.Object.Invoke;
import edu.fu.rentcarnavbar.R;

public class InvokeFragment extends Fragment {

    private InvokeViewModel invokeViewModel;
    ListView listView;
    ArrayList<Invoke> arrayList;
    InvokeAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invoke, container, false);

        listView = root.findViewById(R.id.listViewInvoke);
        arrayList = new ArrayList<>();
        arrayList.add(new Invoke(R.drawable.ic_baseline_person_24, "Porsche Cayman 720 2019", 20));
        arrayList.add(new Invoke(R.drawable.ic_dashboard_black_24dp, "Porsche Cayman 720 2020", 30));
        arrayList.add(new Invoke(R.drawable.ic_baseline_person_24, "BMW X7 2020", 32));
        arrayList.add(new Invoke(R.drawable.ic_dashboard_black_24dp, "Honda Civic 2020", 15));
        arrayList.add(new Invoke(R.drawable.ic_dashboard_black_24dp, "Honda City 2020", 12));

        adapter = new InvokeAdapter(getActivity().getBaseContext(), R.layout.invoke_item, arrayList);
        listView.setAdapter(adapter);

        return root;
    }

}