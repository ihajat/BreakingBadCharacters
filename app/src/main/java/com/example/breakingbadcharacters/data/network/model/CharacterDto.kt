package com.example.breakingbadcharacters.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterDto(
 @SerializedName("char_id") @Expose val char_id : Int =0,
 @SerializedName("name") @Expose val name : String = "",
 @SerializedName("birthday") @Expose val birthday : String = "",
 @SerializedName("occupation") @Expose val occupation : List<String> = emptyList(),
 @SerializedName("img") @Expose val img : String = "",
 @SerializedName("status") @Expose val status : String = "",
 @SerializedName("nickname") @Expose val nickname : String = "",
 @SerializedName("appearance") @Expose val appearance : List<Int> = emptyList(),
 @SerializedName("portrayed") @Expose val portrayed : String = "",
 @SerializedName("category") @Expose val category : String = "",
 @SerializedName("better_call_saul_appearance") @Expose val better_call_saul_appearance : List<String> = emptyList()
)