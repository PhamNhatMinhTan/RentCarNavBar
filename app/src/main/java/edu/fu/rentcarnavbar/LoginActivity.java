package edu.fu.rentcarnavbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.fu.rentcarnavbar.Model.UserDAO;
import edu.fu.rentcarnavbar.Object.User;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    SignInButton btnSignIn;
    private GoogleApiClient googleApiClient;
    private static final int SIGN_IN = 1;

    //declare variables
    private List<User> list = new ArrayList<>();
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDao = new UserDAO(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //if(googleApiClient != null) {
            // Build a GoogleSignInClient with the options specified by gso to access Google Sign In.
            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        //}


        btnSignIn = findViewById(R.id.sign_in_button);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);
                Log.d("Success!", googleApiClient.isConnected() + "");
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Failed!", connectionResult + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

            //if has get result from GoogleSignInClient.getSignInIntent(...)
            if(result.isSuccess()) {
                //Go to Update Profile Update Activity
                //startActivity(new Intent(LoginActivity.this, UpdateProfileActivity.class));
                //finish();
            } else {    //show warning message
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()) {
            //get information of sign in account
            GoogleSignInAccount account = result.getSignInAccount();
            Intent intent = null;

            String emailEmp1 = "phamnhatminhtan@gmail.com";
            String id = "1";
            if (emailEmp1.equals(account.getEmail())) {
                if(!isExist(account.getEmail())) {
                    intent = new Intent(LoginActivity.this, MainActivity.class);

                    User user1 = new User(id, "090456987", "Minh Tan", emailEmp1, "Can Tho", "123586985", 2);
                    userDao.insert(user1);

                    intent.putExtra("id", id);
                    startActivity(intent);
                } else {
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id", id);

                    startActivity(intent);
                }
            } else {
                if(!isExist(account.getEmail())) {
                    intent = new Intent(LoginActivity.this, UpdateProfileActivity.class);
                    intent.putExtra("id", account.getId());
                    intent.putExtra("name", account.getDisplayName());
                    intent.putExtra("email", account.getEmail());

                    startActivity(intent);
                } else {
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id", account.getId());

                    startActivity(intent);
                }
            }
        }
    }

    public boolean isExist(String email) {
        list = new ArrayList<>();
        list = userDao.getAllUser();

        for (User user : list) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}