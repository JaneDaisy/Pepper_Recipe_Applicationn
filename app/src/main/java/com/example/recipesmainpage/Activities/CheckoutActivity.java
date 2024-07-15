package com.example.recipesmainpage.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesmainpage.PaymentActivity;
import com.example.recipesmainpage.R;

import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private TextView textViewFinalTotalPrice;
    private Button buttonPayNow;
    private Button buttonEnterCardInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        textViewFinalTotalPrice = findViewById(R.id.textViewFinalTotalPrice);
        buttonPayNow = findViewById(R.id.buttonPayNow);
        buttonEnterCardInfo = findViewById(R.id.buttonEnterCardInfo);

        // Get the final total price from the intent
        Intent intent = getIntent();
        double finalTotalPrice = intent.getDoubleExtra("finalTotalPrice", 0.0);

        // Display the final total price
        String finalTotalPriceText = getString(R.string.total_price, String.format(Locale.getDefault(), "%.2f", finalTotalPrice));
        textViewFinalTotalPrice.setText(finalTotalPriceText);

        // Set click listener for the Pay Now button
        buttonPayNow.setOnClickListener(v -> {
            Intent paymentIntent = new Intent(CheckoutActivity.this, PaymentActivity.class);
            startActivity(paymentIntent);
        });

        // Set click listener for the Enter Card Info button
        buttonEnterCardInfo.setOnClickListener(v -> {
            Intent cardInfoIntent = new Intent(CheckoutActivity.this, CardEntryActivity.class);
            startActivity(cardInfoIntent);
        });
    }
}
