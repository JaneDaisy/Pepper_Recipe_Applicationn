package com.example.recipesmainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipesmainpage.Activities.DetailActivity;
import com.example.recipesmainpage.Models.IngredientsInformation;

import java.util.ArrayList;
import java.util.List;

public class MainIngredientsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IngredientAdapter ingredientAdapter;
    private List<IngredientsInformation> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ingredientList = new ArrayList<>();
        // Add ingredients to the list
        ingredientList.add(new IngredientsInformation("Tomato", R.drawable.tomato, 10));
        ingredientList.add(new IngredientsInformation("Onion", R.drawable.onion, 5));
        ingredientList.add(new IngredientsInformation("Almond Milk", R.drawable.almond_milk, 50));
        ingredientList.add(new IngredientsInformation("Avocado", R.drawable.avocado, 30));
        ingredientList.add(new IngredientsInformation("Cheddar Cheese", R.drawable.cheddar_cheese, 200));
        ingredientList.add(new IngredientsInformation("Coriander", R.drawable.coriander, 5));
        ingredientList.add(new IngredientsInformation("Garlic", R.drawable.garlic, 30));
        ingredientList.add(new IngredientsInformation("Lemon", R.drawable.lemon, 10));
        ingredientList.add(new IngredientsInformation("Lime", R.drawable.lime, 10));
        ingredientList.add(new IngredientsInformation("Peanuts", R.drawable.peanuts, 200));
        // Add more ingredients as needed

        ingredientAdapter = new IngredientAdapter(ingredientList);
        recyclerView.setAdapter(ingredientAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_view_cart) {
            // Open the cart activity or fragment here
            Intent intent = new Intent(MainIngredientsActivity.this, CartActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.cart, menu);
        return true;
    }


    private class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

        private final List<IngredientsInformation> ingredients;

        IngredientAdapter(List<IngredientsInformation> ingredients) {
            this.ingredients = ingredients;
        }

        @NonNull
        @Override
        public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
            return new IngredientViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
            IngredientsInformation ingredient = ingredients.get(position);
            holder.imageView.setImageResource(ingredient.getImageResId());
            holder.textViewName.setText(ingredient.getName());
            holder.buttonView.setOnClickListener(v -> {
                Intent intent = new Intent(MainIngredientsActivity.this, DetailActivity.class);
                intent.putExtra("ingredientName", ingredient.getName());
                intent.putExtra("ingredientImage", ingredient.getImageResId());
                intent.putExtra("ingredientPrice", (double) ingredient.getPrice());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return ingredients.size();
        }

        class IngredientViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textViewName;
            Button buttonView;

            IngredientViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                textViewName = itemView.findViewById(R.id.textViewName);
                buttonView = itemView.findViewById(R.id.buttonView);
            }
        }
    }
}
