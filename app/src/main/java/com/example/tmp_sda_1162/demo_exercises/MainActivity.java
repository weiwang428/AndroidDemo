package com.example.tmp_sda_1162.demo_exercises;

import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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

    public void testDB(View view){
        Intent newIntent= new Intent(this,TestDBActivity.class);
        startActivity(newIntent);
    }

    public void start(View view){
        Intent newIntent= new Intent(this,AddRecordActivity.class);
        startActivity(newIntent);
    }


}
