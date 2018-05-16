package com.example.mansopresk05.registration;




import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Forget extends AppCompatActivity
{
    private static final String TAG ="Email" ;
    EditText et1;
    Button bt1;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);


    et1 = (EditText)findViewById(R.id.et1);
    bt1 =(Button)findViewById(R.id.bt1);

        auth = FirebaseAuth.getInstance();
    }
    public void forgotPass(View v)
    {
        final String forgot = et1.getText().toString().trim();
        //if (et_forgot.equals(""))


        final FirebaseUser user = auth.getCurrentUser();

        auth.sendPasswordResetEmail(forgot).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(Forget.this, "Link sended to entered Email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Forget.this, "Failed to send Link", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


