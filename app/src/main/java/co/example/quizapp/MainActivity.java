package co.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //final int USER_PROGRESS = 10;

    Button btnWrong;
    Button btnTrue;

    private TextView mTxtQuestion ;

    private int mQuestionIndex;
    private int mQuizQuestion;

    private ProgressBar mProgressBar;
    private TextView mQuizStatsTextView;

    private int mUserScore;

    private QuizModel[] questionCollection = new QuizModel[]{

            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false),

    };

    final int USER_PROGRESS = (int) Math.ceil ( 100.0 / questionCollection.length );






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtQuestion=findViewById(R.id.txtQuestion); //bunları set content viewdan sonra initilazie etmemiz önemli olan,

        QuizModel q1= questionCollection[mQuestionIndex];

        mQuizQuestion = q1.getmQuestion();

        mTxtQuestion.setText(mQuizQuestion);

        mProgressBar = findViewById(R.id.quizPB);
        mQuizStatsTextView=findViewById(R.id.txtQuizStats);


        btnTrue = findViewById(R.id.btnTrue);
        btnWrong = findViewById(R.id.btnWrong);

        /* java.lang.object
            android.view.View
                android.widget.TextView
                    android.widget.Button
                    (button ease textview... button indrectyl object..) */

        View.OnClickListener myClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("Listener","My button is tapped"); // logda gözüksün diye yapıyoruz bu tagde bu mesajı versin

                if(v.getId()==R.id.btnTrue) {
                    //Log.i("MyApp", "btn Ture is tapped");

                    evaluateUsersAnswer(true);
                    changeQuestionOnButtonClick();
                    //yerlerini tamm tersini yazınca indexle sorunlar oluyor önce cevabı al sonra değiştir.


                   // Toast myToastObject = Toast.makeText(getApplicationContext(),"Button true is tapped",Toast.LENGTH_LONG);
                    //myToastObject.show();

                }else if (v.getId()==R.id.btnWrong){

                    evaluateUsersAnswer(false);
                    changeQuestionOnButtonClick();


                    ///Toast.makeText(getApplicationContext(),"Button wrong is tapped",Toast.LENGTH_SHORT).show(); // ananoymus sekilde olusturuldu. We are not assign


                    //Log.i("MyApp","btn Wrong is tapped");


                    }
                }

            };

        btnTrue.setOnClickListener(myClickListener);
        btnWrong.setOnClickListener(myClickListener);

        //bir de tıklanacak sey az ve tek ise anonymus click listener vardır bizim her zaman yaptıgımız

        /*btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {} });*/



        //MCV Model Controller View :) reusable,multitask




    }

    private void changeQuestionOnButtonClick(){



        mQuestionIndex =  (mQuestionIndex + 1) % 10;

        if (mQuestionIndex == 0){

            AlertDialog.Builder quizAlert = new AlertDialog.Builder(MainActivity.this);
            quizAlert.setCancelable(false); // Alertin gösterdiği beyaz dısındaki bos yere tıklayınca sorun olmuyor artık..
            quizAlert.setTitle("The Quiz is Finished");
            quizAlert.setMessage("Your Score is " + mUserScore);
            quizAlert.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish(); // close current application
                }
            });
            quizAlert.show();
        }

        mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion();

        mTxtQuestion.setText(mQuizQuestion);


        mProgressBar.incrementProgressBy(USER_PROGRESS);

        mQuizStatsTextView.setText(String.valueOf(mUserScore));

        // mQuizStatsTextView.setText(mUserScore + "" ); böyle de  oluyor haha:)




    }

    private void evaluateUsersAnswer(boolean userGuess){

        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].ismAnswer();

        if(currentQuestionAnswer == userGuess){

            Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();

            mUserScore = mUserScore + 1 ;

        } else{

            Toast.makeText(getApplicationContext(),R.string.incorrect_toast_message,Toast.LENGTH_SHORT).show();

        }


    }


}
