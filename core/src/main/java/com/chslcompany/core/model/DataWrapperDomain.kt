package com.chslcompany.core.model

data class PhotoDomain(
    var description: String,
    var avgColor: String,
    var height: Int,
    var id: Int,
    var liked: Boolean,
    var photographer: String,
    var photographerId: Int,
    var photographerUrl: String,
    var srcDomain: SrcDomain,
    var url: String,
    var width: Int
)

data class SrcDomain(
    var landscape: String,
    var large: String,
    var large2x: String,
    var medium: String,
    var original: String,
    var portrait: String,
    var small: String,
    var tiny: String
)