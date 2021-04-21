package com.spaceIntruders.SpaceIntruders_game.persistence;

import android.content.Context;
import android.provider.SyncStateContract;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

@Database(entities = {Player_user.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TABLE_NAME_PLAYER_USER = "myPlayerDatebase";
   // public abstract DAO_player_user getPlayer_userDao;


    private static AppDatabase Player_DB;   // Var 4 Databasee
    private static final  int numberOfThreads = 4;
    //static final ExecutorService databaseWriteExecutor = Executor.newFixedThreadPool(numberOfThreads);
/*

    public static volatile AppDatabase getInstance(Context context) {
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

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate{@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            databaseWriteExecutor.execute(() ->{
                Player_user player_user
            });
        }
        }
    }*/

}