package com.daffa.e_buy.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.daffa.core.domain.model.Product
import com.daffa.core.domain.usecase.GetAllProducts
import com.daffa.core.domain.usecase.AddCartItem
import com.daffa.core.domain.usecase.SubtractCartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllProducts: GetAllProducts,
    private val addCartItem: AddCartItem,
    private val subtractCartItem: SubtractCartItem
) : ViewModel() {

    fun getAllProduct() = getAllProducts().asLiveData()

    fun addCartItem(product: Product) {
        viewModelScope.launch {
            addCartItem.invoke(product)
        }
    }

    fun subtractCartItem(product: Product) {
        viewModelScope.launch {
            subtractCartItem.invoke(product)
        }
    }
}