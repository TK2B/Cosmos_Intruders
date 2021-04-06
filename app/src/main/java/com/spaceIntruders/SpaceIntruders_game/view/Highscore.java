package com.spaceIntruders.SpaceIntruders_game.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.spaceIntruders.basicAplication.R;

public class Highscore extends AppCompatActivity {

    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Intent highScoresIntent = getIntent();
        String message = highScoresIntent.getStringExtra("nameWeWantToSend");

    }

    public void playAgain(View view) {
        Intent getToSelectScreen = new Intent(this, bevore_you_start.class);
        startActivity(getToSelectScreen);
    }
}