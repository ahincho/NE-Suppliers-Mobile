package com.unsa.suppliers.data.model

class UserProvider {
    companion object {
        fun login(username: String, password: String): Boolean {
            val user = users.find { it.username == username }
            return user?.let { it.password == password } ?: false
        }
        fun add(user: UserModel) {
            users.add(user)
        }
        private val users = mutableListOf<UserModel> (
            UserModel("Angel", "Hincho", "ahincho", "ahincho@unsa.edu.pe", "ahincho@dev"),
            UserModel("Erick", "Anco", "eanco", "eanco@unsa.edu.pe", "eanco@dev@")
        )
    }
}