package com.sda4.teamproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sda4.teamproject.model.Expense;
import com.sda4.teamproject.model.User;
import com.sda4.teamproject.util.DataUtil;


public class UserDaoImpl implements UserDao {
    private SQLiteUtil sqLiteUtil;
    private static final String USER_TABLE_NAME = "user_record";
    private static final String USER_TABLE_INFO_COLUM_USERNAME = "username";
    private static final String USER_TABLE_INFO_COLUM_PASSWORD = "password";
    private static final String USER_TABLE_INFO_COLUM_STATUS = "status";
    private SQLiteDatabase db;

    public UserDaoImpl(Context context) {
        sqLiteUtil = new SQLiteUtil(context, 3);
        db = sqLiteUtil.getWritableDatabase();
    }

    @Override
    public void add(User user) {
        if (user != null) {
            sqLiteUtil.onUpgrade(db, 3, 3);
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_TABLE_INFO_COLUM_USERNAME, user.getUsername());
            contentValues.put(USER_TABLE_INFO_COLUM_PASSWORD, user.getPassword());
            contentValues.put(USER_TABLE_INFO_COLUM_STATUS, user.isStatus());
            db.insert(USER_TABLE_NAME, null, contentValues);
            db.close();
        }
    }

    @Override
    public void delete(String user_name) {

    }

    @Override
    public void update(String user_name, String password) {

    }

    @Override
    public User getUser(String user_name) {
        Cursor cursor = db.rawQuery("select * from " + USER_TABLE_NAME, null);
        User userObj = null;

        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex(USER_TABLE_INFO_COLUM_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(USER_TABLE_INFO_COLUM_PASSWORD));
            int statusInt = cursor.getInt(cursor.getColumnIndex(USER_TABLE_INFO_COLUM_STATUS));
            boolean status;
            if (statusInt == 1) {
                status = true;
            } else {
                status = false;
            }
            userObj = new User(username, password, status);
        }
        cursor.close();
        db.close();
        return userObj;
    }
}
