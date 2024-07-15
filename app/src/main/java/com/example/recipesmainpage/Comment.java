package com.example.recipesmainpage;

public class Comment {

    private String username;
    private String commentText;
    private long timestamp;

    // Constructor (assuming you have parameters for username, comment text, and timestamp)
    public Comment(String username, String commentText, long timestamp) {
        this.username = username;
        this.commentText = commentText;
        this.timestamp = timestamp;
    }

    // Getter methods
    public String getUsername() {
        return username;
    }

    public String getCommentText() {
        return commentText;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

