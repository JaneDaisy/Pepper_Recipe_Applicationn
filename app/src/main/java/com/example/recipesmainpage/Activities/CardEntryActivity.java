package com.example.recipesmainpage.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.recipesmainpage.CardInfoDatabase;
import com.example.recipesmainpage.Models.CardInformation;
import com.example.recipesmainpage.R;

public class CardEntryActivity extends AppCompatActivity {

    private EditText editTextCardNumber, editTextCardHolderName, editTextExpiryDate, editTextCVV, editTextPin;
    private Button buttonSaveCardInfo;
    private CardInfoDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_entry);

        editTextCardNumber = findViewById(R.id.editTextCardNumber);
        editTextCardHolderName = findViewById(R.id.editTextCardHolderName);
        editTextExpiryDate = findViewById(R.id.editTextExpiryDate);
        editTextCVV = findViewById(R.id.editTextCVV);
        editTextPin = findViewById(R.id.editTextPin);
        buttonSaveCardInfo = findViewById(R.id.buttonSaveCardInfo);

        db = Room.databaseBuilder(getApplicationContext(), CardInfoDatabase.class, "card_info_db").build();

        buttonSaveCardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumber = editTextCardNumber.getText().toString();
                String cardHolderName = editTextCardHolderName.getText().toString();
                String expiryDate = editTextExpiryDate.getText().toString();
                String cvv = editTextCVV.getText().toString();
                String pin = editTextPin.getText().toString();

                if (cardNumber.isEmpty() || cardHolderName.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                    Toast.makeText(CardEntryActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    saveCardInfo(new CardInformation(cardNumber, cardHolderName, expiryDate, cvv, pin));
                }
            }
        });
    }

    private void saveCardInfo(final CardInformation cardInfo) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.cardInfoDao().insert(cardInfo);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CardEntryActivity.this, "Card info saved", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
