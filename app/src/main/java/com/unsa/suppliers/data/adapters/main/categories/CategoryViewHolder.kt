package com.unsa.suppliers.data.adapters.main.categories

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.databinding.ItemCategoryBinding

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCategoryBinding.bind(view)
    fun renderCategory(categoryResponse: CategoryResponse) {
        binding.itemCategoryId.text = categoryResponse.id.toString()
        binding.itemCategoryName.text = categoryResponse.name
        binding.itemCategoryState.text = categoryResponse.state
    }
}