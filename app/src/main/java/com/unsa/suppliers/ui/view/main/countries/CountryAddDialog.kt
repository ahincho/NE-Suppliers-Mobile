package com.unsa.suppliers.ui.view.main.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.unsa.suppliers.R
import com.unsa.suppliers.data.dtos.main.countries.CountryRequest
import com.unsa.suppliers.ui.view.main.MainActivity
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class CountryAddDialog : DialogFragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var tilName: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var btnCancel: Button
    private lateinit var btnSubmit: Button
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.country_dialog, container, false)
        tilName = rootView.findViewById(R.id.createCountryHolder)
        etName = rootView.findViewById(R.id.createCountryName)
        btnCancel = rootView.findViewById(R.id.cancelButton)
        btnSubmit = rootView.findViewById(R.id.submitButton)
        val mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(mainActivity)[MainViewModel::class.java]
        initListeners()
        return rootView
    }
    private fun initListeners() {
        btnCancel.setOnClickListener {
            dismiss()
        }
        btnSubmit.setOnClickListener {
            if (countryInputIsValid()) {
                mainViewModel.createCountry(CountryRequest(etName.text.toString()))
                dismiss()
            }
        }
    }
    private fun countryInputIsValid(): Boolean {
        return checkTextInput(etName, tilName, getString(R.string.country_name_required))
    }
    private fun checkTextInput(inputText: TextInputEditText, inputLayout: TextInputLayout, errorMessage: String): Boolean {
        val isValid = inputText.text.toString().isNotBlank()
        inputLayout.error = if (isValid) null else errorMessage
        return isValid
    }
}
