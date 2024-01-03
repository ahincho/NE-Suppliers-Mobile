package com.unsa.suppliers.domain

import android.util.Log
import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import javax.inject.Inject

class GetAllSuppliersUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(): MutableList<SupplierResponse> {
        val suppliers = supplierRepository.attemptGetSuppliers() ?: mutableListOf()
        Log.d("GET ALL USE CASE", (suppliers ?: "Empty List").toString())
        return suppliers
    }
}