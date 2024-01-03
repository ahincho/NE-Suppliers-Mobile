package com.unsa.suppliers.ui.view.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsa.suppliers.data.adapters.SupplierAdapter
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import com.unsa.suppliers.databinding.FragmentSupplierBinding
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class SupplierFragment : Fragment() {
    private var _binding: FragmentSupplierBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: SupplierAdapter
    private val manager = LinearLayoutManager(context)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSupplierBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(mainActivity)[MainViewModel::class.java]
        mainViewModel.getAllSuppliers()
        mainViewModel.suppliers.observe(viewLifecycleOwner) {
            Log.d("SUPPLIER FRAGMENT", (mainViewModel.suppliers.value ?: "ERROR").toString())
            updateRecyclerView(mainViewModel.suppliers.value ?: emptyList())
        }
        initListeners()
    }
    private fun updateRecyclerView(suppliers: List<SupplierResponse>) {
        adapter = SupplierAdapter(suppliers)
        binding.suppliersRv.layoutManager = manager
        binding.suppliersRv.adapter = adapter
    }
    private fun initListeners() {

    }
}