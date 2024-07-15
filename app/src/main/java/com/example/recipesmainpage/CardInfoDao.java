package com.example.recipesmainpage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.recipesmainpage.Models.CardInformation;

@Dao
public interface CardInfoDao {
    @Insert
    void insert(CardInformation cardInfo);

    @Query("SELECT * FROM card_info WHERE pin = :pin LIMIT 1")
    CardInformation getCardInfoByPin(String pin);
}
