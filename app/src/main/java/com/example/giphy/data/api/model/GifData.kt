package com.example.giphy.data.api.model

import com.google.gson.annotations.SerializedName

data class GifData (
    @SerializedName("id"    ) var id      : String,
    @SerializedName("url"   ) var url     : String,
    @SerializedName("title" ) var title   : String? = null,
    @SerializedName("images") var images  : Images,
)

data class Images (
    @SerializedName("original" ) var original         : Original,
    @SerializedName("downsized") var downsized        : Downsized,
)

data class Original (
    @SerializedName("height") var height : String,
    @SerializedName("width" ) var width  : String,
    @SerializedName("size"  ) var size   : String,
    @SerializedName("url"   ) var url    : String
)

data class Downsized (
    @SerializedName("height") var height : String,
    @SerializedName("width" ) var width  : String,
    @SerializedName("size"  ) var size   : String,
    @SerializedName("url"   ) var url    : String
)