package com.oasis.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AndroidQuestionActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton;

    private String[] questions = {
            "Which programming language is often used for developing Android apps?",
            "What is the name of the Android mascot?",
            "What is the name of the build system used in Android Studio?",
            "What is the name of the Android virtual device emulator?",
            "What is the name of the Android SDK tool for inspecting your app's layout?",
    };

    private String[] correctAnswers = {
            "a) Java",
            "b) Bugdroid",
            "c) Gradle",
            "d) AVD Manager",
            "e) Layout Inspector",
    };

    String[][] options = {
            {
                    "a) Java",
                    "b) Python",
                    "c) C++",
                    "d) JavaScript"
            },
            {
                    "a) Androidy",
                    "b) Bugdroid",
                    "c) Droidy",
                    "d) Andy"
            },
            {
                    "a) Maven",
                    "b) Ant",
                    "c) Gradle",
                    "d) Make"
            },
            {
                    "a) Android Emulator",
                    "b) Android Simulator",
                    "c) Android Virtual Device",
                    "d) AVD Manager"
            },
            {
                    "a) Layout Editor",
                    "b) Layout Viewer",
                    "c) Layout Manager",
                    "d) Layout Inspector"
            },
    };

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

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
