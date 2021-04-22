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
import java.util.concurrent.Executors;

@Database(entities = {Player_user.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    abstract DAO_player_user dao_Player_User();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "user_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                Player_user one_player;
                DAO_player_user dao = INSTANCE.dao_Player_User();
                dao.nukeTable();
                one_player = new Player_user(1, "AAA", 9999);
                dao.insert(one_player);
                one_player = new Player_user(2, "BBB", 8888);
                dao.insert(one_player);
                one_player = new Player_user(3, "CCC", 7777);
                dao.insert(one_player);
            });


        }

        ;

    };

}