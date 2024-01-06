package com.daffa.core.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.daffa.core.base.BaseRecyclerViewAdapter
import com.daffa.core.databinding.ItemListProductBinding
import com.daffa.core.domain.model.Product

class ProductAdapter : BaseRecyclerViewAdapter<ItemListProductBinding, Product>() {

    interface OnAddClickCallback {
        fun onAddClicked(product: Product)
    }

    interface OnMinusClickCallback {
        fun onMinusClicked(product: Product)
    }

    private lateinit var onAddClickCallback: OnAddClickCallback
    private lateinit var onMinusClickCallback: OnMinusClickCallback

    fun setOnAddClickCallback(onAddClickCallback: OnAddClickCallback) {
        this.onAddClickCallback = onAddClickCallback
    }

    fun setOnMinusClickCallback(onMinusClickCallback: OnMinusClickCallback) {
        this.onMinusClickCallback = onMinusClickCallback
    }

    override fun inflateViewBinding(parent: ViewGroup): ItemListProductBinding =
        ItemListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    @SuppressLint("SetTextI18n")
    override val binder: (Product, ItemListProductBinding) -> Unit = { data, binding ->
        binding.apply {
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(ivProductImage)

            tvProductTitle.text = data.title
            tvProductDescription.text = data.description
            tvRating.text = data.rate.toString()
            tvRatingCount.text = "(${data.rateCount.toString()})"
            tvCartCount.text = data.cartCount.toString()
            tvPrice.text = data.price.toString()

            ivAddIcon.setOnClickListener {
                onAddClickCallback.onAddClicked(data)
            }

            ivMinusIcon.setOnClickListener {
                onMinusClickCallback.onMinusClicked(data)
            }

            data.cartCount?.let {
                if (it > 0) {
                    ivMinusIcon.visibility = View.VISIBLE
                    tvCartCount.visibility = View.VISIBLE
                } else {
                    ivMinusIcon.visibility = View.GONE
                    tvCartCount.visibility = View.GONE
                }
            }
        }
    }

    override val diffUtilBuilder: (List<Product>, List<Product>) -> DiffUtil.Callback =
        { old, new ->
            ProductDiffUtil(old, new)
        }
}