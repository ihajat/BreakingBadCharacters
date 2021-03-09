package com.example.breakingbadcharacters.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val char_id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "birthday")
    var birthday: String,

    @ColumnInfo(name = "occupation")
    var occupation: List<String>,

    @ColumnInfo(name = "img")
    var img: String,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "nickname")
    var nickname: String,

    @ColumnInfo(name = "appearance")
    var appearance: List<Int>,

    @ColumnInfo(name = "portrayed")
    var portrayed: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "better_call_saul_appearance")
    var better_call_saul_appearance: List<String>

)
