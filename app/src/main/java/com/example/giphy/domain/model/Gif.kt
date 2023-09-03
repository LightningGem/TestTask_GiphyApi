package com.example.giphy.domain.model

data class Gif(
    val id: String,
    val url: String,
    val widthPx: Int,
    val heightPx: Int
)