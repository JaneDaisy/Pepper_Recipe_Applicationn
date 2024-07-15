package com.example.recipesmainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recipesmainpage.Activities.LoginActivity;
import com.example.recipesmainpage.Activities.ProfileActivity;
import com.example.recipesmainpage.Adapters.RandomRecipeAdapter;
import com.example.recipesmainpage.Listeners.RandomRecipeResponseListener;
import com.example.recipesmainpage.Listeners.RecipeClickListener;
import com.example.recipesmainpage.Models.RandomRecipeApiResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    List<String> tags = new ArrayList<>();
    SearchView searchView;
=======

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView myrecyclerView;
    RecyclerViewAdapter myAdapter;

    List<Recipes> recipes1;

    private SearchView searchView;
>>>>>>> d13b62df3996ad188ac4a333796ee4a350138564

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading....");

        searchView = findViewById(R.id.searchView_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener, tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        spinner =  findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener, tags);
        dialog.show();

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_recipes) {
                    // Already on recipes page
                    return true;
                } else if (id == R.id.nav_ingredients) {
                    startActivity(new Intent(MainActivity.this, MainIngredientsActivity.class));
                    return true;
                } else if (id == R.id.nav_profile) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    return true;
                }

                return false;
            }
        });

    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes, recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);

        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener, tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(MainActivity.this, RecipeDetailsActivity.class)
                    .putExtra("id", id));
        }
    };

    private void logoutUser() {
        // Clear login information
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        preferences.edit().clear().apply();

        // Retrieve username from SharedPreferences (if necessary)
        String username = preferences.getString("username", null);

        // Navigate to LoginActivity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();  // Close MainActivity
    }
}
=======
        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.my_toolbar);  // Replace 'my_toolbar' with your actual toolbar ID in layout
        setSupportActionBar(toolbar);

        // Rest of your existing code...

        recipes1 = new ArrayList<>();
        recipes1.add(new Recipes("Chicken Roll","Ingredients","200 gm chopped into cubes chicken" +
                "1 medium chopped tomato" +"\n"+
                "½ pinch red chilli powder" +"\n"+
                "2 tablespoon vegetable oil" +"\n"+
                "½ cut into strips cucumber" +"\n"+
                "½ tablespoon chopped coriander leaves" +"\n"+
                "1 large thinly sliced onion" +"\n"+
                "2 medium chopped green chilli" +"\n"+
                "2 pinches garam masala powder" +"\n"+
                "1 lemon wedges" +"\n"+
                "1 teaspoon tomato ketchup" +"\n"+
                "1 tablespoon green chilli sauce","Method",
                "Step 1"+"\n"+
                        "Take a bowl and to it add the coriander powder, garlic paste, cumin powder, ginger paste, yoghurt, black pepper and turmeric powder. Mix them well" +
                        "Now, add the boneless chicken breast to the mixture and let it marinate for 1 hour or so." +"\n\n"+
                        "Step 2"+"\n"+
                        "Take a frying pan and add oil. Once the oil is warm enough, add onions and saute them.\n" +
                        "Once done, add the marinated chicken pieces and mix well. Cook on low heat for 10-15 minutes." +"\n\n"+
                        "Step 3"+"\n"+
                        "Add water to the mixture.\n" +
                        "Cover it and let it cook. If it feels dry, add more water." +
                        "Once done, keep it aside. Once the chicken breast is cooled, break it into smaller pieces." +"\n\n"+
                        "Step 4"+"\n"+
                        "For the dough, mix all-purpose flour with vegetable oil, salt, and water. Knead into a dough. Roll 2-3 balls out of them.\n" +
                        "Take one of these smooth balls and roll it into round paratha." +
                        "Cook the paratha from all sides on a non-stick pan by adding a few drops of oil." +"\n\n"+
                        "Step 5"+"\n"+
                        "Take the cooked paratha and add the cooked chicken pieces to it." +
                        "Next add the onion, cucumber, chopped coriander, lemon juice, a sprinkle of garam masala and chilli powder and top that off with the tomato ketchup and green chilli sauce." +
                        "Roll it carefully, wrap one end of the chicken roll in a tissue and serve hot.","Nutrition Facts",
                "21g"+"\n"+"Carbs","10g"+"\n"+"Fats","210g"+"\n"+"Calories","10g"+"\n"+"Proteins","Reviews",R.drawable.chicken_roll));

        recipes1.add(new Recipes("Donut","Ingredients","1¼cups milk" +"\n"+
                "2¼teaspoons (one package) active dry yeast" +"\n"+
                "2eggs " +"\n"+
                "8tablespoons (1 stick) butter, melted and cooled" +"\n"+
                "1¼cup granulated sugar" +"\n"+
                "1teaspoon salt" +"\n"+
                "4¼cups all-purpose flour, plus more for rolling out the dough" +"\n"+

                "2quarts neutral oil, for frying, plus more for the bowl.","Directions","\n" +
                "Step 1"+"\n"+
                " Heat the milk until it is warm but not hot, about 90 degrees. In a large bowl, combine it with the yeast. Stir lightly, and let sit until the mixture is foamy, about 5 minutes." +"\n\n"+
                "Step 2"+"\n"+
                "Using an electric mixer or a stand mixer fitted with a dough hook, beat the eggs, butter, sugar and salt into the yeast mixture. Add half of the flour (2 cups plus 2 tablespoons), and mix until combined, then mix in the rest of the flour until the dough pulls away from the sides of the bowl. Add more flour, about 2 tablespoons at a time, if the dough is too wet. If you’re using an electric mixer, the dough will probably become too thick to beat; when it does, transfer it to a floured surface, and gently knead it until smooth. Grease a large bowl with a little oil. Transfer the dough to the bowl, and cover. Let rise at room temperature until it doubles in size, about 1 hour."+"\n\n"+
                "Step 3"+"\n"+
                "Turn the dough out onto a well-floured surface, and roll it to ½-inch thickness. Cut out the doughnuts with a doughnut cutter, concentric cookie cutters or a drinking glass and a shot glass (the larger one should be about 3 inches in diameter), flouring the cutters as you go. Reserve the doughnut holes. If you’re making filled doughnuts, don’t cut out the middle. Knead any scraps together, being careful not to overwork, and let rest for a few minutes before repeating the process." +"\n\n"+
                "Step 4"+"\n"+
                " Put the doughnuts on two floured baking sheets so that there is plenty of room between each one. Cover with a kitchen towel, and let rise in a warm place until they are slightly puffed up and delicate, about 45 minutes. If your kitchen isn’t warm, heat the oven to 200 at the beginning of this step, then turn off the heat, put the baking sheets in the oven and leave the door ajar." +"\n\n"+
                "Step 5"+"\n"+
                " About 15 minutes before the doughnuts are done rising, put the oil in a heavy-bottomed pot or Dutch oven over medium heat, and heat it to 375. Meanwhile, line cooling racks, baking sheets or plates with paper towels." +"\n\n"+

                "Step 6"+"\n"+
                "Carefully add the doughnuts to the oil, a few at a time. If they’re too delicate to pick up with your fingers (they may be this way only if you rose them in the oven), use a metal spatula to pick them up and slide them into the oil. It’s O.K. if they deflate a bit; they’ll puff back up as they fry. When the bottoms are deep golden, after 45 seconds to a minute, use a slotted spoon to flip; cook until they’re deep golden all over. Doughnut holes cook faster. Transfer the doughnuts to the prepared plates or racks, and repeat with the rest of the dough, adjusting the heat as needed to keep the oil at 375. Glaze or fill as follows, and serve as soon as possible.","Nutrition Facts",
                "21g"+"\n"+"Carbs","10g"+"\n"+"Fats","210g"+"\n"+"Calories","10g"+"\n"+"Proteins","Reviews",R.drawable.donut1));
        recipes1.add(new Recipes("Dosa","Ingredients","3 cups rice" +"\n"+
                "1 cup urad daal (split, skinless black gram)" +"\n"+
                "½ teaspoon fenugreek seeds" +"\n"+
                "Salt (to taste)" +"\n"+
                "Vegetable / canola / sunflower cooking oil","Method",
                "Step 1"+"\n"+
                        "Wash the rice and urad daal well. Add the fenugreek seeds to the mix and fill enough water in the rice-daal bowl to cover them about 2-inch deep. Soak overnight." +"\n\n"+
                        "Step 2"+"\n"+
                        "Put some cooking oil in a small bowl and keep ready. You will also need a bowl of ice cold water, a large, flat nonstick pan, 2 sheets of paper towel, a ladle, a spatula, and a basting brush." +"\n\n"+
                        "Step 3"+"\n"+
                        "When the upper surface begins to look cooked (it will no longer look soft or runny), flip the dosa. By this time, ideally, the surface that was underneath should be light golden in color. Cook for 1 minute after flipping."+"\n\n"+
                        "Step 4"+"\n"+
                          "The dosa is almost done. Fold it in half and allow to cook for 30 seconds more.","Nutrition Facts",
                "21g"+"\n"+"Carbs","10g"+"\n"+"Fats","210g"+"\n"+"Calories","10g"+"\n"+"Proteins","Reviews",R.drawable.dosa1));
        recipes1.add(new Recipes("Pancake","Ingredients","1 1/4 cups milk" +
                "1 egg" +"\n"+
                "3 tablespoons butter melted" +"\n"+
                "1 ½ cups all-purpose flour" +"\n"+
                "3 ½ teaspoons baking powder" +"\n"+
                "1 teaspoon salt" +"\n"+
                "1 tablespoon white sugar","Method",

                "Step 1"+"\n"+
                        "In a large bowl, sift together the flour, baking powder, salt and sugar. Make a well in the center and pour in the milk, egg and melted butter; mix until smooth." +"\n\n"+
                        "Step 2"+"\n"+
                        "Heat a lightly oiled griddle or frying pan over medium high heat. Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake. Brown on both sides and serve hot.","Nutrition Facts",
              "21g"+"\n"+"Carbs","10g"+"\n"+"Fats","210g"+"\n"+"Calories","10g"+"\n"+"Proteins","Reviews",R.drawable.pancakes));
        recipes1.add(new Recipes("Pasta","Ingredients","1 tsp oil" +
                "1 tsp butter" +"\n"+
                "2 clove garlic, finely chopped" +"\n"+
                "1 inch ginger, finely chopped" +"\n"+
                "½ onion, finely chopped" +"\n"+
                "1 cup tomato pulp" +"\n"+
                "¼ tsp turmeric / haldi" +"\n"+
                "½ tsp kashmiri red chilli powder" +"\n"+
                "2 tbsp tomato sauce" +"\n"+
                "½ tsp garam masala","Method",

                "Step 1"+"\n"+
                        "firstly, saute 1 inch ginger and 2 clove garlic in 1 tsp oil and 1 tsp butter." +
                        "further saute ½ onion till they turn soft." +"\n\n"+
                        "Step 2"+"\n"+
                        "Add 1 cup tomato pulp and saute well." +
                        "keep stirring till the tomato pulp thickens." +"\n\n"+
                        "Step 3"+"\n"+
                        "now add ¼ tsp turmeric, ½ tsp chilli powder, ½ tsp garam masala and ½ tsp salt." +
                        "saute till the spices gets cooked completely." +"\n\n"+
                        "Step 4"+"\n"+
                        "now add 2 tbsp corn, ¼ capsicum, ¼ carrot, 2 tbsp peas and 7 florets broccoli. saute for a minute." +
                        "add in 3 tbsp water and mix well." +
                        "cover and simmer for 5 minutes." +"\n\n"+
                        "Step 5"+"\n"+
                        "now add in 2 tbsp tomato sauce and ½ tsp mixed herbs. mix well." +
                        "add in cooked pasta and mix gently till the sauce gets coated well." +
                        "finally, serve masala pasta indian style hot topped with cheese if required.","Nutrition Facts",
                "21g"+"\n"+"Carbs","10g"+"\n"+"Fats","210g"+"\n"+"Calories","10g"+"\n"+"Proteins","Reviews",R.drawable.pasta1));



        myrecyclerView = findViewById(R.id.recyclerView_id);

        myAdapter = new RecyclerViewAdapter(this,recipes1);

        myrecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        myrecyclerView.setAdapter(myAdapter);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {  // Profile handling logic
            // Handle profile selection
            Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            return true;
        } else if (id == R.id.action_logout) {  // Logout handling logic (existing code)
            logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    private void logoutUser() {
        // Clear Login Information (Optional)
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        preferences.edit().clear().apply();
        // Retrieve username from SharedPreferences (assuming you have it stored)
        String username = preferences.getString("username", null);  // Ensure username is declared and assigned

        // Navigate to LoginActivity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //intent.putExtra("username", username);
        startActivity(intent);
        finish();  // Close MainActivity
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); // Assuming main_menu.xml

        MenuItem searchItem = menu.findItem(R.id.action_search);  // Assuming search menu item id
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);  // Initialize searchView


        return true;
    }

        @Override
        public boolean onQueryTextSubmit(String query) {
            // Perform search here based on user query
            updateRecipes(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            // Optional: Perform search as the user types
            // updateRecipes(newText);
            return false;
        }

    private void updateRecipes(String query) {
        List<Recipes> filteredRecipes = new ArrayList<>();
        for (Recipes recipe : recipes1) {
            // Search logic - modify this based on your search criteria (e.g., recipe name, ingredients)
            if (recipe.getRecipeName().toLowerCase().contains(query.toLowerCase())) {
                filteredRecipes.add(recipe);
            }
        }
        // Update your adapter with the filtered list
        myAdapter.setRecipes(filteredRecipes);
        myAdapter.notifyDataSetChanged();
    }





}


>>>>>>> d13b62df3996ad188ac4a333796ee4a350138564
