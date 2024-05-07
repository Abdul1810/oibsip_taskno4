package com.oasis.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTextView = findViewById(R.id.score_text_view);

        // Get score passed from QuestionActivity
        int score = getIntent().getIntExtra("SCORE", 0);
        scoreTextView.setText("Your score: " + score);

        Button startAgainButton = findViewById(R.id.start_again_button);
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizAgain();
            }
        });
    }

    private void startQuizAgain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the ScoreActivity
    }
}
