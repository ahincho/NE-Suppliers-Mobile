package com.unsa.suppliers.ui.view.main.suppliers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.unsa.suppliers.R
import com.unsa.suppliers.ui.view.main.MainActivity
import com.unsa.suppliers.ui.viewmodel.main.MainViewModel

class SupplierAddDialog : DialogFragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var btnCancel: Button
    private lateinit var btnSubmit: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.supplier_dialog, container, false)
        btnCancel = rootView.findViewById(R.id.cancelButton)
        btnSubmit = rootView.findViewById(R.id.submitButton)
        val mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(mainActivity)[MainViewModel::class.java]
        initListeners()
        return rootView
    }
    private fun initListeners() {
        btnCancel.setOnClickListener {
            dismiss()
        }
        btnSubmit.setOnClickListener {

        }
    }
}