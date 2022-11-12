package com.example.group44project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuickMealRecyclerAdapter extends RecyclerView.Adapter<QuickMealRecyclerAdapter.MyViewHolder>
{
    Context context;
    ArrayList<QuickMealModel> recipeList;

    public QuickMealRecyclerAdapter(Context context, ArrayList<QuickMealModel> recipeList)
    {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public QuickMealRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.quick_meal_simple_row, parent, false);

        return new QuickMealRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuickMealRecyclerAdapter.MyViewHolder holder, int position) {
        holder.tvRecipeName.setText(recipeList.get(position).getRecipeName());
        String cookingTime = recipeList.get(position).getCookingTime() + "mins";
        holder.tvCookingTime.setText(cookingTime);
        holder.tvTags.setText(recipeList.get(position).getTags().toString());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvRecipeName, tvTags, tvCookingTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recipePhoto);
            tvRecipeName = itemView.findViewById(R.id.recipeName);
            tvTags = itemView.findViewById(R.id.Tags);
            tvCookingTime = itemView.findViewById(R.id.CookingTime);
        }
    }
}
