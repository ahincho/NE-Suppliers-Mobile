package com.unsa.suppliers.core

class Constants {
    companion object {
        // Base URL of API Rest Service
        const val API_REST_SERVICE = "https://suppliers-backend-testing.onrender.com"
        const val AUTH_ENDPOINT = "/api/auth"
        const val SUPPLIER_ENDPOINT = "/api/suppliers"
        const val COUNTRY_ENDPOINT = "/api/countries"
        const val CATEGORY_ENDPOINT = "/api/categories"
        // States on API Rest
        const val ACTIVE_STATE = "Active"
        const val DELETED_STATE = "Deleted"
        const val DISABLED_STATE = "Disabled"
    }
}