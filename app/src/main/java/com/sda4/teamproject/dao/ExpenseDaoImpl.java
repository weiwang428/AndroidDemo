package com.sda4.teamproject.dao;

import com.sda4.teamproject.model.Expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

    public ExpenseDaoImpl(Context context) {
        sqLiteUtil = new SQLiteUtil(context, 3);
    }

    @Override
    public void add(Expense expense) {
        if (expense != null) {
            SQLiteDatabase db = sqLiteUtil.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TABLE_INFO_COLUM_AMOUNT, expense.getAmount());
            contentValues.put(TABLE_INFO_COLUM_CATEGORY,expense.getCategory());
            contentValues.putNull(TABLE_INFO_COLUM_DATETIME);
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
        return null;
    }

    @Override
    public void getSingleExpense(int expense_id) {

    }

    @Override
    public void updateExpense(int expense_id, double amount, String category, String currency, Date datetime) {

    }
}
