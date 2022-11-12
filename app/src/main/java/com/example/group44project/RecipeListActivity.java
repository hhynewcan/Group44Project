package com.example.group44project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Entities.Recipe;
import Entities.RecipeRepository;

public class RecipeListActivity extends AppCompatActivity
{
    // Global variables and references
    RecyclerView recyclerView;
    QuickMealRecyclerAdapter adapter;
    ArrayList<QuickMealModel> recipeList = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipelist);

        context = this.context;
        recyclerView = findViewById(R.id.recipeLibraryList);

        // Display the recipes
        for (Recipe recipe : RecipeRepository.allRecipes)
        {
            recipeList.add(new QuickMealModel(0, recipe.getName(), recipe.getTags(), recipe.getTimeRequired()));
        }
        adapter = new QuickMealRecyclerAdapter(this, recipeList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the jump to the detailed recipe page
        Intent detailedRecipeJump = new Intent(this, DetailedRecipeActivity.class);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Recipe recipe = RecipeRepository.allRecipes.get(position);
                int id = recipe.getRecipeID();
                detailedRecipeJump.putExtra("RECIPEID", id);
                startActivity(detailedRecipeJump);
            }
            @Override public void onLongItemClick(View view, int position) {
            }
        }));
    }
}
