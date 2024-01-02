package com.unsa.suppliers.ui.view.suppliers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.unsa.suppliers.databinding.ActivitySupplierBinding
import com.unsa.suppliers.ui.viewmodel.suppliers.SupplierViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupplierActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySupplierBinding
    private val supplierViewModel: SupplierViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supplierViewModel.getToken()
        Toast.makeText(this, supplierViewModel.getToken(), Toast.LENGTH_SHORT).show()
        binding.supplierJwt.text = supplierViewModel.getToken()
    }
}