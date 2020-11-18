package edu.fu.rentcarnavbar.ui.home;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import edu.fu.rentcarnavbar.MainActivity;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.UserDAO;
import edu.fu.rentcarnavbar.Model.VehicleDAO;
import edu.fu.rentcarnavbar.Object.Invoice;
import edu.fu.rentcarnavbar.Object.User;
import edu.fu.rentcarnavbar.Object.Vehicle;
import edu.fu.rentcarnavbar.R;

import static java.sql.Types.NULL;


public class RentalFragment extends Fragment {

    VehicleDAO vehicleDAO;
    TextView nameCar;
    EditText userName;
    EditText userPhone;
    EditText userIden;
    EditText startdate;
    EditText enddate;
    ImageView imgCar;
    TextView total;
    SimpleDateFormat df;
    Calendar now;
    int currentDate;
    int currentMonth;
    Button btn;
    Button btnEndDate;
    Button rent;
    InvoiceDAO invoiceDAO;
    String totalAmount;
    Vehicle c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_rental, container, false);
        invoiceDAO=new InvoiceDAO(getActivity().getBaseContext());
        df  = new SimpleDateFormat("dd/mm/yyy");
        now = Calendar.getInstance();
        //currentDate = now.get(Calendar.DATE)+"/"+now.get(Calendar.MONTH)+"/"+now.get(Calendar.YEAR);
        //Date date1 = df.parse(endDate));
        //            Date startingDate = df.parse(startDate);
        currentDate = now.get(Calendar.DATE)+1;
        currentMonth = now.get(Calendar.MONTH);


        Bundle bundle = getArguments();
        final int vid = bundle.getInt("vid");
        final String uid = bundle.getString("uid");
        vehicleDAO = new VehicleDAO(getActivity().getBaseContext());
        Log.w("UID in rental: ", uid);
        c = vehicleDAO.GetVehicleById(vid);
        User u = new User();

        //uid="123";
        u = vehicleDAO.getUser(uid);
        Log.w("UID RENTAL: ",u.getId()+"_");
        Log.w("U NAME RENTAL: ",u.getName()+"_");


        imgCar = view.findViewById(R.id.imgRent);
        int iconResource = getActivity().getBaseContext().getResources().getIdentifier(c.getV_image(), "drawable", getActivity().getBaseContext().getPackageName());
        imgCar.setImageResource(iconResource);


        nameCar = view.findViewById(R.id.txtCarNameRent);
        nameCar.setText(c.getV_name());

        userIden = view.findViewById(R.id.txtIdentityRent);
        userIden.setText(u.getIdentity());

        userName = (EditText) view.findViewById(R.id.txtFullNameCarRent);
        userName.setText(u.getName());

        userPhone = (EditText) view.findViewById(R.id.txtPhoneRent);
        userPhone.setText(u.getPhone());

        startdate = (EditText)view.findViewById(R.id.txtStartDateRent);


        btn = (Button)view.findViewById(R.id.btnPickStarDate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        enddate = (EditText)view.findViewById(R.id.txtEndDateRent);

        //Log.w(u.getIdentity(),":ident:");
        //long d = getDaysBetweenDates("20/11/2012","23/11/2012");

        btnEndDate = view.findViewById(R.id.btnPickEndDate);
        total = view.findViewById(R.id.txtTotalRent);


        rent = view.findViewById(R.id.btnRentcar);
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check null of age
                if(TextUtils.isEmpty(userName.getText().toString().trim())){
                    Toast.makeText(getActivity().getBaseContext(), "Invalid name. Enter again please.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(userIden.getText().toString().trim())){
                    Toast.makeText(getActivity().getBaseContext(), "Invalid identity. Enter again please.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(userPhone.getText().toString().trim())){
                    Toast.makeText(getActivity().getBaseContext(), "Invalid phone. Enter again please.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(startdate.getText().toString().trim())){
                    Toast.makeText(getActivity().getBaseContext(), "Invalid start date. Enter again please.", Toast.LENGTH_SHORT).show();
                    return;
                }else  if(TextUtils.isEmpty(enddate.getText().toString().trim())){
                    Toast.makeText(getActivity().getBaseContext(), "Invalid end date. Enter again please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Invoice i = new Invoice(NULL,startdate.getText().toString(), enddate.getText().toString(), Double.parseDouble(totalAmount),userName.getText().toString(),userPhone.getText().toString(),userIden.getText().toString(),1,uid, vid);

                invoiceDAO.InsertInvoice(i);
                //Toast.makeText(getActivity().getBaseContext(), "Hire car succesful", Toast.LENGTH_SHORT);
                MainActivity.deatailCar(c.getV_id());

            }
        });
        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDatePicker(0);
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(1);
            }
        });


    }
    private void showDatePicker(int i) {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH)+1);
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        if(i==0)
            date.setCallBack(ondate);
        else
            date.setCallBack(afterdate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            String d= String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1)
                    + "/" + String.valueOf(year);
            Log.w("date: ",d);
            startdate.setText(d);

        }
    };

    DatePickerDialog.OnDateSetListener afterdate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            String d = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1)
                    + "/" + String.valueOf(year);
            Log.w("afterdate: ", d);
            enddate.setText(d);

            long i = getDaysBetweenDates(startdate.getText().toString(), enddate.getText().toString());
            totalAmount =  String.valueOf(i * c.getV_costPerDate());
            total.setText((totalAmount));
            //Log.w("Total date: ",i+"");
        }
    };

    public long getDaysBetweenDates(String start, String end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy",      Locale.ENGLISH);
        Date startDate, endDate;
        long numberOfDays = 0;
        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
            numberOfDays = getUnitBetweenDates(startDate, endDate, TimeUnit.DAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfDays;
    }


    private long getUnitBetweenDates(Date startDate, Date endDate,   TimeUnit unit) {
        long timeDiff = endDate.getTime() - startDate.getTime();
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

}