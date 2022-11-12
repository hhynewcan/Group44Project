package com.example.group44project;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.group44project.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;

import Entities.Recipe;
import Entities.RecipeRepository;

// Entrance of the App
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    // Global references to the UI elements
    Button groceryListSwitch;
    Button recipeListSwitch;
    Button pantrySwitch;
    Button userSettingsSwitch;
    Button quickMealSwitch;
    String userName = "";
    TextView userGreeting;
    TextView userGreeting2;
    Button searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Generate global test recipe repo
        RecipeRepository testRecipeRepo = new RecipeRepository();
        LinkedList<Recipe> testRecipeRepoList = testRecipeRepo.generateTestRecipes();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain references to UI elements
        quickMealSwitch = findViewById(R.id.QuickMealButton);
        recipeListSwitch = findViewById(R.id.RecipesButton);
        groceryListSwitch = findViewById(R.id.GroceryButton);
        pantrySwitch = findViewById(R.id.PantryButton);
        userSettingsSwitch = findViewById(R.id.UserSettingsButton);
        searchBar = findViewById(R.id.search_bar);
        userGreeting = findViewById(R.id.userGreeting);
        userGreeting2 = findViewById(R.id.userGreeting);

        setUpJumps();
        checkForUserName();
        userGreeting.setText("Hi " + userName);

        adaptForNightMode();
    }

    /**
     * Set up the activity jumps to different parts of the program.
     */
    private void setUpJumps()
    {
        Intent pantryJump = new Intent(MainActivity.this, PantryActivity.class);
        pantrySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(pantryJump);
            }
        });

        Intent quickMealJump = new Intent(this, QuickMealSimpleActivity.class);
        quickMealSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(quickMealJump);
            }
        });

        Intent recipeListJump = new Intent(this, RecipeListActivity.class);
        recipeListSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(recipeListJump);
            }
        });

        Intent groceryListJump = new Intent(this, CalendarActivity.class);
        groceryListSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(groceryListJump);
            }
        });

        Intent searchRecipeJump = new Intent(this, SearchRecipeActivity.class);
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(searchRecipeJump);
            }
        });

        Intent userSettingsJump = new Intent(MainActivity.this, SettingsActivity.class);
        userSettingsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(userSettingsJump);
            }
        });
    }

    /**
     * Get username if it is stored, otherwise go to welcome screen activity to ask for username.
     */
    private void checkForUserName()
    {
        File path = getApplicationContext().getFilesDir();
        String textFilePath = "UserProfile.txt";

        FileInputStream readFilesStream = null;

        File readFiles = new File(path, textFilePath);
        byte[] list = new byte[(int)readFiles.length()];

        try {
            if (readFiles.exists() == false) {
                Intent welcomeScreen = new Intent(this, WelcomeScreenActivity.class);
                startActivity(welcomeScreen);
            } else {
                readFilesStream = new FileInputStream(readFiles);
                readFilesStream.read(list);

                userName = new String(list);

                readFilesStream.close();

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        checkForUserName();
        userGreeting.setText("Hi " + userName);
    }

    /**
     * Adapt the UI to Day/Night mode.
     */
    private void adaptForNightMode()
    {
        int nightModeFlags = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                userGreeting.setTextColor(Color.WHITE);
                userGreeting2.setTextColor(Color.WHITE);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                userGreeting.setTextColor(Color.BLACK);
                userGreeting2.setTextColor(Color.BLACK);
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                userGreeting.setTextColor(Color.WHITE);
                userGreeting2.setTextColor(Color.WHITE);
                break;
        }
    }
}