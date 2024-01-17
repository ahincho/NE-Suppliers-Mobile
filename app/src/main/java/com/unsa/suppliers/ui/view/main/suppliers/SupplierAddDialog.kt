package com.unsa.suppliers.ui.view.main.suppliers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.unsa.suppliers.R
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import com.unsa.suppliers.databinding.SupplierDialogBinding
import com.unsa.suppliers.ui.view.main.MainActivity
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class SupplierAddDialog : DialogFragment() {
    companion object {
        const val RUC_LENGTH: Int = 11
    }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: SupplierDialogBinding
    private var selectedCategoryId: Int = -1
    private var selectedCountryId: Int = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SupplierDialogBinding.inflate(layoutInflater)
        val mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(mainActivity)[MainViewModel::class.java]
        initUserInterface()
        initListeners()
        return binding.root
    }
    private fun initUserInterface() {
        val categories = mainViewModel.categories.value?.map { it.name } ?.toTypedArray()
        if (categories != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)
            binding.dropCategories.setAdapter(adapter)
        }
        val countries = mainViewModel.countries.value?.map { it.name } ?.toTypedArray()
        if (countries != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, countries)
            binding.dropCountries.setAdapter(adapter)
        }
    }
    private fun initListeners() {
        binding.dropCategories.setOnItemClickListener { _, _, position, _ ->
            selectedCategoryId = mainViewModel.categories.value?.get(position)?.id ?: -1
        }
        binding.dropCountries.setOnItemClickListener { _, _, position, _ ->
            selectedCountryId = mainViewModel.countries.value?.get(position)?.id ?: -1
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnSubmit.setOnClickListener {
            if (supplierInputIsValid()) {
                val name = binding.createSupplierName.text.toString()
                val ruc = binding.createSupplierRuc.text.toString()
                mainViewModel.createSupplier(SupplierRequest(name, ruc, selectedCategoryId, selectedCountryId))
                dismiss()
            }
        }
    }
    private fun supplierInputIsValid(): Boolean {
        val isNameValid = checkTextInput(binding.createSupplierName, binding.createSupplierNameHolder, getString(R.string.supplier_name_required))
        val isRucNotEmpty = checkTextInput(binding.createSupplierRuc, binding.createSupplierRucHolder, getString(R.string.supplier_ruc_required))
        val isRucValid = checkTextLength(binding.createSupplierRuc, binding.createSupplierRucHolder, getString(R.string.supplier_ruc_too_short))
        val isCategorySelected = selectedCategoryId != -1
        val isCountrySelected = selectedCountryId != -1
        binding.dropCategoriesHolder.error = if (isCategorySelected) null else getString(R.string.category_required_error)
        binding.dropCountriesHolder.error = if (isCountrySelected) null else getString(R.string.country_required_error)
        return isNameValid && isRucNotEmpty && isRucValid && isCategorySelected && isCountrySelected
    }
    private fun checkTextLength(inputText: TextInputEditText, inputLayout: TextInputLayout, errorMessage: String): Boolean {
        val isValid = inputText.text.toString().length == RUC_LENGTH
        inputLayout.error = if (isValid) null else errorMessage
        return isValid
    }
    private fun checkTextInput(inputText: TextInputEditText, inputLayout: TextInputLayout, errorMessage: String): Boolean {
        val isValid = inputText.text.toString().isNotBlank()
        inputLayout.error = if (isValid) null else errorMessage
        return isValid
    }
}