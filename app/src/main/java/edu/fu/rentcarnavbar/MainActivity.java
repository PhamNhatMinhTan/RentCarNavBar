package edu.fu.rentcarnavbar;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentManager;

import android.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.fu.rentcarnavbar.Model.BranchDAO;
import edu.fu.rentcarnavbar.Model.ColorDAO;
import edu.fu.rentcarnavbar.Model.DBOpenHepler;
import edu.fu.rentcarnavbar.Model.FuelDAO;
import edu.fu.rentcarnavbar.Model.GearDAO;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.Model.VersionDAO;
import edu.fu.rentcarnavbar.ui.Invoice.DetailInvoiceFragment;

public class MainActivity extends AppCompatActivity {

    InvoiceDAO inv;
    ColorDAO color;
    FuelDAO fuel;
    VersionDAO version;
    GearDAO gear;
    BranchDAO branch;
    VehicleDAO vehicleDAO;
    DBOpenHepler helper;
    static FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        inv = new InvoiceDAO(this);
        branch = new BranchDAO(this);
        color = new ColorDAO(this);
        fuel = new FuelDAO(this);
        gear = new GearDAO(this);
        version = new VersionDAO(this);
        vehicleDAO = new VehicleDAO(this);
        helper = new DBOpenHepler(this);

        manager = getFragmentManager();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public static void change(int invoice_Id){
        DetailInvoiceFragment invoiceDetail = new DetailInvoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("invoiceId", invoice_Id);
        invoiceDetail.setArguments(bundle);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, invoiceDetail);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}