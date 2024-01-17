package com.unsa.suppliers.data.repositories

import com.unsa.suppliers.data.dtos.main.suppliers.*
import com.unsa.suppliers.data.network.services.SupplierService
import javax.inject.Inject

class SupplierRepository @Inject constructor (
    private val supplierService: SupplierService,
) {
    suspend fun attemptGetSuppliers(): MutableList<SupplierResponse>? {
        return supplierService.getSuppliers()
    }
    suspend fun attemptGetSupplierById(id: Int): SupplierResponse? {
        return supplierService.getSupplierById(id)
    }
    suspend fun attemptUpdateSupplier(id: Int, supplierRequest: SupplierRequest) {
        supplierService.updateSupplier(id, supplierRequest)
    }
    suspend fun attemptCreateSupplier(supplierRequest: SupplierRequest): SupplierResponse? {
        return supplierService.createSupplier(supplierRequest)
    }
    suspend fun attemptDeleteSupplier(id: Int) {
        supplierService.deleteSupplier(id)
    }
    suspend fun attemptEnableSupplier(id: Int) {
        supplierService.enableSupplier(id)
    }
    suspend fun attemptDisableSupplier(id: Int) {
        supplierService.disableSupplier(id)
    }
}