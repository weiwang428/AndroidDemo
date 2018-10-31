package com.example.tmp_sda_1162.demo_exercises;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUtil extends SQLiteOpenHelper {
    private static final String DBNAME = "test.db";
    private static final String TABLE_NAME = "expense_record";
    private static final String TABLE_INFO_COLUM_ID  = "_id";
    private static final String TABLE_INFO_COLUM_AMOUNT = "amount";
    private static final String TABLE_INFO_COLUM_CATEGORY = "catatory";
    private static final String TABLE_INFO_COLUM_DATE="date";
    private static final String TABLE_INFO_COLUM_TIME="time";
    private static final String TABLE_INFO_COLUM_CURRENCY="currency";
    private static final String TABLE_INFO_COLUM_REMARKS="remarks";

    public SQLiteUtil(Context context, int version) {

        super(context, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "drop table if exists "
                +TABLE_NAME;
        db.execSQL(sql);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE IF NOT EXISTS ");
        stringBuffer.append(TABLE_NAME + "(");
        stringBuffer.append(TABLE_INFO_COLUM_ID+" integer primary key autoincrement ,");
        stringBuffer.append(TABLE_INFO_COLUM_AMOUNT+" varchar(10),");
        stringBuffer.append(TABLE_INFO_COLUM_CATEGORY+" varchar(10),");
        stringBuffer.append(TABLE_INFO_COLUM_DATE+" date,");

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
