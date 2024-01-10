package com.unsa.suppliers.domain.auth

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.auth.UserRequest
import com.unsa.suppliers.data.dtos.auth.UserResponse
import javax.inject.Inject

class RegisterUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(userRequest: UserRequest): UserResponse? {
        return supplierRepository.attemptRegister(userRequest)
    }
}