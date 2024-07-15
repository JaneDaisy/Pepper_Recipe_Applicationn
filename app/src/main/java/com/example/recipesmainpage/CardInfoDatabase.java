package com.example.recipesmainpage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.recipesmainpage.CardInfoDao;
import com.example.recipesmainpage.Models.CardInformation;

@Database(entities = {CardInformation.class}, version = 1)
public abstract class CardInfoDatabase extends RoomDatabase {
    public abstract CardInfoDao cardInfoDao();
}
