
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password , repassword;
    Button btnSignup , btnSignIn;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);


        btnSignup = (Button)findViewById(R.id.btnSignup);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        myDB = new DatabaseHelper(this);

        btnSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String User = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if (username.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(MainActivity.this ,"Fill all the Fields.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (pass.equals(repass))
                    {
                        Boolean usernameResult = myDB.checkusername(User);
                        if(usernameResult == false)
                        {
                            Boolean regResult = myDB.insertData(User,pass);
                            if(regResult = true)
                            {
                                Toast.makeText(MainActivity.this, "Registration is Successful.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Registration is UnSuccessful.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "user already exists.\n please Sign in .", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });



    }

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnHomePage = (Button) findViewById(R.id.button2);

        btnHomePage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),HomePage.class);
                startActivity(i);

            }
        });
    }*/
}