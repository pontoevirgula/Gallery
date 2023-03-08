package com.chslcompany.gallery.framework.network.response

import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.core.model.SrcDomain
import com.google.gson.annotations.SerializedName

data class DataWrapperResponse(
    @SerializedName("next_page")
    val nextPage: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("photos")
    val photos: List<Photo>
)

data class Photo(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("avg_color")
    val avgColor: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("photographer")
    val photographer: String,
    @SerializedName("photographer_id")
    val photographerId: Int,
    @SerializedName("photographer_url")
    val photographerUrl: String,
    @SerializedName("src")
    val src: Src,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

fun Photo.toPhotoDomain(): PhotoDomain =
    PhotoDomain(
        description = this.alt,
        avgColor = this.avgColor,
        height = this.height,
        id = this.id,
        liked = this.liked,
        photographer = this.photographer,
        photographerId = this.photographerId,
        photographerUrl = this.photographerUrl,
        srcDomain = SrcDomain(
            landscape = this.src.landscape,
            large = this.src.large,
            large2x = this.src.large2x,
            medium = this.src.medium,
            original = this.src.original,
            portrait = this.src.portrait,
            small = this.src.small,
            tiny = this.src.tiny,
        ),
        url = this.url,
        width = this.width
    )

data class Src(
    @SerializedName("landscape")
    val landscape: String,
    @SerializedName("large")
    val large: String,
    @SerializedName("large2x")
    val large2x: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("original")
    val original: String,
    @SerializedName("portrait")
    val portrait: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("tiny")
    val tiny: String
)