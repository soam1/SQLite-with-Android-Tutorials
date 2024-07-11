package com.example.sqlitewithandroidtutorials;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
            myDB.addBook(title_input.getText().toString().trim(),
                    author_input.getText().toString().trim(),
                    Integer.parseInt(pages_input.getText().toString().trim()));

        });

    }
}