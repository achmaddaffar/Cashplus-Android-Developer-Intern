package com.daffa.e_buy.presentation.ui.checkout

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daffa.core.domain.model.Product
import com.daffa.core.presentation.adapter.ProductAdapter
import com.daffa.e_buy.databinding.ActivityProductCheckoutBinding
import com.daffa.e_buy.presentation.ui.detail.ProductDetailActivity
import com.daffa.e_buy.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("NotifyDataSetChanged")
@AndroidEntryPoint
class ProductCheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductCheckoutBinding
    private val viewModel by viewModels<ProductCheckoutViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvAdapter = ProductAdapter()

        rvAdapter.setOnItemClickCallback(
            object : ProductAdapter.OnItemClickCallback {
                override fun onItemClicked(product: Product) {
                    val intent = Intent(this@ProductCheckoutActivity, ProductDetailActivity::class.java)
                    intent.putExtra(MainActivity.PRODUCT_ID_EXTRA, product.id)
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
            rvCheckout.adapter = rvAdapter
            rvCheckout.layoutManager =
                LinearLayoutManager(this@ProductCheckoutActivity, RecyclerView.VERTICAL, false)

            viewModel.getCartResult().observe(this@ProductCheckoutActivity) {
                rvAdapter.submitData(it)
                val total = it.map { product -> product.price!! * product.cartCount }
                tvTotal.text = total.sum().toString()
            }

            btnCheckout.setOnClickListener {
                AlertDialog.Builder(this@ProductCheckoutActivity)
                    .setTitle("Payment confirmed!")
                    .setPositiveButton(
                        "View other products"
                    ) { _, _ ->
                        viewModel.deleteAllCartItem()
                        startActivity(Intent(this@ProductCheckoutActivity, MainActivity::class.java))
                        finishAffinity()
                    }
                    .setCancelable(false)
                    .show()
            }
        }
    }
}