package com.example.tmp_sda_1162.demo_exercises;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sda4.teamproject.dao.UserDao;
import com.sda4.teamproject.dao.UserDaoImpl;
import com.sda4.teamproject.model.User;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent newIntent= new Intent(this,DisplayMessageActivity.class);
        startActivity(newIntent);
    }

    public void start(View view){
        Intent newIntent= new Intent(this,AddNewExpense.class);
        startActivity(newIntent);
    }

    public void login(View view){
        String username="";
        String password="";
        //String username=((EditText) findViewById(R.id.userInput)).getText().toString();
        UserDao userDao=new UserDaoImpl(getBaseContext());
        User user=userDao.getUser(username);
        //String password=((EditText) findViewById(R.id.pwInput)).getText().toString();
        if(user==null){
            Toast myToast = Toast.makeText(this, "User does not exist!",
                    Toast.LENGTH_SHORT);
            myToast.show();
        }else if (!password.equals(user.getPassword())){
            Toast myToast = Toast.makeText(this, "Please type in a correct password!",
                    Toast.LENGTH_SHORT);                                              
            myToast.show();
        }
    }

    public void signup(View view){

    }


}
