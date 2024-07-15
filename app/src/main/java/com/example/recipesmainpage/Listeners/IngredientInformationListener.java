package com.example.recipesmainpage.Listeners;

import com.example.recipesmainpage.Models.IngredientInformationResponse;

public interface IngredientInformationListener {
    void didFetch(IngredientInformationResponse response, String message);
    void didError(String message);
}
