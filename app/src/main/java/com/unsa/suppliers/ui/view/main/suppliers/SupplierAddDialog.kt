package com.unsa.suppliers.ui.view.main.suppliers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.unsa.suppliers.R
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import com.unsa.suppliers.ui.view.main.MainActivity
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class SupplierAddDialog : DialogFragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var tilName: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var tilRuc: TextInputLayout
    private lateinit var etRuc: TextInputEditText
    private lateinit var dropCategory: AutoCompleteTextView
    private lateinit var dropCountry: AutoCompleteTextView
    private lateinit var btnCancel: Button
    private lateinit var btnSubmit: Button
    private var selectedCategoryId: Int = -1
    private var selectedCountryId: Int = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.supplier_dialog, container, false)
        tilName = rootView.findViewById(R.id.createSupplierNameHolder)
        etName = rootView.findViewById(R.id.createSupplierName)
        tilRuc = rootView.findViewById(R.id.createSupplierRucHolder)
        etRuc = rootView.findViewById(R.id.createSupplierRuc)
        dropCategory = rootView.findViewById(R.id.dropCategories)
        dropCountry = rootView.findViewById(R.id.dropCountries)
        btnCancel = rootView.findViewById(R.id.cancelButton)
        btnSubmit = rootView.findViewById(R.id.submitButton)
        val mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(mainActivity)[MainViewModel::class.java]
        initUserInterface()
        initListeners()
        return rootView
    }
    private fun initUserInterface() {
        val categories = mainViewModel.categories.value?.map { it.name }?.toTypedArray()
        if (categories != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)
            dropCategory.setAdapter(adapter)
        }
        val countries = mainViewModel.countries.value?.map { it.name }?.toTypedArray()
        if (countries != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, countries)
            dropCountry.setAdapter(adapter)
        }
    }
    private fun initListeners() {
        dropCategory.setOnItemClickListener { _, _, position, _ ->
            selectedCategoryId = mainViewModel.categories.value?.get(position)?.id ?: -1
        }
        dropCountry.setOnItemClickListener { _, _, position, _ ->
            selectedCountryId = mainViewModel.countries.value?.get(position)?.id ?: -1
        }
        btnCancel.setOnClickListener {
            dismiss()
        }
        btnSubmit.setOnClickListener {
            if (supplierInputIsValid()) {
                val name = etName.text.toString()
                val ruc = etRuc.text.toString()
                mainViewModel.createSupplier(SupplierRequest(name, ruc, selectedCategoryId, selectedCountryId))
                dismiss()
            }
        }
    }
    private fun supplierInputIsValid(): Boolean {
        val isNameValid = checkTextInput(etName, tilName, getString(R.string.supplier_name_required))
        val isRucValid = checkTextInput(etRuc, tilRuc, getString(R.string.supplier_ruc_required))
        val isCategorySelected = selectedCategoryId != -1
        val isCountrySelected = selectedCountryId != -1
        dropCategory.error = if (isCategorySelected) null else getString(R.string.category_required_error)
        dropCountry.error = if (isCountrySelected) null else getString(R.string.country_required_error)
        return isNameValid && isRucValid && isCategorySelected && isCountrySelected
    }
    private fun checkTextInput(inputText: TextInputEditText, inputLayout: TextInputLayout, errorMessage: String): Boolean {
        val isValid = inputText.text.toString().isNotBlank()
        inputLayout.error = if (isValid) null else errorMessage
        return isValid
    }
}