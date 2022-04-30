package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TripDetailPage extends AppCompatActivity {

    ListView expensesListview;
    int Tid;
    int Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail_page);

        //Trip

        Intent secondIntent = getIntent( );
        Uid= secondIntent.getIntExtra("Uid",0);
        Tid= secondIntent.getIntExtra("Tid",0);
        //String message = "Selected Item is " + secondIntent.getStringExtra("Value").toString()+" id: "+Uid ;
        DatabaseHelper db = new DatabaseHelper(this);
        Trip tripDetail= db.getTripDetail(Tid);
        TextView name = (TextView) findViewById(R.id.textTDNA);// need to change
        TextView dest = (TextView) findViewById(R.id.textTDDeA);// need to change
        TextView dot = (TextView) findViewById(R.id.textTDDA);// need to change
        TextView ra = (TextView) findViewById(R.id.textTDRA);// need to change
        TextView desc = (TextView) findViewById(R.id.textTDDsA);// need to change
        name.setText(tripDetail.Tname);
        dest.setText(tripDetail.TDestination);
        dot.setText(tripDetail.TDate);
        String risk=tripDetail.TRisk?"Yes":"No";
        ra.setText(risk);
        desc.setText(tripDetail.TDescription);

        //Delete Trip Action
        ImageButton btnDelete = (ImageButton) findViewById(R.id.imageButton3);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long detail = db.DeleteTripDetail(Tid,Uid);
                Intent intent = new Intent(getApplicationContext(), ViewTripPage.class);
                intent.putExtra("Uid",Uid);
                startActivity(intent);
            }
        });

        //Edit Trip Action
        ImageButton btnEdit = (ImageButton) findViewById(R.id.imageButton2);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddEditTrip.class);
                intent.putExtra("Uid",Uid);
                intent.putExtra("Tid",Tid);
                intent.putExtra("IsEdit",true);
                startActivity(intent);
            }
        });

        //Add Expenses Action
        ImageButton btnAddExpenses = (ImageButton) findViewById(R.id.imageButton);
        btnAddExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddEditExpenses.class);
                intent.putExtra("Uid",Uid);
                intent.putExtra("Tid",Tid);
                startActivity(intent);
            }
        });

        //Expenses List View
        ArrayList<Expenses> ExpensesList = new ArrayList<Expenses>();
        ExpensesList = db.getExpensesName(Tid);


        expensesListview = (ListView)findViewById(R.id.expListView);

        CustomEListAdapter adapter = new CustomEListAdapter(this, R.layout.list_view, ExpensesList);
        expensesListview.setAdapter(adapter);


        expensesListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expenses itemValue =(Expenses) expensesListview.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(),ExpenseDetailPage.class);
                i.putExtra("Value",itemValue.EType);
                i.putExtra("Uid",Uid);
                i.putExtra("Eid",itemValue.Eid);
                i.putExtra("Tid",Tid);
                startActivity(i);
            }

        });
    }
}

