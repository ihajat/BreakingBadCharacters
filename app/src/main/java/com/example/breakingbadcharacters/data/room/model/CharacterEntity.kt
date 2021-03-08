package com.example.breakingbadcharacters.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var char_id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "birthday")
    var birthday: String,

    @ColumnInfo(name = "occupation")
    val occupation: List<String>,

    @ColumnInfo(name = "img")
    val img: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "nickname")
    val nickname: String,

    @ColumnInfo(name = "appearance")
    val appearance: List<Int>,

    @ColumnInfo(name = "portrayed")
    val portrayed: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "better_call_saul_appearance")
    val better_call_saul_appearance: List<String>

)
