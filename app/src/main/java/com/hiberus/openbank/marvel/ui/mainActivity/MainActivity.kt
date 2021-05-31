package com.hiberus.openbank.marvel.ui.mainActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    var TAG: String? = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        //Click back in menu, needs activity components
        mainViewModel.menuModel.clickBack =
            View.OnClickListener { findNavController(R.id.nav_host_fragment).navigateUp() }

        binding.mvm = mainViewModel
        binding.menuM = mainViewModel.menuModel

        //bottomnavigation view config
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        Log.d(TAG, "mainactivity created")

    }


}