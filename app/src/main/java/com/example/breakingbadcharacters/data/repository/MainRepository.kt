package com.example.breakingbadcharacters.data.repository

import com.example.breakingbadcharacters.data.network.api.BreakingBadCharacterServiceAPI
import com.example.breakingbadcharacters.data.network.mappers.NetworkMapper
import com.example.breakingbadcharacters.data.room.db.CharacterDao
import com.example.breakingbadcharacters.data.room.mappers.CacheMapper
import com.example.breakingbadcharacters.domain.model.BBCharacter
import com.example.breakingbadcharacters.presentation.datastate.MainDataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val CharacterDao: CharacterDao,
    private val api: BreakingBadCharacterServiceAPI,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) : Repository {
    override suspend fun getCharacters(): Flow<MainDataState<List<BBCharacter>>> = flow {
        emit(MainDataState.Loading)
        try {
            var cachedCharacters = CharacterDao.getAll()
            if(cachedCharacters.isEmpty()) {
                val networkCharacters = api.get()
                val bbCharacters = networkMapper.mapFromEntityList(networkCharacters)
                for (bbCharacter in bbCharacters) {
                    CharacterDao.insert(cacheMapper.mapToEntity(bbCharacter))
                }
                cachedCharacters = CharacterDao.getAll()
            }
            emit(MainDataState.Success(cacheMapper.mapFromEntityList(cachedCharacters)))
        } catch (e: Exception) {
            emit(MainDataState.Error(e))
        }
    }
}