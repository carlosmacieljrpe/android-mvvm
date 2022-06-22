package com.android.mvvm.domain.mapper

import com.android.mvvm.common.RandomData.randomString
import com.android.mvvm.data.model.DrinksDataModel
import org.junit.Assert.*
import org.junit.Test

class DrinksMapperTest {

    @Test
    fun `should map drink`() {
        val drinksDataModel = createDrinksDataModel()
        val drinkDomainModel = DrinksMapper.toDomain(drinksDataModel)

        assertEquals(drinksDataModel.id, drinkDomainModel.id)
        assertEquals(drinksDataModel.description, drinkDomainModel.description)
        assertEquals(drinksDataModel.drinkThumbnail, drinkDomainModel.drinkThumbnail)
    }

    private fun createDrinksDataModel(): DrinksDataModel {
        return DrinksDataModel(
            id = randomString(),
            description = randomString(),
            drinkThumbnail = randomString()
        )
    }
}