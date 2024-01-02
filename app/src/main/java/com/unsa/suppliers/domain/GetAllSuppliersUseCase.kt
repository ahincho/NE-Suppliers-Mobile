package com.unsa.suppliers.domain

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import javax.inject.Inject

class GetAllSuppliersUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(): List<SupplierResponse> {
        return supplierRepository.attemptGetSuppliers() ?: emptyList()
    }
}