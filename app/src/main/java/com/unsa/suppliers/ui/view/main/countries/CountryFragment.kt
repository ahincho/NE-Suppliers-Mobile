package com.unsa.suppliers.ui.view.main.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsa.suppliers.data.adapters.main.countries.CountryAdapter
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.databinding.FragmentCountryBinding
import com.unsa.suppliers.ui.view.main.MainActivity
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class CountryFragment : Fragment() {
    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: CountryAdapter
    private val manager = LinearLayoutManager(context)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(mainActivity)[MainViewModel::class.java]
        mainViewModel.countries.observe(viewLifecycleOwner) {
            initRecyclerView(mainViewModel.countries.value ?: emptyList())
        }
        initListeners()
    }
    override fun onResume() {
        super.onResume()
        mainViewModel.getAllCountries()
    }
    private fun initRecyclerView(countries: List<CountryResponse>) {
        adapter = CountryAdapter(countries)
        binding.countriesRecyclerView.layoutManager = manager
        binding.countriesRecyclerView.adapter = adapter
        binding.countrySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredCountries = mainViewModel.countries.value?.filter {
                        country -> country.name.contains(newText.orEmpty(), ignoreCase = true)
                }
                adapter.updateCountries(filteredCountries)
                return true
            }
        })
    }
    private fun initListeners() {
        binding.countryBtnAdd.setOnClickListener {
            val dialog = CountryAddDialog()
            dialog.show(childFragmentManager, "Country Add Dialog")
        }
    }
}