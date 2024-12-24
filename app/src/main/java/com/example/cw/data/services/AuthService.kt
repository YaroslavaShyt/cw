package com.example.cw.data.services

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.cw.domain.services.IAuthService
import com.example.cw.domain.services.IUserService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class AuthService(private val userService: IUserService) : IAuthService {
    private var _user = MutableStateFlow(Firebase.auth.currentUser)
    private var _googleSignInClient: GoogleSignInClient? = null

    override var user: StateFlow<FirebaseUser?> = _user

    override fun signInWithGoogle(token: String, context: Context): GoogleSignInClient? {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(token)
                .requestEmail()
                .build()
        _googleSignInClient = GoogleSignIn.getClient(context, gso)
        return _googleSignInClient
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
        _googleSignInClient?.signOut()?.await()
        _user.value = null
        userService.cleanData()
    }

    @Composable
    override fun authFlow(
        onAuthSuccess: (user: FirebaseUser) -> Unit,
        onAuthFailure: () -> Unit
    ): ManagedActivityResultLauncher<Intent, ActivityResult> {
        val scope = rememberCoroutineScope()
        return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                scope.launch {
                    val authResult = Firebase.auth.signInWithCredential(credential).await()
                    System.out.println("in auth result: ${authResult.user}")
                    if (authResult.user != null) {
                        _user.value = authResult.user
                        userService.initUser(_user.value!!.uid, _user.value)
                        onAuthSuccess(_user.value!!)
                    } else {
                        onAuthFailure()
                    }

                }
            } catch (e: ApiException) {
                onAuthFailure()
            }
        }
    }


}