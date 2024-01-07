package com.daffa.core.data

import com.daffa.core.data.source.remote.response.ProductResponseItem
import com.daffa.core.data.source.remote.response.Rating

object DataDummy {
    fun generateDummyProductResponse(): List<ProductResponseItem> {
        val productList = ArrayList<ProductResponseItem>()
        for (i in 0..30) {
            val productResponse = ProductResponseItem(
                imageUrl = "imageUrl $i",
                price = i.toDouble(),
                rating = Rating(
                    rate = i.toDouble(),
                    count = i
                ),
                description = "description $i",
                id = i,
                title = "Title $i",
                category = "Category $i"
            )
            productList.add(productResponse)
        }
        return productList
    }
}