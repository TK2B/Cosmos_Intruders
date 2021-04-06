package com.spaceIntruders.SpaceIntruders_game.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Player_user.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO_player_user userDao();
}