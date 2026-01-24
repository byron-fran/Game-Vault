package com.example.gamervault.features.auth.events

import com.example.gamervault.features.auth.states.TypeField

sealed interface FormEvent {

    data class OnValueChange(val value: String, val field: TypeField) : FormEvent
    data object HidePassword : FormEvent
    data object ClearErrors : FormEvent
    data object ResetForm : FormEvent

}