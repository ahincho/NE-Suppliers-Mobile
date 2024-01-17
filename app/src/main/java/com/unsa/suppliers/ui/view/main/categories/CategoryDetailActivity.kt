package com.unsa.suppliers.ui.view.main.categories

import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.unsa.suppliers.R
import com.unsa.suppliers.core.Constants
import com.unsa.suppliers.data.adapters.main.categories.CategoryAdapter
import com.unsa.suppliers.data.dtos.main.categories.CategoryRequest
import com.unsa.suppliers.databinding.ActivityCategoryDetailBinding
import com.unsa.suppliers.ui.viewmodel.main.CategoryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryDetailBinding
    private val categoryDetailViewModel: CategoryDetailViewModel by viewModels()
    private lateinit var state: String
    private var categoryId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        categoryId = intent?.getIntExtra(CategoryAdapter.CATEGORY_ID, -1) ?: -1
        setContentView(binding.root)
        categoryDetailViewModel.getCategoryById(categoryId)
        categoryDetailViewModel.category.observe(this) { loadCategoryInfo() }
        initUserInterface()
        initListeners()
    }
    private fun initUserInterface() {
        val states = resources.getStringArray(R.array.states)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, states)
        binding.dropStates.setAdapter(adapter)
    }
    private fun initListeners() {
        binding.dropStates.setOnClickListener {
            binding.dropStates.text.clear()
        }
        binding.dropStates.setOnItemClickListener { _, _, position, _ ->
            state = binding.dropStates.adapter.getItem(position).toString()
        }
        binding.btnSave.setOnClickListener {
            if (checkStateWasEdited()) {
                when (state) {
                    Constants.ACTIVE_STATE -> if (shouldPerformAction(Constants.ACTIVE_STATE)) categoryDetailViewModel.enableCategory(categoryId)
                    Constants.DELETED_STATE -> if (shouldPerformAction(Constants.DELETED_STATE)) categoryDetailViewModel.deleteCategory(categoryId)
                    Constants.DISABLED_STATE -> if (shouldPerformAction(Constants.DISABLED_STATE)) categoryDetailViewModel.disableCategory(categoryId)
                }
                if (checkCategoryWasEdited()) {
                    categoryDetailViewModel.updateCategory(categoryId, CategoryRequest(binding.detailCategoryName.text.toString()))
                }
                Toast.makeText(this, "Category Updated", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun shouldPerformAction(desiredState: String): Boolean {
        return categoryDetailViewModel.category.value?.state != desiredState
    }
    private fun checkStateWasEdited(): Boolean {
        val isNotEmpty = state.isNotEmpty()
        val isEdited = !state.equals(categoryDetailViewModel.category.value?.state, true)
        return isNotEmpty && isEdited
    }
    private fun checkCategoryWasEdited(): Boolean {
        return !binding.detailCategoryName.text.toString().equals(categoryDetailViewModel.category.value.toString(), true)
    }
    private fun loadCategoryInfo() {
        binding.detailCategoryName.text = Editable.Factory.getInstance().newEditable(categoryDetailViewModel.category.value!!.name)
        binding.dropStates.text = Editable.Factory.getInstance().newEditable(categoryDetailViewModel.category.value!!.state)
        state = categoryDetailViewModel.category.value!!.state
    }
}