package com.example.giphy.presentation.screens.trending_gifs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.giphy.domain.GifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingGifsViewModel @Inject constructor(repository: GifRepository) : ViewModel() {
    val gifs = repository.gifsFlow().cachedIn(viewModelScope)
}