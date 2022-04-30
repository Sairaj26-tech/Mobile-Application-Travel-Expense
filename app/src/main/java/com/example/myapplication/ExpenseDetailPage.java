package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExpenseDetailPage extends AppCompatActivity {

    int Tid;
    int Uid;
    int Eid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail_page);

        Intent secondIntent = getIntent( );
        Uid= secondIntent.getIntExtra("Uid",0);
        Tid= secondIntent.getIntExtra("Tid",0);
        Eid= secondIntent.getIntExtra("Eid",0);
        //String message = "Selected Item is " + secondIntent.getStringExtra("Value").toString()+" id: "+Uid ;

        DatabaseHelper db = new DatabaseHelper(this);

        Expenses expenseDetail= db.getExpenseDetail(Eid);
        TextView Type = (TextView) findViewById(R.id.textETA);// need to change
        TextView Amount = (TextView) findViewById(R.id.textEAA);// need to change
        TextView dot = (TextView) findViewById(R.id.textEDA);// need to change
        TextView Comment = (TextView) findViewById(R.id.textECA);// need to change

        Type.setText(expenseDetail.EType);
        Amount.setText(String.valueOf(expenseDetail.EAmount));
        dot.setText(expenseDetail.EDate);
        Comment.setText(expenseDetail.EComment);

        Button cancelbtn = (Button) findViewById(R.id.button5);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), TripDetailPage.class);
                intent.putExtra("Uid",Uid);
                intent.putExtra("Tid",Tid);
                startActivity(intent);

            }
        });


    }
}