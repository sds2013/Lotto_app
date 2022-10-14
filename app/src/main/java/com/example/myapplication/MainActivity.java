package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation ani1;
    ImageView img1;
    Button btnplay, btn_winner_search;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = (ImageView) findViewById(R.id.lottoimg);
        ani1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale2);
        img1.startAnimation(ani1);
        btnplay = (Button)findViewById(R.id.btnplay);
        btn_winner_search = (Button) findViewById(R.id.btn_winner_search);
        findViewById(R.id.btnplay).setOnClickListener(btnclick);
        findViewById(R.id.btn_winner_search).setOnClickListener(btnclick);
    }

    View.OnClickListener btnclick = new View.OnClickListener()
     {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btnplay:
                    Intent next1 = new Intent(MainActivity.this,random_number.class);
                    startActivity(next1);
                    break;
                case R.id.btn_winner_search:
                    Intent next2 = new Intent(MainActivity.this, winner_list.class);
                    startActivity(next2);
                    break;

            }
        }
    };
}