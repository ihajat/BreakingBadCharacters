package com.example.breakingbadcharacters.presentation.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.presentation.ui.MainAdapter.MainViewHolder
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : BaseActivityTest() {

    /*
    recyclerview comes into view
     */
    @Test
    fun recyclerview_comes_into_view() {
        Espresso.onView(withId(R.id.recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /*
    select item, right data in view
     */
    @Test
    fun select_item_right_data_in_view() {
        Espresso.onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MainViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(withId(R.id.bbccharacter_name))
            .check(ViewAssertions.matches(ViewMatchers.withText("Name: Walter White")))
    }

    /*
    select item, select back, back to recyclerview
     */
    @Test
    fun select_item_select_back_to_recyclerview() {

        Espresso.onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MainViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.pressBack()
        Espresso.onView(withId(R.id.recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}