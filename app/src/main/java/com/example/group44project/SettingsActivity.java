package com.example.group44project;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class SettingsActivity extends AppCompatActivity
{
    MaterialButtonToggleGroup notificationSwitch;
    boolean isNotificationOn = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        notificationSwitch = findViewById(R.id.NotificationSettingsButton);

        loadNotificationSettings();

        notificationSwitch.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                changeNotificationSettings(checkedId);
            }
        });
    }
    private void buttonChanged()
    {
        notificationSwitch.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                changeNotificationSettings(checkedId);
            }
        });
    }
    private void changeNotificationSettings(int checkedId)
    {
        String settings = "On";
        if (checkedId == R.id.On)
        {
            settings = "On";
        }
        if (checkedId == R.id.Off)
        {
            settings = "Off";
        }
        File path = getApplicationContext().getFilesDir();
        String textFilePath = "NotificationSettings.txt";
        File readFiles = new File(path, textFilePath);
        byte[] option = new byte[(int)readFiles.length()];
        FileInputStream readFilesStream = null;

        try
        {
            File file = new File(path, textFilePath);
            FileWriter fileWrites = new FileWriter(file);
            fileWrites.write(settings);
            fileWrites.flush();
            fileWrites.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void loadNotificationSettings()
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
                    notificationSwitch.check(R.id.On);
                }
                else
                {
                    isNotificationOn = false;
                    notificationSwitch.check(R.id.Off);
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
