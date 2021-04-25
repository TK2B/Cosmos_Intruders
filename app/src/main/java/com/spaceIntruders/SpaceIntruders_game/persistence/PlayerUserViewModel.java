package com.spaceIntruders.SpaceIntruders_game.persistence;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PlayerUserViewModel extends AndroidViewModel {

    private
    Player_user_Repository mRepository;
    private final LiveData<List<Player_user>> mAllPlayer;

    public PlayerUserViewModel(Application application) {
        super(application);
        mRepository = new Player_user_Repository(application);
        mAllPlayer = mRepository.getAllUserPlayer();
    }

    public LiveData<List<Player_user>> getmAllPlayer() {
        return mAllPlayer;
    }

    public void insert(Player_user one_player) {
        mRepository.insert(one_player);
    }


}
