package com.unsa.suppliers.ui.viewmodel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import com.unsa.suppliers.domain.GetAllSuppliersUseCase
import com.unsa.suppliers.domain.GetJwtTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getJwtTokenUseCase: GetJwtTokenUseCase,
    private val getAllSuppliersUseCase: GetAllSuppliersUseCase
) : ViewModel() {
    val suppliers = MutableLiveData<List<SupplierResponse>>()
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
            suppliers.postValue(getAllSuppliersUseCase.invoke())
        }
    }
}