package com.sda4.teamproject.activity;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.tmp_sda_1162.demo_exercises.R;
import com.sda4.teamproject.dao.ExpenseDaoImpl;
import com.sda4.teamproject.model.Expense;
import com.sda4.teamproject.model.ExpenseAdapter;

import java.util.List;

public class ExpenseListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        ExpenseDaoImpl imp = new ExpenseDaoImpl(this);
        List<Expense> itemList = imp.getExpenseList(null);
        ListView lv = getListView();
        try {
            ExpenseAdapter adapter = new ExpenseAdapter(this, R.layout.listview_entry, itemList);
            setListAdapter(adapter);
        }
        catch (Exception ex) {

        }
    }

    public void back(View v) {
        finish();
    }
}
