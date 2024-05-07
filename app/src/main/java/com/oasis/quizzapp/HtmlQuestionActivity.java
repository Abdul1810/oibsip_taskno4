package com.oasis.quizzapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HtmlQuestionActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton;

    private String[] questions = {
            "What does HTML stand for?",
            "Which is the correct HTML element for the largest heading?",
            "How can you create a numbered list in HTML?",
            "Which HTML attribute is used to specify an alternate text for an image, if the image cannot be displayed?",
            "What does CSS stand for?",
    };

    private String[] correctAnswers = {
            "a) Hyper Text Markup Language",
            "a) <h1>",
            "b) <ol>",
            "a) alt",
            "a) Cascading Style Sheets",
    };

    String[][] options = {
            {
                    "a) Hyper Text Markup Language",
                    "b) Hyperlinks and Text Markup Language",
                    "c) Home Tool Markup Language",
                    "d) Hyperlinks and Text Markup Language"
            },
            {
                    "a) <h1>",
                    "b) <h6>",
                    "c) <head>",
                    "d) <header>"
            },
            {
                    "a) <ul>",
                    "b) <ol>",
                    "c) <li>",
                    "d) <dl>"
            },
            {
                    "a) alt",
                    "b) src",
                    "c) href",
                    "d) rel"
            },
            {
                    "a) Cascading Style Sheets",
                    "b) Colorful Style Sheets",
                    "c) Creative Style Sheets",
                    "d) Computer Style Sheets"
            },
    };

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView titleTextView = findViewById(R.id.title);
        titleTextView.setText("HTML Quiz");

        questionTextView = findViewById(R.id.question_text_view);
        optionsRadioGroup = findViewById(R.id.options_radio_group);
        nextButton = findViewById(R.id.next_button);

        displayQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        questionTextView.setText(currentQuestionIndex + 1 + ". " + questions[currentQuestionIndex]);

        optionsRadioGroup.removeAllViews();

        String[] optionsForThisQuestion = options[currentQuestionIndex];
        for (int i = 0; i < optionsForThisQuestion.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(optionsForThisQuestion[i]);
            optionsRadioGroup.addView(radioButton);
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = correctAnswers[currentQuestionIndex];
            if (selectedAnswer.equals(correctAnswer)) {
                score++;
            }
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                displayQuestion();
            } else {
                Intent intent = new Intent(this, ScoreActivity.class);
                intent.putExtra("SCORE", score);
                startActivity(intent);
                finish(); // Close the QuestionActivity
            }
        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }
}
