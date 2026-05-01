package com.example.sqlitedatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Detail extends AppCompatActivity {

    ListView listUserData;
    Database databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        listUserData = findViewById(R.id.listUserData);
        databaseHelper = new Database(this);

        Cursor cursor = databaseHelper.getAllData();
        ArrayList<String> userList = new ArrayList<>();

        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String data =
                        "ID: " + cursor.getInt(0) + "\n" +
                                "Name: " + cursor.getString(1) + "\n" +
                                "Location: " + cursor.getString(2) + "\n" +
                                "Designation: " + cursor.getString(3);

                userList.add(data);
            }
            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                userList
        );

        listUserData.setAdapter(adapter);
    }
}