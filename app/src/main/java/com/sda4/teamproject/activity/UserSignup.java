package com.sda4.teamproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tmp_sda_1162.demo_exercises.R;
import com.sda4.teamproject.MainActivity;
import com.sda4.teamproject.dao.UserDao;
import com.sda4.teamproject.dao.UserDaoImpl;
import com.sda4.teamproject.model.User;

public class UserSignup extends AppCompatActivity {
    private EditText userInput;
    private EditText pwInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
    }

    public void signup(View view) {
        userInput = (EditText) findViewById(R.id.userInput);
        pwInput = (EditText) findViewById(R.id.pwInput);
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
            if (user != null) {
                Toast myToast = Toast.makeText(this, "Username is already used!",
                        Toast.LENGTH_SHORT);
                myToast.show();
                ((EditText) findViewById(R.id.userInput)).requestFocus();
                ((UserDaoImpl) userDao).closeDBConnection();
            } else {
                User newUser = new User(username, password, true);
                userDao.add(newUser);
                Toast myToast = Toast.makeText(this, "Registration Success!",
                        Toast.LENGTH_SHORT);
                myToast.show();
                ((UserDaoImpl) userDao).closeDBConnection();
                Intent newIntent = new Intent(this, MainActivity.class);
                startActivity(newIntent);
            }
        }

    }

    public void cancel(View view) {
        Intent newIntent = new Intent(this, MainActivity.class);
        startActivity(newIntent);
    }
}
