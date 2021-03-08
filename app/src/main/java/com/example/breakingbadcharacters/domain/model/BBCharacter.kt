package com.example.breakingbadcharacters.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BBCharacter (
 val char_id: Int,
 val name: String,
 val birthday: String,
 val occupation: List<String>,
 val img: String,
 val status: String,
 val nickname: String,
 val appearance: List<Int>,
 val portrayed: String,
 val category: String,
 val better_call_saul_appearance: List<String>
): Parcelable