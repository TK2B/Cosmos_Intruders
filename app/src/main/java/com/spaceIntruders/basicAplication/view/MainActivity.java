package com.spaceIntruders.basicAplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spaceIntruders.basicAplication.R;

public class MainActivity extends AppCompatActivity {


    // TODO IMPORTANT set right textviews in all activitys (input only where its needed Text within fields

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start_a_game(View view) {
        Intent get_to_next_screen = new Intent(this, bevore_you_start.class);
        startActivity(get_to_next_screen);


    }

    public void goto_settings(View view) {
        Intent get_to_setting_screen = new Intent(this, SettingsActivity.class);
        startActivity(get_to_setting_screen);
    }
}