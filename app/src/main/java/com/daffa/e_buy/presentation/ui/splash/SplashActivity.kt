package com.daffa.e_buy.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.daffa.e_buy.databinding.ActivitySplashBinding
import com.daffa.e_buy.presentation.ui.main.MainActivity
import com.daffa.e_buy.util.Constant
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val handler = Handler(mainLooper)
            handler.postDelayed(
                {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                },
                Constant.SPLASH_DURATION
            )

            supportActionBar?.hide()
        }
    }
}