package com.example.lab_11;

import org.junit.Test;
import com.example.lab_11.DBHandler;
import com.example.lab_11.MainActivity.*;
import com.example.lab_11.GameScreen;
import com.example.lab_11.GameMenu;
import static com.example.lab_11.Constants.SinjajaSvita;
import static com.example.lab_11.Constants.ZalatyPtah;

import static org.junit.Assert.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  /*  @Test
    public void databaseCreatedTest() {
        assertFalse(MainActivity.dbHandler.checkDataBase());
    }

    @Test
    public void getStageClassTest() {
        SQLiteDatabase db = MainActivity.dbHandler.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from stories ", null );
        res.moveToFirst();
        StageClass sc_tested = MainActivity.dbHandler.getStageClass(res, ZalatyPtah);
        StageClass sc = new StageClass();
        sc.setID(0);
        sc.setText("Сардэчна запрашаем вас у падарожжа па сюжэце беларускай народнай казкі “Залаты птах”\n");
        sc.setNumChoices(1);
        sc.setChoice("Пазнаёміцца з сынамі", 0);
        sc.setLink(1, 0);
        sc.setImage("pic5");
        assertEquals(sc, sc_tested);
    }*/

    @Test
    public void stageClassTest() {
        StageClass sc = new StageClass();
        sc.setID(0);
        sc.setText("abc");
        sc.setNumChoices(2);
        sc.setChoice("ab", 0);
        sc.setChoice("c", 1);
        sc.setLink(1, 0);
        sc.setLink(2, 1);
        sc.setImage("picture");
        assertEquals(sc.getChoice(0), "ab");
        assertEquals(sc.getChoice(1), "c");
        assertEquals(sc.getLink(0), 1);
        assertEquals(sc.getLink(1), 2);
    }

}