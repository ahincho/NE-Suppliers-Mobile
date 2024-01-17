package com.unsa.suppliers.data.adapters.main.countries

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.unsa.suppliers.R
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.ui.view.main.countries.CountryDetailActivity

class CountryAdapter (
    private var countries: List<CountryResponse>
): RecyclerView.Adapter<CountryViewHolder>() {
    companion object {
        const val COUNTRY_ID = "COUNTRY_ID"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(layoutInflater.inflate(R.layout.item_country, parent, false))
    }
    override fun getItemCount(): Int {
        return countries.size
    }
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countryResponse = countries[position]
        holder.itemView.findViewById<CardView>(R.id.countryCardView).setOnClickListener {
            val intent = Intent(it.context, CountryDetailActivity::class.java)
            intent.putExtra(COUNTRY_ID, countries[position].id)
            it.context.startActivity(intent)
        }
        holder.renderCountry(countryResponse)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateCountries(countries: List<CountryResponse>?) {
        this.countries = countries ?: emptyList()
        notifyDataSetChanged()
    }
}