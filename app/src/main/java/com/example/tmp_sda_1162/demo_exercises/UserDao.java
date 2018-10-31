package com.example.tmp_sda_1162.demo_exercises;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private SQLiteUtil sqLiteUtil;
    public UserDao(Context context){
        sqLiteUtil = new SQLiteUtil(context,1);
    }
    //search
    public List search(){

        List list = new ArrayList();
        SQLiteDatabase db = sqLiteUtil.getReadableDatabase();
        System.out.println(db.getPath());
        Cursor cursor = db.rawQuery("select * from user_info ", null);
        UserInfor userInfo = null;

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String username = cursor.getString(cursor.getColumnIndex("name"));
            userInfo = new UserInfor(id,username);
            list.add(userInfo);
        }
        cursor.close();
        db.close();
        return list;
    }

    //add
    public void insert(UserInfor userInfo){
        SQLiteDatabase db = sqLiteUtil.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",userInfo.getName());
        db.insert("user_info", null, contentValues);
        db.close();
    }

    //delete
    public void del(int id){
        SQLiteDatabase db = sqLiteUtil.getWritableDatabase();
        db.delete("user_info", "_id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //update
    public void update(UserInfor userInfo){
        SQLiteDatabase db = sqLiteUtil.getWritableDatabase();
        String sql = "update user_info set name = ? where  _id = ?";
        db.execSQL(sql,new Object[]{userInfo.getName(),userInfo.getId()});
        db.close();
    }



}
