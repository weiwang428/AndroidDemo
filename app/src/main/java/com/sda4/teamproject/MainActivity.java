package com.sda4.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tmp_sda_1162.demo_exercises.R;
import com.sda4.teamproject.activity.AddNewExpense;
import com.sda4.teamproject.activity.ExpenseListActivity;
import com.sda4.teamproject.activity.UserSignup;
import com.sda4.teamproject.dao.UserDao;
import com.sda4.teamproject.dao.UserDaoImpl;
import com.sda4.teamproject.model.User;

public class MainActivity extends AppCompatActivity {
    private static final String USER_NAME = "username";
    private EditText userInput;
    private EditText pwInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        userInput=(EditText) findViewById(R.id.userInput);
        pwInput=(EditText) findViewById(R.id.pwInput);
        String username = userInput.getText().toString().trim();
        String password = pwInput.getText().toString().trim(); 

        if (username.equals("") || username == null) {
            Toast myToast = Toast.makeText(this, "Please input the username!",
                    Toast.LENGTH_SHORT);
            myToast.show();
            ((EditText) findViewById(R.id.userInput)).requestFocus();
        } else if (password.equals("") || password == null) {
            Toast myToast = Toast.makeText(this, "Please input the password!",
                    Toast.LENGTH_SHORT);
            myToast.show();
            ((EditText) findViewById(R.id.pwInput)).requestFocus();
        } else {
            UserDao userDao = new UserDaoImpl(getBaseContext());
            User user = userDao.getUser(username);
            if (user == null) {
                Toast myToast = Toast.makeText(this, "User does not exist!",
                        Toast.LENGTH_SHORT);
                myToast.show();
                ((EditText) findViewById(R.id.userInput)).requestFocus();
                ((UserDaoImpl) userDao).closeDBConnection();
            } else if (!password.equals(user.getPassword())) {
                Toast myToast = Toast.makeText(this, "Please type in a correct password!",
                        Toast.LENGTH_SHORT);
                myToast.show();
                ((EditText) findViewById(R.id.pwInput)).requestFocus();
                ((UserDaoImpl) userDao).closeDBConnection();
            } else if (password.equals(user.getPassword())) {
                ((UserDaoImpl) userDao).closeDBConnection();  
                Intent newIntent = new Intent(this, ExpenseListActivity.class);
                newIntent.putExtra(USER_NAME, username);
                startActivity(newIntent);
            }
        }

    }

    public void signup(View view) {
        Intent newIntent = new Intent(this, UserSignup.class);
         startActivity(newIntent);
    }


}
