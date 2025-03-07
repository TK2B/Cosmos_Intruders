package com.spaceIntruders.SpaceIntruders_game.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spaceIntruders.SpaceIntruders_game.persistence.PlayerUserViewModel;
import com.spaceIntruders.SpaceIntruders_game.persistence.Player_user;
import com.spaceIntruders.SpaceIntruders_game.persistence.Player_userViewHolder;
import com.spaceIntruders.SpaceIntruders_game.persistence.Player_user_list_adapter;
import com.spaceIntruders.SpaceIntruders_game.viewmodel.SpaceInvadersView;
import com.spaceIntruders.basicAplication.R;

import java.util.Random;


public class Highscore extends AppCompatActivity {

    TextView score;
    private PlayerUserViewModel mPlayerUserViewModel;
    private int highscore = 0;
    private String winnername = "default";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_highscore);

        Intent highScoresIntent = getIntent();
        String message = highScoresIntent.getStringExtra("nameWeWantToSend");
        // get views from XML

        Button myButto = (Button) findViewById(R.id.playAgain);
        RecyclerView myRecViewer =  (RecyclerView) findViewById(R.id.recycleview);
        final Player_user_list_adapter adapter = new Player_user_list_adapter(new Player_user_list_adapter.PlayerUserDiff());
        myRecViewer.setAdapter(adapter);
        myRecViewer.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        //mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mPlayerUserViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PlayerUserViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mPlayerUserViewModel.getmAllPlayer().observe(this, player_users -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(player_users);
        });

        Intent scoreIntent = getIntent();
        highscore = scoreIntent.getIntExtra("Score", 0);

        if (highscore != 0) {
            winnername = scoreIntent.getStringExtra("PlayersName");
            int uid = new Random().nextInt(10000);
            Log.e ("Score" , String.valueOf(highscore));
            Log.e ("PlayersName" , winnername);
            Player_user toWriteUser = new Player_user(uid, winnername, highscore);
            mPlayerUserViewModel.insert(toWriteUser);
        }


    }

    public void playAgain(View view) {
        Intent getToSelectScreen = new Intent(this, bevore_you_start.class);
        startActivity(getToSelectScreen);
    }

    // Stuff for writing to the Database

    //mPlayerUserViewModel.insert(toDatabaseUSer);








}