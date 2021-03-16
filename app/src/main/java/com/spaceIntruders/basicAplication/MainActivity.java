package com.spaceIntruders.basicAplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start_a_game(View view) {
        Intent get_to_next_screem = new Intent(this, bevore_you_start.class);
        startActivity(get_to_next_screem);
    }
}