package com.example.giphy.data.api.model

import com.google.gson.annotations.SerializedName

data class GifResponseObject(
    @SerializedName("data" ) var data : GifData? = null,
    @SerializedName("meta" ) var meta : Meta? = null
)