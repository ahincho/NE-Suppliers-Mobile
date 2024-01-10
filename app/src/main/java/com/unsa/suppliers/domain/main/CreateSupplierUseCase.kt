package com.unsa.suppliers.domain.main

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import javax.inject.Inject

class CreateSupplierUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(supplierRequest: SupplierRequest) {

    }
}