package com.example.group44project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.LinkedList;
import Entities.Recipe;
import Entities.RecipeRepository;
import Search.SearchByID;
import Search.SearchByIngredients;
import Search.SearchByName;
import Search.SearchByTags;

public class SearchRecipeActivity extends AppCompatActivity
{
    // Global references to variables and UI elements
    ArrayList<Recipe> filteredRecipe = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> filteredRecipeNameList = new ArrayList<>();
    SearchView searchView;
    ListView listView;
    SearchByName searchByName;
    SearchByID searchByID;
    SearchByTags searchByTags;
    SearchByIngredients searchByIngredients;
    Spinner spinner;
    ArrayList<String> choices;
    String currentChoice = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchrecipe);

        // Initiate references
        searchByName = new SearchByName();
        searchByID = new SearchByID();
        searchByTags = new SearchByTags();
        searchByIngredients = new SearchByIngredients();
        choices = new ArrayList<>();

        // Get UI references
        searchView = findViewById(R.id.recipeSearchBox);
        listView = findViewById(R.id.filteredRecipeView);
        spinner = findViewById(R.id.spinner);

        // Set up jump to the recipe details
        Intent detailedRecipeJump = new Intent(this, DetailedRecipeActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                if (!filteredRecipe.isEmpty())
                {
                    Recipe recipe = filteredRecipe.get(i);
                    int id = recipe.getRecipeID();
                    detailedRecipeJump.putExtra("RECIPEID", id);
                    startActivity(detailedRecipeJump);
                }
            }
        });

        // Set up search choices
        choices.add("Search by Name");
        choices.add("Search by ID");
        choices.add("Search by Ingredients");
        choices.add("Search by Tags");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choices);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                currentChoice = adapterView.getSelectedItem().toString();
                searchView.setQueryHint(currentChoice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // React to search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                LinkedList<Recipe> resultList = new LinkedList<>();
                filteredRecipeNameList = new ArrayList<>();
                switch (currentChoice)
                {
                    case "Search by Name":
                        resultList = searchByName.search(s, RecipeRepository.allRecipes);
                        break;
                    case "Search by ID":
                        Recipe foundRecipe;
                        foundRecipe = searchByID.search(s, RecipeRepository.allRecipes);
                        if (foundRecipe == null)
                        {
                            System.out.println("searchByID returned null!" );
                        }
                        else
                        {
                            System.out.println("Found recipeByID: " + foundRecipe);
                            resultList.add(searchByID.search(s, RecipeRepository.allRecipes));
                        }
                        break;
                    case "Search by Ingredients":
                        String[] ingredients = new String[1];
                        ingredients[0] = s;
                        resultList = searchByIngredients.search(ingredients);
                        break;
                    case "Search by Tags":
                        String[] arr = s.split("[ ,]+");
                        resultList = searchByTags.search(arr, RecipeRepository.allRecipes);
                        break;
                }
                if (resultList.isEmpty())
                {
                    filteredRecipeNameList.add("Sorry! Could not find your item.");
                }
                else
                {
                    filteredRecipe.addAll(resultList);
                    for (Recipe recipe : resultList) {
                        filteredRecipeNameList.add(recipe.getName());
                    }
                }
                adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, filteredRecipeNameList);
                listView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
}
