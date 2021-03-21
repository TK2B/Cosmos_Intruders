package com.spaceIntruders.basicAplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // get intent from before_you_start
        Intent gameIntent = getIntent();
        String message = gameIntent.getStringExtra("nameWeWantToSend");

        // Good Luck toast message with player's name
        Toast.makeText(this, "Good luck " + message + "!", Toast.LENGTH_LONG).show();
    }

    public void goto_highscores(View view) {
        Intent get_to_highscores = new Intent(this, Highscore.class);
        startActivity(get_to_highscores);
    }
}