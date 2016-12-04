package com.ch.circlecountdowntimer;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CircleProgressView circleTimer;
    private int state = 0;
    public static final int START = 1;
    public static final int STOP = 2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);

        circleTimer = (CircleProgressView) findViewById(R.id.circleCountDownTimer);
        circleTimer.setSecond(30);//time
        //circleTimer.setPaintStyle(Paint.Style.FILL);//fill all circle
        circleTimer.setFinishListener(new CircleProgressView.FinishListener() {
            @Override
            public void finish() {
                Toast.makeText(getApplicationContext(), "finish:" + circleTimer.getProgress(), Toast
                        .LENGTH_SHORT).show();
            }
        });
    }

    public void push(View v) {
        switch (state) {
            case START:
                state = STOP;
                circleTimer.stop();
                btn.setText("START");
                btn.setBackgroundColor(Color.GREEN);
                break;
            case STOP:
                state = START;
                circleTimer.start();
                btn.setText("STOP");
                btn.setBackgroundColor(Color.RED);
                break;
            default:
                state = START;
                circleTimer.start();
                btn.setText("STOP");
                btn.setBackgroundColor(Color.RED);
                break;
        }
    }

    public void reset(View v){
        circleTimer.reset();
    }
    public void speedUp(View v){
        circleTimer.speedUp();
    }
    public void speedDown(View v){
        circleTimer.speedDown();
    }
}
