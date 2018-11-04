package com.sda4.teamproject.dao;

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
    private static final String EXPENSE_TABLE_NAME = "user_expense_record";
    private static final String EXPENSE_TABLE_INFO_COLUM_ID = "_id";
    private static final String EXPENSE_TABLE_INFO_COLUM_AMOUNT = "amount";
    private static final String EXPENSE_TABLE_INFO_COLUM_CATEGORY = "catatory";
    private static final String EXPENSE_TABLE_INFO_COLUM_DATETIME = "datetime";
    private static final String EXPENSE_TABLE_INFO_COLUM_CURRENCY = "currency";
    private static final String EXPENSE_TABLE_INFO_COLUM_ACCOUNT = "account";
    private static final String EXPENSE_TABLE_INFO_COLUM_REMARKS = "remarks";
    private static final String EXPENSE_TABLE_INFO_COLUM_USER = "user";
    private SQLiteDatabase db;

    public ExpenseDaoImpl(Context context) {
        sqLiteUtil = new SQLiteUtil(context, 8);
        System.out.println(sqLiteUtil.getWritableDatabase().getVersion());
        db = sqLiteUtil.getWritableDatabase();
    }

    @Override
    public void add(Expense expense) {
        if (expense != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(EXPENSE_TABLE_INFO_COLUM_AMOUNT, expense.getAmount());
            contentValues.put(EXPENSE_TABLE_INFO_COLUM_CATEGORY, expense.getCategory());
            contentValues.put(EXPENSE_TABLE_INFO_COLUM_DATETIME, DataUtil.dateToString(expense.getDatetime()));
            contentValues.put(EXPENSE_TABLE_INFO_COLUM_CURRENCY, expense.getCurrency());
            contentValues.put(EXPENSE_TABLE_INFO_COLUM_ACCOUNT, expense.getAccount());
            contentValues.put(EXPENSE_TABLE_INFO_COLUM_REMARKS, expense.getRemarks());
            contentValues.put(EXPENSE_TABLE_INFO_COLUM_USER, expense.getUser());
            db.insert(EXPENSE_TABLE_NAME, null, contentValues);
            db.close();
        }

    }

    @Override
    public void delete(int expense_id) {
        db.delete(EXPENSE_TABLE_NAME, EXPENSE_TABLE_INFO_COLUM_ID+" = ?", new String[]{String.valueOf(expense_id)});
        db.close();
    }

    @Override
    public List<Expense> getExpenseList(String condition) {
        List<Expense> list = new ArrayList<Expense>();
        String querySql="";
        if (condition==null||condition.trim().equals("")){
            querySql="select * from " + EXPENSE_TABLE_NAME;
        }else {
            querySql="select * from " + EXPENSE_TABLE_NAME +condition;
        }
        Cursor cursor = db.rawQuery(querySql, null);
        Expense expense = null;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_ID));
            Double amount = cursor.getDouble(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_AMOUNT));
            String category = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_CATEGORY));
            String datetime = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_DATETIME));
            String currency = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_CURRENCY));
            String account = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_ACCOUNT));
            String remarks = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_REMARKS));
            String user = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_USER));
            expense = new Expense(id, amount, category, currency,account, DataUtil.createDate(datetime), remarks, user);
            list.add(expense);
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public Expense getSingleExpense(int expense_id) {
        String selectQuery="Select * From " + EXPENSE_TABLE_NAME
                + " WHERE " +
                EXPENSE_TABLE_INFO_COLUM_ID + "=?";
        int iCount=0;
        Expense expense =new Expense();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(expense_id)});
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_ID));
                Double amount = cursor.getDouble(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_AMOUNT));
                String category = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_CATEGORY));
                String datetime = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_DATETIME));
                String currency = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_CURRENCY));
                String remarks = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_REMARKS));
                String user = cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_USER));
                String account=cursor.getString(cursor.getColumnIndex(EXPENSE_TABLE_INFO_COLUM_ACCOUNT));
                expense = new Expense(id, amount, category, currency, account,DataUtil.createDate(datetime), remarks, user);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return expense;
    }


    @Override
    public void updateExpense(int expense_id, double amount, String category, String currency, String account,Date datetime,String remarks) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXPENSE_TABLE_INFO_COLUM_AMOUNT,amount);
        contentValues.put(EXPENSE_TABLE_INFO_COLUM_CATEGORY,category);
        contentValues.put(EXPENSE_TABLE_INFO_COLUM_DATETIME,DataUtil.dateToString(datetime));
        contentValues.put(EXPENSE_TABLE_INFO_COLUM_CURRENCY,currency);
        contentValues.put(EXPENSE_TABLE_INFO_COLUM_ACCOUNT,account);
        contentValues.put(EXPENSE_TABLE_INFO_COLUM_REMARKS,remarks);
        db.update(EXPENSE_TABLE_NAME,contentValues,EXPENSE_TABLE_INFO_COLUM_ID+"="+expense_id,null);
        db.close();
    }
}
