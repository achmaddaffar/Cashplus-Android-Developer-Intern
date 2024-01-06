package com.daffa.e_buy.presentation.ui.main

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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvAdapter = ProductAdapter()

        rvAdapter.setOnAddClickCallback(
            object : ProductAdapter.OnAddClickCallback {
                override fun onAddClicked(product: Product) {
                    viewModel.addCartItem(product)
                }
            }
        )

        rvAdapter.setOnMinusClickCallback(
            object : ProductAdapter.OnMinusClickCallback {
                override fun onMinusClicked(product: Product) {
                    viewModel.subtractCartItem(product)
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
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}