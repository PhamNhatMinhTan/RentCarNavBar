package edu.fu.rentcarnavbar.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        //db.insert();
        repository = new VehicleDAO(getActivity().getBaseContext());
        //db.insertUser();
        lstCar = new ArrayList<>();
        lstCar = repository.getCarList();

        Button btnFilterBMW = root.findViewById(R.id.btnBMW);
        btnFilterBMW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstCar.clear();
                lstCar = repository.filterByBrand(1);
                listCarAdapter = new CarAdapter(getActivity().getBaseContext(), R.layout.invoke_item, lstCar);
                carListView.setAdapter(listCarAdapter);

            }
        });
        Button btnFilterPorche = root.findViewById(R.id.btnPorsche);
        btnFilterPorche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstCar.clear();
                lstCar = repository.filterByBrand(2);
                listCarAdapter = new CarAdapter(getActivity().getBaseContext(), R.layout.invoke_item, lstCar);
                carListView.setAdapter(listCarAdapter);

            }
        });
        Button btnFilterToyota = root.findViewById(R.id.btnToyota);
        btnFilterToyota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstCar.clear();
                lstCar = repository.filterByBrand(3);
                listCarAdapter = new CarAdapter(getActivity().getBaseContext(), R.layout.invoke_item, lstCar);
                carListView.setAdapter(listCarAdapter);

            }
        });
        Button btnFilter = root.findViewById(R.id.btnHonda);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstCar.clear();
                lstCar = repository.filterByBrand(4);
                listCarAdapter = new CarAdapter(getActivity().getBaseContext(), R.layout.invoke_item, lstCar);
                carListView.setAdapter(listCarAdapter);

            }
        });
        final TextView searchVehicle = root.findViewById(R.id.txtSearch);
        Button btn_Search = root.findViewById(R.id.btnSearch);
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstCar.clear();
                lstCar = repository.searchVehicle(searchVehicle.getText().toString());
                listCarAdapter = new CarAdapter(getActivity().getBaseContext(), R.layout.invoke_item, lstCar);
                carListView.setAdapter(listCarAdapter);
            }
        });
        listCarAdapter = new CarAdapter(getActivity().getBaseContext(), R.layout.invoke_item, lstCar);
        carListView.setAdapter(listCarAdapter);
        return root;
    }
}