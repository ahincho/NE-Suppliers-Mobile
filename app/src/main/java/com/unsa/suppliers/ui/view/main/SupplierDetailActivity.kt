package com.unsa.suppliers.ui.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.unsa.suppliers.data.adapters.main.suppliers.SupplierAdapter.Companion.SUPPLIER_ID
import com.unsa.suppliers.databinding.ActivitySupplierDetailBinding
import com.unsa.suppliers.ui.viewmodel.main.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupplierDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySupplierDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private var supplierId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierDetailBinding.inflate(layoutInflater)
        supplierId = intent?.getIntExtra(SUPPLIER_ID, -1) ?: -1
        setContentView(binding.root)
        detailViewModel.getSupplierById(supplierId)
        detailViewModel.supplier.observe(this) { loadSupplierInfo() }
        initListeners()
    }
    private fun initListeners() {
        binding.btnDelete.setOnClickListener {
            detailViewModel.deleteSupplier(supplierId)
        }
        binding.btnDisable.setOnClickListener {
            detailViewModel.disableSupplier(supplierId)
        }
        binding.btnEnable.setOnClickListener {
            detailViewModel.enableSupplier(supplierId)
        }
    }
    private fun loadSupplierInfo() {
        binding.detailSupplierName.text = detailViewModel.supplier.value!!.name
        binding.detailSupplierRuc.text = detailViewModel.supplier.value!!.ruc
        binding.detailSupplierCategory.text = detailViewModel.supplier.value!!.categoryName
        binding.detailSupplierCountry.text = detailViewModel.supplier.value!!.countryName
        binding.detailSupplierState.text = detailViewModel.supplier.value!!.stateName
    }
}