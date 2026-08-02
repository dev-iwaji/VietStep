package com.example.vocabapp.ui.login

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.vocabapp.R
import com.example.vocabapp.ui.auth.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel
) {

    Column(

        modifier = Modifier
            .fillMaxSize(),

        verticalArrangement =
        Arrangement.Center,

        horizontalAlignment =
        Alignment.CenterHorizontally

    ) {

        val context = LocalContext.current

        val isNetworkAvailable = remember {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = cm.activeNetwork
            val caps = cm.getNetworkCapabilities(network)
            caps?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        }

        val googleSignInClient =
            GoogleSignIn.getClient(
                context,
                GoogleSignInOptions.Builder(
                    GoogleSignInOptions.DEFAULT_SIGN_IN
                )
                    .requestIdToken(
                        context.getString(
                            R.string.default_web_client_id
                        )
                    )
                    .requestEmail()
                    .build()
            )

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
                    }
                } catch (
                    e: Exception
                ) {
                    Log.e(
                        "GoogleLogin",
                        "error",
                        e)
                }
            }

        Text(
            text = "VietStep"
        )

        Spacer(Modifier.height(24.dp))

        if (isNetworkAvailable) {
            Button(
                onClick = {
                    launcher.launch(
                        googleSignInClient
                            .signInIntent
                    )
                }

            ) {
                Text("Googleでログイン")
            }
        } else {
            Text(
                text = "ネットワークに接続できません",
                color = Color.Gray
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    authViewModel.enterOfflineMode()
                }
            ) {
                Text("オフラインで続ける")
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
