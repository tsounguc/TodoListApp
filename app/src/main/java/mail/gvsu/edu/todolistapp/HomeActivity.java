package mail.gvsu.edu.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeItent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(homeItent);
                finish();
//                startActivity(new Intent(this, MainActivity.class));
            }
        },SPLASH_TIME_OUT);
    }
}
