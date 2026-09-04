package com.iwaji.vietstep.ui.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import com.iwaji.vietstep.ui.auth.AuthViewModel
import com.iwaji.vietstep.util.GoogleLoginHelper
import android.util.Log

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    isOnline: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    var isLoggingIn by remember {mutableStateOf(false)}

    val googleSignInClient =
        GoogleLoginHelper.getClient(context)

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            val task =
                GoogleSignIn
                    .getSignedInAccountFromIntent(
                        result.data
                    )

            try {
                val account =
                    task.getResult(
                        ApiException::class.java
                    )

                firebaseAuthWithGoogle(
                    account.idToken!!
                ) {
                    authViewModel.refreshLoginState()

                    isLoggingIn = false
                    onDismiss()
                }

            } catch (e: Exception) {
                isLoggingIn = false

                Log.e(
                    "GoogleLogin",
                    "error",
                    e
                )
            }
        }

    Surface(
        shape = MaterialTheme.shapes.large,
        tonalElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .widthIn(
                    min = 260.dp,
                    max = 340.dp
                )
                .padding(
                    horizontal = 24.dp,
                    vertical = 24.dp
                ),
            horizontalAlignment =
            Alignment.CenterHorizontally,
            verticalArrangement =
            Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Firebase同期",
                style =
                MaterialTheme.typography.titleMedium
            )
            when {
                isLoggingIn -> {
                    CircularProgressIndicator()

                    Spacer(
                        Modifier.height(8.dp)
                    )

                    Text("ログイン中...")
                }

                isOnline -> {
                    Button(
                        onClick = {
                            isLoggingIn = true

                            launcher.launch(
                                googleSignInClient.signInIntent
                            )
                        }
                    ) {
                        Text("Googleでログイン")
                    }
                }

                else -> {
                    Text(
                        text =
                        "Googleログインには\nネットワーク接続が必要です",
                        color = Color.Gray
                    )
                }
            }

            TextButton(
                onClick = onDismiss,
                enabled = !isLoggingIn
            ) {
                Text("戻る")
            }
        }
    }
}

fun firebaseAuthWithGoogle(
    idToken: String,
    onSuccess: () -> Unit
) {

    val credential =
        GoogleAuthProvider
            .getCredential(
                idToken,
                null
            )

    FirebaseAuth
        .getInstance()
        .signInWithCredential(
            credential
        )
        .addOnSuccessListener {
            onSuccess()
        }
}
