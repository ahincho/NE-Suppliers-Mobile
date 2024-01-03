package com.unsa.suppliers.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unsa.suppliers.R
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse

class SupplierAdapter (
    private var suppliers: List<SupplierResponse>
) : RecyclerView.Adapter<SupplierViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SupplierViewHolder(layoutInflater.inflate(R.layout.item_supplier, parent, false))
    }
    override fun getItemCount(): Int {
        return suppliers.size
    }
    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        val supplierResponse = suppliers[position]
        holder.renderSupplier(supplierResponse)
    }
}