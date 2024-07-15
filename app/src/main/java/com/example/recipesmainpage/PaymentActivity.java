package com.example.recipesmainpage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.recipesmainpage.Activities.EditAddressActivity;
import com.example.recipesmainpage.Models.CardInformation;

import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    private TextView textViewTotalAmount;
    private TextView textViewUsername;
    private TextView textViewAddress;
    private Button buttonConfirmPayment;
    private Button buttonEditAddress;
    private CardInfoDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewAddress = findViewById(R.id.textViewAddress);
        buttonConfirmPayment = findViewById(R.id.buttonConfirmPayment);
        buttonEditAddress = findViewById(R.id.buttonEditAddress);

        db = Room.databaseBuilder(getApplicationContext(), CardInfoDatabase.class, "card_info_db").build();

        // Fetch total amount from intent
        double totalAmount = getIntent().getDoubleExtra("totalAmount", 0.00);
        textViewTotalAmount.setText(String.format(Locale.getDefault(), "%.2f", totalAmount));

        // Fetch username and address from shared preferences or intent
        String username = getIntent().getStringExtra("username");
        String address = getIntent().getStringExtra("address");

        if (username != null) {
            textViewUsername.setText(username);
        }

        if (address != null) {
            textViewAddress.setText(address);
        }

        buttonConfirmPayment.setOnClickListener(v -> showPinDialog());

        buttonEditAddress.setOnClickListener(v -> {
            // Navigate to the Edit Address activity
            Intent intent = new Intent(PaymentActivity.this, EditAddressActivity.class);
            startActivity(intent);
        });
    }

    private void showPinDialog() {
        // Create a dialog to ask for PIN
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_enter_pin, null);
        builder.setView(dialogView);

        EditText editTextPin = dialogView.findViewById(R.id.editTextPin);

        builder.setPositiveButton("Confirm", (dialog, id) -> {
            String pin = editTextPin.getText().toString();
            validatePin(pin);
        });
        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void validatePin(String pin) {
        AsyncTask.execute(() -> {
            CardInformation cardInfo = db.cardInfoDao().getCardInfoByPin(pin);
            runOnUiThread(() -> {
                if (cardInfo != null) {
                    // Handle the payment confirmation logic here
                    Toast.makeText(PaymentActivity.this, "Payment Confirmed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PaymentActivity.this, PaymentCompleteActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(PaymentActivity.this, "Wrong PIN. Please enter card information first.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
