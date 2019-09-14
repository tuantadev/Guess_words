package com.t3h.duoihinhbatchu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Answer> answers;
    private int currentIndex;
    private List<Character> allChar;
    private List<Character> answerButtom;
    private LinearLayout ll1, ll2;
    private int currentIndexChildAnwser = 0;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intis();
        initButtonAnswer();
        filterBottomAnswer();

        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndexChildAnwser = 0;
                currentIndex++;
                ll1.removeAllViews();
                ll2.removeAllViews();
                initButtonAnswer();
                filterBottomAnswer();
                LinearLayout llOne = findViewById(R.id.ll_bottom_1);
                for (int i = 0; i < llOne.getChildCount(); i++) {
                    llOne.getChildAt(i).setVisibility(View.VISIBLE);
                }
                LinearLayout llTwo = findViewById(R.id.ll_bottom_2);
                for (int i = 0; i < llTwo.getChildCount(); i++) {
                    llTwo.getChildAt(i).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initAllChar() {
        allChar = new ArrayList<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            allChar.add(i);
        }
    }

    private void filterBottomAnswer() {
        initAllChar();
        answerButtom = new ArrayList<>();
        for (int i = 0; i < answers.get(currentIndex).getAnswer().length(); i++) {
            answerButtom.add(answers.get(currentIndex).getAnswer().charAt(i));
        }
        int rema = 16 - answerButtom.size();
        Random rd = new Random();
        for (int i = 0; i < rema; i++) {
            answerButtom.add(allChar.get(rd.nextInt(allChar.size())));
        }

        Collections.shuffle(answerButtom);

        LinearLayout llOne = findViewById(R.id.ll_bottom_1);
        for (int i = 0; i < llOne.getChildCount(); i++) {
            Button bt = (Button) llOne.getChildAt(i);
            bt.setText(answerButtom.get(i) + "");
            bt.setOnClickListener(this);
        }
        LinearLayout llTwo = findViewById(R.id.ll_bottom_2);
        for (int i = 0; i < llTwo.getChildCount(); i++) {
            Button bt = (Button) llTwo.getChildAt(i);
            bt.setText(answerButtom.get(i + 8) + "");
            bt.setOnClickListener(this);
        }

        iv.setImageResource(answers.get(currentIndex).getIdImage());

    }

    private void intis() {
        ll1 = findViewById(R.id.ll_1);
        ll2 = findViewById(R.id.ll_2);
        iv = findViewById(R.id.iv_source);
        answers = new ArrayList<>();
        answers.add(new Answer("HOIDONG", R.drawable.hoidong));
        answers.add(new Answer("BAOCAO", R.drawable.baocao));
        answers.add(new Answer("AOMUA", R.drawable.aomua));
        answers.add(new Answer("CANTHIEP", R.drawable.canthiep));
        answers.add(new Answer("CATTUONG", R.drawable.cattuong));
        answers.add(new Answer("CHIEUTRE", R.drawable.chieutre));
        answers.add(new Answer("NAMBANCAU", R.drawable.nambancau));
        Collections.shuffle(answers);

    }

    private void initButtonAnswer() {
        String currentAnswer = answers.get(currentIndex).getAnswer();
        if (currentAnswer.length() > 8) {
            for (int i = 0; i < 8; i++) {
                Button btn = inflateButton();
                ll2.addView(btn);
            }
            ll2.setVisibility(View.VISIBLE);
            for (int i = 0; i < currentAnswer.length() - 8; i++) {
                Button btn = inflateButton();
                ll1.addView(btn);
            }
        } else {
            for (int i = 0; i < currentAnswer.length(); i++) {
                Button btn = inflateButton();
                ll1.addView(btn);
            }
            ll2.setVisibility(View.GONE);
        }
    }

    private Button inflateButton() {
        LayoutInflater inflater = LayoutInflater.from(this);
        return (Button) inflater.inflate(R.layout.layout_button, ll1, false);
    }


    @Override
    public void onClick(View view) {
        view.setVisibility(View.INVISIBLE);
        if (answers.get(currentIndex).getAnswer().length() < 8) {
            ((Button) ll1.getChildAt(currentIndexChildAnwser)).setText(
//                    answers.get(currentIndex).getAnswer().charAt(currentIndexChildAnwser)+""
                    ((Button) view).getText().toString()
            );
            currentIndexChildAnwser++;

            if (currentIndexChildAnwser < answers.get(currentIndex).getAnswer().length()) {
                boolean isCorrect = true;
                for (int i = 0; i < currentIndexChildAnwser; i++) {
                    if (answers.get(currentIndex).getAnswer().charAt(i) != ((Button) ll1.getChildAt(i)).getText().toString().charAt(0)) {
                        isCorrect = false;
                        break;
                    }
                }
                if (isCorrect) {
                    nextTrue();
                }
            }
        } else {

        }

    }

    private void nextTrue() {
        currentIndexChildAnwser = 0;
        currentIndex++;
        ll1.removeAllViews();
        ll2.removeAllViews();
        initButtonAnswer();
        filterBottomAnswer();
        LinearLayout llOne = findViewById(R.id.ll_bottom_1);
        for (int i = 0; i < llOne.getChildCount(); i++) {
            llOne.getChildAt(i).setVisibility(View.VISIBLE);
        }
        LinearLayout llTwo = findViewById(R.id.ll_bottom_2);
        for (int i = 0; i < llTwo.getChildCount(); i++) {
            llTwo.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }
}

