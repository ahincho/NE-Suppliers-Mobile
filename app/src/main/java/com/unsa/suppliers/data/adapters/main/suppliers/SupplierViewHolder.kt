package com.unsa.suppliers.data.adapters.main.suppliers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import com.unsa.suppliers.databinding.ItemSupplierBinding

class SupplierViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemSupplierBinding.bind(view)
    fun renderSupplier(supplierResponse: SupplierResponse) {
        binding.itemSupplierId.text = supplierResponse.id.toString()
        binding.itemSupplierName.text = supplierResponse.name
        binding.itemSupplierRuc.text = supplierResponse.ruc
        binding.itemSupplierState.text = supplierResponse.state
    }
}