package com.example.breakingbadcharacters.data.repository

import com.example.breakingbadcharacters.domain.model.BBCharacter
import com.example.breakingbadcharacters.presentation.datastate.MainDataState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCharacters(): Flow<MainDataState<List<BBCharacter>>>
}