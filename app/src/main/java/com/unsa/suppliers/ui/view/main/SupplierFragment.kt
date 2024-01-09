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
import java.text.FieldPosition

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
            initRecyclerView(mainViewModel.suppliers.value ?: emptyList())
        }
    }
    private fun initRecyclerView(suppliers: List<SupplierResponse>) {
        adapter = SupplierAdapter (
            suppliers = suppliers,
            deleteListener = { position -> onSupplierDelete(position) },
            inactivateListener = { position -> onSupplierInactivate(position) },
            reactivateListener = { position -> onSupplierReactivate(position) }
        )
        binding.suppliersRv.layoutManager = manager
        binding.suppliersRv.adapter = adapter
    }
    private fun onSupplierDelete(position: Int) {
        mainViewModel.suppliers.value?.get(position)?.let { mainViewModel.deleteSupplier(it.id) }
        mainViewModel.getSupplierById(position)
        adapter.notifyItemChanged(position)
    }
    private fun onSupplierInactivate(position: Int) {
        mainViewModel.inactivateSupplier(position)
        mainViewModel.getSupplierById(position)
        adapter.notifyItemChanged(position)
    }
    private fun onSupplierReactivate(position: Int) {
        mainViewModel.reactivateSupplier(position)
        mainViewModel.getSupplierById(position)
        adapter.notifyItemChanged(position)
    }
}