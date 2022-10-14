package com.example.myapplication;

import static android.content.ContentValues.TAG;
import static com.example.myapplication.R.id.btn_save_nom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.xml.transform.Result;

public class random_number extends AppCompatActivity {
    ImageButton ball1, ball2, ball3, ball4, ball5, ball6;
    Button random_num, save_num, reset, btnJoinOk;
    TextView tx1, tx2, tx3, tx4, tx5, tx6, tx7,
            tx8, tx9, tx10, tx11, tx12, tx13;
    String one, two, three, four, five, six, result;


    //SQLITE 데이타베이스 관련변수
    SQLiteDatabase db;
    MySQLHelper helper;

    String TAG = "회원가입 예제";

    //Handler mhandler = new Handler();

    //    String round = "";
    String num1 = "";
    String num2 = "";
    String num3 = "";
    String num4 = "";
    String num5 = "";
    String num6 = "";

    //CountDownTimer countDownTimer;

    //Integer arr_random_num[] = new Integer[6];


    HashSet<String> lottoSet = new HashSet<String>();
    public static ArrayList<String> arrList = new ArrayList<String>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //데이베이스 생성.
        helper = new MySQLHelper(
                random_number.this, // 현재 화면의 context
                "member3.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        //db = helper.getWritableDatabase(); // db 객체를 얻어온다. 쓰기 가능


        setContentView(R.layout.activity_random_number);

        ball1 = (ImageButton) findViewById(R.id.frist_ball);
        ball2 = (ImageButton) findViewById(R.id.second_ball);
        ball3 = (ImageButton) findViewById(R.id.thrid_ball);
        ball4 = (ImageButton) findViewById(R.id.fourth_ball);
        ball5 = (ImageButton) findViewById(R.id.fith_ball);
        ball6 = (ImageButton) findViewById(R.id.sixth_ball);

        random_num = (Button) findViewById(R.id.createNum);
        save_num = (Button) findViewById(btn_save_nom);
        reset = (Button) findViewById(R.id.btn_reset);

        // 로또번호 표시될 텍스트뷰
        tx1 = (TextView) findViewById(R.id.frist_ball_tx);
        tx2 = (TextView) findViewById(R.id.second_ball_tx);
        tx3 = (TextView) findViewById(R.id.thrid_ball_tx);
        tx4 = (TextView) findViewById(R.id.fourth_ball_tx);
        tx5 = (TextView) findViewById(R.id.fith_ball_tx);
        tx6 = (TextView) findViewById(R.id.sixth_ball_tx);

        // 회차에 적용될 로또번호
        tx7 = (TextView) findViewById(R.id.round1_number);
//        tx8 = (TextView) findViewById(R.id.round2_number);
//        tx9 = (TextView) findViewById(R.id.round3_number);
//        tx10 = (TextView) findViewById(R.id.round4_number);
//        tx11 = (TextView) findViewById(R.id.round5_number);
//        tx12 = (TextView) findViewById(R.id.round6_number);
//        tx13 = (TextView) findViewById(R.id.round7_number);
//        tx14 = (TextView) findViewById(R.id.round8_number);
//        tx15 = (TextView) findViewById(R.id.round9_number);
//        tx16 = (TextView) findViewById(R.id.round10_number);


        //로또생성 버튼, 음악재생버튼, 숫자 딜레이 포함
        Button createNum = findViewById(R.id.createNum);
        createNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                MediaPlayer m = MediaPlayer.create(c, R.raw.btnbgm);
                m.start();

                m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                //로또번호 생성뷰 초기화
                tx1.setText("");
                tx2.setText("");
                tx3.setText("");
                tx4.setText("");
                tx5.setText("");
                tx6.setText("");

                while (lottoSet.size() < 7) {
                    int num = (int) (Math.random() * 45);
                    Log.d("Main", String.valueOf(num));
                    if (num != 0) {
                        lottoSet.add(num + "");
                    }

                }

                //set을 list에 담기
                random_number.arrList.addAll(lottoSet);

                num1 = random_number.arrList.get(0);
                num2 = random_number.arrList.get(1);
                num3 = random_number.arrList.get(2);
                num4 = random_number.arrList.get(3);
                num5 = random_number.arrList.get(4);
                num6 = random_number.arrList.get(5);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tx1.setText(num1);
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tx2.setText(num2);
                    }
                }, 5000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tx3.setText(num3);
                    }
                }, 8000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tx4.setText(num4);
                    }
                }, 11000);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tx5.setText(num5);
                    }
                }, 14000);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tx6.setText(num6);
                    }
                }, 16000);

                //초기화
                lottoSet.clear();
                arrList.clear();

            }

        });


        //번호저장
        Button save_num = findViewById(btn_save_nom);
        save_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_save_nom:
                        //공백체크
                        if (tx1.getText().toString().equals("번호")) {
                            Toast.makeText(random_number.this, "번호를 생성해주세요", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        //공백없이 입력잘되었을경우 변수에 저장
                        one = tx1.getText().toString();

                        if (tx2.getText().toString().equals("번호")) {
                            Toast.makeText(random_number.this, "번호를 생성해주세요", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        //공백없이 입력잘되었을경우 변수에 저장
                        two = tx2.getText().toString();

                        if (tx3.getText().toString().equals("번호")) {
                            Toast.makeText(random_number.this, "번호를 생성해주세요", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        //공백없이 입력잘되었을경우 변수에 저장
                        three = tx3.getText().toString();

                        if (tx4.getText().toString().equals("번호")) {
                            Toast.makeText(random_number.this, "번호를 생성해주세요", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        //공백없이 입력잘되었을경우 변수에 저장
                        four = tx4.getText().toString();

                        if (tx5.getText().toString().equals("번호")) {
                            Toast.makeText(random_number.this, "번호를 생성해주세요", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        //공백없이 입력잘되었을경우 변수에 저장
                        five = tx5.getText().toString();

                        if (tx6.getText().toString().equals("번호")) {
                            Toast.makeText(random_number.this, "번호를 생성해주세요", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        //공백없이 입력잘되었을경우 변수에 저장
                        six = tx6.getText().toString();

                        //회원정보를 다 입력하였을경우 데이타베이스에 insert를 호출
                        insert(one, two, three, four, five, six);

                        //회원가입 후 저장정보 확1인하기.
                        //Intent intentJoinOk = new Intent(random_num_save.this, random_number.class);
                        //intentJoinOk.putExtra("one", one);
                        //intentJoinOk.putExtra("two", two);
                        //intentJoinOk.putExtra("three", three);
                        //intentJoinOk.putExtra("four", four);
                        //intentJoinOk.putExtra("five", five);
                        //intentJoinOk.putExtra("six", six);
                        //startActivity(intentJoinOk);
                        //finish();
                        selectAll();
                        break;


                }

            }
            //데이타베이스 메서드 처리  ////////////////////////////
            public void insert(String one, String two, String three, String four, String five, String six) {

                db = helper.getWritableDatabase(); // db 객체를 얻어온다. 쓰기 가능

                //값들을 컨트롤 하려고 클래스 생성
                ContentValues values = new ContentValues();

                // db.insert의 매개변수인 values가 ContentValues 변수이므로 그에 맞춤
                // 데이터의 삽입은 put을 이용한다.
                values.put("one", one);
                values.put("two", two);
                values.put("three", three);
                values.put("four", four);
                values.put("five", five);
                values.put("six", six);
                db.insert("member2", null, values); // 테이블/널컬럼핵/데이터(널컬럼핵=디폴트)

                // tip : 마우스를 db.insert에 올려보면 매개변수가 어떤 것이 와야 하는지 알 수 있다.
                db.close();
                Toast.makeText(getApplicationContext(), "번호저장완료.", Toast.LENGTH_LONG).show();

                Log.d(TAG, one + "/" + two + "/" + three + "/" + four + "/" + five + "/" + six + " 의 정보로 디비저장완료.");
            }
            // 회원목록전체조회
            public void selectAll() {

                // 1) db의 데이터를 읽어와서, 2) 결과 저장, 3)해당 데이터를 꺼내 사용
                db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
                Cursor c = db.rawQuery("SELECT * FROM member2 order by idx desc", null);

                String Result = "";
                while (c.moveToNext()) {
                    int idx = c.getInt(0);
                    String one = c.getString(1);
                    String two = c.getString(2);
                    String three = c.getString(3);
                    String four = c.getString(4);
                    String five = c.getString(5);
                    String six = c.getString(6);

                    Result += one + " / " + two + " / " + three + " / " + four + " / " + five + " / " + six + "\n";
                }
                //tx7.setText(Result);
                tx7.setText(Result);
                c.close();
                db.close();
            }
        });


        //번호초기화
        Button resetnum = findViewById(R.id.btn_reset);
        resetnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tx1.setText("번호");
                tx2.setText("번호");
                tx3.setText("번호");
                tx4.setText("번호");
                tx5.setText("번호");
                tx6.setText("번호");
                tx7.setText("당첨번호");
//                tx8.setText("당첨번호");
//                tx9.setText("당첨번호");
//                tx10.setText("당첨번호");
//                tx11.setText("당첨번호");
//                tx12.setText("당첨번호");
//                tx13.setText("당첨번호");
//                tx14.setText("당첨번호");
//                tx15.setText("당첨번호");
//                tx16.setText("당첨번호");



            }

        });


    }


}


