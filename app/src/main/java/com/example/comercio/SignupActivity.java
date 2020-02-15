package com.example.comercio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    EditText email;
    EditText password;
    EditText registerno;
    EditText name;

    Button signup_button;
    TextView login_view;

    UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Users");
        //myRef.setValue("Hello, World!");


        email = findViewById(R.id.input_signup_email);
        password = findViewById(R.id.input_signup_password);
        registerno = findViewById(R.id.input_signup_regno);
        name = findViewById(R.id.input_signup_name);
        signup_button = findViewById(R.id.btn_signup);
        login_view = findViewById(R.id.link_login);


        login_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAccount(email.getText().toString(),password.getText().toString());

            }
        });


    }


    private void createAccount(final String email, String password) {
        Log.d("SignUp Activity", "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressBar();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            userInfo  = new UserInfo();

                            userInfo.setEmail(email);
                            userInfo.setName(name.getText().toString());
                            userInfo.setRegister_number(registerno.getText().toString());

                            myRef.child(registerno.getText().toString()).setValue(userInfo);

                            Log.d("SignUp Activity", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SignUp Activity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                       //hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }


    private boolean validateForm() {
        boolean valid = true;

        String emailid = email.getText().toString();
        if (TextUtils.isEmpty(emailid)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        String passwordid = password.getText().toString();
        if (TextUtils.isEmpty(passwordid)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        String username = name.getText().toString();
        if (TextUtils.isEmpty(passwordid)) {
            name.setError("Required.");
            valid = false;
        } else {
            name.setError(null);
        }

        String register_no = registerno.getText().toString();
        if (TextUtils.isEmpty(passwordid)) {
            registerno.setError("Required.");
            valid = false;
        } else {
            registerno.setError(null);
        }

        return valid;
    }

}
