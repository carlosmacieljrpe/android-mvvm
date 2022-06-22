package com.android.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class DrinksListDataModel(
    @SerializedName("drinks") val drinks: List<DrinksDataModel>
)

data class DrinksDataModel(
    @SerializedName("idDrink") val id: String, // Id is a required field
    @SerializedName("strDrink") val description: String, // Description is a required field
    @SerializedName("strInstructions") val instructionsEN: String? = null,
    @SerializedName("strInstructionsDE") val instructionsDE: String? = null,
    @SerializedName("strInstructionsIT") val instructionsIT: String? = null,
    @SerializedName("strDrinkThumb") val drinkThumbnail: String, // Thumbnail is a required field
    @SerializedName("strImageSource") val drinkImage: String? = null,
    @SerializedName("dateModified") val dateModified: String? = null
)