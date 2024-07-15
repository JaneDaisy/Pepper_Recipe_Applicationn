package com.example.recipesmainpage.Listeners;

import com.example.recipesmainpage.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
