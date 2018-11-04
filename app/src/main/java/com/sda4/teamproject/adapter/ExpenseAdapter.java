package com.sda4.teamproject.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tmp_sda_1162.demo_exercises.R;
import com.sda4.teamproject.model.Expense;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExpenseAdapter extends ArrayAdapter {
    private final Activity context;
    private final List<Expense> exp_list;


    public ExpenseAdapter(Activity context, int resource, List<Expense> exp_list) throws NoSuchFieldException, IllegalAccessException {
        super(context, R.layout.listview_entry, exp_list);
        this.context = context;
        this.exp_list = exp_list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_entry, null, true);

        ImageView item_image = (ImageView) rowView.findViewById(R.id.itemIcon);
        TextView item_id = (TextView) rowView.findViewById(R.id.item_Id);
        TextView item_label = (TextView) rowView.findViewById(R.id.itemLabel);
        TextView item_date = (TextView) rowView.findViewById(R.id.itemDate);
        TextView item_money = (TextView) rowView.findViewById(R.id.itemCount);

        // Do the assignment.
        try {
            item_image.setImageResource(R.drawable.class.getDeclaredField(this.exp_list.get(position).getCategory()).getInt(R.drawable.class));
            item_id.setText(Integer.toString(this.exp_list.get(position).getId()));
            item_label.setText(this.exp_list.get(position).getCategory());
            item_date.setText(sm.format(this.exp_list.get(position).getDatetime()));
            item_money.setText(String.format("%.2f", this.exp_list.get(position).getAmount()));
        } catch (Exception e) {

        }

        return rowView;
    }
}
