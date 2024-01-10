package com.unsa.suppliers.data.model

import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse

class SupplierProvider {
    companion object {
        private val suppliers = mutableListOf<SupplierResponse>()
    }
}