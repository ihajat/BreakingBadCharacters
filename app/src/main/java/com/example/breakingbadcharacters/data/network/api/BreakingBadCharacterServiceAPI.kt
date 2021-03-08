package com.example.breakingbadcharacters.data.network.api

import com.example.breakingbadcharacters.data.network.model.CharacterDto
import retrofit2.http.GET

interface BreakingBadCharacterServiceAPI {
    @GET("characters")
    suspend fun get(): List<CharacterDto>
}