package com.example.recipesmainpage.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_info")

public class CardInformation {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String cardNumber;
    public String cardHolderName;
    public String expiryDate;
    public String cvv;
    public String pin;

        public CardInformation(String cardNumber, String cardHolderName, String expiryDate, String cvv, String pin) {
            this.cardNumber = cardNumber;
            this.cardHolderName = cardHolderName;
            this.expiryDate = expiryDate;
            this.cvv = cvv;
            this.pin = pin;
        }
    }


