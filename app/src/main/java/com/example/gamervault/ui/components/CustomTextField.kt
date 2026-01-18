package com.example.gamervault.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.gamervault.R

@Composable
fun  CustomTextField(
    value : String,
    onValueChange : (String) -> Unit,
    placerHolder : String,
    readOnly : Boolean = false,
    trailingIcon : @Composable (() -> Unit)? = null,
    leadingIcon : @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation : VisualTransformation = VisualTransformation.None,
    modifier : Modifier = Modifier,

    ) {


    TextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            cursorColor = MaterialTheme.colorScheme.primary,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        readOnly = readOnly,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        maxLines = 1,
        visualTransformation = visualTransformation,
        placeholder = {
            LabelMedium(placerHolder, color = MaterialTheme.colorScheme.onSurface)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier
    )
}

@Composable
fun CustomTextFieldPassword(
    value : String,
    modifier : Modifier = Modifier,
    onValueChange : (String) -> Unit,
    hiddenPassword : Boolean = true,
    onHiddenChange : (Boolean) -> Unit = {}
) {
    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        placerHolder = stringResource(R.string.password),
        modifier = modifier,
        keyboardType = KeyboardType.Password,
        visualTransformation = if (hiddenPassword) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = {
            CustomIcon(icon = R.drawable.icon_outline_lock, tint = MaterialTheme.colorScheme.onSurface)
        },
        trailingIcon = {
            CustomIconButton(
                icon = if(hiddenPassword) R.drawable.icon_eye_fill else R.drawable.icon_eye_slash_fill,
                tint = MaterialTheme.colorScheme.onSurface) {
                onHiddenChange(!hiddenPassword)
            }
        },
    )
}