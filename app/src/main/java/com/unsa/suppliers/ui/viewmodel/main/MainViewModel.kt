package com.unsa.suppliers.ui.viewmodel.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
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