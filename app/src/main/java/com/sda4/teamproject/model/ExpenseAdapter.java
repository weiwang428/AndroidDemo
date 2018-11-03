package com.sda4.teamproject.model;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tmp_sda_1162.demo_exercises.R;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseAdapter extends ArrayAdapter {
    private final Activity context;
    private final List<Expense> exp_list;
    HashMap<String,Integer> imageMap;


    public ExpenseAdapter(Activity context, int resource, List<Expense> exp_list) throws NoSuchFieldException, IllegalAccessException{
        super(context, R.layout.listview_entry, exp_list);
        this.context = context;
        this.exp_list = exp_list;
        imageMap =  new HashMap<>();
        Resources res = context.getResources();
        String[] catagory = res.getStringArray(R.array.catagory);
        for (String cat_name:catagory) {
            imageMap.put(cat_name, R.drawable.class.getDeclaredField(cat_name).getInt(R.drawable.class));
        }
/*
        imageMap.put("food",R.drawable.food);
        imageMap.put("cloth",R.drawable.cloth);
        imageMap.put("entertainment",R.drawable.entertainment);
        imageMap.put("traffic",R.drawable.traffic);
        imageMap.put("health",R.drawable.health);
        */
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_entry, null,true);

        ImageView item_image = (ImageView) rowView.findViewById(R.id.itemIcon);
        TextView item_label = (TextView) rowView.findViewById(R.id.itemLabel);
        TextView item_date = (TextView) rowView.findViewById(R.id.itemDate);
        TextView item_money = (TextView) rowView.findViewById(R.id.itemCount);

        // Do the assignment.
        item_image.setImageResource(imageMap.get(this.exp_list.get(position).getCategory()));
        item_label.setText(this.exp_list.get(position).getCategory());
        item_date.setText(sm.format(this.exp_list.get(position).getDatetime()));
        item_money.setText(String.format("%.2f", this.exp_list.get(position).getAmount()));

        return rowView;
    }
}
