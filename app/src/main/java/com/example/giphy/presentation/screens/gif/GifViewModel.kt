package com.example.giphy.presentation.screens.gif

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphy.domain.GifRepository
import com.example.giphy.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val repository: GifRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val ID_KEY = "id_key"
    }

    private val id = savedStateHandle.get<String>(ID_KEY)

    private val _screenState = MutableStateFlow(if(id == null) GifScreenState.Error else GifScreenState.Loading)
    val screenState : StateFlow<GifScreenState> = _screenState

    var loadGifJob: Job? = null

    fun load() {
        loadGifJob?.cancel()
        if(id != null) loadGifJob = viewModelScope.launch {
            repository.getGif(id).let { result ->
                when(result) {
                    is Result.Error -> _screenState.value = GifScreenState.Error
                    is Result.Success -> _screenState.value = GifScreenState.Success(result.value)
                }
            }
        }
    }

    init { load() }
}