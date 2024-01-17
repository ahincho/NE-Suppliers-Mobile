package com.unsa.suppliers.ui.view.main.suppliers

import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.unsa.suppliers.R
import com.unsa.suppliers.core.Constants
import com.unsa.suppliers.data.adapters.main.suppliers.SupplierAdapter.Companion.SUPPLIER_ID
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import com.unsa.suppliers.databinding.ActivitySupplierDetailBinding
import com.unsa.suppliers.ui.viewmodel.main.SupplierDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupplierDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySupplierDetailBinding
    private val supplierDetailViewModel: SupplierDetailViewModel by viewModels()
    private lateinit var name: String
    private lateinit var ruc: String
    private lateinit var state: String
    private var categoryId: Int? = -1
    private var countryId: Int? = -1
    private var supplierId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierDetailBinding.inflate(layoutInflater)
        supplierId = intent?.getIntExtra(SUPPLIER_ID, -1) ?: -1
        setContentView(binding.root)
        supplierDetailViewModel.getSupplierById(supplierId)
        supplierDetailViewModel.getAllCategories()
        supplierDetailViewModel.getAllCountries()
        supplierDetailViewModel.supplier.observe(this) { loadSupplierInfo() }
        supplierDetailViewModel.categories.observe(this) { loadCategories() }
        supplierDetailViewModel.countries.observe(this) { loadCountries() }
        initUserInterface()
        initListeners()
    }
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (checkStateWasEdited() || checkSupplierWasEdited()) {
                showExitConfirmationDialog()
            } else {
                finish()
            }
        }
    }
    fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Unsaved Changes")
        builder.setMessage("Are you sure you want to exit without saving changes?")
        builder.setPositiveButton("Yes") { _, _ ->
            finish()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun initUserInterface() {
        this.onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
        val stateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.states))
        binding.dropStates.setAdapter(stateAdapter)
    }
    private fun initListeners() {
        binding.dropStates.setOnClickListener {
            binding.dropStates.text.clear()
        }
        binding.dropStates.setOnItemClickListener { _, _, position, _ ->
            state = binding.dropStates.adapter.getItem(position).toString()
        }
        binding.dropCategories.setOnClickListener {
            binding.dropCategories.text.clear()
        }
        binding.dropCategories.setOnItemClickListener { _, _, _, _ ->
            val selectedCategoryName = binding.dropCategories.text.toString()
            val selectedCategory = supplierDetailViewModel.categories.value?.find { it.name == selectedCategoryName }
            categoryId = selectedCategory?.id ?: -1
        }
        binding.dropCountries.setOnItemClickListener { _, _, _, _ ->
            val selectedCountryName = binding.dropCountries.text.toString()
            val selectedCountry = supplierDetailViewModel.countries.value?.find { it.name == selectedCountryName }
            countryId = selectedCountry?.id ?: -1
        }
        binding.dropCountries.setOnClickListener {
            binding.dropCountries.text.clear()
        }
        binding.btnSave.setOnClickListener {
            if (checkStateWasEdited()) {
                when (state) {
                    Constants.ACTIVE_STATE -> if (shouldPerformAction(Constants.ACTIVE_STATE)) supplierDetailViewModel.enableSupplier(supplierId)
                    Constants.DELETED_STATE -> if (shouldPerformAction(Constants.DELETED_STATE)) supplierDetailViewModel.deleteSupplier(supplierId)
                    Constants.DISABLED_STATE -> if (shouldPerformAction(Constants.DISABLED_STATE)) supplierDetailViewModel.disableSupplier(supplierId)
                }
            }
            if (checkSupplierWasEdited()) {
                supplierDetailViewModel.updateSupplier(supplierId, SupplierRequest(binding.detailSupplierName.text.toString(), binding.detailSupplierRuc.text.toString(), categoryId!!, countryId!!))
            }
            Toast.makeText(this, "Supplier Updated", Toast.LENGTH_SHORT).show()
        }
    }
    private fun shouldPerformAction(desiredState: String): Boolean {
        return supplierDetailViewModel.supplier.value?.state != desiredState
    }
    private fun checkStateWasEdited(): Boolean {
        val isNotEmpty = state.isNotEmpty()
        val isEdited = !state.equals(supplierDetailViewModel.supplier.value?.state, true)
        return isNotEmpty && isEdited
    }
    private fun checkSupplierWasEdited(): Boolean {
        val isNameNotEmpty = name.isNotEmpty()
        val isRucNotEmpty = ruc.isNotEmpty()
        val isNameEdited = !binding.detailSupplierName.text.toString().equals(supplierDetailViewModel.supplier.value?.name, true)
        val isRucEdited = !binding.detailSupplierRuc.text.toString().equals(supplierDetailViewModel.supplier.value?.ruc, true)
        val isCategoryEdited = categoryId != supplierDetailViewModel.categories.value?.find { it.name == supplierDetailViewModel.supplier.value?.category } ?.id
        val isCountryEdited = countryId != supplierDetailViewModel.countries.value?.find { it.name == supplierDetailViewModel.supplier.value?.country } ?.id
        return (isNameNotEmpty && isNameEdited) || (isRucNotEmpty && isRucEdited) || isCategoryEdited || isCountryEdited
    }
    private fun loadCategories() {
        val categories = supplierDetailViewModel.categories.value?.map { it.name } ?: emptyList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        categoryId = supplierDetailViewModel.categories.value?.find { it.name == supplierDetailViewModel.supplier.value?.category } ?.id
        binding.dropCategories.setAdapter(adapter)
    }
    private fun loadCountries() {
        val countriesList = supplierDetailViewModel.countries.value?.map { it.name } ?: emptyList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countriesList)
        countryId = supplierDetailViewModel.countries.value?.find { it.name == supplierDetailViewModel.supplier.value?.country } ?.id
        binding.dropCountries.setAdapter(adapter)
    }
    private fun loadSupplierInfo() {
        binding.detailSupplierName.text = Editable.Factory.getInstance().newEditable(supplierDetailViewModel.supplier.value!!.name)
        binding.detailSupplierRuc.text = Editable.Factory.getInstance().newEditable(supplierDetailViewModel.supplier.value!!.ruc)
        binding.dropCategories.text = Editable.Factory.getInstance().newEditable(supplierDetailViewModel.supplier.value!!.category)
        binding.dropCountries.text = Editable.Factory.getInstance().newEditable(supplierDetailViewModel.supplier.value!!.country)
        binding.dropStates.text = Editable.Factory.getInstance().newEditable(supplierDetailViewModel.supplier.value!!.state)
        name = supplierDetailViewModel.supplier.value!!.name
        ruc = supplierDetailViewModel.supplier.value!!.ruc
        state = supplierDetailViewModel.supplier.value!!.state
    }
}