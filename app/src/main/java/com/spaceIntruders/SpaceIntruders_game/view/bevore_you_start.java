package com.spaceIntruders.SpaceIntruders_game.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.spaceIntruders.basicAplication.R;

public class bevore_you_start extends AppCompatActivity {

    protected int color = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bevore_you_start);
    }

    // Toast when a ship is selected
    public void clickNew(View v) {
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void realy_start_a_Game(View view) {
        Intent gotonextActivity = new Intent(this, Game.class);
        EditText editText = (EditText) findViewById(R.id.player_name_input);
        String message = editText.getText().toString();
        gotonextActivity.putExtra("nameWeWantToSend", message);
        gotonextActivity.putExtra("ShipColor", color);
        startActivity(gotonextActivity);
    }


    public void clickRed(View view) {
        color = 8;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void clickOrange(View view) {
        color = 5;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void clickYellow(View view) {
        color = 9;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void clickGreen(View view) {
        color = 2;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void clickLightBlue(View view) {
        color =3;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void clickBlue(View view) {
        color = 0;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void clickLightPurple(View view) {
        color = 4;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void clickPurple(View view) {
        color = 7;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void clickPink(View view) {
        color = 6;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }

    public void clickDirtyWhite(View view) {
        color = 1;
        Toast.makeText(this, "Great choice! Press 'Start Game' to play!", Toast.LENGTH_LONG).show();
    }
}