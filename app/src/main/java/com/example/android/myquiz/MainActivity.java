package com.example.android.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score = 0;

    EditText questionOneAnswer;
    RadioButton qTwoRbTwo, qFourRbThree, qSixRbTwo;
    CheckBox qThreeCbOne, qThreeCbTwo, qThreeCbThree, qThreeCbFour;
    CheckBox qFiveCbOne, qFiveCbTwo, qFiveCbThree, qFiveCbFour;
    Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        questionOneAnswer = findViewById(R.id.questionOne_answer);
        qTwoRbTwo = findViewById(R.id.qTwoRbTwo);
        qThreeCbOne = findViewById(R.id.qThreeCbOne);
        qThreeCbTwo = findViewById(R.id.qThreeCbTwo);
        qThreeCbThree = findViewById(R.id.qThreeCbThree);
        qThreeCbFour = findViewById(R.id.qThreeCbFour);
        qFourRbThree = findViewById(R.id.qFourRbThree);
        qFiveCbOne = findViewById(R.id.qFiveCbOne);
        qFiveCbTwo = findViewById(R.id.qFiveCbTwo);
        qFiveCbThree = findViewById(R.id.qFiveCbThree);
        qFiveCbFour = findViewById(R.id.qFiveCbFour);
        qSixRbTwo = findViewById(R.id.qSixRbTwo);
        resetBtn = findViewById(R.id.resetBtn);
    }

    //Calculate the total score
    private int getTotalScore() {

        // Question 1 EditText - Correct Answer is (Second Degree)
        EditText questionOneAnswer = findViewById(R.id.questionOne_answer);
        boolean answer1;
        String q1s = questionOneAnswer.getText().toString();

        if (q1s.isEmpty()) {
            return -1;
        }

        answer1 = q1s.equalsIgnoreCase("second degree") || q1s.equals("2nd degree");

        // Question 2 Radio - Correct Answer is 2 (Ligaments)
        Boolean answer2 = qTwoRbTwo.isChecked();

        // Question 3  Checkbox - Correct Answers are 1 (Fingerprints) and 3 (Toeprints)
        Boolean isAnswer3_choice1 = qThreeCbOne.isChecked();
        Boolean isAnswer3_choice2 = qThreeCbTwo.isChecked();
        Boolean isAnswer3_choice3 = qThreeCbThree.isChecked();
        Boolean isAnswer3_choice4 = qThreeCbFour.isChecked();
        Boolean answer3 = isAnswer3_choice1 && isAnswer3_choice3 && !isAnswer3_choice2 && !isAnswer3_choice4;

        // Question 4 RadioButton - Correct Answer is 4 (Sensory Reception)
        Boolean answer4 = qFourRbThree.isChecked();

        // Question 5  Checkbox- Correct Answer is 4 (Hemopoiesis)
        Boolean isAnswer5_choice1 = qFiveCbOne.isChecked();
        Boolean isAnswer5_choice2 = qFiveCbTwo.isChecked();
        Boolean isAnswer5_choice3 = qFiveCbThree.isChecked();
        Boolean isAnswer5_choice4 = qFiveCbFour.isChecked();
        Boolean answer5 = !isAnswer5_choice1 && !isAnswer5_choice3 && !isAnswer5_choice2 && isAnswer5_choice4;

        // Question 6:  RadioButton, Correct Answer 2 (Long Bone)
        Boolean answer6 = qSixRbTwo.isChecked();

        //  Scoring
        if (answer1) {
            score++;
        }

        if (answer2) {
            score++;
        }

        if (answer3) {
            score++;
        }

        if (answer4) {
            score++;
        }

        if (answer5) {
            score++;
        }

        if (answer6) {
            score++;
        }

        return score;
    }

    /*  Submits answers, calculates score */
    public void submitAnswers(View view) {
        int finalScore = getTotalScore();

        if (finalScore < 0) {
            Toast.makeText(this, "Please answer all questions", Toast.LENGTH_SHORT).show();
            score = 0;
            return;
        }

        Button submitButton = findViewById(R.id.submitBtn);
        submitButton.setEnabled(false);
        Toast.makeText(this, String.format(getString(R.string.scoreMessage), finalScore), Toast.LENGTH_LONG).show();
    }

    /* Resets the score to zero */
    public void resetScore(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}