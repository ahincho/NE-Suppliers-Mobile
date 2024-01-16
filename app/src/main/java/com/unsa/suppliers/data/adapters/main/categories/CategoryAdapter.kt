package com.unsa.suppliers.data.adapters.main.categories

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.unsa.suppliers.R
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.ui.view.main.categories.CategoryDetailActivity

class CategoryAdapter (private var categories: List<CategoryResponse>) : RecyclerView.Adapter<CategoryViewHolder>() {
    companion object {
        const val CATEGORY_ID = "CATEGORY_ID"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(layoutInflater.inflate(R.layout.item_category, parent, false))
    }
    override fun getItemCount(): Int {
        return categories.size
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryResponse = categories[position]
        holder.itemView.findViewById<CardView>(R.id.categoryCardView).setOnClickListener {
            val intent = Intent(it.context, CategoryDetailActivity::class.java)
            intent.putExtra(CATEGORY_ID, categories[position].id)
            it.context.startActivity(intent)
        }
        holder.renderCategory(categoryResponse)
    }
}