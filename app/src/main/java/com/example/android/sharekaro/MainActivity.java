package com.example.android.sharekaro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(this).start();

    }
    @Override
    public void run(){
        try{
            Thread.sleep(2400);
        }catch (Exception e){

        }
        Intent i = new Intent(this,SignupLoginActivity.class);
        startActivity(i);
    }
}
