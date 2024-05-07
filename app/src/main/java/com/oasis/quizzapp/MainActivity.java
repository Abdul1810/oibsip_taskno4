package com.oasis.quizzapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button htmlButton = findViewById(R.id.htmlButton);
        Button androidButton = findViewById(R.id.androidButton);

        htmlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHtmlQuiz();
            }
        });

        androidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAndroidQuiz();
            }
        });
    }

    private void startHtmlQuiz() {
        Intent intent = new Intent(this, HtmlQuestionActivity.class);
        startActivity(intent);
    }

    private void startAndroidQuiz() {
        Intent intent = new Intent(this, AndroidQuestionActivity.class);
        startActivity(intent);
    }
}
