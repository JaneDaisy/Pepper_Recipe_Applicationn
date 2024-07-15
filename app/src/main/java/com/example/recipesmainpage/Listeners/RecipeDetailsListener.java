package com.example.recipesmainpage.Listeners;

import com.example.recipesmainpage.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
