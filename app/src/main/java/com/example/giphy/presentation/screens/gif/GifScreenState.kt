package com.example.giphy.presentation.screens.gif

import com.example.giphy.domain.model.Gif

sealed interface GifScreenState {
    object Loading : GifScreenState
    data class Success(val value: Gif) : GifScreenState
    object Error : GifScreenState
}