package com.example.booklist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText user_id_input, establishment_name_input, food_served_input, location_input, review_input;

    Button add_button;
    SQLiteDatabase db;

    public String establishment_type;

    String types[] = { "--Establishment Type--", "Cafe", "Bar", "Restaurant"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        user_id_input = findViewById(R.id.user_id_input);
        establishment_name_input = findViewById(R.id.establishment_name_input);
//        establishment_type_input = findViewById(R.id.establishment_type_input);
        food_served_input = findViewById(R.id.food_served_input);
        location_input = findViewById(R.id.location_input);
        review_input = findViewById(R.id.review_input);
        add_button = findViewById(R.id.add_button);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                types
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(adapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_id_input.getText().toString().isEmpty() || establishment_name_input.getText().toString().isEmpty() || review_input.getText().toString().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                } else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addBook(user_id_input.getText().toString().trim(),
                            establishment_name_input.getText().toString().trim(),
                            establishment_type.trim(),
                            food_served_input.getText().toString().trim(),
                            location_input.getText().toString().trim(),
                            review_input.getText().toString().trim());
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        establishment_type = types[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(), "You must select the establishment type", Toast.LENGTH_SHORT).show();
    }
}