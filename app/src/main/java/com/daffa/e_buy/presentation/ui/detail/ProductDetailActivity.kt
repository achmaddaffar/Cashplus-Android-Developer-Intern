package com.daffa.e_buy.presentation.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.daffa.e_buy.databinding.ActivityProductDetailBinding
import com.daffa.e_buy.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private val viewModel by viewModels<ProductDetailViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(MainActivity.PRODUCT_ID_EXTRA, -1)

        binding.apply {
            viewModel.getCartItem(id).observe(this@ProductDetailActivity) { product ->
                Glide.with(this@ProductDetailActivity)
                    .load(product.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivProductImage)

                tvProductTitle.text = product.title
                tvProductDescription.text = product.description
                tvRating.text = product.rate.toString()
                tvRatingCount.text = "(${product.rateCount.toString()})"
                tvCartCount.text = product.cartCount.toString()
                tvPrice.text = product.price.toString()

                ivMinusIcon.setOnClickListener {
                    viewModel.subtractCartItem(product)
                }

                ivAddIcon.setOnClickListener {
                    viewModel.addCartItem(product)
                }

                if (product.cartCount > 0) {
                    ivMinusIcon.visibility = View.VISIBLE
                    tvCartCount.visibility = View.VISIBLE
                } else {
                    ivMinusIcon.visibility = View.GONE
                    tvCartCount.visibility = View.GONE
                }
            }
        }
    }
}