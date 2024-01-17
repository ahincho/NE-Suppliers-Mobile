package com.unsa.suppliers.ui.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import com.unsa.suppliers.domain.main.categories.GetAllCategoriesUseCase
import com.unsa.suppliers.domain.main.countries.GetAllCountriesUseCase
import com.unsa.suppliers.domain.main.suppliers.DeleteSupplierUseCase
import com.unsa.suppliers.domain.main.suppliers.DisableSupplierUseCase
import com.unsa.suppliers.domain.main.suppliers.EnableSupplierUseCase
import com.unsa.suppliers.domain.main.suppliers.GetSupplierByIdUseCase
import com.unsa.suppliers.domain.main.suppliers.UpdateSupplierUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupplierDetailViewModel @Inject constructor (
    private val getSupplierByIdUseCase: GetSupplierByIdUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val updateSupplierUseCase: UpdateSupplierUseCase,
    private val deleteSupplierUseCase: DeleteSupplierUseCase,
    private val disableSupplierUseCase: DisableSupplierUseCase,
    private val enableSupplierUseCase: EnableSupplierUseCase,
) : ViewModel() {
    var supplier = MutableLiveData<SupplierResponse>()
    var categories = MutableLiveData<MutableList<CategoryResponse>>()
    var countries = MutableLiveData<MutableList<CountryResponse>>()
    fun getSupplierById(id: Int) {
        viewModelScope.launch {
            supplier.postValue(getSupplierByIdUseCase.invoke(id))
        }
    }
    fun updateSupplier(id: Int, supplierRequest: SupplierRequest) {
        viewModelScope.launch {
            updateSupplierUseCase(id, supplierRequest)
            supplier.postValue(getSupplierByIdUseCase.invoke(id))
        }
    }
    fun getAllCategories() {
        viewModelScope.launch {
            categories.postValue(getAllCategoriesUseCase.invoke())
        }
    }
    fun getAllCountries() {
        viewModelScope.launch {
            countries.postValue(getAllCountriesUseCase.invoke())
        }
    }
    fun deleteSupplier(id: Int) {
        viewModelScope.launch {
            deleteSupplierUseCase(id)
            supplier.postValue(getSupplierByIdUseCase.invoke(id))
        }
    }
    fun disableSupplier(id: Int) {
        viewModelScope.launch {
            disableSupplierUseCase(id)
            supplier.postValue(getSupplierByIdUseCase.invoke(id))
        }
    }
    fun enableSupplier(id: Int) {
        viewModelScope.launch {
            enableSupplierUseCase(id)
            supplier.postValue(getSupplierByIdUseCase.invoke(id))
        }
    }
}