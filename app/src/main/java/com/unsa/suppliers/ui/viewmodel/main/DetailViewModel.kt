package com.unsa.suppliers.ui.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import com.unsa.suppliers.domain.DeleteSupplierUseCase
import com.unsa.suppliers.domain.DisableSupplierUseCase
import com.unsa.suppliers.domain.EnableSupplierUseCase
import com.unsa.suppliers.domain.GetSupplierByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    private val getSupplierByIdUseCase: GetSupplierByIdUseCase,
    private val deleteSupplierUseCase: DeleteSupplierUseCase,
    private val disableSupplierUseCase: DisableSupplierUseCase,
    private val enableSupplierUseCase: EnableSupplierUseCase
) : ViewModel() {
    var supplier = MutableLiveData<SupplierResponse>()
    fun getSupplierById(id: Int) {
        viewModelScope.launch {
            supplier.postValue(getSupplierByIdUseCase.invoke(id))
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