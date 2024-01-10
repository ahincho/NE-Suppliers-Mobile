package com.unsa.suppliers.domain.auth

import com.unsa.suppliers.data.PreferencesRepository
import javax.inject.Inject

class GetJwtTokenUseCase @Inject constructor (
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(): String {
        return preferencesRepository.getJwtToken()
    }
}