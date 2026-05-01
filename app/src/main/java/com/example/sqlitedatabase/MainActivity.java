package com.example.sqlitedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputName, inputLocation, inputDesignation;
    Button buttonInsert, buttonView;
    Database databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.inputName);
        inputLocation = findViewById(R.id.inputLocation);
        inputDesignation = findViewById(R.id.inputDesignation);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonView = findViewById(R.id.buttonView);

        databaseHelper = new Database(this);

        buttonInsert.setOnClickListener(v -> {
            boolean inserted = databaseHelper.insertData(
                    inputName.getText().toString(),
                    inputLocation.getText().toString(),
                    inputDesignation.getText().toString()
            );

            if (inserted) {
                Toast.makeText(this, getString(R.string.insert_success), Toast.LENGTH_SHORT).show();
                inputName.setText("");
                inputLocation.setText("");
                inputDesignation.setText("");
            } else {
                Toast.makeText(this, getString(R.string.insert_fail), Toast.LENGTH_SHORT).show();
            }
        });

        buttonView.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Detail.class));
        });
    }
}