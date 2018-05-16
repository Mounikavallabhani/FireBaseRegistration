package com.example.mansopresk05.registration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {

    EditText et1, et2, et3, et4;
    Button bt1;
    String uname;
    FirebaseAuth firebaseAuth;

    public static final String REF_NAME = "RegisterValues";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = FirebaseAuth.getInstance();

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        bt1 = (Button) findViewById(R.id.bt1);


    }

    public void register(View v)
    {

        if (et1.getText().toString().isEmpty())
        {
            et1.requestFocus();
            et1.setError("");
        }

        else if (et2.getText().toString().isEmpty())
        {
            et2.requestFocus();
            et2.setError("");
        }
        else if( et3.getText().toString().isEmpty())
        {
            et3.requestFocus();
            et3.setError("");

        }
        else if (et4.getText().toString().isEmpty())
        {
            et4.requestFocus();
            et4.setError("");

        }

        else
        {

            uname = et1.getText().toString().trim();
            String mail = et2.getText().toString().trim();
            String pwd = et3.getText().toString().trim();
            String mno = et4.getText().toString().trim();

            if (mail.contains("@") && mail.contains(".com"))
            {

                firebaseAuth.createUserWithEmailAndPassword(mail, pwd)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //checking if success
                                if (task.isSuccessful())
                                {
                                   // finish();
                                    //startActivity(new Intent(getApplicationContext(), Successfull.class));
                                } else {
                                    //display some message here
                                    Toast.makeText(Registration.this, ""+task.getException(), Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                RegisterValues register = new RegisterValues(uname, mno, mail, pwd);
                FirebaseDatabase fd = FirebaseDatabase.getInstance();
                DatabaseReference dr = fd.getReference(REF_NAME);
                dr.child(uname).setValue(register);
                Toast.makeText(this, "Data added to database", Toast.LENGTH_SHORT).show();

                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");


                /*DatabaseReference fd1 = FirebaseDatabase.getInstance().getReference(REF_NAME).child(uname);

                fd1.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {

                        if (dataSnapshot.exists())
                        {
                            RegisterValues d = dataSnapshot.getValue(RegisterValues.class);
                            String mail_get = d.getMail();
                            String pass_get = d.getPassword();

                            Intent i = new Intent(Registration.this, Login.class);
                            i.putExtra("k1", mail_get);
                            i.putExtra("k2", pass_get);
                            startActivity(i);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {

                    }
                });
                Toast.makeText(this, "Registered succesfully", Toast.LENGTH_SHORT).show();*/
            }
            else
            {
                Toast.makeText(this, "provide proper email id", Toast.LENGTH_SHORT).show();
            }
        }

    }

}

