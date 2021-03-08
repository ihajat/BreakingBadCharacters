package com.example.breakingbadcharacters.data.di

import com.example.breakingbadcharacters.data.network.api.BreakingBadCharacterServiceAPI
import com.example.breakingbadcharacters.data.network.mappers.NetworkMapper
import com.example.breakingbadcharacters.data.repository.MainRepository
import com.example.breakingbadcharacters.data.room.db.CharacterDao
import com.example.breakingbadcharacters.data.room.mappers.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        dao: CharacterDao,
        retrofit: BreakingBadCharacterServiceAPI,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(dao, retrofit, cacheMapper, networkMapper)
    }
}


