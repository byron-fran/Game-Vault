package com.example.gamervault.features.auth.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gamervault.features.auth.events.FormEvent
import com.example.gamervault.features.auth.states.DataForm
import com.example.gamervault.features.auth.states.FormUiState
import com.example.gamervault.features.auth.states.TypeField

class FormViewModel : ViewModel() {
    private var _formUiData = mutableStateOf(DataForm())
    val formUiData = _formUiData
    private var _formUiState = mutableStateOf(FormUiState())
    val formUiState = _formUiState

    fun onValueChange(value: String, field: TypeField) {
        when (field) {
            TypeField.EMAIL -> _formUiData.value = _formUiData.value.copy(email = value)
            TypeField.PASSWORD -> _formUiData.value = _formUiData.value.copy(password = value)
            TypeField.USERNAME -> _formUiData.value = _formUiData.value.copy(username = value)
        }
    }

    fun enableButtonSubmit(): Boolean {
        return formUiData.value.email.trim().isNotEmpty() && formUiData.value.password.trim().isNotEmpty()
    }

    fun hidePassword() {
        _formUiState.value = _formUiState.value.copy(hidePassword = !_formUiState.value.hidePassword)
    }

    fun validateEmail(email: String, messageError : String): Boolean {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$".toRegex()
        val isEmailValid = emailRegex.matches(email)
        if (!isEmailValid) {
            _formUiState.value = _formUiState.value.copy(errorEmail = messageError)
        } else {
            _formUiState.value = _formUiState.value.copy(errorEmail = "")
        }
        return isEmailValid
    }

    fun validatePassword(password: String, messageError : String): Boolean {
        if (password.length < 6) {
            _formUiState.value = _formUiState.value.copy(
                errorPassword = messageError
            )
            return false
        } else {
            _formUiState.value = _formUiState.value.copy(errorPassword = "")
            return true
        }
    }

    fun clearErrors() {
        _formUiState.value = _formUiState.value.copy(
            errorEmail = "",
            errorPassword = ""
        )
    }

    fun resetForm() {
        _formUiData.value = _formUiData.value.copy(
            email = "",
            password = "",
            username = ""
        )
    }

    fun onEvent(event : FormEvent) {
        when(event) {
            is FormEvent.ResetForm -> resetForm()
            is FormEvent.ClearErrors -> clearErrors()
            is FormEvent.HidePassword -> hidePassword()
            is FormEvent.OnValueChange -> onValueChange(event.value, event.field)
        }
    }
}