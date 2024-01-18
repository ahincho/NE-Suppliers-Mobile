package com.unsa.suppliers.domain.main.suppliers

import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import com.unsa.suppliers.data.repositories.SupplierRepository
import javax.inject.Inject

class UpdateSupplierUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int, supplierRequest: SupplierRequest) {
        supplierRepository.attemptUpdateSupplier(id, supplierRequest)
    }
}