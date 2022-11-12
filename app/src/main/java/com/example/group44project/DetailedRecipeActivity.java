package com.example.group44project;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButtonToggleGroup;
import Entities.IngredientPair;
import Entities.Recipe;
import Entities.RecipeRepository;
import Entities.Tag;
import Search.SearchByID;

public class DetailedRecipeActivity extends AppCompatActivity
{
    // Global variables and references
    TextView recipeName;
    TextView timeRequired;
    LinearLayout linearLayout;
    MaterialButtonToggleGroup ingredientOrInstructionSwitch;
    TextView ingredientsOrInstructions;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipedetails);

        // Get UI references
        ingredientsOrInstructions = findViewById(R.id.ingredientsOrInsturctions);
        ingredientOrInstructionSwitch = findViewById(R.id.NotificationSettingsButton);
        recipeName = findViewById(R.id.textViewRecipeName);
        timeRequired = findViewById(R.id.textViewTime);

        //Get recipe position from previous activity
        int recipePositionOriginal = getIntent().getIntExtra("RECIPE", -1);
        int recipePositionModified = getIntent().getIntExtra("RECIPEID", -1);
        int recipePosition;
        if (recipePositionOriginal != -1)
        {
            recipe = RecipeRepository.allRecipes.get(recipePositionOriginal);
        }
        else if (recipePositionModified != -1)
        {
            SearchByID searchByID = new SearchByID();
            recipe = searchByID.search(String.valueOf(recipePositionModified), RecipeRepository.allRecipes);
        }
        else
        {
            recipePosition = 0;
            recipe = RecipeRepository.allRecipes.get(recipePosition);
        }

        recipeName.setText(recipe.getName());
        String timeToDisplay = String.valueOf(recipe.getTimeRequired() + " mins");
        timeRequired.setText(timeToDisplay);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutTags);

        // Display tags
        for (Tag t: recipe.getTags())
        {
            TextView textView = new TextView(DetailedRecipeActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(100,0,50,0);
            textView.setLayoutParams(params);
            textView.setText(t.getName());
            textView.setBackgroundColor(Color.parseColor("#FF018786"));

            linearLayout.addView(textView);
        }

        // Display ingredients or instructions
        ingredientsOrInstructions.setMovementMethod(new ScrollingMovementMethod());
        int checkID = ingredientOrInstructionSwitch.getCheckedButtonId();
        buttonChanged();
        displayText(checkID);

    }

    // React when the user press the ingredient/instruction switch button
    private void buttonChanged()
    {
        ingredientOrInstructionSwitch.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                displayText(checkedId);
            }
        });
    }

    private void displayText(int checkedId)
    {
        if (checkedId == R.id.ingredients)
        {
            String ingredients = "";
            for (IngredientPair pair : recipe.getIngredients())
            {
                ingredients += pair.ingredientString() + "\n";
            }
            ingredientsOrInstructions.setText(ingredients);
        }
        if (checkedId == R.id.instructions)
        {
            String instructions = recipe.getInstructions();
            ingredientsOrInstructions.setText(instructions);
        }
    }
}
