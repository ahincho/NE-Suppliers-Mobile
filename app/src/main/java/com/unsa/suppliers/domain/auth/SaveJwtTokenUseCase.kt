package com.unsa.suppliers.domain.auth

import com.unsa.suppliers.data.PreferencesRepository
import javax.inject.Inject

class SaveJwtTokenUseCase @Inject constructor (
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(token: String) {
        preferencesRepository.saveJwtToken(token)
    }
}