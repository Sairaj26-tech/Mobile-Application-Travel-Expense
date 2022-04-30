package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class resetpass extends AppCompatActivity {

    EditText oldpassword , newpassword;
    Button button;
    int Uid;

    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);

        oldpassword = (EditText)findViewById(R.id.editTextTextPersonName);
        newpassword = (EditText)findViewById(R.id.editTextTextPersonName2);
        button = (Button)findViewById(R.id.button);
        Uid = getIntent().getIntExtra("Uid",0);


        myDB = new DatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String user = oldpassword.getText().toString();
                String pass = newpassword.getText().toString();

                if (user.equals("") || pass.equals(""))
                {
                    Toast.makeText(resetpass.this, "Please enter old password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(myDB.resetpassword(Uid,user,pass)){
                    Intent intent = new Intent(getApplicationContext(),HomePage.class);
                    intent.putExtra("Uid",Uid);
                    startActivity(intent);
                    }
                    else{
                        Toast.makeText(resetpass.this, "Incorrect Old Password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}