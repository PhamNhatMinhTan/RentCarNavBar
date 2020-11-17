package edu.fu.rentcarnavbar;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Model.BranchDAO;
import edu.fu.rentcarnavbar.Model.ColorDAO;
import edu.fu.rentcarnavbar.Model.DBOpenHepler;
import edu.fu.rentcarnavbar.Model.FuelDAO;
import edu.fu.rentcarnavbar.Model.GearDAO;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.UserDAO;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.Object.User;
import edu.fu.rentcarnavbar.ui.Invoice.DetailInvoiceFragment;
import edu.fu.rentcarnavbar.ui.home.RentalFragment;
import edu.fu.rentcarnavbar.ui.home.carDetailFragment;

public class MainActivity extends AppCompatActivity {

    InvoiceDAO inv;
    ColorDAO color;
    FuelDAO fuel;
    //VersionDAO version;
    GearDAO gear;
    BranchDAO branch;
    VehicleDAO vehicleDAO;
    DBOpenHepler helper;
    UserDAO userDAO;
    static FragmentManager manager;
    static String userID;
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        inv = new InvoiceDAO(this);
        branch = new BranchDAO(this);
        color = new ColorDAO(this);
        fuel = new FuelDAO(this);
        gear = new GearDAO(this);
        userDAO = new UserDAO(this);
        List<User> uList = new ArrayList<>();
        uList = userDAO.getAllUser();
        //version = new VersionDAO(this);
        vehicleDAO = new VehicleDAO(this);
        helper = new DBOpenHepler(this);
        userDAO = new UserDAO(this);

        manager = getFragmentManager();
        int size = inv.getAllInvoice().size();
//        User user = new User("1312asd", "0868772887", "Tran Minh Thien", "Thientm@gmail.com", "AG",
//                "352506532", 2);
//        userDAO.insert(user);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        Intent intent = MainActivity.this.getIntent();
        Bundle bundle = new Bundle();
        userID= intent.getStringExtra("id");
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


    public static void deatailCar(int id){
        carDetailFragment carDetail = new carDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        carDetail.setArguments(bundle);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, carDetail);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public static void rentalCar(int v_id){
        RentalFragment rentalFragment = new RentalFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uid", userID);
        Log.w("UID", userID);
        bundle.putInt("vid", v_id);
        rentalFragment.setArguments(bundle);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, rentalFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}