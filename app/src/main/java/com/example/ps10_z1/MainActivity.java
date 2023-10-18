package com.example.ps10_z1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ps10_z1.R;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private int currentIndex = 0;
    private boolean questionAnswered = false;
    private int correctAnswers = 0;
    private Question[] questions = new Question[]{
      new Question(R.string.q_activity,true),
      new Question(R.string.q_find_resources,false),
      new Question(R.string.q_listener,true),
      new Question(R.string.q_resources,true),
      new Question(R.string.q_version,false),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        setNextQuestion();

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                if(currentIndex == questions.length){
                    showCorrectAnswersAmount();
                    currentIndex = 0;
                } else {
                    setNextQuestion();
                }


            }
        });
    }

    private void checkAnswerCorrectness(boolean userAnswer){
        if(questionAnswered){
            Toast.makeText(this, R.string.question_answered, Toast.LENGTH_SHORT).show();
            return;
        }
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer){
            correctAnswers++;
            resultMessageId = R.string.correct_answer;
        } else {
            correctAnswers--;
            resultMessageId = R.string.incorrect_answer;
        }

        questionAnswered = true;
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }
    private void showCorrectAnswersAmount(){

        Toast.makeText(this,"Twój wynik : "+correctAnswers +"/"+questions.length,Toast.LENGTH_SHORT).show();
    }
    private void setNextQuestion(){
        questionAnswered = false;
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }
}