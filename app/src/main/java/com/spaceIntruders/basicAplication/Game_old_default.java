package com.spaceIntruders.basicAplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spaceIntruders.basicAplication.view.Highscore;

public class Game_old_default extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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