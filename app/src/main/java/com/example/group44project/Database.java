package com.example.group44project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {



    public Database(@Nullable Context context) {
        super(context, "localStorage", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE USERNAME_TABLE (ID STRING PRIMARY KEY AUTOINCREMENT, )";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean doesUserNameExist()
    {
        //SQLiteDatabase sqLiteDatabase = this.getReadableDatabase());
        return true;
    }
}
