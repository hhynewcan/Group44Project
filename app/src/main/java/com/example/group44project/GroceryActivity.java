package com.example.group44project;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Calendar;
import Entities.GroceryList;
import Entities.Ingredient;

public class GroceryActivity extends AppCompatActivity
{
    // Global variables and references
    static ListView listView;
    static GroceryList groceryList = new GroceryList();
    static DetailedGroceryListAdapter adapter;
    EditText itemInput;
    Button addItemButton;
    Toast toast;
    File path;
    String Year;
    String Month;
    String Day;
    String textFilePath;
    Boolean isNotificationOn = true;

    // Save the grocery list and set up notification when the user quits
    @Override
    protected void onDestroy() {
        try
        {
            if (!groceryList.getGroceryList().isEmpty())
            {
                File file = new File(path, textFilePath);
                FileWriter fileWrites = new FileWriter(file);
                fileWrites.write(groceryList.getGroceryList().toString());
                fileWrites.flush();
                fileWrites.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Reset the grocery list reference upon quitting
        groceryList = new GroceryList();

        loadNotificationSettings();

        if (isNotificationOn == true) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(Year), Integer.parseInt(Month), Integer.parseInt(Day), 0, 0, 0);
            System.out.println(calendar.getTime().toString());
            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
        super.onDestroy();
    }

    private void loadNotificationSettings()
    {
        File path = getApplicationContext().getFilesDir();
        String textFilePath = "NotificationSettings.txt";
        File readFiles = new File(path, textFilePath);
        byte[] option = new byte[(int)readFiles.length()];
        FileInputStream readFilesStream = null;

        try
        {
            if (readFiles.exists() == false)
            {
                isNotificationOn = true;
                File file = new File(path, textFilePath);
                FileWriter fileWrites = new FileWriter(file);
                fileWrites.write("On");
                fileWrites.flush();
                fileWrites.close();
            }
            else
            {
                readFilesStream = new FileInputStream(readFiles);
                readFilesStream.read(option);
                String isOn = new String(option);
                if (isOn.equals("On"))
                {
                    isNotificationOn = true;
                }
                else
                {
                    isNotificationOn = false;
                }
                readFilesStream.close();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Try to load the grocery list if it exits
     */
    public void loadGroceryList()
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
                    groceryList.addIngredient(new Ingredient(s));
                }
                readFilesStream.close();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocerylist);

        // Extract information from calendar jump and set up for file I/O
        Intent intent = getIntent();
        Year = intent.getExtras().getString("YEAR");
        Month = intent.getExtras().getString("MONTH");
        Day = intent.getExtras().getString("DAY");
        setTitle("Grocery List of " + Month + "/" + Day + "/" + Year);
        path = getApplicationContext().getFilesDir();
        textFilePath = Year + Month + Day + "grocerylist.txt";

        // Get UI references
        itemInput = findViewById(R.id.itemInput);
        addItemButton = findViewById(R.id.addItem);
        listView = findViewById(R.id.listview);

        // Display the grocery list if it exists
        loadGroceryList();
        adapter = new DetailedGroceryListAdapter(getApplicationContext(), groceryList.getGroceryList());
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
                    makeToast("Added to grocery list.");
                }
            }
        });
    }

    void addItem(String itemName)
    {
        groceryList.getGroceryList().add(new Ingredient(itemName));
        listView.setAdapter(adapter);
    }

    static void removeItem(int index)
    {
        groceryList.remove(index);
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
}
