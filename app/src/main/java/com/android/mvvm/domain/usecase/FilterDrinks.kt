package com.android.mvvm.domain.usecase

import com.android.mvvm.domain.model.Drinks
import java.util.Locale
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * This use case filters for facts excluding white spaces at the end and ignoring case sensitivity
 */
interface FilterDrinks {

    operator fun invoke(queryString: String, factList: List<Drinks>): List<Drinks>
}

class FilterDrinksImpl @Inject constructor() : FilterDrinks {

    override operator fun invoke(
        queryString: String,
        factList: List<Drinks>
    ): List<Drinks> {
        val filteredList = ArrayList<Drinks>()

        for (item in factList) {
            if (item.description.toLowerCase(Locale.ROOT)
                    .contains(queryString.trim().toLowerCase(Locale.ROOT))
            ) {
                filteredList.add(item)
            }
        }
        return filteredList
    }
}