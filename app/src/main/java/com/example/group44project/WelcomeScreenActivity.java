package com.example.group44project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WelcomeScreenActivity extends AppCompatActivity
{
    // Global references to UI elements
    TextView textView;
    ImageView imageView2;
    EditText editText;

    // Indicator for stage of the welcome screen
    int state = 0;

    // Reference to the toast
    Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        // Get references to UI elements
        textView = findViewById(R.id.welcome_text);
        editText = findViewById(R.id.getUsername);
        imageView2 = findViewById(R.id.imageView2);

        // Rotate through the stages of welcome screen to get username
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (state)
                {
                    case 0:
                        textView.setText("Please enter your name");
                        editText.setVisibility(View.VISIBLE);
                        state +=1;
                        break;
                    case 1:
                        if (editText.getText().toString().equals(""))
                        {
                            makeToast("Please enter a valid name.");
                        }
                        else
                        {
                            File path = getApplicationContext().getFilesDir();
                            String textFilePath = "UserProfile.txt";

                            String name = editText.getText().toString();
                            File file = new File(path, textFilePath);
                            FileWriter fileWrites = null;
                            try {
                                fileWrites = new FileWriter(file);
                                fileWrites.write(name);
                                fileWrites.flush();
                                fileWrites.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            finish();
                        }
                        break;
                }
            }
        });

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
