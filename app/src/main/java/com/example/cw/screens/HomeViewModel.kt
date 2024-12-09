package com.example.cw.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.cw.R
import com.example.cw.screens.home.favorite.FavoriteViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

class HomeViewModel : KoinComponent{

    private val _firebaseUser = MutableStateFlow<FirebaseUser?>(Firebase.auth.currentUser)
    val firebaseUser: StateFlow<FirebaseUser?> = _firebaseUser

    fun onAuthComplete(user: FirebaseUser?){
        _firebaseUser.value = user
    }

    fun onLogout(){
        Firebase.auth.signOut()
        _firebaseUser.value = null
    }

}