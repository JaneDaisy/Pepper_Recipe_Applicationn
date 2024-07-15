package com.example.recipesmainpage.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.recipesmainpage.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartPreferences {

    private static final String CART_PREFS = "cart_prefs";
    private static final String CART_ITEMS = "cart_items";

    public static void addItemToCart(Context context, CartItem newItem) {
        SharedPreferences prefs = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(CART_ITEMS, null);
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(json, type);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        boolean itemExists = false;
        for (CartItem item : cartItems) {
            if (item.getName().equals(newItem.getName())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            cartItems.add(newItem);
        }

        SharedPreferences.Editor editor = prefs.edit();
        json = gson.toJson(cartItems);
        editor.putString(CART_ITEMS, json);
        editor.apply();
    }

    public static List<CartItem> getCartItems(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(CART_ITEMS, null);
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(json, type);

        return cartItems != null ? cartItems : new ArrayList<>();
    }

    public static void removeItemFromCart(Context context, String itemName) {
        SharedPreferences prefs = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(CART_ITEMS, null);
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(json, type);

        if (cartItems != null) {
            for (int i = 0; i < cartItems.size(); i++) {
                if (cartItems.get(i).getName().equals(itemName)) {
                    cartItems.remove(i);
                    break;
                }
            }

            SharedPreferences.Editor editor = prefs.edit();
            json = gson.toJson(cartItems);
            editor.putString(CART_ITEMS, json);
            editor.apply();
        }
    }

    public static void updateItemQuantity(Context context, String itemName, int newQuantity) {
        SharedPreferences prefs = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(CART_ITEMS, null);
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(json, type);

        if (cartItems != null) {
            for (CartItem item : cartItems) {
                if (item.getName().equals(itemName)) {
                    item.setQuantity(newQuantity);
                    break;
                }
            }

            SharedPreferences.Editor editor = prefs.edit();
            json = gson.toJson(cartItems);
            editor.putString(CART_ITEMS, json);
            editor.apply();
        }
    }

    public static void clearCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(CART_ITEMS);
        editor.apply();
    }
}
