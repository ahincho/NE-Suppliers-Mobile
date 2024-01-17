package com.unsa.suppliers.ui.view.main.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.unsa.suppliers.R
import com.unsa.suppliers.data.dtos.main.categories.CategoryRequest
import com.unsa.suppliers.databinding.CategoryDialogBinding
import com.unsa.suppliers.ui.view.main.MainActivity
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class CategoryAddDialog : DialogFragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: CategoryDialogBinding
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CategoryDialogBinding.inflate(layoutInflater)
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
            if (categoryInputIsValid()) {
                mainViewModel.createCategory(CategoryRequest(binding.createCategoryName.text.toString()))
                dismiss()
            }
        }
    }
    private fun categoryInputIsValid(): Boolean {
        return checkTextInput(binding.createCategoryName, binding.createCategoryNameHolder, getString(R.string.category_name_required))
    }
    private fun checkTextInput(inputText: TextInputEditText, inputLayout: TextInputLayout, errorMessage: String): Boolean {
        val isValid = inputText.text.toString().isNotBlank()
        inputLayout.error = if (isValid) null else errorMessage
        return isValid
    }
}