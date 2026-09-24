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
    onLoginSuccess: () -> Unit,
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

            Log.e("LOGIN", "resultCode=${result.resultCode}")
            Log.e("LOGIN", "intent=${result.data}")

            val task =
                GoogleSignIn
                    .getSignedInAccountFromIntent(
                        result.data
                    )

            Log.e("LOGIN", "GoogleSignIn task created")

            try {
                val account =
                    task.getResult(
                        ApiException::class.java
                    )

                Log.e("LOGIN", "account.id=${account.id}")
                Log.e("LOGIN", "account.email=${account.email}")
                Log.e("LOGIN", "displayName=${account.displayName}")
                Log.e("LOGIN", "idToken=${account.idToken}")
                Log.e("LOGIN", "serverAuthCode=${account.serverAuthCode}")

                firebaseAuthWithGoogle(
                    account.idToken!!
                ) {

                    Log.e("LOGIN", "LoginScreen onSuccess")

                    authViewModel.refreshLoginState()

                    isLoggingIn = false
                    onLoginSuccess()
                    onDismiss()
                }

            } catch (e: ApiException) {
                isLoggingIn = false

                Log.e("LOGIN", "statusCode=${e.statusCode}")
                Log.e("LOGIN", "status=${e.status}")
                Log.e("LOGIN", "message=${e.message}")
                Log.e("LOGIN", "localized=${e.localizedMessage}")
                Log.e("LOGIN", "cause=${e.cause}")

                e.printStackTrace()
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
                            googleSignInClient
                                .signOut()
                                .addOnCompleteListener {

                                    Log.e("LOGIN", "launch Google SignIn")

                                    launcher.launch(
                                        googleSignInClient.signInIntent
                                    )
                                }
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
    Log.e("LOGIN", "FirebaseAuth start")
    Log.e("LOGIN", "token=${idToken.take(30)}...")

    val credential =
        GoogleAuthProvider
            .getCredential(
                idToken,
                null
            )

    Log.e("LOGIN", "credential created")

    FirebaseAuth
        .getInstance()
        .signInWithCredential(
            credential
        )
        .addOnCompleteListener { task ->

            Log.e("LOGIN", "onComplete")
            Log.e("LOGIN", "isSuccessful=${task.isSuccessful}")
            Log.e("LOGIN", "isComplete=${task.isComplete}")

            if (task.exception != null) {
                Log.e("LOGIN", "task.exception", task.exception!!)
            }
        }
        .addOnSuccessListener {
            Log.e("LOGIN", "SUCCESS")
            Log.e(
                "LOGIN",
                "uid=${FirebaseAuth.getInstance().currentUser?.uid}"
            )

            onSuccess()
        }
        .addOnFailureListener { e ->
            Log.e("LOGIN", "FAILED")
            Log.e("LOGIN", "Failure", e)
            Log.e("LOGIN", "message=${e.message}")
            Log.e(
                "LOGIN",
                "exceptionClass=${e.javaClass.name}"
            )

            if (e is com.google.firebase.auth.FirebaseAuthException) {
                Log.e("LOGIN", "errorCode=${e.errorCode}")
            }
        }
}
