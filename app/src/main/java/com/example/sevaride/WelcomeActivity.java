package com.example.sevaride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity
{
    private Button WelcomeDriverButton;
    private Button WelcomeCustomerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        WelcomeCustomerButton = (Button) findViewById(R.id.welcome_customer);
        WelcomeDriverButton = (Button) findViewById(R.id.welcome_driver);


        WelcomeCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent LoginRegisterCustomerIntent = new Intent(WelcomeActivity.this, CustomerloginActivity.class);
                startActivity(LoginRegisterCustomerIntent);
            }
        });

        WelcomeDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent LoginRegisterCustomerIntent = new Intent(WelcomeActivity.this, DriverloginActivity.class);
                startActivity(LoginRegisterCustomerIntent);
            }
        });
    }
}