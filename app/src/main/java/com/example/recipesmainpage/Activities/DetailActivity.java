package com.example.recipesmainpage.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesmainpage.CartItem;
import com.example.recipesmainpage.Preferences.CartPreferences;
import com.example.recipesmainpage.R;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private ImageView imageViewDetail;
    private TextView textViewNameDetail, textViewQuantity, textViewUnitPrice, textViewTotalPrice;
    private Button buttonDecrease, buttonIncrease, buttonAddToCart;
    private int quantity = 1;
    private double unitPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageViewDetail = findViewById(R.id.imageViewDetail);
        textViewNameDetail = findViewById(R.id.textViewNameDetail);
        textViewUnitPrice = findViewById(R.id.textViewUnitPrice);
        textViewQuantity = findViewById(R.id.textViewQuantity);
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        buttonDecrease = findViewById(R.id.buttonDecrease);
        buttonIncrease = findViewById(R.id.buttonIncrease);
        buttonAddToCart = findViewById(R.id.buttonAddToCart);

        String ingredientName = getIntent().getStringExtra("ingredientName");
        int ingredientImage = getIntent().getIntExtra("ingredientImage", 0);
        unitPrice = getIntent().getDoubleExtra("ingredientPrice", 0.0);

        Log.d(TAG, "Ingredient Name: " + ingredientName);
        Log.d(TAG, "Ingredient Image: " + ingredientImage);
        Log.d(TAG, "Unit Price: " + unitPrice);

        textViewNameDetail.setText(ingredientName);
        imageViewDetail.setImageResource(ingredientImage);
        textViewUnitPrice.setText(String.format(Locale.getDefault(), "$%.2f", unitPrice));
        Log.d(TAG, "Unit Price Text: " + textViewUnitPrice.getText().toString());

        updateTotalPrice();

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    textViewQuantity.setText(String.valueOf(quantity));
                    updateTotalPrice();
                }
            }
        });

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                textViewQuantity.setText(String.valueOf(quantity));
                updateTotalPrice();
            }
        });

        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalPrice = unitPrice * quantity;
                CartItem newItem = new CartItem(ingredientName, ingredientImage, quantity, unitPrice, totalPrice);
                CartPreferences.addItemToCart(DetailActivity.this, newItem);

                Toast.makeText(DetailActivity.this, ingredientName + " added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTotalPrice() {
        double totalPrice = unitPrice * quantity;
        String totalPriceText = getString(R.string.total_price, String.format(Locale.getDefault(), "%.2f", totalPrice));
        textViewTotalPrice.setText(totalPriceText);
        Log.d(TAG, "Total Price: " + totalPriceText);
    }
}
