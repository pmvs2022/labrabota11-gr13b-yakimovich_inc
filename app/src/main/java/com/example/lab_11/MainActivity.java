package com.example.lab_11;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.lab_11.Constants.SinjajaSvita;
import static com.example.lab_11.Constants.ZalatyPtah;

public class MainActivity extends AppCompatActivity {
    private TextView textZalatyPtah;
    private TextView textSinjajaSvita;
    public static DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        textZalatyPtah = (TextView) findViewById(R.id.firstGame);
        textZalatyPtah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textZalatyPtah.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent intent = new Intent(getApplicationContext(), GameMenu.class);
                intent.putExtra("gameName", ZalatyPtah);
                startActivity(intent);
            }
        });

        textSinjajaSvita = (TextView) findViewById(R.id.secondGame);
        textSinjajaSvita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSinjajaSvita.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent intent = new Intent(getApplicationContext(), GameMenu.class);
                intent.putExtra("gameName", SinjajaSvita);
                startActivity(intent);
            }
        });

        dbHandler = new DBHandler(MainActivity.this);
    }
}
