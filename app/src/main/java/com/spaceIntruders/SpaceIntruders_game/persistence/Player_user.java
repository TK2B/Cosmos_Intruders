package com.spaceIntruders.SpaceIntruders_game.persistence;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class Player_user {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName4db;

    @ColumnInfo(name = "highScore")
    private  int highScore4db ;

    public Player_user(int uid, String firstName4db, int highScore4db) {
        this.uid = uid;
        this.firstName4db = firstName4db;
        this.highScore4db = highScore4db;
    }

    public int getHighScore4db() {
        return highScore4db;
    }

    public void setHighScore4db(int highScore4db) {
        this.highScore4db = highScore4db;
    }

    public String getFirstName4db() {
        return firstName4db;
    }

    public int getUid() {
        return uid;
    }

   // TODO something like an override toString() (vor printing )

    }

