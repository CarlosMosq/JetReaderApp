package com.company.jetreaderapp.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.company.jetreaderapp.components.UserForm
import com.company.jetreaderapp.navigation.ScreensList

@ExperimentalComposeUiApi
@Composable
fun ReaderLoginScreen(
    navController: NavController,
    loginScreenViewModel: LoginScreenViewModel = viewModel()) {


    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "A. Reader",
                style = MaterialTheme.typography.h3,
                color = Color.Red.copy(alpha = 0.5f))

            if(showLoginForm.value) {
                UserForm(loading = false, isCreateAccount = false) { email, password ->
                    loginScreenViewModel
                        .signInWithEmailAndPassword(email = email, password = password) {
                            navController.navigate(ScreensList.ReaderHomeScreen.name)
                        }
                }
            }
            else {
                UserForm(loading = false, isCreateAccount = true) { email, password ->
                    loginScreenViewModel
                        .createUserWithEmailAndPassword(email = email, password = password) {
                            navController.navigate(ScreensList.ReaderHomeScreen.name)
                        }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            
            Row(
                modifier = Modifier.padding(15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text = if(showLoginForm.value) "Sign Up" else "Login"
                Text(text = "New User?")
                Text(
                    text = text,
                    modifier = Modifier
                        .clickable {
                            showLoginForm.value = !showLoginForm.value
                        }
                        .padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondaryVariant
                )
            }


        }
    }
}