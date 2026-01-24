package com.example.gamervault.features.auth.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
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
import com.example.gamervault.features.auth.states.TypeField
import com.example.gamervault.features.auth.viewmodel.AuthViewModel
import com.example.gamervault.features.auth.viewmodel.FormViewModel
import com.example.gamervault.ui.components.CustomIcon
import com.example.gamervault.ui.components.CustomTextField

@Composable
fun SignUpRoute(
    authViewModel: AuthViewModel = hiltViewModel(),
    formViewModel: FormViewModel = viewModel(),
    onNavigateTo: (Route) -> Unit
) {
    val authUiState by authViewModel.authUiState
    val formUiState by formViewModel.formUiState
    val formUiData by formViewModel.formUiData

    SignUpScreen(
        authUiState = authUiState,
        formUiState = formUiState,
        formUiData = formUiData,
        hidePassword = formViewModel::hidePassword,
        onFormEvent = formViewModel::onEvent,
        onAuthEvent = authViewModel::onEvent,
        validateEmail = formViewModel::validateEmail,
        validatePassword = formViewModel::validatePassword,
        enableButton = formViewModel::enableButtonSubmit,
        onNavigateTo = onNavigateTo
    )

}

@Composable
fun SignUpScreen(
    authUiState: AuthUiState,
    formUiState: FormUiState,
    formUiData: DataForm,
    hidePassword: () -> Unit,
    onFormEvent: (FormEvent) -> Unit,
    onAuthEvent: (AuthEvent) -> Unit,
    enableButton: () -> Boolean,
    validateEmail: (String, String) -> Boolean,
    validatePassword: (String, String) -> Boolean,
    onNavigateTo: (Route) -> Unit
) {
    val context = LocalContext.current
    val errorEmailMessage = stringResource(R.string.email_invalid)
    val errorPasswordMessage = stringResource(R.string.password_invalid)
    val registerSuccess = stringResource(R.string.account_created_successfully)
    val toast = Toast.makeText(context, registerSuccess, Toast.LENGTH_SHORT)

    BackHandler(true) {
        onNavigateTo(Route.GameScreen)
    }

    LaunchedEffect(authUiState.isAuthenticated) {
        if (authUiState.isAuthenticated) {
            onNavigateTo(Route.GameScreen)
            onFormEvent(FormEvent.ResetForm)
            onFormEvent(FormEvent.ClearErrors)
            onAuthEvent(AuthEvent.ClearErrors)
            toast.show()
        }
    }

    FormTemplate(modifier = Modifier.fillMaxSize()) {
        Column {
            FormErrors(
                formUiState = formUiState,
                authUiState = authUiState,
                modifier = Modifier.fillMaxWidth()
            )
            BasicForm(
                titleButton = stringResource(R.string.sign_up),
                textToNavigate = stringResource(R.string.sign_in),
                dataForm = formUiData,
                formUiState = formUiState,
                onValueChange = { value, field ->
                    onFormEvent(FormEvent.OnValueChange(value, field))
                },
                onHidePassword = hidePassword,
                onNavigateTo = {
                    onNavigateTo(Route.SignInScreen)
                    onFormEvent(FormEvent.ResetForm)
                    onFormEvent(FormEvent.ClearErrors)
                    onAuthEvent(AuthEvent.ClearErrors)
                },
                isLoading = authUiState.isLoading,
                enableButton = enableButton(),
                onSubmit = {
                    val isEmailValid = validateEmail(formUiData.email, errorEmailMessage)
                    val isPasswordValid =
                        validatePassword(formUiData.password, errorPasswordMessage)
                    if (isEmailValid && isPasswordValid) {
                        onFormEvent(FormEvent.ClearErrors)
                        onAuthEvent(
                            AuthEvent.SignUp(
                                formUiData.email,
                                formUiData.password,
                                formUiData.username
                            )
                        )
                    }
                }
            ) {
                CustomTextField(
                    value = formUiData.username,
                    onValueChange = {
                        onFormEvent(
                            FormEvent.OnValueChange(it, TypeField.USERNAME)
                        )
                    },
                    placerHolder = stringResource(R.string.username),
                    leadingIcon = {
                        CustomIcon(
                            icon = R.drawable.icon_outline_person,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}