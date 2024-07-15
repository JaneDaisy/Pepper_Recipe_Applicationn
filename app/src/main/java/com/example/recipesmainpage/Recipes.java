package com.example.recipesmainpage;

public class Recipes {

    private String RecipeName;
    private String RecipeIngredients;
    private String RecipeMethodTitle;
    private String Recipe;
    private int Thumbnail;
    private String IngredientName;
    private String NutritionFacts;
private String NutritionTitle;
//added part of table
    private String Carbs;
    private String Fats;
    private String Calories;
    private String Proteins;

    private String Reviews;
    public Recipes(String name,String ingredient_name, String recipeIngredients, String recipeMethodTitle,String recipe,String nutritiontitle,String carbs, String fats, String calories, String proteins,String reviews, int thumbnail){

        RecipeName = name;
        IngredientName = ingredient_name;
        RecipeIngredients = recipeIngredients;
        RecipeMethodTitle = recipeMethodTitle;
        Recipe = recipe;
        NutritionTitle=nutritiontitle;

        Carbs= carbs;
        Fats= fats;
        Calories= calories;
        Proteins= proteins;
        Reviews= reviews;


        Thumbnail = thumbnail;

    }


    public  String getRecipeName(){

        return RecipeName;
    }
    public  String getIngredientName(){

        return IngredientName;
    }
    public String getRecipeIngredients(){
        return RecipeIngredients;
    }

    public String getRecipeMethodTitle(){
        return RecipeMethodTitle;
    }

    public String getRecipe(){
        return Recipe;
    }
    public  String getNutritionTitle(){

        return NutritionTitle;
    }



    public String getCarbs(){
        return Carbs;
    }
    public String getFats(){
        return Fats;
    }
    public String getCalories(){
        return Calories;
    }
    public String getProteins(){
        return Proteins;
    }

    public String getReviews(){
        return Reviews;
    }

    public int getThumbnail(){
        return Thumbnail;
    }
}


