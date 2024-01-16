package com.unsa.suppliers.ui.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.domain.main.categories.DeleteCategoryUseCase
import com.unsa.suppliers.domain.main.categories.DisableCategoryUseCase
import com.unsa.suppliers.domain.main.categories.EnableCategoryUseCase
import com.unsa.suppliers.domain.main.categories.GetCategoryByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor (
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val disableCategoryUseCase: DisableCategoryUseCase,
    private val enableCategoryUseCase: EnableCategoryUseCase,
) : ViewModel() {
    var category = MutableLiveData<CategoryResponse>()
    fun getCategoryById(id: Int) {
        viewModelScope.launch {
            category.postValue(getCategoryByIdUseCase.invoke(id))
        }
    }
    fun deleteCategory(id: Int) {
        viewModelScope.launch {
            deleteCategoryUseCase(id)
            category.postValue(getCategoryByIdUseCase.invoke(id))
        }
    }
    fun disableCategory(id: Int) {
        viewModelScope.launch {
            disableCategoryUseCase(id)
            category.postValue(getCategoryByIdUseCase.invoke(id))
        }
    }
    fun enableCategory(id: Int) {
        viewModelScope.launch {
            enableCategoryUseCase(id)
            category.postValue(getCategoryByIdUseCase.invoke(id))
        }
    }
}