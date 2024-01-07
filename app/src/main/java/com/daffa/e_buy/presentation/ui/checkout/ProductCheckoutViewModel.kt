package com.daffa.e_buy.presentation.ui.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.daffa.core.domain.model.Product
import com.daffa.core.domain.usecase.AddCartItem
import com.daffa.core.domain.usecase.DeleteAllCartItem
import com.daffa.core.domain.usecase.GetAllCartItems
import com.daffa.core.domain.usecase.GetCartItem
import com.daffa.core.domain.usecase.GetCartResult
import com.daffa.core.domain.usecase.SubtractCartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductCheckoutViewModel @Inject constructor(
    private val getCartResult: GetCartResult,
    private val addCartItem: AddCartItem,
    private val subtractCartItem: SubtractCartItem,
    private val deleteAllCartItem: DeleteAllCartItem
): ViewModel() {

    fun getCartResult() = getCartResult.invoke().asLiveData()

    fun addCartItem(product: Product) = addCartItem.invoke(product)

    fun subtractCartItem(product: Product) = subtractCartItem.invoke(product)

    fun deleteAllCartItem() = deleteAllCartItem.invoke()
}