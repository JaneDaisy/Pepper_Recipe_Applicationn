package com.example.recipesmainpage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipesmainpage.Activities.CheckoutActivity;
import com.example.recipesmainpage.CartAdapter;
import com.example.recipesmainpage.CartItem;
import com.example.recipesmainpage.Preferences.CartPreferences;
import com.example.recipesmainpage.R;

import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private TextView textViewFinalTotalPrice;
    private Button buttonCheckout;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        textViewFinalTotalPrice = findViewById(R.id.textViewFinalTotalPrice);
        buttonCheckout = findViewById(R.id.buttonCheckout);

        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        // Fetch cart items from your data source
        cartItems = CartPreferences.getCartItems(this);

        cartAdapter = new CartAdapter(cartItems);
        recyclerViewCart.setAdapter(cartAdapter);

        updateFinalTotalPrice();

        buttonCheckout.setOnClickListener(v -> proceedToCheckout());
    }

    private void proceedToCheckout() {
        double finalTotalPrice = calculateFinalTotalPrice();
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra("finalTotalPrice", finalTotalPrice);
        startActivity(intent);
    }

    private void updateFinalTotalPrice() {
        double finalTotalPrice = 0.0;
        for (CartItem item : cartItems) {
            finalTotalPrice += item.getTotalPrice();
        }
        textViewFinalTotalPrice.setText(String.format(Locale.getDefault(), "Total: $%.2f", finalTotalPrice));
    }

    private double calculateFinalTotalPrice() {
        double finalTotalPrice = 0.0;
        for (CartItem item : cartItems) {
            finalTotalPrice += item.getTotalPrice();
        }
        return finalTotalPrice;
    }

    private void addItemToCart(CartItem newItem) {
        boolean itemExists = false;
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            if (item.getName().equals(newItem.getName())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                cartAdapter.notifyItemChanged(i);
                updateFinalTotalPrice();
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            cartItems.add(newItem);
            cartAdapter.notifyItemInserted(cartItems.size() - 1);
            updateFinalTotalPrice();
        }
    }
}
