package com.example.lab_11;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.lab_11.Constants.SinjajaSvita;
import static com.example.lab_11.Constants.ZalatyPtah;

public class GameScreen extends AppCompatActivity {
    private ArrayList<StageClass> stageList;
    private String savedata;
    private String gameName;
    private TextView text;
    private final int MAX_CHOICES = 3;
    private TextView[] choiceText = new TextView[MAX_CHOICES];
    private int position = 0;
    private LinearLayout layout;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        layout = findViewById(R.id.gameScreen);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                position = 0;
                gameName = ZalatyPtah;
            }
            else {
                position = extras.getInt("numStage");
                gameName = extras.getString("gameName");
            }
        }
        else {
            position = savedInstanceState.getInt("position");
            gameName = savedInstanceState.getString("gameName");
        }

        stageList = new ArrayList<StageClass>();
        switch (gameName) {
            case ZalatyPtah: {
                savedata = GameMenu.SAVEDATA_ZALATY;
                stageList = MainActivity.dbHandler.getStory(ZalatyPtah);
                break;
            }
            case SinjajaSvita: {
                savedata = GameMenu.SAVEDATA_SINJAJA;
                stageList = MainActivity.dbHandler.getStory(SinjajaSvita);
                break;
            }
            default: {
                savedata = GameMenu.SAVEDATA_ZALATY;
                stageList = MainActivity.dbHandler.getStory(ZalatyPtah);
            }
        }

        text = (TextView) findViewById(R.id.mainText);
        choiceText[0] = (TextView) findViewById(R.id.choice1);
        choiceText[1] = (TextView) findViewById(R.id.choice2);
        choiceText[2] = (TextView) findViewById(R.id.choice3);
        scrollView = (ScrollView)  findViewById(R.id.scrollview);

        updatePage(position);

        choiceText[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText[0].performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                position = stageList.get(position).getLink(0);
                updatePage(position);
            }
        });

        choiceText[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText[1].performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                position = stageList.get(position).getLink(1);
                updatePage(position);
            }
        });

        choiceText[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText[2].performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                position = stageList.get(position).getLink(2);
                updatePage(position);
            }
        });
    }

    @Override
    protected void onPause() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Прагрэс захаваны", Toast.LENGTH_SHORT);
        toast.show();
        super.onPause();
    }

    private void updatePage(@NotNull int position) {
        if (position >= stageList.size()) {
            deleteFile(savedata);
            this.finish();
        }
        else {
            StageClass stage = stageList.get(position);
            getIntent().putExtra("numStage", position);
            saveGame(position);
            int id = this.getResources().getIdentifier(stage.getImage(), "drawable", this.getPackageName());
            Drawable drawable = this.getResources().getDrawable(id);
            layout.setBackground(drawable);

            if (stage.getText() != null)
                text.setText(stage.getText());
            scrollView.scrollTo(0, 0);

            for (int i = 0; i < stage.getNumChoices(); i++) {
                if (stage.getChoice(i) != null) {
                    choiceText[i].setText(stage.getChoice(i));
                    choiceText[i].setEnabled(true);
                    choiceText[i].setVisibility(TextView.VISIBLE);
                }
            }
            for (int i = stage.getNumChoices(); i < choiceText.length; i++) {
                choiceText[i].setText("");
                choiceText[i].setEnabled(false);
                choiceText[i].setVisibility(TextView.GONE);
            }
        }
    }

    private void saveGame(int position) {
        try {
            FileOutputStream f = openFileOutput(savedata, MODE_PRIVATE);
            String s = Integer.toString(position);
            f.write(s.getBytes());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
