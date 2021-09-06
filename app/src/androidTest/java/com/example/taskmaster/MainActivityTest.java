package com.example.taskmaster;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertEquals;


    @RunWith(AndroidJUnit4.class)
    @LargeTest
    public class MainActivityTest {
        @Test
        public void useAppContext() {
            // Context of the app under test.
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            assertEquals("com.example.taskmaster", appContext.getPackageName());

        }
        @Rule
        public ActivityScenarioRule<MainActivity> settingsRule =
                new ActivityScenarioRule<>(MainActivity.class);
        @Test
        public void checkHomeView(){
            onView(withId(R.id.textView)).check(matches(withText(" My Taskes")));

        }
        @Test
        public void settingsButton(){
            onView(withId(R.id.goToSettingId)).perform(click());

        }
        @Test
        public void recyclerTest(){
            onView(withId(R.id.taskRecyclerView)).perform(click());

        }
        @Test
        public void adapterTest(){
            onView(allOf(withId(R.id.taskTitelId),withText("lab28"))).perform(click());

        }
    }

