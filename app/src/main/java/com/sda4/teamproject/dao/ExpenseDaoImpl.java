package com.sda4.teamproject.dao;

import com.example.tmp_sda_1162.demo_exercises.UserInfor;
import com.sda4.teamproject.model.Expense;
import com.sda4.teamproject.util.DataUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao {
    private SQLiteUtil sqLiteUtil;
    private static final String TABLE_NAME = "user_expense_record";
    private static final String TABLE_INFO_COLUM_ID = "_id";
    private static final String TABLE_INFO_COLUM_AMOUNT = "amount";
    private static final String TABLE_INFO_COLUM_CATEGORY = "catatory";
    private static final String TABLE_INFO_COLUM_DATETIME = "datetime";
    private static final String TABLE_INFO_COLUM_CURRENCY = "currency";
    private static final String TABLE_INFO_COLUM_REMARKS = "remarks";
    private static final String TABLE_INFO_COLUM_USER = "user";
    private SQLiteDatabase db;

    public ExpenseDaoImpl(Context context) {
        sqLiteUtil = new SQLiteUtil(context, 3);
        db = sqLiteUtil.getWritableDatabase();

    }

    @Override
    public void add(Expense expense) {
        if (expense != null) {
            sqLiteUtil.onUpgrade(db,3,3);
            ContentValues contentValues = new ContentValues();
            contentValues.put(TABLE_INFO_COLUM_AMOUNT, expense.getAmount());
            contentValues.put(TABLE_INFO_COLUM_CATEGORY,expense.getCategory());
            contentValues.put(TABLE_INFO_COLUM_DATETIME, DataUtil.dateToString(expense.getDatetime()));
            contentValues.put(TABLE_INFO_COLUM_CURRENCY,expense.getCurrency());
            contentValues.put(TABLE_INFO_COLUM_REMARKS,expense.getRemarks());
            contentValues.put(TABLE_INFO_COLUM_USER,expense.getUser().getUsername());
            db.insert(TABLE_NAME, null, contentValues);
            db.close();
        }

    }

    @Override
    public void delete(int expense_id) {

    }

    @Override
    public List<Expense> getExpenseList(String condition) {
        List<Expense> list = new ArrayList<Expense>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME, null);
        Expense expense = null;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            Double amount = cursor.getDouble(cursor.getColumnIndex(TABLE_INFO_COLUM_AMOUNT));
            String category = cursor.getString(cursor.getColumnIndex(TABLE_INFO_COLUM_CATEGORY));
            String datetime = cursor.getString(cursor.getColumnIndex(TABLE_INFO_COLUM_DATETIME));
            String currency = cursor.getString(cursor.getColumnIndex(TABLE_INFO_COLUM_CURRENCY));
            String remarks = cursor.getString(cursor.getColumnIndex(TABLE_INFO_COLUM_REMARKS));
            String user = cursor.getString(cursor.getColumnIndex(TABLE_INFO_COLUM_USER));
            expense = new Expense(id, amount,category,currency,DataUtil.createDate(datetime),remarks,null);
            list.add(expense);
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public void getSingleExpense(int expense_id) {

    }

    @Override
    public void updateExpense(int expense_id, double amount, String category, String currency, Date datetime) {

    }
}
