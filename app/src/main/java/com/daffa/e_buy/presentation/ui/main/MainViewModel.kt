package com.daffa.e_buy.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.daffa.core.domain.model.Product
import com.daffa.core.domain.usecase.GetAllProducts
import com.daffa.core.domain.usecase.AddCartItem
import com.daffa.core.domain.usecase.GetCartResult
import com.daffa.core.domain.usecase.SubtractCartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllProducts: GetAllProducts,
    private val addCartItem: AddCartItem,
    private val subtractCartItem: SubtractCartItem,
    private val getCartResult: GetCartResult
) : ViewModel() {

    fun getAllProduct() = getAllProducts().asLiveData()

    fun addCartItem(product: Product) {
        addCartItem.invoke(product)
    }

    fun subtractCartItem(product: Product) {
        subtractCartItem.invoke(product)
    }

    fun getCartResult() = getCartResult.invoke().asLiveData()
}