package com.example.sevaride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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


public class DriverloginActivity extends AppCompatActivity
{
    private Button DriverLoginButton;
    private Button DriverRegistrationButton;
    private TextView DriverRegisterLink;
    private TextView DriverStatus;
    private EditText EmailDriver;
    private EditText PhoneNoDriver;
    private EditText PasswordDriver;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverlogin);

        mAuth = FirebaseAuth.getInstance();

       DriverLoginButton = (Button) findViewById(R.id.driver_login_btn);
       DriverRegistrationButton = (Button) findViewById(R.id.driver_register_button);
       DriverRegisterLink = (TextView) findViewById(R.id.driver_register_link);
       DriverStatus = (TextView) findViewById(R.id.driver_status);
       EmailDriver = (EditText) findViewById(R.id.emai_driver);
       PhoneNoDriver = (EditText) findViewById(R.id.phone_driver);
       PasswordDriver = (EditText) findViewById(R.id.password_driver);
       loadingBar = new ProgressDialog(this);


        DriverRegistrationButton.setVisibility(View.INVISIBLE);
        DriverRegistrationButton.setEnabled(false);

        DriverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DriverLoginButton.setVisibility(View.INVISIBLE);
                DriverRegisterLink.setVisibility(View.INVISIBLE);
                DriverStatus.setText("Register Driver");

                DriverRegistrationButton.setVisibility(View.VISIBLE);
                DriverRegistrationButton.setEnabled(true);
            }
        });


        DriverRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             String email = EmailDriver.getText().toString();
             String phone_no = PhoneNoDriver.getText().toString();
             String password = PasswordDriver.getText().toString();

             RegisterDriver(email,phone_no,password);
            }
        });
    }




    private void RegisterDriver(String email, String phoneNo, String password)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(DriverloginActivity.this, "Please enter email..", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(phoneNo))
        {
            Toast.makeText(DriverloginActivity.this, "Please enter phoneNo...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverloginActivity.this, "Please enter password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Driver Registration");
            loadingBar.setMessage("Please wait while we're registering you");
            loadingBar.show();

         mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task)
             {
               if (task.isSuccessful())
               {
                   Toast.makeText(DriverloginActivity.this, "Driver Registered Successfully", Toast.LENGTH_SHORT).show();
                   loadingBar.dismiss();
               }
               else
               {
                   Toast.makeText(DriverloginActivity.this, "Registration Unsuccessful,Please Try Again", Toast.LENGTH_SHORT).show();
                   loadingBar.dismiss();
               }
             }
         });
        }
    }
}