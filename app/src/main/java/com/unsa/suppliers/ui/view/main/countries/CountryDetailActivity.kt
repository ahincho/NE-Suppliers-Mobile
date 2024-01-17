package com.unsa.suppliers.ui.view.main.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.unsa.suppliers.core.Constants
import com.unsa.suppliers.data.adapters.main.countries.CountryAdapter
import com.unsa.suppliers.databinding.ActivityCountryDetailBinding
import com.unsa.suppliers.ui.viewmodel.main.CountryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryDetailBinding
    private val countryDetailViewModel: CountryDetailViewModel by viewModels()
    private var countryId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        countryId = intent?.getIntExtra(CountryAdapter.COUNTRY_ID, -1) ?: -1
        setContentView(binding.root)
        countryDetailViewModel.getCountryById(countryId)
        countryDetailViewModel.country.observe(this) { loadSupplierInfo() }
        initListeners()
    }
    private fun initListeners() {
        binding.btnDelete.setOnClickListener {
            if (countryDetailViewModel.country.value?.state != Constants.DELETED_STATE) {
                countryDetailViewModel.deleteCountry(countryId)
            }
        }
        binding.btnDisable.setOnClickListener {
            if (countryDetailViewModel.country.value?.state != Constants.DISABLED_STATE) {
                countryDetailViewModel.disableCountry(countryId)
            }
        }
        binding.btnEnable.setOnClickListener {
            if (countryDetailViewModel.country.value?.state != Constants.ACTIVE_STATE) {
                countryDetailViewModel.enableCountry(countryId)
            }
        }
    }
    private fun loadSupplierInfo() {
        binding.detailCountryName.text = countryDetailViewModel.country.value!!.name
        binding.detailCountryState.text = countryDetailViewModel.country.value!!.state
    }
}