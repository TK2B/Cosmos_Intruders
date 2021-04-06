package com.spaceIntruders.SpaceIntruders_game.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

import com.spaceIntruders.SpaceIntruders_game.viewmodel.SpaceInvadersView;
import com.spaceIntruders.basicAplication.R;

public class Game extends Activity {
    //TODO take incoming Data from intent and hand it over to Spaceinvaders view to set the model classes there

    SpaceInvadersView spaceInvadersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // TODO Check alternativ 4 FLAG_Ful_...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // get intent from before_you_start
        Intent gameIntent = getIntent();
        String message = gameIntent.getStringExtra("nameWeWantToSend");

        // Good Luck toast message with player's name
        Toast.makeText(this, "Good luck " + message + "!", Toast.LENGTH_LONG).show();

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        // Initialize gameView and set it as the view
        spaceInvadersView = new SpaceInvadersView(this, size.x, size.y);
        setContentView(spaceInvadersView);
    }
    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        spaceInvadersView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        spaceInvadersView.pause();
    }


    public void goto_highscores(View view) {
        Intent get_to_highscores = new Intent(this, Highscore.class);
        startActivity(get_to_highscores);
    }
}