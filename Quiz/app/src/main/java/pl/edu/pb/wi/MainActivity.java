package pl.edu.pb.wi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonTrue;
    private Button buttonFalse;
    private Button buttonNext;
    private TextView questionTextView;

    private int currentIndex = 0;

    private Question[] questions = new Question[]{
            new Question(R.string.q_android, false),
            new Question(R.string.q_pb, true),
            new Question(R.string.q_apple, true),
            new Question(R.string.q_debil, true),
            new Question(R.string.q_mobilki, false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTrue = findViewById(R.id.button_true);
        buttonFalse = findViewById(R.id.button_false);
        buttonNext = findViewById(R.id.button_next);
        questionTextView = findViewById(R.id.question_text_view);

        buttonTrue.setOnClickListener(v -> checkAnswer(true));

        buttonFalse.setOnClickListener(v -> checkAnswer(false));

        buttonNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % questions.length;
            setNextQuestion();
        });

        setNextQuestion();
    }

    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    private void checkAnswer(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (correctAnswer == userAnswer){
            resultMessageId = R.string.correct_answer;
        }else {
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }
}