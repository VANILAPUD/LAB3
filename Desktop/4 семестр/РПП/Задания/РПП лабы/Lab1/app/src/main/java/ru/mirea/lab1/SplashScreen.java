package ru.mirea.lab1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        myintent = new Intent(this, MainActivity.class);
        if(savedInstanceState == null)  splashScreen(2000);
    }

    public void splashScreen (final int x) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(myintent);
                finish();
            }
        }).start();
    }

}
