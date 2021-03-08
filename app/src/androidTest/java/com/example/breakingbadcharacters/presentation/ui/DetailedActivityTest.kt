package com.example.breakingbadcharacters.presentation.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.breakingbadcharacters.R
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class DetailedActivityTest : BaseActivityTest() {

    /*
    select item, right data in view
     */
    @Test
    fun select_item_right_data_in_view() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MainAdapter.MainViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.bbccharacter_name)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Name: Walter White")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.bbccharacter_occupation)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Occupation: High School Chemistry Teacher Meth King Pin")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.bbccharacter_status)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Status: Presumed dead")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.bbccharacter_nickname)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Nickname: Heisenberg")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.bbccharacter_season_appearance)).check(
            ViewAssertions.matches(ViewMatchers.withText("Season Appearance: 1,2,3,4,5"))
        )
    }
}