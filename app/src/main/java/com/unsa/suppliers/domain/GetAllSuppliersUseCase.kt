package com.unsa.suppliers.domain

import android.util.Log
import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import javax.inject.Inject

class GetAllSuppliersUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(): MutableList<SupplierResponse> {
        return supplierRepository.attemptGetSuppliers() ?: mutableListOf()
    }
}