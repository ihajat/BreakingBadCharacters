package com.example.breakingbadcharacters.data.room.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.breakingbadcharacters.data.room.model.CharacterEntity
import com.example.breakingbadcharacters.presentation.ui.EspressoIdlingResource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.time.ExperimentalTime

@RunWith(AndroidJUnit4ClassRunner::class)
class CharacterDaoTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var characterDao: CharacterDao
    private lateinit var db: CharacterDatabase

    var testCharacterEntity = CharacterEntity(
        1, "fred", "26/01/2000", listOf("programmer"),
        "na", "married", "big Johnny", listOf(1, 2, 3, 4, 5), "goodie", "yes", listOf("")
    )


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharacterDatabase::class.java
        ).build()
        characterDao = db.characterDao()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    /*
    Test 1: Test empty table
     */
    @ExperimentalTime
    @Test
    fun basicEmptyTest() = runBlockingTest {
        assertThat(characterDao?.getAll()).isEmpty()
    }

    /*
    Test 2: Test insert of CharacterEntity
     */
    @ExperimentalTime
    @Test
    fun insertTest() = runBlockingTest {
        val characterEntity = CharacterEntity(
            1, "fred", "26/01/2000", listOf("programmer"),
            "na", "married", "big Johnny", listOf(1, 2, 3, 4, 5), "goodie", "yes", listOf("")
        )
        characterDao.insert(characterEntity)

        val allCharacters = characterDao.getAll()

        assertThat(allCharacters).contains(characterEntity)
    }

    /*
    Test 3: Test update of CharacterEntity
    */
    @ExperimentalTime
    @Test
    fun updateTest() = runBlockingTest {

        characterDao.insert(testCharacterEntity)
        testCharacterEntity.category = "test"
        characterDao.update(testCharacterEntity)

        val allCharacters = characterDao.getAll()

        assertThat(allCharacters.get(0).category).contains("test")
    }

    /*
    Test 4: Test delete of CharacterEntity
    */
    @ExperimentalTime
    @Test
    fun deleteTest() = runBlockingTest {

        characterDao.insert(testCharacterEntity)
        characterDao.delete(testCharacterEntity)

        assertThat(characterDao?.getAll()).isEmpty()
    }

    /*
    Test 5: Test insert TWO identical CharacterEntity objects,
    ensure the correct object is inserted

    if an exception is thrown the test will automatically fail.
    altneratively, we can use assertDoesNotThrow from JUnit5
     */
    @ExperimentalTime
    @Test
    fun doubleInsertTest() = runBlockingTest {
        val characterEntity = CharacterEntity(
            1, "fred", "26/01/2000", listOf("programmer"),
            "na", "married", "big Johnny", listOf(1, 2, 3, 4, 5), "goodie", "yes", listOf("")
        )
        // Insert two identical objects

        characterDao.insert(characterEntity)
        characterDao.insert(characterEntity)

        val allCharacters = characterDao.getAll()

        assertThat(allCharacters).contains(characterEntity)
        assertThat(characterDao?.getAll()).containsNoDuplicates()
    }
}