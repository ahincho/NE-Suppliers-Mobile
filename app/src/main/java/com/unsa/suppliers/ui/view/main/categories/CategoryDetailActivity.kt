package com.unsa.suppliers.ui.view.main.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.unsa.suppliers.data.adapters.main.categories.CategoryAdapter
import com.unsa.suppliers.databinding.ActivityCategoryDetailBinding
import com.unsa.suppliers.ui.viewmodel.main.CategoryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryDetailBinding
    private val categoryDetailViewModel: CategoryDetailViewModel by viewModels()
    private var categoryId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        categoryId = intent?.getIntExtra(CategoryAdapter.CATEGORY_ID, -1) ?: -1
        setContentView(binding.root)
        categoryDetailViewModel.getCategoryById(categoryId)
        categoryDetailViewModel.category.observe(this) { loadSupplierInfo() }
        initListeners()
    }
    private fun initListeners() {
        binding.btnDelete.setOnClickListener {
            categoryDetailViewModel.deleteCategory(categoryId)
        }
        binding.btnDisable.setOnClickListener {
            categoryDetailViewModel.disableCategory(categoryId)
        }
        binding.btnEnable.setOnClickListener {
            categoryDetailViewModel.enableCategory(categoryId)
        }
    }
    private fun loadSupplierInfo() {
        binding.detailCategoryName.text = categoryDetailViewModel.category.value!!.name
        binding.detailSupplierState.text = categoryDetailViewModel.category.value!!.state
    }
}