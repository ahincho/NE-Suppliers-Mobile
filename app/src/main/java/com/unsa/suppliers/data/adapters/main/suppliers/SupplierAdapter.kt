package com.unsa.suppliers.data.adapters.main.suppliers

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.unsa.suppliers.R
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import com.unsa.suppliers.ui.view.main.suppliers.SupplierDetailActivity

class SupplierAdapter (
    private var suppliers: List<SupplierResponse>
): RecyclerView.Adapter<SupplierViewHolder>() {
    companion object {
        const val SUPPLIER_ID = "SUPPLIER_ID"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SupplierViewHolder(layoutInflater.inflate(R.layout.item_supplier, parent, false))
    }
    override fun getItemCount(): Int {
        return suppliers.size
    }
    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        val supplierResponse = suppliers[position]
        holder.itemView.findViewById<CardView>(R.id.supplierCardView).setOnClickListener {
            val intent = Intent(it.context, SupplierDetailActivity::class.java)
            intent.putExtra(SUPPLIER_ID, suppliers[position].id)
            it.context.startActivity(intent)
        }
        holder.renderSupplier(supplierResponse)
    }
}