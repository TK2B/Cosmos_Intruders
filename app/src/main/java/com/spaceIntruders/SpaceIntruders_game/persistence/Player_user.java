package com.spaceIntruders.SpaceIntruders_game.persistence;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Player_user {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName4db;

    @ColumnInfo(name = "highScore")
    public int highScore4db ;

}
