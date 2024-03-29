package com.unsa.suppliers.domain.main.suppliers

import com.unsa.suppliers.data.repositories.SupplierRepository
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import javax.inject.Inject

class GetAllSuppliersUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(): MutableList<SupplierResponse> {
        return supplierRepository.attemptGetSuppliers() ?: mutableListOf()
    }
}