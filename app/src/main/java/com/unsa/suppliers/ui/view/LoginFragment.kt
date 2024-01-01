package com.unsa.suppliers.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.unsa.suppliers.R
import com.unsa.suppliers.databinding.FragmentLoginBinding
import com.unsa.suppliers.ui.viewmodel.AuthViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
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
        binding.loginBtnSend.setOnClickListener {
            if (usernameInputIsValid() && passwordInputIsValid()) {
                val username = binding.loginEtUsername.text.toString()
                val password = binding.loginEtPassword.text.toString()
                if (authViewModel.login(username, password)) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun usernameInputIsValid(): Boolean {
        return if (binding.loginEtUsername.text.toString().isBlank()) {
            binding.loginTilUsername.error = getString(R.string.username_required)
            false
        } else {
            binding.loginTilUsername.error = null
            true
        }
    }
    private fun passwordInputIsValid(): Boolean {
        return if (binding.loginEtPassword.text.toString().isBlank()) {
            binding.loginTilPassword.error = getString(R.string.password_required)
            false
        } else {
            binding.loginTilPassword.error = null
            true
        }
    }
}