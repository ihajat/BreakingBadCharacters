package com.example.breakingbadcharacters.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.breakingbadcharacters.data.repository.MainRepository
import com.example.breakingbadcharacters.domain.model.BBCharacter
import com.example.breakingbadcharacters.presentation.datastate.MainDataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _state: MutableLiveData<MainDataState<List<BBCharacter>>> = MutableLiveData()
    val state: LiveData<MainDataState<List<BBCharacter>>>
        get() = _state

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.Chaacters -> {
                    mainRepository.getCharacters()
                        .onEach {
                            _state.value = it
                        }
                        .launchIn(this)
                }
                is MainStateEvent.None -> {

                }
            }
        }
    }

}
sealed class MainStateEvent{
    object Chaacters: MainStateEvent()
    object None: MainStateEvent()
}