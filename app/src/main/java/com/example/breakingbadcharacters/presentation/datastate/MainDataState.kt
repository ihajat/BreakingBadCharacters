package com.example.breakingbadcharacters.presentation.datastate

sealed class MainDataState<out T> {

    data class Success<out T>(val data: T) : MainDataState<T>()
    data class Error(val exception: Exception) : MainDataState<Nothing>()
    object Loading : MainDataState<Nothing>()
}