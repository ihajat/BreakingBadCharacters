package com.example.breakingbadcharacters.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.breakingbadcharacters.data.room.mappers.Converters
import com.example.breakingbadcharacters.data.room.model.CharacterEntity


@Database(entities = [CharacterEntity::class], version = 1)
@TypeConverters( Converters::class )
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        val DATABASE_NAME: String = "character_db"
    }
}