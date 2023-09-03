package com.example.giphy.data.api.model

import com.google.gson.annotations.SerializedName

data class GifData (
    @SerializedName("id"                         ) var id      : String? = null,
    @SerializedName("url"                        ) var url     : String? = null,
    @SerializedName("title"                      ) var title   : String? = null,
    @SerializedName("images"                     ) var images  : Images,
)

data class Images (
    @SerializedName("original"                 ) var original               : Original? = null,
    @SerializedName("downsized"                ) var downsized              : Downsized? = null,
    @SerializedName("downsized_large"          ) var downsizedLarge         : DownsizedLarge? = null,
    @SerializedName("downsized_medium"         ) var downsizedMedium        : DownsizedMedium? = null
)

data class Original (
    @SerializedName("height" ) var height : String? = null,
    @SerializedName("width"  ) var width  : String? = null,
    @SerializedName("size"   ) var size   : String? = null,
    @SerializedName("url"    ) var url    : String? = null
)

data class Downsized (
    @SerializedName("height" ) var height : String? = null,
    @SerializedName("width"  ) var width  : String? = null,
    @SerializedName("size"   ) var size   : String? = null,
    @SerializedName("url"    ) var url    : String? = null
)

data class DownsizedLarge (
    @SerializedName("height" ) var height : String? = null,
    @SerializedName("width"  ) var width  : String? = null,
    @SerializedName("size"   ) var size   : String? = null,
    @SerializedName("url"    ) var url    : String? = null
)

data class DownsizedMedium (
    @SerializedName("height" ) var height : String? = null,
    @SerializedName("width"  ) var width  : String? = null,
    @SerializedName("size"   ) var size   : String? = null,
    @SerializedName("url"    ) var url    : String? = null
)