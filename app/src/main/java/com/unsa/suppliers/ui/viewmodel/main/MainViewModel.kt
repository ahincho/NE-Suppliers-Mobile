package com.unsa.suppliers.ui.viewmodel.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import com.unsa.suppliers.domain.DeleteSupplierUseCase
import com.unsa.suppliers.domain.GetAllSuppliersUseCase
import com.unsa.suppliers.domain.GetJwtTokenUseCase
import com.unsa.suppliers.domain.GetSupplierByIdUseCase
import com.unsa.suppliers.domain.InactivateSupplierUseCase
import com.unsa.suppliers.domain.ReactivateSupplierUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getJwtTokenUseCase: GetJwtTokenUseCase,
    private val getAllSuppliersUseCase: GetAllSuppliersUseCase,
    private val getSupplierByIdUseCase: GetSupplierByIdUseCase,
    private val deleteSupplierUseCase: DeleteSupplierUseCase,
    private val inactivateSupplierUseCase: InactivateSupplierUseCase,
    private val reactivateSupplierUseCase: ReactivateSupplierUseCase
) : ViewModel() {
    var suppliers = MutableLiveData<MutableList<SupplierResponse>>()
    fun getToken(): String {
        var token: String? = null
        viewModelScope.launch {
            token = getJwtTokenUseCase()
            Log.d("MAIN VIEW MODEL PREFERENCE", token!!)
        }
        return token ?: "Nothing"
    }
    fun getAllSuppliers() {
        viewModelScope.launch {
            suppliers.postValue(getAllSuppliersUseCase() ?: mutableListOf())
        }
        Log.d("MAIN VIEW MODEL", suppliers.toString())
    }
    fun getSupplierById(id: Int) {
        viewModelScope.launch {
            val supplier = getSupplierByIdUseCase.invoke(id)
            if (supplier.id != 0) {
                suppliers.value?.set(supplier.id, supplier)
            }
        }
    }
    fun deleteSupplier(id: Int) {
        viewModelScope.launch {
            deleteSupplierUseCase(id)
        }
    }
    fun inactivateSupplier(id: Int) {
        viewModelScope.launch {
            inactivateSupplierUseCase(id)
        }
    }
    fun reactivateSupplier(id: Int) {
        viewModelScope.launch {
            reactivateSupplierUseCase(id)
        }
    }
}