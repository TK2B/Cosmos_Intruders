package com.spaceIntruders.SpaceIntruders_game.persistence;

import android.provider.SyncStateContract;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import com.spaceIntruders.SpaceIntruders_game.persistence.Player_user;
import com.spaceIntruders.SpaceIntruders_game.persistence.AppDatabase;



@Dao
public interface DAO_player_user {
    @Query(value = "SELECT * FROM myPlayerDatebase")   //ToDO make sure it exists manual https://www.pluralsight.com/guides/making-a-notes-app-using-room-database
    List <Player_user> getAll();


    @Query("SELECT * FROM Player_user ORDER BY highScore ASC")
    List <Player_user> getAll();

    //TODO make SQL request that shows maybe the first 5 - 10 highscores


    @Query("SELECT * FROM Player_user WHERE first_name LIKE :first LIMIT 1")
    Player_user findByName(String first);

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    void insertAll(Player_user users);
    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(Player_user user);

    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void delete(Player_user... userS);      // Player_user... is varargs, here note is an array


    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void update(Player_user repos);
}
