package com.android.mvvm.domain.model

data class Drinks(
    val id: String, // Id is a required field
    val description: String, // Description is a required field
    val instructionsEN: String?,
    val instructionsDE: String?,
    val instructionsIT: String?,
    val drinkThumbnail: String, // Thumbnail is a required field
    val drinkImage: String?,
    val dateModified: String?
)