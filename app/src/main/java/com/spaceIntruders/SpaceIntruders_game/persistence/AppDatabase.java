package com.spaceIntruders.SpaceIntruders_game.persistence;

import android.content.Context;
import android.provider.SyncStateContract;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Player_user.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TABLE_NAME_PLAYER_USER = "myPlayerDatebase";
    public abstract DAO_player_user getPlayer_userDao;


    private static AppDatabase Player_DB;


    public static AppDatabase getInstance(Context context) {
        if (null == Player_DB) {
            Player_DB = buildDatabaseInstance(context);
        }
        return Player_DB;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
               TABLE_NAME_PLAYER_USER)  // TODO MAke Constant for DAO and Here
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        AppDatabase = null;
    }

}