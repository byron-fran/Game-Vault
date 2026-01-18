package com.example.gamervault.features.auth.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamervault.R
import com.example.gamervault.core.navigation.Route
import com.example.gamervault.features.auth.components.BasicForm
import com.example.gamervault.features.auth.components.FormErrors
import com.example.gamervault.features.auth.components.FormTemplate
import com.example.gamervault.features.auth.viewmodel.AuthViewModel
import com.example.gamervault.features.auth.viewmodel.FormViewModel

@Composable
fun SignInScreen(
    formViewModel: FormViewModel = viewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateTo: (Route) -> Unit
) {
    val context = LocalContext.current
    val authUiState by authViewModel.authUiState
    val formUiState by formViewModel.formUiState
    val errorEmailMessage = stringResource(R.string.email_invalid)
    val errorPasswordMessage = stringResource(R.string.password_invalid)
    val loginSuccess = stringResource(R.string.login_successful)
    val toast = Toast.makeText(context, loginSuccess, Toast.LENGTH_SHORT)

    LaunchedEffect(authUiState.isAuthenticated) {
        if (authUiState.isAuthenticated) {
            onNavigateTo(Route.GameScreen)
            formViewModel.resetForm()
            formViewModel.clearErrors()
            toast.show()
        }
    }

    BackHandler(true) {
        onNavigateTo(Route.GameScreen)
    }

    FormTemplate(modifier = Modifier.fillMaxSize()) {

        Column {
            FormErrors(
                formUiState = formUiState,
                authUiState = authUiState,
                modifier = Modifier.fillMaxWidth()
            )
            BasicForm(
                titleButton = stringResource(R.string.sign_in),
                textToNavigate = stringResource(R.string.sign_up),
                dataForm = formViewModel.formUiData.value,
                formUiState = formUiState,
                onValueChange = formViewModel::onValueChange,
                onHidePassword = formViewModel::hidePassword,
                isLoading = authUiState.isLoading,
                onNavigateTo = {
                    onNavigateTo(Route.SignUpScreen)
                    formViewModel.clearErrors()
                    formViewModel.resetForm()
                    authViewModel.clearError()
                },
                enableButton = formViewModel.enableButtonSubmit(),
                onSubmit = {
                    val isEmailValid = formViewModel.validateEmail(
                        formViewModel.formUiData.value.email,
                        messageError = errorEmailMessage
                    )
                    val isPasswordValid = formViewModel.validatePassword(
                        formViewModel.formUiData.value.password,
                        messageError = errorPasswordMessage
                    )
                    if (isEmailValid && isPasswordValid) {
                        formViewModel.clearErrors()
                        authViewModel.signIn(
                            formViewModel.formUiData.value.email,
                            formViewModel.formUiData.value.password
                        )
                    }
                }
            )
        }
    }
}