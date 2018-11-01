package com.sda4.teamproject.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUtil extends SQLiteOpenHelper {
    private static final String DBNAME = "expense.db";
    private static final String EXPENSE_TABLE_NAME = "user_expense_record";
    private static final String EXPENSE_TABLE_INFO_COLUM_ID = "_id";
    private static final String EXPENSE_TABLE_INFO_COLUM_AMOUNT = "amount";
    private static final String EXPENSE_TABLE_INFO_COLUM_CATEGORY = "catatory";
    private static final String EXPENSE_TABLE_INFO_COLUM_DATETIME = "datetime";
    private static final String EXPENSE_TABLE_INFO_COLUM_CURRENCY = "currency";
    private static final String EXPENSE_TABLE_INFO_COLUM_REMARKS = "remarks";
    private static final String EXPENSE_TABLE_INFO_COLUM_USER = "user";
    private static final String USER_TABLE_NAME = "user_record";
    private static final String USER_TABLE_INFO_COLUM_USERNAME = "username";
    private static final String USER_TABLE_INFO_COLUM_PASSWORD = "password";
    private static final String USER_TABLE_INFO_COLUM_STATUS = "status";

    public SQLiteUtil(Context context, int version) {

        super(context, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer createExpenseTable = new StringBuffer();
        createExpenseTable.append("CREATE TABLE IF NOT EXISTS ");
        createExpenseTable.append(EXPENSE_TABLE_NAME + "(");
        createExpenseTable.append(EXPENSE_TABLE_INFO_COLUM_ID + " integer primary key autoincrement ,");
        createExpenseTable.append(EXPENSE_TABLE_INFO_COLUM_AMOUNT + " varchar(10),");
        createExpenseTable.append(EXPENSE_TABLE_INFO_COLUM_CATEGORY + " varchar(10),");
        createExpenseTable.append(EXPENSE_TABLE_INFO_COLUM_DATETIME + " text,");
        createExpenseTable.append(EXPENSE_TABLE_INFO_COLUM_CURRENCY + " varchar(10),");
        createExpenseTable.append(EXPENSE_TABLE_INFO_COLUM_REMARKS + " varchar(30),");
        createExpenseTable.append(EXPENSE_TABLE_INFO_COLUM_USER + " varchar(20))");

        System.out.println(createExpenseTable.toString());
        db.execSQL(createExpenseTable.toString());

        StringBuffer createUserTable = new StringBuffer();
        createUserTable.append("CREATE TABLE IF NOT EXISTS ");
        createUserTable.append(USER_TABLE_NAME + "(");
        createUserTable.append(USER_TABLE_INFO_COLUM_USERNAME + "varchar(12),");
        createUserTable.append(USER_TABLE_INFO_COLUM_PASSWORD + "varchar(20),");
        createUserTable.append(USER_TABLE_INFO_COLUM_STATUS + "integer)");

        System.out.println(createUserTable.toString());
        db.execSQL(createUserTable.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteExpenseTable = "drop table if exists "
                + USER_TABLE_NAME;
        String deleteUserTable = "drop table if exists "
                + USER_TABLE_NAME;
        db.execSQL(deleteExpenseTable);
        db.execSQL(deleteUserTable);
        onCreate(db);
    }


}
