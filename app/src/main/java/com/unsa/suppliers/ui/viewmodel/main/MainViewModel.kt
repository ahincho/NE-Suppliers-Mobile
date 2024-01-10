package com.unsa.suppliers.ui.viewmodel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import com.unsa.suppliers.domain.GetAllSuppliersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getAllSuppliersUseCase: GetAllSuppliersUseCase
) : ViewModel() {
    var suppliers = MutableLiveData<MutableList<SupplierResponse>>()
    fun getAllSuppliers() {
        viewModelScope.launch {
            suppliers.postValue(getAllSuppliersUseCase() ?: mutableListOf())
        }
    }
}