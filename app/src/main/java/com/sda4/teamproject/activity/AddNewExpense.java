package com.sda4.teamproject.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sda4.teamproject.MainActivity;
import com.example.tmp_sda_1162.demo_exercises.R;
import com.sda4.teamproject.dao.ExpenseDao;
import com.sda4.teamproject.dao.ExpenseDaoImpl;
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
    private String username="";
    private EditText datePicker;
    private EditText timePicker;
    private EditText amountInput;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expense);
        initView();

    }

    private void initView() {
        username=getIntent().getExtras().getString("username");
        datePicker = (EditText) findViewById(R.id.datePicker);
        datePicker.setInputType(InputType.TYPE_NULL);
        timePicker = (EditText) findViewById(R.id.timePicker);
        timePicker.setInputType(InputType.TYPE_NULL);
        amountInput = (EditText) findViewById(R.id.amountInput);


        datePicker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
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
                if (hasFocus) {
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

        amountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        amountInput.setText(s);
                        amountInput.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    amountInput.setText(s);
                    amountInput.setSelection(2);
                }

                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        amountInput.setText(s.subSequence(0, 1));
                        amountInput.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void showDatePickerDialog() {
        c = Calendar.getInstance();
        new DatePickerDialog(AddNewExpense.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                datePicker.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }


    private void showTimePickerDialog() {
        c = Calendar.getInstance();
        new TimePickerDialog(AddNewExpense.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timePicker.setText(hourOfDay + ":" + minute);
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }


    public void addNewExpense(View view) {
        // Do something in response to button

        EditText amountText = (EditText) findViewById(R.id.amountInput);
        String amountStr=amountText.getText().toString().trim();
        String remarks = ((EditText) findViewById(R.id.remarksInput)).getText().toString().trim();
        EditText dateText =(EditText) findViewById(R.id.datePicker);
        String date = dateText.getText().toString().trim();
        EditText timeText =(EditText) findViewById(R.id.timePicker);
        String time = timeText.getText().toString().trim();

        if(amountStr.length()==0)
        {
            Toast.makeText(AddNewExpense.this,"Amount can not be empty!", Toast.LENGTH_LONG).show();
            amountText.requestFocus();
        }
        else if(date.length()==0){
            Toast.makeText(AddNewExpense.this,"Date can not be empty!", Toast.LENGTH_LONG).show();
            dateText.requestFocus();
        }else if(time.length()==0){
            Toast.makeText(AddNewExpense.this,"Time can not be empty!", Toast.LENGTH_LONG).show();
            timeText.requestFocus();
        } else {

            double amount = Double.parseDouble(amountText.getText().toString());
            String account = (String) ((Spinner) findViewById(R.id.accountSpinner)).getSelectedItem();
            String currency = (String) ((Spinner) findViewById(R.id.currencySpinner)).getSelectedItem();
            String category = (String) ((Spinner) findViewById(R.id.categorySpinner)).getSelectedItem();
            System.out.println(amount);
            System.out.println(remarks);
            System.out.println(account);
            System.out.println(date + " " + time);
            System.out.println(DataUtil.createDate(date + " " + time));
            System.out.println(currency);
            System.out.println(category);
            Expense expense = new Expense(amount, category, currency, DataUtil.createDate(date + " " + time), remarks, username);
            ExpenseDao expenseDao = new ExpenseDaoImpl(getBaseContext());
            expenseDao.add(expense);
            Intent newIntent = new Intent(this, ExpenseListActivity.class);
            newIntent.putExtra("username",username);
            startActivity(newIntent);

        }
    }
}
