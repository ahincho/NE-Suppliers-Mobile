package com.unsa.suppliers.ui.view.main.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.unsa.suppliers.R
import com.unsa.suppliers.core.Constants
import com.unsa.suppliers.data.adapters.main.countries.CountryAdapter
import com.unsa.suppliers.data.dtos.main.countries.CountryRequest
import com.unsa.suppliers.databinding.ActivityCountryDetailBinding
import com.unsa.suppliers.ui.viewmodel.main.CountryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryDetailBinding
    private val countryDetailViewModel: CountryDetailViewModel by viewModels()
    private lateinit var state: String
    private var countryId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        countryId = intent?.getIntExtra(CountryAdapter.COUNTRY_ID, -1) ?: -1
        setContentView(binding.root)
        countryDetailViewModel.getCountryById(countryId)
        countryDetailViewModel.country.observe(this) { loadCountryInfo() }
        initUserInterface()
        initListeners()
    }
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (checkStateWasEdited() || checkCategoryWasEdited()) {
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
        val states = resources.getStringArray(R.array.states)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, states)
        binding.dropStates.setAdapter(adapter)
    }
    private fun initListeners() {
        this.onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        binding.dropStates.setOnClickListener {
            binding.dropStates.text.clear()
        }
        binding.dropStates.setOnItemClickListener { _, _, position, _ ->
            state = binding.dropStates.adapter.getItem(position).toString()
        }
        binding.btnSave.setOnClickListener {
            if (checkStateWasEdited()) {
                when (state) {
                    Constants.ACTIVE_STATE -> if (shouldPerformAction(Constants.ACTIVE_STATE)) countryDetailViewModel.enableCountry(countryId)
                    Constants.DELETED_STATE -> if (shouldPerformAction(Constants.DELETED_STATE)) countryDetailViewModel.deleteCountry(countryId)
                    Constants.DISABLED_STATE -> if (shouldPerformAction(Constants.DISABLED_STATE)) countryDetailViewModel.disableCountry(countryId)
                }
                if (checkCategoryWasEdited()) {
                    countryDetailViewModel.updateCountry(countryId, CountryRequest(binding.detailCountryName.text.toString()))
                }
                Toast.makeText(this, "Country Updated", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun shouldPerformAction(desiredState: String): Boolean {
        return countryDetailViewModel.country.value?.state != desiredState
    }
    private fun checkStateWasEdited(): Boolean {
        val isNotEmpty = state.isNotEmpty()
        val isEdited = !state.equals(countryDetailViewModel.country.value?.state, true)
        return isNotEmpty && isEdited
    }
    private fun checkCategoryWasEdited(): Boolean {
        return !binding.detailCountryName.text.toString().equals(countryDetailViewModel.country.value?.name, true)
    }
    private fun loadCountryInfo() {
        binding.detailCountryName.text = Editable.Factory.getInstance().newEditable(countryDetailViewModel.country.value!!.name)
        binding.dropStates.text = Editable.Factory.getInstance().newEditable(countryDetailViewModel.country.value!!.state)
        state = countryDetailViewModel.country.value!!.state
    }
}