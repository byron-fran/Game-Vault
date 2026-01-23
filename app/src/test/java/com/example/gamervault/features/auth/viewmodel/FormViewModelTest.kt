package com.example.gamervault.features.auth.viewmodel

import com.example.gamervault.features.auth.states.TypeField
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class FormViewModelTest {

    private lateinit var formViewModel: FormViewModel

    @Before
    fun setUp() {
        formViewModel = FormViewModel()

    }

    @Test
    fun `initial formUiData is empty`() {

        assertEquals("", formViewModel.formUiData.value.email)
        assertEquals("", formViewModel.formUiData.value.password)
        assertEquals("", formViewModel.formUiData.value.username)

    }

    @Test
    fun `initial formUiState is empty`() {

        assertEquals(true, formViewModel.formUiState.value.hidePassword)
        assertEquals(null, formViewModel.formUiState.value.errorEmail)
        assertEquals(null, formViewModel.formUiState.value.errorPassword)
    }

    @Test
    fun `onValueChange updates formUiData correctly`() {

        val email = "byron99@gmail"
        val password = "byron123"
        val username = "byron fran"

        formViewModel.onValueChange(email, TypeField.EMAIL)
        formViewModel.onValueChange(password, TypeField.PASSWORD)
        formViewModel.onValueChange(username, TypeField.USERNAME)

        assertEquals(email , formViewModel.formUiData.value.email)
        assertEquals(password, formViewModel.formUiData.value.password)
        assertEquals(username, formViewModel.formUiData.value.username)

    }

    @Test
    fun `enableButtonSubmit returns true when email and password are not empty`() {
        val email = "byron99@gmail"
        val password = "byron123"
        formViewModel.formUiData.value.email = email
        formViewModel.formUiData.value.password = password

        val isEnable = formViewModel.enableButtonSubmit()

        assertEquals(true, isEnable)
    }

    @Test
    fun `enableButtonSubmit returns false when email is empty`() {

        formViewModel.formUiData.value =
            formViewModel.formUiData.value.copy(email = "", password = "byron123")

        assertEquals(false, formViewModel.enableButtonSubmit())
    }

    @Test
    fun `enableButtonSubmit returns false when password is empty`() {

        formViewModel.formUiData.value =
            formViewModel.formUiData.value.copy(email = "byron99@gmail", password = "")

        assertEquals(false, formViewModel.enableButtonSubmit())
    }

    @Test
    fun `enableButtonSubmit returns false when both email and password are empty`() {

        formViewModel.formUiData.value =
            formViewModel.formUiData.value.copy(email = "", password = "")

        assertEquals(false, formViewModel.enableButtonSubmit())
    }

    @Test
    fun `hidePassword toggles hidePassword state`() {
        formViewModel.formUiState.value.hidePassword = true

        formViewModel.hidePassword()

        assertEquals(false, formViewModel.formUiState.value.hidePassword)
    }

    @Test
    fun `validateEmail return true when email is valid`() {
        val email = "byron99@gmail.com"

        val isEmailValid = formViewModel.validateEmail(email, messageError = "")

        assertEquals(true, isEmailValid)
        assertEquals("", formViewModel.formUiState.value.errorEmail)

    }

    @Test
    fun `validateEmail return false when email is invalid`() {
        val email = "byron99"
        val errorEmail = "Email is invalid"

        val isEmailValid = formViewModel.validateEmail(email, messageError = errorEmail)

        assertEquals(false, isEmailValid)
        assertEquals(errorEmail, formViewModel.formUiState.value.errorEmail)
    }


    @Test
    fun `validatePassword return true when password is valid`() {
        val password = "123456"

        val isPasswordValid = formViewModel.validatePassword(password, messageError = "")

        assertEquals(true, isPasswordValid)
        assertEquals("", formViewModel.formUiState.value.errorPassword)

    }


    @Test
    fun `validatePassword returns false when password length is 5`() {
        val password = "12345"
        val errorPassword = "Password too short"

        val isPasswordValid = formViewModel.validatePassword(password, messageError = errorPassword)

        assertEquals(false, isPasswordValid)
        assertEquals(errorPassword, formViewModel.formUiState.value.errorPassword)
    }

    @Test
    fun `validatePassword return false when password is invalid`() {
        val password = ""
        val errorPassword = "Password is invalid"

        val isPasswordValid = formViewModel.validatePassword(password, messageError = errorPassword)

        assertEquals(false, isPasswordValid)
        assertEquals(errorPassword, formViewModel.formUiState.value.errorPassword)
    }

    @Test
    fun `clearErrors clears error messages`() {
        formViewModel.formUiState.value.errorEmail = "Invalid email"
        formViewModel.formUiState.value.errorPassword = "Invalid password"

        formViewModel.clearErrors()

        assertEquals("", formViewModel.formUiState.value.errorEmail)
        assertEquals("", formViewModel.formUiState.value.errorPassword)
    }

    @Test
    fun `resetForm resets formUiData`() {
        formViewModel.formUiData.value.email = "byron99@gmail"
        formViewModel.formUiData.value.password = "byron123"

        formViewModel.resetForm()

        assertEquals("", formViewModel.formUiData.value.email)
        assertEquals("", formViewModel.formUiData.value.password)
    }
}