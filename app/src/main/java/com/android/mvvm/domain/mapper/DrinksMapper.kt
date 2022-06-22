package com.android.mvvm.domain.mapper

import com.android.mvvm.data.model.DrinksDataModel
import com.android.mvvm.domain.model.Drinks

object DrinksMapper {

    fun toDomain(dataModel: DrinksDataModel): Drinks {
        return Drinks(
            dataModel.id,
            dataModel.description,
            dataModel.instructionsEN,
            dataModel.instructionsDE,
            dataModel.instructionsIT,
            dataModel.drinkThumbnail,
            dataModel.drinkImage,
            dataModel.dateModified
        )
    }
}