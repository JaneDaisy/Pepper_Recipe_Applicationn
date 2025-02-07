package com.example.recipesmainpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private final Context mContext;
    private List<Recipes> mData;

    public RecyclerViewAdapter(Context mContext, List<Recipes> mData){
        this.mContext = mContext;
        this.mData = mData;
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_recipe,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, @SuppressLint("RecyclerView") final int i) {

        myHolder.recipeTitle.setText(mData.get(i).getRecipeName());
        myHolder.img_recipe_thumbnail.setImageResource(mData.get(i).getThumbnail());
        myHolder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,RecipeActivity.class);

            intent.putExtra("RecipeName",mData.get(i).getRecipeName());
            intent.putExtra("RecipeIngredients",mData.get(i).getRecipeIngredients());
            intent.putExtra("IngredientName",mData.get(i).getIngredientName());
            intent.putExtra("RecipeMethodTitle",mData.get(i).getRecipeMethodTitle());
            intent.putExtra("Recipe",mData.get(i).getRecipe());
            intent.putExtra("NutritionTitle",mData.get(i).getNutritionTitle());

            intent.putExtra("Carbs",mData.get(i).getCarbs());
            intent.putExtra("Fats",mData.get(i).getFats());
            intent.putExtra("Calories",mData.get(i).getCalories());
            intent.putExtra("Proteins",mData.get(i).getProteins());
            intent.putExtra("Reviews",mData.get(i).getReviews());

            intent.putExtra("Thumbnail",mData.get(i).getThumbnail());

            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        TextView recipeTitle;
        CardView cardView;
        ImageView img_recipe_thumbnail;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            recipeTitle = itemView.findViewById(R.id.recipe_text);
            img_recipe_thumbnail = itemView.findViewById(R.id.recipe_img_id);
            cardView = itemView.findViewById(R.id.cardview_id);


        }
    }

    public void setRecipes(List<Recipes> recipes) {
        mData = recipes; // Update the data list
        notifyDataSetChanged(); // Inform RecyclerView about data change
    }
}