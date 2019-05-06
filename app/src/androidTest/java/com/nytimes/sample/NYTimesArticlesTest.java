package com.nytimes.sample;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import com.nytimes.sample.ui.news.NYNewsMainActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.os.SystemClock.sleep;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class NYTimesArticlesTest {


    @Before
    public void launchScreen() {
        ActivityScenario.launch(NYNewsMainActivity.class);
        sleep(8000);
    }

    @Test
    public void initialItemClicked_toLaunchDetailScreen() {
        initialItemClicked();
    }


    @Test
    public void detailScreen_navigationUpCheck() {
        initialItemClicked();
        onView(withContentDescription("Navigate up"))
                .perform(click());
        onView(withId(R.id.newsRecyclerView)).check(matches(isDisplayed()));

    }


    @Test
    public void scrollTo12Item_toPerformLazyLoading() {
        onView(withId(R.id.newsRecyclerView))
                .perform(RecyclerViewActions.scrollToPosition(12));
        sleep(6000);
        onView(withId(R.id.newsRecyclerView))
                .perform(RecyclerViewActions.scrollToPosition(22));

    }


    @Test
    public void itemPullToRefresh_check() {
        onView(withId(R.id.pullToRefresh)).perform(swipeDown());
    }


    @Test
    public void detailScreenShare_actionCheck() {
        initialItemClicked();
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Share")).perform(click());
    }


    private void initialItemClicked() {
        onView(withId(R.id.newsRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }


}
