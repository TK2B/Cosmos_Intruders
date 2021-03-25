package com.spaceIntruders.basicAplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.spaceIntruders.basicAplication.R;
import com.spaceIntruders.basicAplication.view.Game;

public class bevore_you_start extends AppCompatActivity {

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
        startActivity(gotonextActivity);
    }
}