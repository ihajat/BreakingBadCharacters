package com.example.breakingbadcharacters.data.repository

import com.example.breakingbadcharacters.data.network.api.BreakingBadCharacterServiceAPI
import com.example.breakingbadcharacters.data.network.mappers.NetworkMapper
import com.example.breakingbadcharacters.data.network.model.CharacterDto
import com.example.breakingbadcharacters.data.room.db.CharacterDao
import com.example.breakingbadcharacters.data.room.mappers.CacheMapper
import com.example.breakingbadcharacters.data.room.model.CharacterEntity
import com.example.breakingbadcharacters.presentation.datastate.MainDataState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import junit.framework.TestCase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.time.ExperimentalTime

class MainRepositoryTest : TestCase() {

    val characterDao = mock<CharacterDao>()
    val api = mock<BreakingBadCharacterServiceAPI>()
    val cacheMapper = CacheMapper()
    val networkMapper = NetworkMapper()

    @ExperimentalTime
    @Test
    fun `test repo gets list of characters`() = runBlockingTest {
        val characterDto = CharacterDto(
            1, "fred", "26/01/2000", listOf("programmer"),
            "na", "married", "big Johnny", listOf(1, 2, 3, 4, 5), "goodie", "yes", listOf("")
        )
        val characterEntity = CharacterEntity(1, "fred", "26/01/2000", listOf("programmer"),
            "na","married","big Johnny",listOf(1,2,3,4,5),"goodie","yes", listOf(""))
        val characterDtos = listOf(characterDto)
        val cachedCharacters = listOf(characterEntity)

        val repo = MainRepository(characterDao, api, cacheMapper, networkMapper)
        whenever(api.get()) doReturn characterDtos
        whenever(characterDao.getAll()) doReturn cachedCharacters

        val loading = MainDataState.Loading
        val data = MainDataState.Success(cacheMapper.mapFromEntityList(cachedCharacters))

        val characters = repo.getCharacters().toList()
        assertEquals(loading, characters.get(0))
        assertEquals(data, characters.get(1))
        Assert.assertEquals(2, characters.size)
    }

    @ExperimentalTime
    @Test
    fun `test repo error state on api error`() = runBlockingTest {

        val repo = MainRepository(characterDao, api, cacheMapper, networkMapper)
        given(api.get()).willAnswer { throw Exception() }
        val loading = MainDataState.Loading
        val characters = repo.getCharacters().toList()
        assertEquals(loading, characters.get(0))
        Assert.assertTrue(characters.get(1) is MainDataState.Error)
        Assert.assertEquals(2, characters.size)
    }
}