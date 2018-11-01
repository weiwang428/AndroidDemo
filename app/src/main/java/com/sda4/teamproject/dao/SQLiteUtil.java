package com.sda4.teamproject.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUtil extends SQLiteOpenHelper {
    private static final String DBNAME = "expense.db";
    private static final String TABLE_NAME = "user_expense_record";
    private static final String TABLE_INFO_COLUM_ID = "_id";
    private static final String TABLE_INFO_COLUM_AMOUNT = "amount";
    private static final String TABLE_INFO_COLUM_CATEGORY = "catatory";
    private static final String TABLE_INFO_COLUM_DATETIME = "datetime";
    private static final String TABLE_INFO_COLUM_CURRENCY = "currency";
    private static final String TABLE_INFO_COLUM_REMARKS = "remarks";
    private static final String TABLE_INFO_COLUM_USER = "user";

    public SQLiteUtil(Context context, int version) {

        super(context, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE IF NOT EXISTS ");
        stringBuffer.append(TABLE_NAME + "(");
        stringBuffer.append(TABLE_INFO_COLUM_ID+" integer primary key autoincrement ,");
        stringBuffer.append(TABLE_INFO_COLUM_AMOUNT+" varchar(10),");
        stringBuffer.append(TABLE_INFO_COLUM_CATEGORY+" varchar(10),");
        stringBuffer.append(TABLE_INFO_COLUM_DATETIME+" text,");
        stringBuffer.append(TABLE_INFO_COLUM_CURRENCY+" varchar(10),");
        stringBuffer.append(TABLE_INFO_COLUM_REMARKS+" varchar(30),");
        stringBuffer.append(TABLE_INFO_COLUM_USER+" varchar(20))");
        System.out.println(stringBuffer.toString());
        db.execSQL(stringBuffer.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists "
                +TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }


}
