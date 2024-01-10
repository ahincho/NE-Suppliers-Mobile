package com.unsa.suppliers.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.unsa.suppliers.R

class SupplierAddDialog : DialogFragment() {
    private lateinit var btnCancel: Button
    private lateinit var btnSubmit: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.supplier_dialog, container, false)
        btnCancel = rootView.findViewById(R.id.cancelButton)
        btnSubmit = rootView.findViewById(R.id.submitButton)
        btnCancel.setOnClickListener {
            dismiss()
        }
        btnSubmit.setOnClickListener {

        }
        return rootView
    }
}