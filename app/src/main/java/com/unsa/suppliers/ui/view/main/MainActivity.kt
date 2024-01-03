package com.unsa.suppliers.ui.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.unsa.suppliers.R
import com.unsa.suppliers.databinding.ActivityMainBinding
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigation: BottomNavigationView
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.commit {
            replace<SupplierFragment>(binding.mainContainer.id)
            setReorderingAllowed(true)
        }
        navigation = binding.mainNavMenu
        initListeners()
    }
    private fun initListeners() {
        navListener()
    }
    private fun navListener() {
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemSuppliers -> {
                    supportFragmentManager.commit {
                        replace<SupplierFragment>(R.id.mainContainer)
                        setReorderingAllowed(true)
                    }
                    true
                }
                R.id.itemCategories -> {
                    supportFragmentManager.commit {
                        replace<CategoryFragment>(R.id.mainContainer)
                        setReorderingAllowed(true)
                    }
                    true
                }
                R.id.itemCountries -> {
                    supportFragmentManager.commit {
                        replace<CountryFragment>(R.id.mainContainer)
                        setReorderingAllowed(true)
                    }
                    true
                }
                else -> true
            }
        }
    }
}