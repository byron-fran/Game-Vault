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
import com.example.gamervault.features.auth.events.AuthEvent
import com.example.gamervault.features.auth.events.FormEvent
import com.example.gamervault.features.auth.states.AuthUiState
import com.example.gamervault.features.auth.states.DataForm
import com.example.gamervault.features.auth.states.FormUiState
import com.example.gamervault.features.auth.viewmodel.AuthViewModel
import com.example.gamervault.features.auth.viewmodel.FormViewModel

@Composable
fun SignInRoute(
    authViewModel: AuthViewModel = hiltViewModel(),
    formViewModel: FormViewModel = viewModel(),
    onNavigateTo : (Route) -> Unit
) {
    val authUiState by authViewModel.authUiState
    val formUiState by formViewModel.formUiState
    val formUiData by formViewModel.formUiData

    SignInScreen(
        authUiState=authUiState,
        formUiState = formUiState,
        formUiData = formUiData,
        onFormEvent = formViewModel::onEvent,
        onAuthEvent = authViewModel::onEvent,
        validatePassword = formViewModel::validatePassword,
        validateEmail = formViewModel::validateEmail,
        enableButton = formViewModel::enableButtonSubmit,
        onNavigateTo = onNavigateTo

    )
}


@Composable
fun SignInScreen(
    authUiState: AuthUiState,
    formUiState: FormUiState,
    formUiData: DataForm,
    onFormEvent : (FormEvent) -> Unit,
    onAuthEvent : (AuthEvent) -> Unit,
    validatePassword : (String, String) -> Boolean,
    validateEmail : (String, String)->Boolean,
    enableButton : () -> Boolean,
    onNavigateTo: (Route) -> Unit
) {
    val context = LocalContext.current
    val errorEmailMessage = stringResource(R.string.email_invalid)
    val errorPasswordMessage = stringResource(R.string.password_invalid)
    val loginSuccess = stringResource(R.string.login_successful)
    val toast = Toast.makeText(context, loginSuccess, Toast.LENGTH_SHORT)

    LaunchedEffect(authUiState.isAuthenticated) {
        if (authUiState.isAuthenticated) {
            onNavigateTo(Route.GameScreen)
            onFormEvent(FormEvent.ResetForm)
            onFormEvent(FormEvent.ClearErrors)
            onFormEvent(FormEvent.ClearErrors)
            onAuthEvent(AuthEvent.ClearErrors)
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
                dataForm = formUiData,
                formUiState = formUiState,
                onValueChange = { value, field ->
                    onFormEvent(FormEvent.OnValueChange(value, field))
                },
                onHidePassword = {
                    onFormEvent(FormEvent.HidePassword)
                },
                isLoading = authUiState.isLoading,
                onNavigateTo = {
                    onNavigateTo(Route.SignUpScreen)
                    onFormEvent(FormEvent.ResetForm)
                    onFormEvent(FormEvent.ClearErrors)
                    onFormEvent(FormEvent.ClearErrors)
                },
                enableButton = enableButton(),
                onSubmit = {
                    val isEmailValid = validateEmail(
                        formUiData.email,
                        errorEmailMessage
                    )
                    val isPasswordValid = validatePassword(
                        formUiData.password,
                        errorPasswordMessage
                    )
                    if (isEmailValid && isPasswordValid) {
                        onFormEvent(FormEvent.ClearErrors)
                        onAuthEvent(
                            AuthEvent.SignIn(
                                formUiData.email,
                                formUiData.password
                            )
                        )
                    }
                }
            )
        }
    }
}