package com.example.group44project;

import java.util.TreeSet;

import Entities.Tag;

public class QuickMealModel
{
    int recipePhoto;
    String recipeName;
    TreeSet<Entities.Tag> tags;
    int cookingTime;

    public QuickMealModel(int recipePhoto, String recipeName, TreeSet<Tag> tags, int cookingTime) {
        this.recipePhoto = recipePhoto;
        this.recipeName = recipeName;
        this.tags = tags;
        this.cookingTime = cookingTime;
    }


    public int getRecipePhoto() {
        return recipePhoto;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public TreeSet<Entities.Tag> getTags() {
        return tags;
    }

    public int getCookingTime() {
        return cookingTime;
    }
}
