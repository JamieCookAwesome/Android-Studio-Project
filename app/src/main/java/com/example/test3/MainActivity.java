package com.example.test3;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etTime;
    private Button btnStart;
    private TextView tvCountdown;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTime = findViewById(R.id.et_time);
        btnStart = findViewById(R.id.btn_start);
        tvCountdown = findViewById(R.id.tv_countdown);

        btnStart.setOnClickListener(v -> {
            String input = etTime.getText().toString();
            if (TextUtils.isEmpty(input)) {
                Toast.makeText(this, "Please enter a valid time!", Toast.LENGTH_SHORT).show();
                return;
            }

            int timeInSeconds = Integer.parseInt(input);
            startTimer(timeInSeconds * 1000); // Convert seconds to milliseconds
        });
    }

    private void startTimer(long duration) {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Cancel any existing timer
        }

        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                // Update the TextView
                tvCountdown.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                tvCountdown.setText("00:00");
                Toast.makeText(MainActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                //insert sound playing here
            }
        }.start();
    }
}