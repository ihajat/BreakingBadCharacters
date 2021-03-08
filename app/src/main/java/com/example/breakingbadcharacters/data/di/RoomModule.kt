package com.example.breakingbadcharacters.data.di

import android.content.Context
import androidx.room.Room
import com.example.breakingbadcharacters.data.room.db.CharacterDao
import com.example.breakingbadcharacters.data.room.db.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room
            .databaseBuilder(
                context,
                CharacterDatabase::class.java,
                CharacterDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.characterDao()
    }

}
