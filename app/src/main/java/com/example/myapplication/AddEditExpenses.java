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

public class AddEditExpenses extends AppCompatActivity {

    EditText enterAmount;
    EditText tripDetails;
    Button btn;
    Button cancelbtn;
    TextView dob;
    EditText eComment;
    int Tid=1;
    int Uid=1;
//need to check
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_expenses);

        Intent secondIntent = getIntent( );
        Uid= secondIntent.getIntExtra("Uid",0);
        Tid= secondIntent.getIntExtra("Tid",0);

        enterAmount =(EditText) findViewById(R.id.enterAmount);
        tripDetails = (EditText) findViewById(R.id.tripDetails);
        eComment=(EditText) findViewById(R.id.editTextTextPersonName4);

        String dateStr = new java.util.Date().toString();
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        dob=(TextView)findViewById(R.id.dobE);
        try {
            dob.setText(formatter1.format(formatter.parse(dateStr)));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    saveExpenseDetails();
                    Intent intent = new Intent(getApplicationContext(), TripDetailPage.class);
                    intent.putExtra("Uid",Uid);
                    intent.putExtra("Tid",Tid);
                    startActivity(intent);
                }
            }
        });
        cancelbtn = (Button) findViewById(R.id.button4);
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

    private boolean validate() {
        boolean flag=true;

        if(TextUtils.isEmpty(enterAmount.getText())){
            enterAmount.setError("Please Enter the Amount");
            enterAmount.requestFocus();
            flag=false;
        }

        if(TextUtils.isEmpty(tripDetails.getText())){
            tripDetails.setError("Please Enter the type of expenses");
            tripDetails.requestFocus();
            flag=false;
        }
        if(dob.getText().equals("Select the Date"))
        {
            Toast.makeText(AddEditExpenses.this, "Please Select the Date", Toast.LENGTH_SHORT).show();
            flag=false;
        }
        return flag;
    }

    private void saveExpenseDetails() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        double amount = Double.valueOf(enterAmount.getText().toString());
        long EId = dbHelper.InsertExpensesDetail(tripDetails.getText().toString(), amount, dob.getText().toString(), eComment.getText().toString(), Tid, Uid);
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

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
        public void onDateSet(DatePicker datePicker, int year, int month, int day)
        {
            LocalDate dob = LocalDate.of(year, ++month, day);
            ((AddEditExpenses)getActivity()).updateDOB(dob);
        }
    }
    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDOB(LocalDate dob) {
        TextView dobText = (TextView)findViewById(R.id.dobE);
        dobText.setText(dob.toString());
    }
}