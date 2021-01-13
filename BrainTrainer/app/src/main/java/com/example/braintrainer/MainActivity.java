package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton ;
    TextView resTextView ;
    TextView sumTextView ;
    TextView scoreTextView ;
    Button button0;
    Button button1 ;
    Button button2 ;
    Button button3 ;
    ArrayList<Integer> options=new ArrayList<Integer>();
    int correctAnsLocation ;
    int score=0;
    int numQues=0 ;
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
    }
    public void generateQues(){

        Random rand = new Random();
        int a=rand.nextInt(100) ;
        int b=rand.nextInt(100) ;
        sumTextView.setText(String.valueOf(a)+"+"+String.valueOf(b));
        correctAnsLocation =rand.nextInt(4);
        options.clear();
        for(int i=0 ; i<4 ;++i){
            if(i==correctAnsLocation){
                options.add(a+b);
            }
            else{
                int incorrectAns = rand.nextInt(200);
                while(incorrectAns==a+b){
                    incorrectAns=rand.nextInt(200);
                }
                options.add(incorrectAns);
            }
        }
        button0.setText(Integer.toString(options.get(0)));
        button1.setText(Integer.toString(options.get(1)));
        button2.setText(Integer.toString(options.get(2)));
        button3.setText(Integer.toString(options.get(3)));
    }
    public void chooseAns(View view){
        if(view.getTag().toString().equals(Integer.toString(correctAnsLocation))){
           score++ ;
            resTextView.setText("CORRECT!");
        }
        else {
            resTextView.setText("WRONG!");
        }
        ++numQues ;
        scoreTextView.setText(String.valueOf(score)+"/"+String.valueOf(numQues));
        generateQues();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button) findViewById(R.id.startButton);
        sumTextView=(TextView) findViewById(R.id.sumTextView);
        scoreTextView=(TextView) findViewById(R.id.scoreTextView);
        button0=(Button) findViewById(R.id.button0);
         button1=(Button) findViewById(R.id.button1);
         button2=(Button) findViewById(R.id.button2);
         button3=(Button) findViewById(R.id.button3);
        resTextView=(TextView) findViewById(R.id.resTextView);
        TextView timerTextView =(TextView) findViewById(R.id.timerTextView);
        generateQues();
        new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resTextView.setText("TIME'S UP!");
            }
        }.start();
    }
}