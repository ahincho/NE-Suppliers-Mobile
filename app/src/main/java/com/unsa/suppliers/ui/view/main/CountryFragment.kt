package com.unsa.suppliers.ui.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.unsa.suppliers.databinding.FragmentCountryBinding
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class CountryFragment : Fragment() {
    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(mainActivity)[MainViewModel::class.java]
        initListeners()
    }
    private fun initListeners() {

    }
}