package com.example.breakingbadcharacters.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object UrlModule {

    @Provides
    fun providesBaseUrl(): String {
        return "https://breakingbadapi.com/api/"
    }

}