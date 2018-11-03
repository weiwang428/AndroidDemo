package com.sda4.teamproject.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmp_sda_1162.demo_exercises.R;
import com.sda4.teamproject.dao.ExpenseDaoImpl;
import com.sda4.teamproject.model.Expense;
import com.sda4.teamproject.adapter.ExpenseAdapter;

import java.util.List;

public class ExpenseListActivity extends ListActivity {
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        username=getIntent().getExtras().getString("username");

        ExpenseDaoImpl imp = new ExpenseDaoImpl(this);
        List<Expense> itemList = imp.getExpenseList(" where user='"+username+"' order by datetime desc");
        //ListView lv = getListView();
        try {

            ExpenseAdapter adapter = new ExpenseAdapter(this, R.layout.listview_entry, itemList);
            setListAdapter(adapter);

        }
        catch (Exception ex) {

        }
    }

    public void addNewExpense(View v) {
        username=getIntent().getExtras().getString("username");
        Intent newIntent = new Intent(this, AddNewExpense.class);
        newIntent.putExtra("username",username);
        startActivity(newIntent);
    }
}
