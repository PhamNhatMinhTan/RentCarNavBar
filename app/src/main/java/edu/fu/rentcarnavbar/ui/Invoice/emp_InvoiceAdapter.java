package edu.fu.rentcarnavbar.ui.Invoice;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import edu.fu.rentcarnavbar.MainActivity;
import edu.fu.rentcarnavbar.Model.InvoiceDAO;
import edu.fu.rentcarnavbar.Model.UserDAO;
import edu.fu.rentcarnavbar.Object.InvoiceList;
import edu.fu.rentcarnavbar.R;

public class emp_InvoiceAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<InvoiceList> list;
    private UserDAO userDAO;
    private InvoiceDAO invoiceDAO;


    public emp_InvoiceAdapter(Context context, int layout, List<InvoiceList> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        userDAO = new UserDAO(context);
        invoiceDAO = new InvoiceDAO(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.invoice_item_emp, null);


        TextView txtCar = convertView.findViewById(R.id.txtNameCar_emp);
        TextView txtName = convertView.findViewById(R.id.txtCusName_emp);
        TextView txtDateStart = convertView.findViewById(R.id.txtDateStart_emp);
        TextView txtDateEnd = convertView.findViewById(R.id.txtDateEnd_emp);
        TextView txtStatus = convertView.findViewById(R.id.txtStatus_emp);
        ImageView image = convertView.findViewById(R.id.imageView_emp);

        txtCar.setTextColor(Color.BLUE);
        final InvoiceList invoiceList = list.get(position);

        txtCar.setText(invoiceList.getName());
        //txtName.setText(userDAO.getUserById(invoiceDAO.GetInvoiceById(invoiceList.getId()).getU_id()).getName());
        txtDateStart.setText("Date Start: "+invoiceList.getDateStart());
        txtDateEnd.setText("Date End: "+invoiceList.getDateEnd());
        image.setImageResource(Integer.parseInt(invoiceList.getImage()));
        String status = "";
        if(invoiceDAO.GetInvoiceById(invoiceList.getId()).getStatus() == 0)
        {
            status = "Processing";
            txtStatus.setTextColor(Color.DKGRAY);
        }
        else if(invoiceDAO.GetInvoiceById(invoiceList.getId()).getStatus() == 1){
            status = "Approved";
            txtStatus.setTextColor(Color.GREEN);
        }
        else if(invoiceDAO.GetInvoiceById(invoiceList.getId()).getStatus() == 2){
            status = "Returned";
            txtStatus.setTextColor(Color.LTGRAY);
        } else if (invoiceDAO.GetInvoiceById(invoiceList.getId()).getStatus() == 3) {
            status = "Rejected";
            txtStatus.setTextColor(Color.RED);
        }
        txtStatus.setText(status);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.change(invoiceList.getId());
            }
        });

        return convertView;
    }
}
