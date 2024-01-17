package com.unsa.suppliers.ui.view.main.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.unsa.suppliers.R
import com.unsa.suppliers.data.dtos.main.countries.CountryRequest
import com.unsa.suppliers.databinding.CountryDialogBinding
import com.unsa.suppliers.ui.view.main.MainActivity
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class CountryAddDialog : DialogFragment() {
    private lateinit var binding: CountryDialogBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CountryDialogBinding.inflate(layoutInflater)
        val mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(mainActivity)[MainViewModel::class.java]
        initListeners()
        return binding.root
    }
    private fun initListeners() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnSubmit.setOnClickListener {
            if (countryInputIsValid()) {
                mainViewModel.createCountry(CountryRequest(binding.createCountryName.text.toString()))
                dismiss()
            }
        }
    }
    private fun countryInputIsValid(): Boolean {
        return checkTextInput(binding.createCountryName, binding.createCountryNameHolder, getString(R.string.country_name_required))
    }
    private fun checkTextInput(inputText: TextInputEditText, inputLayout: TextInputLayout, errorMessage: String): Boolean {
        val isValid = inputText.text.toString().isNotBlank()
        inputLayout.error = if (isValid) null else errorMessage
        return isValid
    }
}
