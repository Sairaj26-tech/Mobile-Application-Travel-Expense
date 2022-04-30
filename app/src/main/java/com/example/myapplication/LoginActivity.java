package com.example.myapplication;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{

    EditText username , password;
    Button btnLogIn;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.userenameLogin);
        password = (EditText)findViewById(R.id.passwordLogin);
        btnLogIn = (Button)findViewById(R.id.btnSignIn);


        myDB = new DatabaseHelper(this);

        btnLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Please enter all the Credential.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int result = myDB.checkusernamepassword(user,pass);
                    if (result > 0)
                    {
                        Intent intent = new Intent(getApplicationContext(),HomePage.class);
                        intent.putExtra("Uid",result);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}