package com.example.recipesmainpage.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesmainpage.Databases.DatabaseHelper;
import com.example.recipesmainpage.MainActivity;
import com.example.recipesmainpage.R;

public class ProfileActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        // Retrieve username from Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String retrievedUsername = sharedPreferences.getString("username", null);

        // Update TextView with username (if found)
        TextView usernameTextView = findViewById(R.id.textView);  // Replace ID if different
        if (retrievedUsername != null) {
            usernameTextView.setText( retrievedUsername);
        }



        Button backToMainPageButton = findViewById(R.id.backToMainPageButton); // Replace with your button's ID

// Set an onClickListener for the button
        backToMainPageButton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        // This code will be executed when the button is clicked
                                                        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }

});
    }
}