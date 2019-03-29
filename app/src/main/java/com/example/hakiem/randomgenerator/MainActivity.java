package com.example.hakiem.randomgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    int count=0;
    int out;
    Random r;
    Thread tred;
    TextView tex1, tex2, tex3;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

        tex1 = (TextView) findViewById(R.id.angka1);
        tex2 = (TextView) findViewById(R.id.angka2);
        tex3 = (TextView) findViewById(R.id.angka3);

        btn1 = (Button) findViewById(R.id.start);

        btn2 = (Button) findViewById(R.id.stop);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                if(tred==null||tred.getState()==Thread.State.TERMINATED){

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            try{

                                while (true){
                                    tex1.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            final int nilai= (int) (Math.random()*10);
                                            tex1.setText(String.valueOf(nilai));
                                            tex2.setText(String.valueOf(nilai));
                                            tex3.setText(String.valueOf(nilai));
                                        }
                                    });
                                    Thread.sleep(500);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    tred = new Thread(runnable);
                    tred.start();
                }
                break;

            case R.id.stop:
                if(tred!=null||tred.getState()!=Thread.State.TERMINATED){
                    tred.interrupt();
                }
        }

    }
}
