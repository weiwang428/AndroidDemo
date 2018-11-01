package com.example.tmp_sda_1162.demo_exercises;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.sda4.teamproject.dao.ExpenseDao;
import com.sda4.teamproject.dao.ExpenseDaoImpl;
import com.sda4.teamproject.dao.SQLiteUtil;
import com.sda4.teamproject.model.Expense;
import com.sda4.teamproject.model.User;
import com.sda4.teamproject.util.DataUtil;

import java.util.Calendar;

public class AddNewExpense extends AppCompatActivity {
    private int year = 0;
    private int monthOfYear = 0;
    private int dayOfMonth = 0;
    private int minute = 0;
    private int houre = 0;
    private EditText datePicker;
    private EditText timePicker;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expense);
        initView();

    }

    private void initView(){

        datePicker= (EditText)findViewById(R.id.datePicker);
        datePicker.setInputType(InputType.TYPE_NULL);
        timePicker=(EditText)findViewById(R.id.timePicker);
        timePicker.setInputType(InputType.TYPE_NULL);

        datePicker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    showDatePickerDialog();
                }
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        timePicker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    showTimePickerDialog();
                }
            }
        });

        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

    }

    private void showDatePickerDialog() {
        c= Calendar.getInstance();
        new DatePickerDialog(AddNewExpense.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                datePicker.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }


    private void showTimePickerDialog(){
        c= Calendar.getInstance();
        new TimePickerDialog(AddNewExpense.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timePicker.setText(hourOfDay+":"+minute);
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),true).show();
    }


    public void addNewExpense(View view) {
        // Do something in response to button
        Intent newIntent= new Intent(this,MainActivity.class);
        EditText amountText=(EditText)findViewById(R.id.amountInput);
        double amount =Double.parseDouble(amountText.getText().toString());
        String remarks=((EditText) findViewById(R.id.remarksInput)).getText().toString();
        String date=((EditText)findViewById(R.id.datePicker)).getText().toString();
        String time=((EditText)findViewById(R.id.timePicker)).getText().toString();
        String account=(String)((Spinner) findViewById(R.id.accountSpinner)).getSelectedItem();
        String currency=(String)((Spinner) findViewById(R.id.currencySpinner)).getSelectedItem();
        String category=(String)((Spinner) findViewById(R.id.categorySpinner)).getSelectedItem();
        System.out.println(amount);
        System.out.println(remarks);
        System.out.println(account);
        System.out.println(date + " " + time);
        System.out.println(DataUtil.createDate(date + " " + time));
        System.out.println(currency);
        System.out.println(category);
        Expense expense=new Expense(amount,category,currency,DataUtil.createDate(date+" "+time),remarks,new User());
        ExpenseDao expenseDao=new ExpenseDaoImpl(getBaseContext());
        expenseDao.add(expense);
        startActivity(newIntent);


    }
}
