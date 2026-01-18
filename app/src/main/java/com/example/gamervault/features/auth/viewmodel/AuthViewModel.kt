package com.example.gamervault.features.auth.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamervault.domain.models.AuthFailureException
import com.example.gamervault.domain.usecases.GetCurrentUserUseCase
import com.example.gamervault.domain.usecases.IsAuthenticatedUseCase
import com.example.gamervault.domain.usecases.SignInUseCase
import com.example.gamervault.domain.usecases.SignOutUseCase
import com.example.gamervault.domain.usecases.SignUpUseCase
import com.example.gamervault.features.auth.states.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val isAuthenticatedUseCase: IsAuthenticatedUseCase
) : ViewModel() {

    private var _authUiState = mutableStateOf(AuthUiState())
    val authUiState = _authUiState

    init {
        viewModelScope.launch {
            _authUiState.value = _authUiState.value.copy(isLoading = true)
            try {
                val isAuthenticated = isAuthenticatedUseCase()
                val currentUser = getCurrentUserUseCase()
                _authUiState.value = _authUiState.value.copy(
                    isAuthenticated = isAuthenticated,
                    user = currentUser,
                    isLoading = false
                )
            } catch (e: AuthFailureException) {
                _authUiState.value = _authUiState.value.copy(
                    isError = e.message,
                    isLoading = false,
                    isAuthenticated = false,
                    user = null
                )
            } catch (e: Exception) {
                _authUiState.value = _authUiState.value.copy(
                    isError = e.message,
                    isLoading = false,
                    isAuthenticated = false,
                    user = null
                )
            }
        }
    }

    fun signIn(email: String, password: String) {

        viewModelScope.launch {
            _authUiState.value = _authUiState.value.copy(isLoading = true)
            try {
                signInUseCase(email, password)
                val isAuthenticated = isAuthenticatedUseCase()
                val currentUser = getCurrentUserUseCase()

                _authUiState.value = _authUiState.value.copy(
                    isLoading = false,
                    isAuthenticated = isAuthenticated,
                    user = currentUser
                )

            } catch (e: AuthFailureException) {
                _authUiState.value = _authUiState.value.copy(
                    isLoading = false,
                    isError = e.message
                )
            } catch (e: Exception) {
                _authUiState.value = _authUiState.value.copy(
                    isLoading = false,
                    isError = e.message
                )
            }
        }
    }

    fun signUp(email: String, password: String, username: String) {

        viewModelScope.launch {
            _authUiState.value = _authUiState.value.copy(isLoading = true)
            try {
                signUpUseCase(email, password, username)
                val isAuthenticated = isAuthenticatedUseCase()
                val currentUser = getCurrentUserUseCase()

                _authUiState.value = _authUiState.value.copy(
                    isLoading = false,
                    isAuthenticated = isAuthenticated,
                    user = currentUser
                )

            } catch (e: AuthFailureException) {
                _authUiState.value = _authUiState.value.copy(
                    isLoading = false,
                    isError = e.message,
                    isAuthenticated = false
                )
            } catch (e: Exception) {
                _authUiState.value = _authUiState.value.copy(
                    isLoading = false,
                    isError = e.message,
                    isAuthenticated = false
                )
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                signOutUseCase()
                _authUiState.value = _authUiState.value.copy(
                    isAuthenticated = false,
                    isError = null,
                    user = null,
                    isLoading = false
                )
            } catch (e: AuthFailureException) {
                _authUiState.value = _authUiState.value.copy(
                    isError = e.message,
                    isLoading = false
                )
            } catch (e: Exception) {
                _authUiState.value = _authUiState.value.copy(
                    isError = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun clearError() {
        _authUiState.value = _authUiState.value.copy(isError = null)
    }
}
