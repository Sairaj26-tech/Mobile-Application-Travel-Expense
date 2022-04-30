package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdvanceSearch extends AppCompatActivity {

    EditText tripName,tripDestination;
    Button tripDate;
    TextView dob;
    int Uid;
    CustomListAdapter adapter;
    ListView searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search);


        DatabaseHelper db = new DatabaseHelper(this);
        Uid = getIntent().getIntExtra("Uid",0);

        tripName = (EditText)findViewById(R.id.editTextTextPersonNameA);
        tripDestination = (EditText)findViewById(R.id.editTextTextPersonName2A);
        /*dob = (TextView)findViewById(R.id.dobA);
        String dateStr = new java.util.Date().toString();
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            dob.setText(formatter1.format(formatter.parse(dateStr)));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        //Button
        Button ADSearchbutton=(Button) findViewById(R.id.ADSearchbutton);
        ADSearchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<Trip> TirpList = new ArrayList<Trip>();

                TirpList = db.AdvanceSearchList(Uid,tripName.getText().toString(),tripDestination.getText().toString());

                searchList = (ListView)findViewById(R.id.AdvanceListView);

                adapter = new CustomListAdapter(getApplicationContext(), R.layout.list_view, TirpList);
                searchList.setAdapter(adapter);

                searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Trip itemValue =(Trip) searchList.getItemAtPosition(position);
                        Intent i = new Intent(getApplicationContext(),TripDetailPage.class);
                        i.putExtra("Value",itemValue.Tname);
                        i.putExtra("Uid",Uid);
                        i.putExtra("Tid",itemValue.Tid);
                        startActivity(i);
                    }

                });
            }
        });
    }
    /*
    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener
    {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            LocalDate d = LocalDate.now();
            int year = d.getYear();
            int month = d.getMonthValue();
            int day = d.getDayOfMonth();
            return new DatePickerDialog(getActivity(), this, year, --month, day);
        }
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            LocalDate dob = LocalDate.of(year, ++month, day);
            ((AdvanceSearch)getActivity()).updateDOB(dob);
        }
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new AddEditTrip.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void updateDOB(LocalDate dob)
    {   TextView dobText = (TextView)findViewById(R.id.dob);
        dobText.setText(dob.toString());
    }*/

}