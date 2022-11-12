package com.example.group44project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import Entities.GroceryList;
import Entities.Ingredient;

public class PantryActivity extends AppCompatActivity {
    // Global variables and references
    static GroceryList pantryList = new GroceryList();
    static ListView listView;
    static DetailedPantryListAdapter adapter;
    EditText itemInput;
    Button addItemButton;
    Toast toast;
    File path;
    String textFilePath;

    // Save the pantry when the user quits
    @Override
    protected void onDestroy() {
        try
        {
            if (!pantryList.getGroceryList().isEmpty())
            {
                File file = new File(path, textFilePath);
                FileWriter fileWrites = new FileWriter(file);
                fileWrites.write(pantryList.getGroceryList().toString());
                fileWrites.flush();
                fileWrites.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // Reset the reference
        pantryList = new GroceryList();
        super.onDestroy();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantry);

        // Set up for file I/O
        path = getApplicationContext().getFilesDir();
        textFilePath = "pantry.txt";
        itemInput = findViewById(R.id.itemInput);
        addItemButton = findViewById(R.id.addItem);
        listView = findViewById(R.id.listview);
        loadPantryList();
        adapter = new DetailedPantryListAdapter(getApplicationContext(), pantryList.getGroceryList());
        listView.setAdapter(adapter);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = itemInput.getText().toString();
                if (itemName == null || itemName.length() == 0) {
                    makeToast("Please enter an item.");
                }
                else
                {
                    addItem(itemName);
                    itemInput.setText("");
                    makeToast("Added to pantry list.");
                }
            }
        });
    }

    void addItem(String itemName)
    {
        pantryList.getGroceryList().add(new Ingredient(itemName));
        listView.setAdapter(adapter);
    }

    static void removeItem(int index)
    {
        pantryList.remove(index);
        listView.setAdapter(adapter);
    }

    private void makeToast(String toToast)
    {
        if (toast != null)
        {
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), toToast, Toast.LENGTH_SHORT);
        toast.show();
    }

    // Load the pantry list if it exists
    public void loadPantryList()
    {
        File readFiles = new File(path, textFilePath);
        byte[] list = new byte[(int)readFiles.length()];

        FileInputStream readFilesStream = null;

        try
        {
            if (readFiles.exists() == false)
            {
                return;
            }
            else
            {
                readFilesStream = new FileInputStream(readFiles);
                readFilesStream.read(list);

                String listString = new String(list);

                listString = listString.substring(1, listString.length() - 1);
                String splitedList[] = listString.split(", ");

                for (String s : splitedList)
                {
                    pantryList.addIngredient(new Ingredient(s));
                }
                readFilesStream.close();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
