package com.unsa.suppliers.data.model

class UserProvider {
    companion object {
        fun login(username: String, password: String): Boolean {
            val user = users.find { it.username == username }
            return user?.let { it.password == password } ?: false
        }
        private val users = listOf<UserModel> (
            UserModel(1, "Angel", "Hincho", "ahincho", "ahincho@unsa.edu.pe", "ahincho@dev"),
            UserModel(2, "Erick", "Anco", "eanco", "eanco@unsa.edu.pe", "eanco@dev@")
        )
    }
}