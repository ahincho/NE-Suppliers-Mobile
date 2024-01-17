package com.unsa.suppliers.ui.view.main.suppliers

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.unsa.suppliers.data.adapters.main.suppliers.SupplierAdapter.Companion.SUPPLIER_ID
import com.unsa.suppliers.databinding.ActivitySupplierDetailBinding
import com.unsa.suppliers.ui.viewmodel.main.SupplierDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupplierDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySupplierDetailBinding
    private val supplierDetailViewModel: SupplierDetailViewModel by viewModels()
    private var supplierId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierDetailBinding.inflate(layoutInflater)
        supplierId = intent?.getIntExtra(SUPPLIER_ID, -1) ?: -1
        setContentView(binding.root)
        supplierDetailViewModel.getSupplierById(supplierId)
        supplierDetailViewModel.supplier.observe(this) { loadSupplierInfo() }
        initListeners()
    }
    private fun initListeners() {
        binding.btnDelete.setOnClickListener {
            supplierDetailViewModel.deleteSupplier(supplierId)
        }
        binding.btnDisable.setOnClickListener {
            supplierDetailViewModel.disableSupplier(supplierId)
        }
        binding.btnEnable.setOnClickListener {
            supplierDetailViewModel.enableSupplier(supplierId)
        }
    }
    private fun loadSupplierInfo() {
        binding.detailSupplierName.text = supplierDetailViewModel.supplier.value!!.name
        binding.detailSupplierRuc.text = supplierDetailViewModel.supplier.value!!.ruc
        binding.detailSupplierCategory.text = supplierDetailViewModel.supplier.value!!.categoryName
        binding.detailSupplierCountry.text = supplierDetailViewModel.supplier.value!!.countryName
        binding.detailSupplierState.text = supplierDetailViewModel.supplier.value!!.stateName
    }
}