package com.example.recipesmainpage.Listeners;

import com.example.recipesmainpage.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);
}
