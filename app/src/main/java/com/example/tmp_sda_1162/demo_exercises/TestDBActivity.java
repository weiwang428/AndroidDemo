package com.example.tmp_sda_1162.demo_exercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class TestDBActivity extends AppCompatActivity {
    private TextView dbTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);
    }


    public void DBOperation(View v) {
        dbTextView=(TextView)findViewById(R.id.dbTextView);
        UserDao userDao = new UserDao(getBaseContext());
        int id = v.getId();
        switch (id){

            case R.id.search:
                Log.d("----","search");
                List list = userDao.search();
                if (list.size()==0){
                    dbTextView.setText("No record");
                    return;
                }
                UserInfor user = null;
                StringBuffer stringBuffer = null;
                StringBuffer show = new StringBuffer();
                for (int i = 0;i<list.size();i++){
                    user = (UserInfor) list.get(i);
                    stringBuffer = new StringBuffer();
                    stringBuffer.append("ID:"+user.getId());
                    stringBuffer.append("Name："+user.getName());
                    show.append(stringBuffer);
                }

                dbTextView.setText(show.toString());
                break;
            case R.id.insert:
                Log.d("----","insert");
                UserInfor newUserInfo = new UserInfor();
                newUserInfo.setName("Siqi");
                userDao.insert(newUserInfo);
                break;
            case R.id.update:
                Log.d("----","update");
                UserInfor userInfo = new UserInfor();
                userInfo.setId(1);
                userInfo.setName("Yuting");
                userDao.update(userInfo);
                break;
            case R.id.delete:
                Log.d("----","delete");
                userDao.del(2);
                break;
        }
    }
}
