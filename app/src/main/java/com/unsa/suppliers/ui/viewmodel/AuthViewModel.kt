package com.unsa.suppliers.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.unsa.suppliers.data.model.UserProvider

class AuthViewModel : ViewModel() {
    fun login(username: String, password: String): Boolean {
        return UserProvider.login(username, password)
    }
}