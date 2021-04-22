package com.hawkerlabs.tadah.presentation.splash.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.databinding.ActivitySplashBinding
import com.hawkerlabs.tadah.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Glide.with(this)
                .asBitmap()
                .load(SPLASH_IMAGE_URL)
                .centerInside()
                .into(binding.splashImg)

        Handler(Looper.getMainLooper()).postDelayed({ startActivity(Intent(this, MainActivity::class.java)) }, SPLASH_SCREEN_DELAY)
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY = 3000L
        private const val SPLASH_IMAGE_URL = "https://tadah-hlabs.s3.ap-south-1.amazonaws.com/splash-s2.png"

    }
}