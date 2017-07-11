package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.acme.a3csci3130.R.id.listView;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleInstrumentedTest {
    ListView list;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }
    @Test
    public void B_createBusinessTest() throws Exception{
        onView(withId(R.id.listView));
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.e_number)).perform(typeText("102938495"));
        onView(withId(R.id.e_name)).perform(typeText("Justin"));
        onView(withId(R.id.e_business)).perform(typeText("Fisher"));
        onView(withId(R.id.e_address)).perform(typeText("5505 University Avenue"));
        onView(withId(R.id.e_province)).perform(typeText("NS"));
        onView(withId(R.id.submitButton)).perform(click());
        //onView(allOf(withText("Justin's Business"))).perform(click());
        //onView(withText("Successfully added to database")).inRoot(withDecorView(not(is(new ActivityTestRule<>(CreateBusinessActivity.class).getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void A_checkData() throws Exception{
        //assertEquals(8, mActivityRule.getActivity().getListViewSize());
        //onData(hasToString(startsWith("Justin"))).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
    }
    @Test
    public void D_deleteBusiness() throws Exception{
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }
    @Test
    public void C_updateBusiness() throws Exception{
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.e_business)).perform(replaceText("Processor"));
        onView(withId(R.id.updateButton)).perform(click());
    }
}
