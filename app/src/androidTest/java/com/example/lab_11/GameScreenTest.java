package com.example.lab_11;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GameScreenTest {

    @Rule
    public ActivityScenarioRule<GameScreen> rule = new ActivityScenarioRule<GameScreen>(GameScreen.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("com.example.lab_11", appContext.getPackageName());
    }

    @Test
    public void choice1Test() {
        onView(withId(R.id.choice1)).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void choice2Test() {
        onView(withId(R.id.choice2)).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void choice3Test() {
        onView(withId(R.id.choice3)).check(matches(isDisplayed())).perform(click());
    }

}