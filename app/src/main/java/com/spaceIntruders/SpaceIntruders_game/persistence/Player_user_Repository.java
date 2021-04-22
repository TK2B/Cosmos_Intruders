package com.spaceIntruders.SpaceIntruders_game.persistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Player_user_Repository {

    private DAO_player_user mDAO_player_user;
    private LiveData<List<Player_user>> mAllPlayers;

    Player_user_Repository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDAO_player_user = db.dao_Player_User();
        mAllPlayers = mDAO_player_user.getAll();
    }

    LiveData<List<Player_user>> getAllUserPlayer() {
        return mAllPlayers;
    }

    void insert(Player_user anotherPlayer) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mDAO_player_user.insert(anotherPlayer);
        });
    }
}
