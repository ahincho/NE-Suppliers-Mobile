package com.unsa.suppliers.ui.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.main.countries.CountryRequest
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.domain.main.countries.DeleteCountryUseCase
import com.unsa.suppliers.domain.main.countries.DisableCountryUseCase
import com.unsa.suppliers.domain.main.countries.EnableCountryUseCase
import com.unsa.suppliers.domain.main.countries.GetCountryByIdUseCase
import com.unsa.suppliers.domain.main.countries.UpdateCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor (
    private val getCountryByIdUseCase: GetCountryByIdUseCase,
    private val updateCountryUseCase: UpdateCountryUseCase,
    private val deleteCountryUseCase: DeleteCountryUseCase,
    private val disableCountryUseCase: DisableCountryUseCase,
    private val enableCountryUseCase: EnableCountryUseCase,
) : ViewModel() {
    var country = MutableLiveData<CountryResponse>()
    fun getCountryById(id: Int) {
        viewModelScope.launch {
            country.postValue(getCountryByIdUseCase.invoke(id))
        }
    }
    fun updateCountry(id: Int, countryRequest: CountryRequest) {
        viewModelScope.launch {
            updateCountryUseCase(id, countryRequest)
            country.postValue(getCountryByIdUseCase.invoke(id))
        }
    }
    fun deleteCountry(id: Int) {
        viewModelScope.launch {
            deleteCountryUseCase(id)
            country.postValue(getCountryByIdUseCase.invoke(id))
        }
    }
    fun disableCountry(id: Int) {
        viewModelScope.launch {
            disableCountryUseCase(id)
            country.postValue(getCountryByIdUseCase.invoke(id))
        }
    }
    fun enableCountry(id: Int) {
        viewModelScope.launch {
            enableCountryUseCase(id)
            country.postValue(getCountryByIdUseCase.invoke(id))
        }
    }
}