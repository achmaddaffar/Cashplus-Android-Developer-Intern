package com.daffa.e_buy.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.daffa.core.domain.model.Product
import com.daffa.core.domain.usecase.AddCartItem
import com.daffa.core.domain.usecase.GetCartItem
import com.daffa.core.domain.usecase.SubtractCartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getCartItem: GetCartItem,
    private val addCartItem: AddCartItem,
    private val subtractCartItem: SubtractCartItem
) : ViewModel() {

    fun getCartItem(id: Int) = getCartItem.invoke(id).asLiveData()

    fun addCartItem(product: Product) {
        addCartItem.invoke(product)
    }

    fun subtractCartItem(product: Product) {
        subtractCartItem.invoke(product)
    }
}