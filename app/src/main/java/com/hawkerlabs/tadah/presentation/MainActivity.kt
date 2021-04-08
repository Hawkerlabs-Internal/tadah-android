package com.hawkerlabs.tadah.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.hawkerlabs.tadah.R
import com.hawkerlabs.tadah.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * https://medium.com/@svvashishtha/using-room-with-hilt-cb57a1bc32f
 * https://medium.com/swlh/getting-started-with-android-testing-63a95c3d3576
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


    fun showHomeEnabled(title: String){
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun hideHomeEnabled(title: String){
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }


}