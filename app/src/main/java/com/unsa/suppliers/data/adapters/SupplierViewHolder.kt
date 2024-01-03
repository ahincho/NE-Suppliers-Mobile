package com.unsa.suppliers.data.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import com.unsa.suppliers.databinding.ItemSupplierBinding

class SupplierViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemSupplierBinding.bind(view)
    fun renderSupplier(supplierResponse: SupplierResponse) {
        binding.itemSupplierName.text = supplierResponse.name
        binding.itemSupplierRuc.text = supplierResponse.ruc
        binding.itemSupplierCategory.text = supplierResponse.categoryName
        binding.itemSupplierCountry.text = supplierResponse.countryName
        binding.itemSupplierState.text = supplierResponse.stateName
    }
}