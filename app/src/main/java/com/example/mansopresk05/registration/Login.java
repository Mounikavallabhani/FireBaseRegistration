package com.example.mansopresk05.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity implements View.OnClickListener
{



    //defining view objects
    private EditText UserName;
    private EditText Password;
    private Button Login;
    private TextView forgetpassword;
    private TextView newuserRegister;


    private ProgressDialog progressDialog;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
      //  if (firebaseAuth.getCurrentUser() != null) {
            //that means user is already logged in
            //so close this activity
            //finish();

            //and open profile activity
          // startActivity(new Intent(getApplicationContext(), Successfull.class));
       // }

        //initializing views
        UserName = (EditText) findViewById(R.id.et1);
        Password = (EditText) findViewById(R.id.et2);

        Login = (Button) findViewById(R.id.bt1);
        forgetpassword = (TextView) findViewById(R.id.tv1);
        newuserRegister = (TextView) findViewById(R.id.tv2);


        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        Login.setOnClickListener(this);
        forgetpassword.setOnClickListener(this);
        newuserRegister.setOnClickListener(this);
    }

    private void registerUser() {

        //getting email and password from edit texts
        String uname = UserName.getText().toString().trim();
        String pwd = Password.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(uname)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Loging Please Wait...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(uname,pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), Successfull.class));
                                } else {
                                    //display some message here
                                    Toast.makeText(Login.this, "Login Error", Toast.LENGTH_LONG).show();
                                }

                            }
                        });

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(uname, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), Successfull.class));
                        } else {
                            //display some message here
                            Toast.makeText(Login.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view)
    {

        if (view == Login) {
            registerUser();
        }

        if (view == newuserRegister) {
            //open login activity when user taps on the already registered textview
            startActivity(new Intent(this, Registration.class));
        }
        if (view == forgetpassword) {
            startActivity(new Intent(this,Forget.class));
        }

    }
}
