package com.sda4.teamproject.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tmp_sda_1162.demo_exercises.R;
import com.sda4.teamproject.dao.ExpenseDaoImpl;
import com.sda4.teamproject.model.Expense;
import com.sda4.teamproject.adapter.ExpenseAdapter;

import java.util.List;

public class ExpenseListActivity extends ListActivity {
    private TextView item_view;
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        username = getIntent().getExtras().getString("username");

        ExpenseDaoImpl imp = new ExpenseDaoImpl(this);
        List<Expense> itemList = imp.getExpenseList(" where user='" + username + "' order by datetime desc");
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item_view = (TextView) view.findViewById(R.id.item_Id);
                String itemId = item_view.getText().toString();
                System.out.println(itemId);
                Intent objIndent = new Intent(getApplicationContext(), ExpenseDetailActivity.class);
                objIndent.putExtra("username", username);
                objIndent.putExtra("expense_Id", Integer.parseInt(itemId));
                startActivity(objIndent);

            }
        });
        try {

            ExpenseAdapter adapter = new ExpenseAdapter(this, R.layout.listview_entry, itemList);
            setListAdapter(adapter);

        } catch (Exception ex) {

        }
    }


    public void addNewExpense(View v) {
        username = getIntent().getExtras().getString("username");
        Intent newIntent = new Intent(this, AddNewExpense.class);
        newIntent.putExtra("username", username);
        startActivity(newIntent);
    }
}
