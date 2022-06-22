package com.android.mvvm.data.model

data class CatFactDataModel(
    val status: StatusDataModel,
    val text: String,
    val createdAt: String // Sent in ISO 8601 format
)
