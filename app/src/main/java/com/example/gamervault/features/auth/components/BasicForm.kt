package com.example.gamervault.features.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamervault.R
import com.example.gamervault.features.auth.states.DataForm
import com.example.gamervault.features.auth.states.FormUiState
import com.example.gamervault.features.auth.states.TypeField
import com.example.gamervault.ui.components.BodyLarge
import com.example.gamervault.ui.components.BodyMedium
import com.example.gamervault.ui.components.CustomButton
import com.example.gamervault.ui.components.CustomIcon
import com.example.gamervault.ui.components.CustomTextField
import com.example.gamervault.ui.components.CustomTextFieldPassword

@Composable
fun BasicForm(
    titleButton : String,
    textToNavigate : String,
    modifier: Modifier = Modifier,
    dataForm: DataForm,
    formUiState: FormUiState,
    onValueChange: (String, TypeField) -> Unit,
    onNavigateTo: () -> Unit,
    onHidePassword: () -> Unit,
    enableButton: Boolean,
    isLoading: Boolean,
    onSubmit: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)? = null
) {


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        content?.invoke(this)
        CustomTextField(
            value = dataForm.email,
            onValueChange = { onValueChange(it, TypeField.EMAIL) },
            placerHolder = "Email",
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                CustomIcon(
                    icon = R.drawable.icon_outline_email,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        )
        CustomTextFieldPassword(
            value = dataForm.password,
            onValueChange = { onValueChange(it, TypeField.PASSWORD) },
            hiddenPassword = formUiState.hidePassword,
            modifier = Modifier.fillMaxWidth(),
        ) {
            onHidePassword()
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            enabled = enableButton && !isLoading,
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
            content = {
                if (isLoading) CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                else BodyLarge(titleButton)
            }
        ) { onSubmit() }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            BodyMedium(
                text = textToNavigate,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(end = 8.dp).clickable { onNavigateTo() }
            )
        }
    }
}