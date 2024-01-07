package com.daffa.e_buy.presentation.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daffa.core.data.Resource
import com.daffa.core.domain.model.Product
import com.daffa.core.presentation.adapter.ProductAdapter
import com.daffa.e_buy.R
import com.daffa.e_buy.databinding.ActivityMainBinding
import com.daffa.e_buy.presentation.ui.checkout.ProductCheckoutActivity
import com.daffa.e_buy.presentation.ui.detail.ProductDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("NotifyDataSetChanged")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvAdapter = ProductAdapter()

        rvAdapter.setOnItemClickCallback(
            object : ProductAdapter.OnItemClickCallback {
                override fun onItemClicked(product: Product) {
                    val intent = Intent(this@MainActivity, ProductDetailActivity::class.java)
                    intent.putExtra(PRODUCT_ID_EXTRA, product.id)
                    startActivity(intent)
                }
            }
        )

        rvAdapter.setOnAddClickCallback(
            object : ProductAdapter.OnAddClickCallback {
                override fun onAddClicked(product: Product) {
                    viewModel.addCartItem(product)
                    rvAdapter.notifyDataSetChanged()
                }
            }
        )

        rvAdapter.setOnMinusClickCallback(
            object : ProductAdapter.OnMinusClickCallback {
                override fun onMinusClicked(product: Product) {
                    viewModel.subtractCartItem(product)
                    rvAdapter.notifyDataSetChanged()
                }
            }
        )

        binding.apply {
            rvProducts.adapter = rvAdapter
            rvProducts.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)

            viewModel.getAllProduct().observe(this@MainActivity) {
                when (it) {
                    is Resource.Error -> {
                        showLoading(false)
                        rvProducts.visibility = View.GONE
                        fabCheckout.visibility = View.GONE
                        viewError.root.visibility = View.VISIBLE
                        viewError.tvError.text = it.message ?: getString(R.string.error_general)
                    }

                    is Resource.Loading -> showLoading(true)

                    is Resource.Success -> {
                        showLoading(false)
                        rvProducts.visibility = View.VISIBLE
                        fabCheckout.visibility = View.VISIBLE
                        rvAdapter.submitData(it.data!!)
                    }
                }
            }

            fabCheckout.setOnClickListener {
                startActivity(Intent(this@MainActivity, ProductCheckoutActivity::class.java))
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val PRODUCT_ID_EXTRA = "product_id"
    }
}