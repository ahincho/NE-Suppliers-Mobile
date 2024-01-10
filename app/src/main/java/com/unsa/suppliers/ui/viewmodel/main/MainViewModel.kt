package com.unsa.suppliers.ui.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import com.unsa.suppliers.domain.main.suppliers.GetAllSuppliersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getAllSuppliersUseCase: GetAllSuppliersUseCase,
) : ViewModel() {
    var suppliers = MutableLiveData<MutableList<SupplierResponse>>()
    var categories = MutableLiveData<MutableList<CategoryResponse>>()
    var countries = MutableLiveData<MutableList<CountryResponse>>()
    fun getAllSuppliers() {
        viewModelScope.launch {
            suppliers.postValue(getAllSuppliersUseCase() ?: mutableListOf())
        }
    }
    fun getAllCategories() {
        viewModelScope.launch {

        }
    }
}