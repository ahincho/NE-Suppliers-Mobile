package com.unsa.suppliers.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.unsa.suppliers.R
import com.unsa.suppliers.data.model.UserModel
import com.unsa.suppliers.data.model.UserProvider
import com.unsa.suppliers.databinding.FragmentRegisterBinding
import com.unsa.suppliers.ui.viewmodel.AuthViewModel

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val authActivity = activity as AuthActivity
        authViewModel = ViewModelProvider(authActivity)[AuthViewModel::class.java]
        initListeners()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun initListeners() {
        binding.registerBtnSend.setOnClickListener { saveUser() }
        binding.registerTvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
    private fun saveUser() {
        if (checkRegisterForm()) {
            val user = UserModel (
                recoverTextInput(binding.registerEtName),
                recoverTextInput(binding.registerEtLastname),
                recoverTextInput(binding.registerEtUsername),
                recoverTextInput(binding.registerEtEmail),
                recoverTextInput(binding.registerEtPassword)
            )
            UserProvider.add(user)
            Toast.makeText(context, "Registration was successful", Toast.LENGTH_SHORT).show()
        }
    }
    private fun recoverTextInput(inputEditText: TextInputEditText): String {
        return inputEditText.text.toString()
    }
    private fun checkRegisterForm(): Boolean {
        return checkTextInput(binding.registerEtName, binding.registerTilName, getString(R.string.name_required))
                && checkTextInput(binding.registerEtLastname, binding.registerTilLastname, getString(R.string.lastname_required))
                && checkTextInput(binding.registerEtUsername, binding.registerTilUsername, getString(R.string.username_required))
                && checkTextInput(binding.registerEtEmail, binding.registerTilEmail, getString(R.string.email_required))
                && checkTextInput(binding.registerEtPassword, binding.registerTilPassword, getString(R.string.password_required))
    }
    private fun checkTextInput(inputText: TextInputEditText, inputLayout: TextInputLayout, errorMessage: String): Boolean {
        val isValid = inputText.text.toString().isNotBlank()
        inputLayout.error = if (isValid) null else errorMessage
        return isValid
    }
}