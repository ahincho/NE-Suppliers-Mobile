package com.unsa.suppliers.ui.viewmodel.suppliers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupplierViewModel @Inject constructor (
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    fun getToken(): String {
        var token: String? = null
        viewModelScope.launch {
            token = preferencesRepository.getJwtToken()
            Log.d("PREFERENCE", token!!)
        }
        return token ?: "Nothing"
    }
}