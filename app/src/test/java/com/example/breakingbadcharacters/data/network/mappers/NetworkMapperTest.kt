package com.example.breakingbadcharacters.data.network.mappers

import com.example.breakingbadcharacters.data.network.model.CharacterDto
import com.example.breakingbadcharacters.domain.model.BBCharacter
import junit.framework.TestCase
import org.junit.Test

class NetworkMapperTest : TestCase() {

    val networkMapper = NetworkMapper()

    val characterDto = CharacterDto(
        1, "fred", "26/01/2000", listOf("programmer"),
        "na", "married", "big Johnny", listOf(1, 2, 3, 4, 5), "goodie", "yes", listOf("")
    )
    val character = BBCharacter(
        1, "fred", "26/01/2000", listOf("programmer"),
        "na", "married", "big Johnny", listOf(1, 2, 3, 4, 5), "goodie", "yes", listOf("")
    )
    val characters = listOf(character)
    val characterDtos = listOf(characterDto)

    @Test
    fun `test network mapper correctly mapcharacters from domain model to network model`() {
        assertEquals(character, networkMapper.mapFromEntity(characterDto))
    }

    @Test
    fun `test network mapper correctly from maps network model to domain model`() {
        assertEquals(characterDto, networkMapper.mapToEntity(character))
    }

    @Test
    fun `test network mapper correctly maps list of network models to domain models`() {
        assertEquals(characters, networkMapper.mapFromEntityList(characterDtos))
    }

    public override fun setUp() {
        super.setUp()
    }
}