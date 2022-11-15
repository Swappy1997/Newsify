package com.example.newsify.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.newsify.R;
import com.example.newsify.fragment.LoginUsingPhone;

public class SplashScreen extends AppCompatActivity {
    View first, second, third, fourth, fifth, sixth;
    AppCompatImageView logo;
    Handler handler;
    Animation TopAnimation, BottomAnimation, MiddleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        logo = findViewById(R.id.splash_screen_logo);
        TopAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        BottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        MiddleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        logo.setAnimation(MiddleAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                boolean check = pref.getBoolean("flag", false);
                Intent nextintent;
                if (check) {
                    nextintent = new Intent(SplashScreen.this, MainActivity.class);

                } else {
                    nextintent = new Intent(SplashScreen.this, LoginActivity.class);

                }
                startActivity(nextintent);
                finish();
            }
        }, 2000);
    }
}
