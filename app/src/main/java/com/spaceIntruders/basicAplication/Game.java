package com.spaceIntruders.basicAplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intentRES = getIntent();
        String recivedName = intentRES.getStringExtra("nameWewantToSend");
        Log.i("from intent", recivedName);
        TextView textToView = (TextView) findViewById(R.id.textView2);
        textToView.setText(recivedName);

    }
}