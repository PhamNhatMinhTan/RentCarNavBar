package edu.fu.rentcarnavbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private ImageView avatar;
    private TextView txtName;
    private TextView txtEmail;
    private TextView txtID;
    private Button btnSignOut;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Intent intent = getIntent();

        //TEST DATABASE
        userDao = new UserDAO(this);
        //list = userDao.getAllUser();

        //User userTmp = getSignInResult();

        //init data if database is empty
//        if (list.size() == 0) {
//
//            User user1 = new User("MT001", "123456789", "Minh Tan", "abc@gmail.com",
//                    "123 le anh xuan", "123456789", 1);
//            User user2 = new User("MT002", "1122334455", "Pham Nhat", "def@gmail.com",
//                    "123 le anh xuan", "123456789", 1);
//            User user3 = new User("MT003", "123456789", "Minh Tan", "abc@gmail.com",
//                    "123 le anh xuan", "123456789", 1);
//
//            userDao.insert(user1);
//            userDao.insert(user2);
//            userDao.insert(user3);
//        }

        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        User user1 = new User("MT001", "123456789", name, email,
                    "123 le anh xuan", "123456789", 1);
        userDao.insert(user1);

        //END TEST DATABASE

        avatar = findViewById(R.id.avatar);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtID = findViewById(R.id.txtID);
        btnSignOut = findViewById(R.id.btnSignOut);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso to access Google Sign In.
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });



    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if(status.isSuccess())
                    gotoMainActivity();
                else
                    Toast.makeText(UpdateProfileActivity.this, "Sign out Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gotoMainActivity() {
        startActivity(new Intent(UpdateProfileActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()) {
            //get information of sign in account
            GoogleSignInAccount account = result.getSignInAccount();

            txtName.setText(account.getDisplayName());
            txtEmail.setText(account.getEmail());
            txtID.setText(account.getId());

            //user Picasso to set image to
            Picasso.get().load(account.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(avatar);
        } else {
            gotoMainActivity();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        login();

    }

    private void login() {
        //Authorize Account
        OptionalPendingResult<GoogleSignInResult> optionalPendingResult = Auth.GoogleSignInApi
                .silentSignIn(googleApiClient);

        //If done
        if(optionalPendingResult.isDone()) {
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

}