package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    int Uid=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Uid = getIntent().getIntExtra("Uid",0);

        Button btnViewTrip = (Button) findViewById(R.id.btnViewTrip);

        btnViewTrip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),ViewTripPage.class);
                i.putExtra("Uid",Uid);
                startActivity(i);

            }
        });

        Button btnHttpPost  = (Button) findViewById(R.id.btnHttpPost);

        btnHttpPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),WebService.class);
                i.putExtra("Uid",Uid);
                startActivity(i);

            }
        });

        Button btnAddEdit  = (Button) findViewById(R.id.btnAddTrip);

        btnAddEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),AddEditTrip.class);
                i.putExtra("Uid",Uid);
                startActivity(i);

            }
        });


        Button resetpassword = (Button)findViewById(R.id.resetpassword);
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),resetpass.class);
                intent.putExtra("Uid",Uid);
                startActivity(intent);
            }

        });
    }
}