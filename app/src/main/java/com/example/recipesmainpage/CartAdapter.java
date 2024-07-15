package com.example.recipesmainpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final List<CartItem> items;

    CartAdapter(List<CartItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewCart;
        private final TextView textViewNameCart;
        private final TextView textViewQuantityCart;
        private final TextView textViewTotalPriceCart;

        CartViewHolder(View itemView) {
            super(itemView);
            imageViewCart = itemView.findViewById(R.id.imageViewCart);
            textViewNameCart = itemView.findViewById(R.id.textViewNameCart);
            textViewQuantityCart = itemView.findViewById(R.id.textViewQuantityCart);
            textViewTotalPriceCart = itemView.findViewById(R.id.textViewTotalPriceCart);
        }

        void bind(CartItem item) {
            imageViewCart.setImageResource(item.getImageResource());
            textViewNameCart.setText(item.getName());
            textViewQuantityCart.setText(String.format(Locale.getDefault(), "Quantity: %d", item.getQuantity()));
            textViewTotalPriceCart.setText(String.format(Locale.getDefault(), "Total: $%.2f", item.getTotalPrice()));
        }
    }
}
