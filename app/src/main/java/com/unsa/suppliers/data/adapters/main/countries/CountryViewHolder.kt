package com.unsa.suppliers.data.adapters.main.countries

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.databinding.ItemCategoryBinding
import com.unsa.suppliers.databinding.ItemCountryBinding

class CountryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCountryBinding.bind(view)
    fun renderCountry(countryResponse: CountryResponse) {
        binding.itemCountryId.text = countryResponse.id.toString()
        binding.itemCategoryName.text = countryResponse.name
        binding.itemCategoryState.text = countryResponse.state
    }
}