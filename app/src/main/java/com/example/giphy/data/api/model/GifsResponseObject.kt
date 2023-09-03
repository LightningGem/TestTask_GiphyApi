package com.example.giphy.data.api.model

import com.google.gson.annotations.SerializedName

data class GifsResponseObject(
    @SerializedName("data"       ) var data       : ArrayList<GifData> = arrayListOf(),
    @SerializedName("pagination" ) var pagination : Pagination? = null
)