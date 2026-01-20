package com.example.gamervault.features.auth.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamervault.R
import com.example.gamervault.core.navigation.Route
import com.example.gamervault.domain.models.User
import com.example.gamervault.features.auth.states.DataForm
import com.example.gamervault.features.auth.states.FormUiState
import com.example.gamervault.features.auth.viewmodel.AuthViewModel
import com.example.gamervault.features.auth.viewmodel.FormViewModel
import com.example.gamervault.ui.components.BodyLarge
import com.example.gamervault.ui.components.CustomButton
import com.example.gamervault.ui.components.CustomIcon
import com.example.gamervault.ui.components.CustomIconButton
import com.example.gamervault.ui.components.CustomTextField
import com.example.gamervault.ui.screens.EmptyScreen
import com.example.gamervault.ui.screens.LoadingScreen
import com.example.gamervault.ui.theme.GamerVaultTheme

@Composable
fun AccountScreen(
    authViewModel: AuthViewModel,
    formViewModel : FormViewModel = viewModel(),
    onNavigateTo: (Route) -> Unit
) {

    val authUiState by authViewModel.authUiState

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        when {
            authUiState.isLoading -> {
                LoadingScreen(modifier = Modifier.fillMaxSize())
            }
            authUiState.isError?.isNotEmpty() == true -> {
                EmptyScreen(modifier = Modifier.fillMaxSize()) {
                    BodyLarge(text = authUiState.isError!!, maxLines = 3)
                }
            }
            else -> {
                if (authUiState.isAuthenticated && authUiState.user != null ) {
                    authUiState.user?.let {
                        AccountBody(dataForm = DataForm(
                            email = it.email ?: "",
                            username = it.username ?: "",
                        ),
                            formUiState = formViewModel.formUiState.value,
                            modifier = Modifier.fillMaxSize()) {
                            authViewModel.signOut()
                        }
                    }
                } else {
                    EmptyScreen(modifier = Modifier.fillMaxSize()) {
                        CustomButton(content = { BodyLarge(text = stringResource(R.string.signin_or_create_account)) }
                        ) {
                            onNavigateTo(Route.SignInScreen)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AccountBody(
    dataForm: DataForm,
    formUiState: FormUiState,
    modifier: Modifier = Modifier,
    signOut: () -> Unit
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(32.dp))
        AccountDetail(dataForm, formUiState)
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            containerColor = MaterialTheme.colorScheme.error,
            modifier = Modifier.fillMaxWidth(),
            content = {
                BodyLarge(
                    text = stringResource(R.string.logout),
                    color = MaterialTheme.colorScheme.onError
                )
            }
        ) {
            signOut()
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

}

@Composable
fun AccountDetail(
    dataForm : DataForm,
    formUiState: FormUiState
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CustomTextField(
            value =dataForm.email,
            readOnly = formUiState.readOnlyEmail,
            onValueChange = {
                //TODO update email
            },
            placerHolder = stringResource(R.string.email),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                CustomIcon(icon = R.drawable.icon_outline_email)
            },

        )
        CustomTextField(
            value =dataForm.username,
            readOnly = formUiState.readOnlyUsername,
            onValueChange = {
                // TODO update username
            },
            placerHolder = stringResource(R.string.username),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                CustomIcon(icon = R.drawable.icon_outline_person)
            },
        )
    }
}