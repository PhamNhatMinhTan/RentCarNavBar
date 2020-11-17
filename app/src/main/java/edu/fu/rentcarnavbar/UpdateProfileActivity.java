package edu.fu.rentcarnavbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Model.UserDAO;
import edu.fu.rentcarnavbar.Object.User;

public class UpdateProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //declare variables
    private List<User> list = new ArrayList<>();
    UserDAO userDao;

    //declare widget
    private TextView txtPhone;
    private TextView txtIdentity;
    private TextView txtAddress;
    private EditText edtPhone;
    private EditText edtIdentity;
    private EditText edtAddress;
    private Button btnBack;
    private Button btnDone;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        //get id widget
        txtPhone = findViewById(R.id.txtPhone);
        txtIdentity = findViewById(R.id.txtIdentity);
        txtAddress = findViewById(R.id.txtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        edtIdentity = findViewById(R.id.edtIdentity);
        edtAddress = findViewById(R.id.edtAddress);
        btnBack = findViewById(R.id.btnBackSignIn);
        btnDone = findViewById(R.id.btnDone);

        final Intent intent = getIntent();

        //TEST DATABASE
        userDao = new UserDAO(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        if (googleApiClient != null) {
            // Build a GoogleSignInClient with the options specified by gso to access Google Sign In.
            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackSignIn();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = edtPhone.getText().toString();
                String identity = edtIdentity.getText().toString();
                String address = edtAddress.getText().toString();

                if (phone.length() == 0 || identity.length() == 0 || address.length() == 0) {
                    showWaring("All field must be fill in!!!");
                } else if (!phone.matches("\\d{10}")) {
                    showWaring("The phone number must be the digits and long 10 characters");
                } else if (!(identity.matches("\\d{9}") || identity.matches("\\d{12}"))) {
                    showWaring("The Identity must be the digits and long from 9 or 12 characters");
                } else {
                    String id = insertUser(intent);
                    Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    private String insertUser(Intent intent) {
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String phone = edtPhone.getText().toString();
        String identity = edtIdentity.getText().toString();
        String address = edtAddress.getText().toString();

        User user = new User(id, phone, name, email, address, identity, 1);
        userDao.insert(user);

        return id;
    }

    private void BackSignIn() {
        startActivity(new Intent(UpdateProfileActivity.this, LoginActivity.class));
        finish();
    }

    private void gotoMainActivity() {
        startActivity(new Intent(UpdateProfileActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            //get information of sign in account
            GoogleSignInAccount account = result.getSignInAccount();
        } else {
            gotoMainActivity();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //login();

    }

    private void login() {
        //Authorize Account
        OptionalPendingResult<GoogleSignInResult> optionalPendingResult = Auth.GoogleSignInApi
                .silentSignIn(googleApiClient);

        //If done
        if (optionalPendingResult.isDone()) {
            //get information's account
            GoogleSignInResult result = optionalPendingResult.get();
            handleSignInResult(result);
        } else {
            optionalPendingResult.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }

    private void showWaring(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("ERROR!!!");
        alertDialog.setIcon(R.drawable.warning);
        alertDialog.setMessage(message);

//        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
        alertDialog.show();
    }

}