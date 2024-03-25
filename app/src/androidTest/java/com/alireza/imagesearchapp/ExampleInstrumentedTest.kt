package com.alireza.imageSearchApp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest

import org.junit.Test

import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
class ExampleInstrumentedTest {
    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setup() {
        hiltTestRule.inject()
    }

    @Test
    fun articles_list_is_displayed_successfully() = runTest {

        composeTestRule.onNodeWithTag("ImageLazyVerticalGrid").assertIsDisplayed()

    }

}
