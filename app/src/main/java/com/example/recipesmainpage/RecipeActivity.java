package com.example.recipesmainpage;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.widget.RatingBar;
import android.view.View.OnClickListener;
import android.view.View;
//import com.google.gson.Gson;
import java.util.List;



import com.example.recipesmainpage.Comment;

import java.util.Objects;

public class RecipeActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private EditText commentEditText;
    private Button submitButton;
    private String currentCommentId; // To store the ID of the comment being edited (optional)
    private SharedPrefsHelper sharedPrefsHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


            // Find UI elements
            ratingBar = findViewById(R.id.rating_bar);

// Set RatingBar listener
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    // Handle user interaction with the rating bar
                    // Update recipe rating (backend logic or local storage)
                    // ... (code to update rating based on user input)
                    //Update local rating storage
                    updateLocalRating(rating);
                }
            }
        });

        // Username retrieval (assuming you have a SharedPreferences for login info)
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String username = preferences.getString("username", null);

        commentEditText = findViewById(R.id.comment_edit_text);
        submitButton = findViewById(R.id.submit_button);
        sharedPrefsHelper = new SharedPrefsHelper(this);

        // Handle edit comment scenario (optional)
        String commentIdToEdit = getIntent().getStringExtra("commentId");
        if (commentIdToEdit != null) {
            currentCommentId = commentIdToEdit;
            Comment comment = sharedPrefsHelper.getComment(commentIdToEdit);
            sharedPrefsHelper.saveComment(comment);
            if (comment != null) {
                commentEditText.setText(comment.getCommentText());
            }
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentText = commentEditText.getText().toString();
                if (!commentText.isEmpty()) {
                    // Retrieve username (already done in onCreate)
                    String username = getSharedPreferences("user_info", MODE_PRIVATE).getString("username", null);

                    Comment comment = new Comment(commentText, username, System.currentTimeMillis());
                    sharedPrefsHelper.saveComment(comment); // Assuming this saves the comment object
                }
            }
        });



        TextView mRecipeName = findViewById(R.id.text_recipe);
        TextView mIngredientName = findViewById(R.id.ingredients_recipe);
        TextView mRecipeIngredients = findViewById(R.id.ingredients);
        TextView mRecipeMethodTitle = findViewById(R.id.method);
        TextView mRecipe = findViewById(R.id.recipe);
        TextView mNutritionTitle = findViewById(R.id.nutrition_title);
        TextView mCarbs = findViewById(R.id.text_nutrition_facts);
        TextView mFats = findViewById(R.id.text_fats);
        TextView mCalories = findViewById(R.id.text_carbs);
        TextView mProteins = findViewById(R.id.text_protein);
        TextView mReviews = findViewById(R.id.Reviews);


        Intent intent = getIntent();
        String Title = Objects.requireNonNull(intent.getExtras()).getString("RecipeName");
        String IngredientTitle = Objects.requireNonNull(intent.getExtras()).getString("IngredientName");
        String Ingredients = intent.getExtras().getString("RecipeIngredients");
        String MethodTitle = intent.getExtras().getString("RecipeMethodTitle");
        String Recipe = intent.getExtras().getString("Recipe");
        String NutTitle = Objects.requireNonNull(intent.getExtras()).getString("NutritionTitle");

        String Carbs = intent.getExtras().getString("Carbs");
        String Fats = intent.getExtras().getString("Fats");
        String Calories = intent.getExtras().getString("Calories");
        String Proteins = intent.getExtras().getString("Proteins");
        String Reviews = intent.getExtras().getString("Reviews");

        mRecipeName.setText(Title);
        mIngredientName.setText(IngredientTitle);
        mRecipeIngredients.setText(Ingredients);
        mRecipeMethodTitle.setText(MethodTitle);
        mRecipe.setText(Recipe);
        mNutritionTitle.setText(NutTitle);

        mCarbs.setText(Carbs);
        mFats.setText(Fats);
        mCalories.setText(Calories);
        mProteins.setText(Proteins);
        mReviews.setText(Reviews);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); // Assuming you have a menu resource named main_menu.xml

        MenuItem searchItem = menu.findItem(R.id.action_search);  // Assuming search menu item id is action_search
        MenuItemCompat.getActionView(searchItem);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            // Handle logout functionality
            logoutUser();  // Call a separate method for logout logic
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    private void logoutUser() {
        // Clear Login Information (Optional)
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        preferences.edit().clear().apply();

        // Navigate to LoginActivity
        Intent intent = new Intent(RecipeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();  // Close MainActivity
    }




    // Here's where you place the updateLocalRating method
    private void updateLocalRating(float rating) {
        SharedPreferences sharedPreferences = getSharedPreferences("recipe_ratings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("current_rating", rating);
        editor.apply(); // Apply changes to SharedPreferences
    }




}





