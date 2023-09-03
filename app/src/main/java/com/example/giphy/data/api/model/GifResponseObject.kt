package com.example.giphy.data.api.model

import com.google.gson.annotations.SerializedName

data class GifResponseObject(
    @SerializedName("data") var data : GifData,
    @SerializedName("meta") var meta : Meta
)