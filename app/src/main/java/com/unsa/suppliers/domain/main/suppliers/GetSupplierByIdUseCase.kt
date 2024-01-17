package com.unsa.suppliers.domain.main.suppliers

import com.unsa.suppliers.data.repositories.SupplierRepository
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import javax.inject.Inject

class GetSupplierByIdUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int): SupplierResponse {
        return supplierRepository.attemptGetSupplierById(id) ?:
            SupplierResponse(1, "Error", "Error", "Error", "Error", "Error")
    }
}