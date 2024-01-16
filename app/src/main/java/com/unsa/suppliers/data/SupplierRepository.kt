package com.unsa.suppliers.data

import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.data.dtos.auth.UserRequest
import com.unsa.suppliers.data.dtos.auth.UserResponse
import com.unsa.suppliers.data.dtos.main.categories.CategoryRequest
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.data.dtos.main.countries.CountryRequest
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import com.unsa.suppliers.data.network.services.SupplierService
import com.unsa.suppliers.data.network.services.AuthService
import com.unsa.suppliers.data.network.services.CategoryService
import com.unsa.suppliers.data.network.services.CountryService
import javax.inject.Inject

class SupplierRepository @Inject constructor (
    private val authService: AuthService,
    private val supplierService: SupplierService,
    private val categoryService: CategoryService,
    private val countryService: CountryService
) {
    // Login Actions
    suspend fun attemptLogin(loginRequest: LoginRequest): LoginResponse? {
        return authService.login(loginRequest)
    }
    suspend fun attemptRegister(userRequest: UserRequest): UserResponse? {
        return authService.register(userRequest)
    }
    // Suppliers Actions
    suspend fun attemptGetSuppliers(): MutableList<SupplierResponse>? {
        return supplierService.getSuppliers()
    }
    suspend fun attemptGetSupplierById(id: Int): SupplierResponse? {
        return supplierService.getSupplierById(id)
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
    // Categories Actions
    suspend fun attemptGetCategories(): MutableList<CategoryResponse>? {
        return categoryService.getAllCategories()
    }
    suspend fun attemptCreateCategory(categoryRequest: CategoryRequest): CategoryResponse? {
        return categoryService.createCategory(categoryRequest)
    }
    // Countries Actions
    suspend fun attemptGetCountries(): MutableList<CountryResponse>? {
        return countryService.getAllCountries()
    }
    suspend fun attemptCreateCountry(countryRequest: CountryRequest): CountryResponse? {
        return countryService.createCountry(countryRequest)
    }
}