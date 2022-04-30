package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddEditTrip extends AppCompatActivity {

    EditText TripName;
    EditText Destination;
    EditText Description;
    RadioGroup rgp;
    TextView dob;
    private RadioButton radioButton;
    int Uid=1;
    int Tid=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_trip);

        Intent secondIntent = getIntent( );
        Uid= secondIntent.getIntExtra("Uid",0);
        Tid= secondIntent.getIntExtra("Tid",0);
        boolean isEdit = secondIntent.getBooleanExtra("IsEdit",false);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        TripName = (EditText) findViewById(R.id.editTextTextPersonName);
        Destination = (EditText) findViewById(R.id.editTextTextPersonName2);
        Description = (EditText) findViewById(R.id.editTextTextPersonName4);

        String dateStr = new java.util.Date().toString();
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        dob=(TextView)findViewById(R.id.dob);
        try {
            dob.setText(formatter1.format(formatter.parse(dateStr)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Button btn = (Button) findViewById(R.id.button);
        rgp=(RadioGroup)findViewById(R.id.radioGroup4);


        if(isEdit){
            Trip trip= dbHelper.getTripDetail(Tid);
            TripName.setText(trip.Tname);
            Destination.setText(trip.TDestination);

            String dateStrEdit = trip.TDate;
            try {
                formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String Test=formatter1.format(formatter.parse(dateStrEdit));
                dob.setText(formatter1.format(formatter.parse(dateStrEdit)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(trip.TRisk){
                int btnYesID=findViewById(R.id.radioButton5).getId();
                rgp.check((btnYesID));
            }
            else{
                int btnNoID=findViewById(R.id.radioButton6).getId();
                rgp.check((btnNoID));
            }
            TripName.setText(trip.Tname);
            Description.setText(trip.TDescription);
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    int selectedId = rgp.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    if(isEdit){
                        updateTripDetails(Tid,Uid);
                        Toast.makeText(AddEditTrip.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), TripDetailPage.class);
                        intent.putExtra("Uid",Uid);
                        intent.putExtra("Tid",Tid);
                        startActivity(intent);
                    }
                    else{
                        saveTripDetails();
                        Toast.makeText(AddEditTrip.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
                        intent.putExtra("Uid",Uid);
                        startActivity(intent);
                    }
                }


            }
        });

        // Cancel Button Action
        Button btnCancel = (Button) findViewById(R.id.button3);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                intent.putExtra("Uid",Uid);
                startActivity(intent);
            }
        });

    }
    public boolean validate(){
        boolean flag=true;
        if(TextUtils.isEmpty(TripName.getText())){
            TripName.setError("Please Enter the Name");
            TripName.requestFocus();
            flag=false;
        }
        if(TextUtils.isEmpty(Destination.getText())){
            Destination.setError("Please Enter the Destination");
            Destination.requestFocus();
            flag=false;
        }
        if(rgp.getCheckedRadioButtonId()==-1)
        {
            Toast.makeText(AddEditTrip.this, "Please select Risk Assessment", Toast.LENGTH_SHORT).show();
            flag=false;
        }
        if(dob.getText().equals("Select the Date"))
        {
            Toast.makeText(AddEditTrip.this, "Please Select the Date", Toast.LENGTH_SHORT).show();
            flag=false;
        }
        return flag;
    }

    private void saveTripDetails() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        int selectedId = rgp.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        String rgText=radioButton.getText().toString();
        boolean tRisk=(rgText.equals("Yes")); //Need to Check
        long TripId = dbHelper.InsertTripDetail(TripName.getText().toString(), Destination.getText().toString(), dob.getText().toString(), Description.getText().toString(), tRisk, Uid);

    }
    private void updateTripDetails(int tid, int uid) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        int selectedId = rgp.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        String rgText=radioButton.getText().toString();
        boolean tRisk=(rgText.equals("Yes"));
        long TripId = dbHelper.UpdateTripDetail(tid,TripName.getText().toString(), Destination.getText().toString(), dob.getText().toString(), Description.getText().toString(), tRisk, uid);

    }

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
            ((AddEditTrip)getActivity()).updateDOB(dob);
        }
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void updateDOB(LocalDate dob)
    {   TextView dobText = (TextView)findViewById(R.id.dob);
        dobText.setText(dob.toString());
    }

}