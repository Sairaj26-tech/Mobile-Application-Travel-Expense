package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewTripPage extends AppCompatActivity {


    CustomListAdapter adapter;
    ListView simpleList;
    int Uid = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip_page);

        ArrayList<Trip> TirpList = new ArrayList<Trip>();

        DatabaseHelper db = new DatabaseHelper(this);
        Uid = getIntent().getIntExtra("Uid",0);
        TirpList = db.getTripName(Uid);


        simpleList = (ListView)findViewById(R.id.simpleListView);

        adapter = new CustomListAdapter(this, R.layout.list_view, TirpList);
        simpleList.setAdapter(adapter);

        /*ArrayAdapter<Trip> arrayAdapter = new ArrayAdapter<Trip>(this, R.layout.list_view, R.id.textViewTName, TirpList);
        simpleList.setAdapter(arrayAdapter);*/

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trip itemValue =(Trip) simpleList.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(),TripDetailPage.class);
                i.putExtra("Value",itemValue.Tname);
                i.putExtra("Uid",Uid);
                i.putExtra("Tid",itemValue.Tid);
                startActivity(i);
            }

        });

        Button btnClrDB = (Button) findViewById(R.id.btnClr);

        btnClrDB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                long detail = db.DeleteAllTripDetail(Uid);
                Intent i = new Intent(getApplicationContext(),HomePage.class);
                i.putExtra("Uid",Uid);
                startActivity(i);
            }
        });

        Button btnAdvSearch =(Button) findViewById(R.id.btnAdvSearch);
        btnAdvSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AdvanceSearch.class);
                i.putExtra("Uid",Uid);
                startActivity(i);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchview = (SearchView) menuItem.getActionView();
        searchview.setQueryHint("Type here to search");

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                CustomListAdapter filtered= new CustomListAdapter(getApplicationContext(), R.layout.list_view, adapter.customFilter(newText));
                simpleList.setAdapter(filtered);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

}

