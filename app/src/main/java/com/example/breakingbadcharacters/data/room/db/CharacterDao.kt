package com.example.breakingbadcharacters.data.room.db

import androidx.room.Dao
import androidx.room.Query
import com.example.breakingbadcharacters.data.room.model.CharacterEntity

@Dao
abstract class CharacterDao : BaseDao<CharacterEntity> {

    @Query("DELETE FROM characters")
    suspend abstract fun nukeTable()

    @Query("SELECT * FROM characters")
    suspend abstract fun getAll(): List<CharacterEntity>
}