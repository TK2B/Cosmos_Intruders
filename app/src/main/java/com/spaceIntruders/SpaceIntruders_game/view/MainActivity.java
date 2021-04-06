package com.spaceIntruders.SpaceIntruders_game.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spaceIntruders.basicAplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start_a_game(View view) {
        Intent get_to_next_screen = new Intent(this, bevore_you_start.class);
        startActivity(get_to_next_screen);
    }

    public void highScores(View view) {
        Intent get_to_high_scores = new Intent(this, Highscore.class);
        startActivity(get_to_high_scores);
    }
}