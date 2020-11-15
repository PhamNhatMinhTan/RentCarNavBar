package edu.fu.rentcarnavbar;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.fu.rentcarnavbar.Model.BranchDAO;
import edu.fu.rentcarnavbar.Model.ColorDAO;
import edu.fu.rentcarnavbar.Model.FuelDAO;
import edu.fu.rentcarnavbar.Model.GearDAO;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.VersionDAO;
import edu.fu.rentcarnavbar.Object.Branch;
import edu.fu.rentcarnavbar.Object.Color;
import edu.fu.rentcarnavbar.Object.Fuel;
import edu.fu.rentcarnavbar.Object.Gear;
import edu.fu.rentcarnavbar.Object.Invoice;
import edu.fu.rentcarnavbar.Object.Version;

public class MainActivity extends AppCompatActivity {

    InvoiceDAO inv;
    ColorDAO color;
    FuelDAO fuel;
    VersionDAO version;
    GearDAO gear;
    BranchDAO branch;

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
        //insert default
///*        Invoice invoice1 = new Invoice("12/11/2020", "15/11/2020", 50, "Nguyen Minh Thao", "0868772887", "352506532", 1, 2, 2);
//
//        inv.InsertInvoice(invoice1);*/
//        Fuel f = new Fuel("Gasoline");
//        Fuel f2 = new Fuel("Oil");
//        fuel.InsertFuel(f);data
//        fuel.InsertFuel(f2);
//        Gear g = new Gear("Auto");
//        Gear g2 = new Gear("Manual");
//        gear.InsertGear(g);
//        gear.InsertGear(g2);
//        Version v = new Version("Cayman 2020");
//        Version v1 = new Version("Cayman 2019");
//        version.InsertVersion(v);
//        version.InsertVersion(v1);
//        Branch br = new Branch("Porsche", "porsche");
//        Branch br1 = new Branch("BMW", "bmw");
//        branch.InsertBranch(br);
//        branch.InsertBranch(br1);
//        Color col = new Color("Blue");
//        Color col2 = new Color("Black");
//        color.InsertColor(col);
//        color.InsertColor(col2);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}