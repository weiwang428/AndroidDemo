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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tmp_sda_1162.demo_exercises.R;
import com.sda4.teamproject.dao.ExpenseDao;
import com.sda4.teamproject.dao.ExpenseDaoImpl;
import com.sda4.teamproject.model.Expense;
import com.sda4.teamproject.util.DataUtil;

import java.util.Calendar;

public class ExpenseDetailActivity extends AppCompatActivity implements android.view.View.OnClickListener {

    private String username = "";
    private EditText amountInput;
    private Spinner categorySpinner;
    private Spinner currencySpinner;
    private Spinner accountSpinner;
    private EditText datePicker;
    private EditText timePicker;
    private EditText remarksInput;
    private Calendar c;
    private int expense_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);
        initView();
    }

    private void initView() {
        username = getIntent().getExtras().getString("username");
        expense_id = getIntent().getIntExtra("expense_Id", 0);
        System.out.println(expense_id);
        ExpenseDao expenseDao = new ExpenseDaoImpl(this);
        Expense expense = expenseDao.getSingleExpense(expense_id);
        amountInput = (EditText) findViewById(R.id.amountInput);
        amountInput.setText(String.format("%.2f", expense.getAmount()));
        datePicker = (EditText) findViewById(R.id.datePicker);
        datePicker.setInputType(InputType.TYPE_NULL);
        String dateStr = DataUtil.dateToString(expense.getDatetime());
        datePicker.setText(dateStr.substring(0, dateStr.indexOf(" ")));

        timePicker = (EditText) findViewById(R.id.timePicker);
        timePicker.setInputType(InputType.TYPE_NULL);
        timePicker.setText(dateStr.substring(dateStr.indexOf(" ") + 1, dateStr.length()));
        remarksInput = (EditText) findViewById(R.id.remarksInput);
        remarksInput.setText(expense.getRemarks());
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        String[] categoryItems = getResources().getStringArray(R.array.catagory);
        ArrayAdapter<String> cat_array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryItems);
        categorySpinner.setSelection(cat_array_adapter.getPosition(expense.getCategory()));

        currencySpinner = (Spinner) findViewById(R.id.currencySpinner);
        String[] currencyItems = getResources().getStringArray(R.array.currency);
        ArrayAdapter<String> cur_array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencyItems);
        currencySpinner.setSelection(cur_array_adapter.getPosition(expense.getCurrency()));

        accountSpinner = (Spinner) findViewById(R.id.accountSpinner);
        String[] accountItems = getResources().getStringArray(R.array.Account);
        ArrayAdapter<String> acc_array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountItems);
        accountSpinner.setSelection(acc_array_adapter.getPosition(expense.getAccount()));


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
        new DatePickerDialog(ExpenseDetailActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                datePicker.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }


    private void showTimePickerDialog() {
        c = Calendar.getInstance();
        new TimePickerDialog(ExpenseDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timePicker.setText(hourOfDay + ":" + minute);
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.saveButton)) {
            String amountStr = amountInput.getText().toString().trim();
            double amount = Double.parseDouble(amountStr);
            String remarks = remarksInput.getText().toString().trim();
            String date = datePicker.getText().toString().trim();
            String time = timePicker.getText().toString().trim();
            String account = (String) ((Spinner) findViewById(R.id.accountSpinner)).getSelectedItem();
            String currency = (String) ((Spinner) findViewById(R.id.currencySpinner)).getSelectedItem();
            String category = (String) ((Spinner) findViewById(R.id.categorySpinner)).getSelectedItem();
            ExpenseDao expenseDao = new ExpenseDaoImpl(this);
            expenseDao.updateExpense(expense_id, amount, category, currency, account, DataUtil.createDate(date + " " + time), remarks);
            Intent newIntent = new Intent(this, ExpenseListActivity.class);
            newIntent.putExtra("username", username);
            startActivity(newIntent);
        } else if (v == findViewById(R.id.deleteButton)) {
            ExpenseDao expenseDao = new ExpenseDaoImpl(this);
            expenseDao.delete(expense_id);
            Toast.makeText(this, "Expense Record Deleted", Toast.LENGTH_SHORT);
            Intent newIntent = new Intent(this, ExpenseListActivity.class);
            newIntent.putExtra("username", username);
            startActivity(newIntent);
        } else if (v == findViewById(R.id.cancelButton)) {
            finish();
        }
    }
}
