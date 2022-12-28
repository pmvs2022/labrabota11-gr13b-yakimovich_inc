package com.example.lab_11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static com.example.lab_11.Constants.SinjajaSvita;
import static com.example.lab_11.Constants.ZalatyPtah;

public class GameMenu extends AppCompatActivity {

    public static final String SAVEDATA_ZALATY = "saveDataZalaty.txt";
    public static final String SAVEDATA_SINJAJA = "saveDataSinjaja.txt";
    private TextView textNewGame;
    private TextView textContinue;
    private String[] files;
    private String savedata;
    private String backgroundName;
    private String gameName;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.game_menu);
        layout = (ConstraintLayout) findViewById(R.id.gameMenuLayout);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null)
                gameName = ZalatyPtah;
            else
                gameName = extras.getString("gameName");
        }
        else {
            gameName = savedInstanceState.getString("gameName");
        }

        switch (gameName) {
            case ZalatyPtah: {
                savedata = SAVEDATA_ZALATY;
                backgroundName = "pic7";
                break;
            }
            case SinjajaSvita: {
                savedata = SAVEDATA_SINJAJA;
                backgroundName = "pic10";
                break;
            }
        }

        int id = this.getResources().getIdentifier(backgroundName, "drawable", this.getPackageName());
        Drawable drawable = this.getResources().getDrawable(id);
        layout.setBackground(drawable);

        textNewGame = (TextView) findViewById(R.id.newGame);
        textNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNewGame.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                startNewGame();
                intent.putExtra("numStage", 0);
                intent.putExtra("gameName", gameName);
                startActivity(intent);
            }
        });

        textContinue = (TextView) findViewById(R.id.contin);
        onUiUpdate();
        textContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textContinue.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                intent.putExtra("numStage", readSaveFiles());
                intent.putExtra("gameName", gameName);
                startActivity(intent);
            }
        });

    }

    private void onUiUpdate() {
        files = fileList();
        if (textContinue != null) {
            textContinue.setEnabled(false);
            textContinue.setTextColor(0x80000000);
            for (int i = 0; i < files.length; i++) {
                if (files[i].equals(savedata)) {
                    textContinue.setEnabled(true);
                    textContinue.setTextColor(0xDD000000);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onUiUpdate();
    }

    private int readSaveFiles() {
        files = fileList();
        Scanner scanner;
        for (int i = 0; i < files.length; i++) {
            if (files[i].equals(savedata)) {
                try {
                    scanner = new Scanner(openFileInput(savedata));
                    return Integer.parseInt(scanner.next());
                } catch (FileNotFoundException e) {
                }
            }
        }
        return 0;
    }

    private void startNewGame() {
        try {
            FileOutputStream f = openFileOutput(savedata, MODE_PRIVATE);
            String s = "0";
            f.write(s.getBytes());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

