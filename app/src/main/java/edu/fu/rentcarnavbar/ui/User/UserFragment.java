package edu.fu.rentcarnavbar.ui.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import edu.fu.rentcarnavbar.LoginActivity;
import edu.fu.rentcarnavbar.Model.UserDAO;
import edu.fu.rentcarnavbar.Object.User;
import edu.fu.rentcarnavbar.R;
import edu.fu.rentcarnavbar.ui.Invoke.InvokeFragment;

public class UserFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener{
    private boolean isEdit = true;
    private UserViewModel userViewModel;
    private UserDAO userDao;
    private User user = new User();

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    //declare widget
    private TextView txtName;
    private TextView txtPhoneDetail;
    private TextView txtEmailDetail;
    private TextView txtIdentityDetail;
    private TextView txtAddressDetail;
    private Button btnEdit;
    private Button btnSignOut;

    Intent intent;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        userViewModel =
//                ViewModelProviders.of(this).get(UserViewModel.class);
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        final View root2 = inflater.inflate(R.layout.fragment_invoke, container, false);
        final TextView textView = view.findViewById(R.id.text_notifications);
        /*userViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        userDao = new UserDAO(getActivity());

        intent = getActivity().getIntent();

        final String id = intent.getStringExtra("id");
        //Log.d("Log user ID", id);

        user = userDao.getUserById(id);

        txtName = view.findViewById(R.id.txtName);
        txtPhoneDetail = view.findViewById(R.id.txtPhoneDetail);
        txtEmailDetail = view.findViewById(R.id.txtEmailDetail);
        txtIdentityDetail = view.findViewById(R.id.txtIdentityDetail);
        txtAddressDetail = view.findViewById(R.id.txtAddressDetail);
        final Button btnEdit = view.findViewById(R.id.btnEdit);
        Button btnSignOut = view.findViewById(R.id.btnSignOut);

//        txtPhoneDetail.setEnabled(false);
//        txtEmailDetail.setEnabled(false);
//        txtIdentityDetail.setEnabled(false);
//        txtAddressDetail.setEnabled(false);

        txtName.setText(user.getName());
        txtPhoneDetail.setText(user.getPhone());
        txtEmailDetail.setText(user.getEmail());
        txtIdentityDetail.setText(user.getIdentity());
        txtAddressDetail.setText(user.getAddress());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEdit){
                    isEdit = false;
                    btnEdit.setText("Save");
                    //txtPhoneDetail.setEnabled(true);
                    //txtAddressDetail.setEnabled(true);
                } else {
                    isEdit = true;
                    //String phone = txtPhoneDetail.getText().toString();
                    //String address = txtAddressDetail.getText().toString();
                    //userDao.update(id, phone, address);
                    btnEdit.setText("Edit");
                    //txtPhoneDetail.setEnabled(false);
                    //txtAddressDetail.setEnabled(false);
                }
            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        textView.setText("My name ntk");

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(root., root2.getClass()));
                //Auth.GoogleSignInApi.signOut(googleApiClient).add
                signOut();
            }
        });
        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void signOut() {
        GoogleSignIn.getClient(getActivity(), gso).signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }
}