package com.example.group44project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import Entities.GroceryList;
import Entities.Ingredient;

public class DetailedGroceryListAdapter extends ArrayAdapter<Ingredient>
{
    ArrayList<Ingredient> items;
    Context context;

    public DetailedGroceryListAdapter(Context context, ArrayList<Ingredient> items)
    {
        super(context, R.layout.grocery_row, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.grocery_row, null);

            TextView number = convertView.findViewById(R.id.number);
            number.setText(position + 1 + ".");

            TextView name = convertView.findViewById(R.id.name);
            name.setText(items.get(position).getName());

            Button delete = convertView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GroceryActivity.removeItem(position);
                }
            });
        }
        return convertView;
    }
}
