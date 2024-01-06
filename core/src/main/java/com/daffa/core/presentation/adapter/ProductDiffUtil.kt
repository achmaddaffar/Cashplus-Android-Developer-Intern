package com.daffa.core.presentation.adapter

import com.daffa.core.base.BaseDiffUtil
import com.daffa.core.domain.model.Product

class ProductDiffUtil(
    oldList: List<Product>,
    newList: List<Product>
): BaseDiffUtil<Product, Int?>(oldList, newList) {

    override fun Product.getItemIdentifier(): Int? = this.id
}