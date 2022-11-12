package com.example.group44project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import Entities.Recipe;

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewAdapter.MyViewHolder>
{
    Context context;
    LinkedList<Recipe> allRecipes;

    public RecipeViewAdapter(Context context, LinkedList<Recipe> allRecipes)
    {
        this.context = context;
        this.allRecipes = allRecipes;
    }

    @NonNull
    @Override
    public RecipeViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reciperow, parent, false);

        return new RecipeViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewAdapter.MyViewHolder holder, int position) {
        holder.name.setText(allRecipes.get(position).getName());
        holder.tags.setText(allRecipes.get(position).getTags().toString());
    }

    @Override
    public int getItemCount() {
        return allRecipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView tags;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            tags = itemView.findViewById(R.id.tags);
        }
    }
}
