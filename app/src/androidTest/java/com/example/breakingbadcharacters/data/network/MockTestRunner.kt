package com.example.breakingbadcharacters.data.network

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class MockTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(
            classLoader,
            // Use the test application provided by Hilt
            HiltTestApplication::class.java.name,
            context
        )
    }
}