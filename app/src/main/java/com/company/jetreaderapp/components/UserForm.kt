package com.company.jetreaderapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
fun UserForm(
    loading: Boolean = false,
    isCreateAccount: Boolean,
    onDone: (String, String) -> Unit
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val modifier = Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(
            emailState = email,
            enabled = !loading,
            onAction = KeyboardActions {passwordFocusRequest.requestFocus()}
        )

        PasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if(!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())}
        )

        SubmitButton(
            textId = if(isCreateAccount) "Create Account" else "Login",
            loading = loading,
            validInputs = valid) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }

}






