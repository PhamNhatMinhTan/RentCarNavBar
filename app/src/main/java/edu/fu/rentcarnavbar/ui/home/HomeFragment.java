package edu.fu.rentcarnavbar.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Model.DBOpenHepler;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.Object.Vehicle;
import edu.fu.rentcarnavbar.R;

public class HomeFragment extends Fragment {
    ListView carListView;
    List<Vehicle> lstCar;
    VehicleDAO repository;
    CarAdapter listCarAdapter;
    private InvoiceDAO inv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        carListView = (ListView) root.findViewById(R.id.listViewCar);

        DBOpenHepler db = new DBOpenHepler(getActivity().getBaseContext());
        db.insert();
        repository = new VehicleDAO(getActivity().getBaseContext());

        lstCar = new ArrayList<>();
        lstCar = repository.getCarList();
        listCarAdapter = new CarAdapter(getActivity().getBaseContext(), R.layout.invoke_item, lstCar);
        carListView.setAdapter(listCarAdapter);
        return root;
    }
}