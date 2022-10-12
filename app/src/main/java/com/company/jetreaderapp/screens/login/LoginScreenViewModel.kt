package com.company.jetreaderapp.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.jetreaderapp.model.MUser
import com.company.jetreaderapp.utils.getDisplayName
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel(){
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit)
    = viewModelScope.launch {
        if(_loading.value == false) {
            _loading.value = true
            try {
                auth
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful) {
                            val displayName = getDisplayName(task.result.user?.email.toString())
                            createUser(displayName)
                            home()
                        }
                        else {
                            Log.d("TaskError", "Error: ${task.result}")
                        }
                        _loading.value = false
                    }
            } catch (ex: Exception) {
                Log.d("SignInError", "Error to SignIn: ${ex.message}")
            }
        }

    }

    private fun createUser(displayName: String?) {
        val userID = auth.currentUser?.uid
        val user = MUser(
            id = null,
            userId = userID.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Life is great",
            profession = "Android Developer").toMap()

        FirebaseFirestore.getInstance().collection("users").add(user)
    }

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
    = viewModelScope.launch {
        try {
            auth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful) {
                        home()
                    }
                    else {
                        Log.d("TaskError", "Error: ${task.result}")
                    }
                }
        } catch (ex: Exception) {
            Log.d("LoginError", "Error to login: ${ex.message}")
        }
    }


}