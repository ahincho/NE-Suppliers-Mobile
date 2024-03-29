package com.unsa.suppliers.domain.main.suppliers

import com.unsa.suppliers.data.repositories.SupplierRepository
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import javax.inject.Inject

class CreateSupplierUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(supplierRequest: SupplierRequest) {
        supplierRepository.attemptCreateSupplier(supplierRequest)
    }
}