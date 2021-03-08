package com.example.breakingbadcharacters.data.room.mappers

import com.example.breakingbadcharacters.data.room.model.CharacterEntity
import com.example.breakingbadcharacters.domain.model.BBCharacter
import junit.framework.TestCase
import org.junit.Test

class CacheMapperTest : TestCase() {

    public override fun setUp() {
        super.setUp()
    }

    val cacheMapper = CacheMapper()

    val characterEntity = CharacterEntity(1, "fred", "26/01/2000", listOf("programmer"),
        "na","married","big Johnny",listOf(1,2,3,4,5),"goodie","yes", listOf(""))
    val character = BBCharacter(1, "fred", "26/01/2000", listOf("programmer"), 
        "na","married","big Johnny",listOf(1,2,3,4,5),"goodie","yes", listOf(""))

    val characters = listOf(character)
    val characterEntites = listOf(characterEntity)


    @Test
    fun `test cache mapper correctly maps from domain model to cache`() {
        assertEquals(character, cacheMapper.mapFromEntity(characterEntity))
    }

    @Test
    fun `test cache mapper correctly from maps cache to domain model`() {
        assertEquals(characterEntity, cacheMapper.mapToEntity(character))
    }

    @Test
    fun `test cache mapper correctly from maps list of entities to domain models`() {
        assertEquals(characters, cacheMapper.mapFromEntityList(characterEntites))
    }
}