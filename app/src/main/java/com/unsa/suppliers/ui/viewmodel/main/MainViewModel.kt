package com.unsa.suppliers.ui.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.main.categories.CategoryRequest
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.data.dtos.main.countries.CountryRequest
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import com.unsa.suppliers.domain.main.categories.CreateCategoryUseCase
import com.unsa.suppliers.domain.main.categories.GetAllCategoriesUseCase
import com.unsa.suppliers.domain.main.countries.CreateCountryUseCase
import com.unsa.suppliers.domain.main.countries.GetAllCountriesUseCase
import com.unsa.suppliers.domain.main.suppliers.CreateSupplierUseCase
import com.unsa.suppliers.domain.main.suppliers.GetAllSuppliersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getAllSuppliersUseCase: GetAllSuppliersUseCase,
    private val createSupplierUseCase: CreateSupplierUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val createCountryUseCase: CreateCountryUseCase
) : ViewModel() {
    var suppliers = MutableLiveData<MutableList<SupplierResponse>>()
    var categories = MutableLiveData<MutableList<CategoryResponse>>()
    var countries = MutableLiveData<MutableList<CountryResponse>>()
    fun getAllSuppliers() {
        viewModelScope.launch {
            suppliers.postValue(getAllSuppliersUseCase() ?: mutableListOf())
        }
    }
    fun createSupplier(supplierRequest: SupplierRequest) {
        viewModelScope.launch {
            createSupplierUseCase(supplierRequest)
            suppliers.postValue(getAllSuppliersUseCase() ?: mutableListOf())
        }
    }
    fun getAllCategories() {
        viewModelScope.launch {
            categories.postValue(getAllCategoriesUseCase() ?: mutableListOf())
        }
    }
    fun createCategory(categoryRequest: CategoryRequest) {
        viewModelScope.launch {
            createCategoryUseCase(categoryRequest)
            categories.postValue(getAllCategoriesUseCase() ?: mutableListOf())
        }
    }
    fun getAllCountries() {
        viewModelScope.launch {
            countries.postValue(getAllCountriesUseCase() ?: mutableListOf())
        }
    }
    fun createCountry(countryRequest: CountryRequest) {
        viewModelScope.launch {
            createCountryUseCase(countryRequest)
            countries.postValue(getAllCountriesUseCase() ?: mutableListOf())
        }
    }
}