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
import java.util.LinkedList;

import Entities.Recipe;
import Entities.RecipeRepository;
import Search.SearchByTimeRequired;

public class QuickMealSimpleActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    QuickMealRecyclerAdapter adapter;
    Context context;
    ArrayList<QuickMealModel> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_meal_simple);

        // References initializations
        context = this.context;
        recyclerView = findViewById(R.id.quickMealRecipeList);
        SearchByTimeRequired searchByTimeRequired = new SearchByTimeRequired();
        LinkedList<Recipe> sorted = searchByTimeRequired.search(RecipeRepository.allRecipes);

        // Sort the recipes based on time and display
        for (Recipe recipe : sorted)
        {
            recipeList.add(new QuickMealModel(0, recipe.getName(), recipe.getTags(), recipe.getTimeRequired()));
        }
        adapter = new QuickMealRecyclerAdapter(this, recipeList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the jump to recipe details
        Intent detailedRecipeJump = new Intent(this, DetailedRecipeActivity.class);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Recipe recipe = sorted.get(position);
                int id = recipe.getRecipeID();
                detailedRecipeJump.putExtra("RECIPEID", id);
                startActivity(detailedRecipeJump);
            }
            @Override public void onLongItemClick(View view, int position) {
            }
        }));
    }
}
