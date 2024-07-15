package com.example.recipesmainpage.Preferences;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.recipesmainpage.Models.Comment;

public class SharedPrefsHelper {

    private final SharedPreferences sharedPreferences;

    public SharedPrefsHelper(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    // Method to save a Comment object to SharedPreferences
    public void saveComment(Comment comment) {
        // Editor to modify SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Assuming Comment has getter methods for username, comment text, and timestamp
        String username = comment.getUsername();
        String commentText = comment.getCommentText();
        long timestamp = comment.getTimestamp();

        // Put data in SharedPreferences (adjust key names as needed)
        editor.putString("username", username);
        editor.putString("comment_text", commentText);
        editor.putLong("timestamp", timestamp);

        // Apply changes to SharedPreferences
        editor.apply();
    }

    // Method to retrieve a Comment object based on its ID (optional)
    public Comment getComment(String commentId) {
        SharedPreferences preferences = sharedPreferences;
        String username = preferences.getString("username_" + commentId, null);
        String commentText = preferences.getString("comment_text_" + commentId, null);
        long timestamp = preferences.getLong("timestamp_" + commentId, -1);

        // If all data is found, create and return a Comment object
        if (username != null && commentText != null && timestamp != -1) {
            return new Comment(commentText, username, timestamp);
        } else {
            // Comment not found, return null
            return null;
        }
    }

    // You can add other methods to save different types of data or retrieve data differently
    // For example, saving a list of comments might involve storing them as JSON strings
}