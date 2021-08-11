package com.example.pokemonracing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SeekBar sbrPikachu, sbrCharmander, sbrPsyduck;
    Button btnStart;
    TextView txtThongbao, txtScore;
    RadioGroup RadioGr;
    RadioButton rbtnPikachu, rbtnCharmander, rbtnPsyduck;

    int time_ms = 1;
    int score = 10;
    boolean startIsClicked = false, gameOver = false;
    boolean pikachuWin = false, charmanderWin = false, psyduckWin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        reset();
        Toast.makeText(MainActivity.this, "Xoay ngang màn hình ra cho đẹp :v", Toast.LENGTH_LONG).show();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startIsClicked == false) {
                    btnStart.setText("Play again");
                    startIsClicked = true;
                    runPokemon(time_ms);
                }
                else if (gameOver) {
                    reset();
                    btnStart.setText("start");
                    startIsClicked = false;
                    gameOver = false;
                }
            }
        });
    }

    private void runPokemon(int time_ms) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sbrCharmander.setProgress(sbrCharmander.getProgress() + randomSpeedS());
                sbrPikachu.setProgress(sbrPikachu.getProgress() + randomSpeedS());
                sbrPsyduck.setProgress(sbrPsyduck.getProgress() + randomSpeedS());
                String toastStr = "";
                if (sbrPikachu.getProgress() >= sbrPikachu.getMax()) {
                    pikachuWin = true;
                    toastStr += "Pikachu ";
                }
                else if (sbrCharmander.getProgress() >= sbrPikachu.getMax()) {
                    charmanderWin = true;
                    toastStr += "Charmander ";
                }
                else if (sbrPsyduck.getProgress() >= sbrPikachu.getMax()) {
                    psyduckWin = true;
                    toastStr += "Psyduck ";
                }
                toastStr += "win!!!";

                if (pikachuWin == false && charmanderWin == false && psyduckWin == false) runPokemon(time_ms);
                else {
                    Toast.makeText(MainActivity.this, toastStr, Toast.LENGTH_SHORT).show();
                    correctAns();
                    txtScore.setText("Score: " + score);
                    gameOver = true;
                }
            }
        }, time_ms);
    }
    private int randomSpeedS() {
        Random random = new Random();
        int max = 15, min = 1;
        int speed = random.nextInt(max - min + 1) + min;
        return speed;
    }
    private void reset() {
        pikachuWin = false;
        charmanderWin = false;
        psyduckWin = false;
        sbrPikachu.setMax(1000);
        sbrCharmander.setMax(1000);
        sbrPsyduck.setMax(1000);
        sbrPikachu.setProgress(30);
        sbrCharmander.setProgress(30);
        sbrPsyduck.setProgress(30);
        txtThongbao.setText("Choose a pokemon!");
    }
    private void thongbao(String toastStr) {
        txtThongbao.setAllCaps(true);
        txtThongbao.setText(toastStr);
    }
    private void anhXa() {
        sbrPikachu = findViewById(R.id.sbrPikachu);
        sbrCharmander = findViewById(R.id.sbrCharmander);
        sbrPsyduck = findViewById(R.id.sbrPsyduck);
        btnStart = findViewById(R.id.btnStart);
        txtThongbao = findViewById(R.id.txtThongBao);
        RadioGr = findViewById(R.id.radioGroup);
        rbtnPikachu = findViewById(R.id.rbtnPikachu);
        rbtnCharmander = findViewById(R.id.rbtnCharmander);
        rbtnPsyduck = findViewById(R.id.rbtnPsyduck);
        txtScore = findViewById(R.id.txtScore);
    }
    private void correctAns() {
        switch (RadioGr.getCheckedRadioButtonId()) {
            case R.id.rbtnPikachu: {
                if (pikachuWin) {
                    txtThongbao.setText("You are right!!! +2 points");
                    score += 2;
                }
                else {
                    txtThongbao.setText("You are wrong!!! -1 point");
                    score -= 1;
                }
                break;
            }
            case R.id.rbtnCharmander: {
                if (charmanderWin) {
                    txtThongbao.setText("You are right!!! +2 points");
                    score += 2;
                }
                else {
                    txtThongbao.setText("You are wrong!!! -1 point");
                    score -= 1;
                }
                break;
            }
            case R.id.rbtnPsyduck: {
                if (psyduckWin) {
                    txtThongbao.setText("You are right!!! +2 points");
                    score += 2;
                }
                else {
                    txtThongbao.setText("You are wrong!!! -1 point");
                    score -= 1;
                }
                break;
            }
        }
    }
}