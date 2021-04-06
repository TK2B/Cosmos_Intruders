package com.spaceIntruders.SpaceIntruders_game.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import com.spaceIntruders.SpaceIntruders_game.persistence.Player_user;

@Dao
public interface DAO_player_user {
    @Query("SELECT * FROM Player_user ORDER BY highScore ASC")
    List <Player_user> getAll();

    //TODO make SQL request that shows maybe the first 5 - 10 highscores


    @Query("SELECT * FROM Player_user WHERE first_name LIKE :first LIMIT 1")
    Player_user findByName(String first);

    @Insert
    void insertAll(Player_user... users);

    @Delete
    void delete(Player_user user);
}
