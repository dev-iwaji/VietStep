package com.iwaji.vietstep.util

import android.content.Context

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.iwaji.vietstep.R
import android.util.Log

object GoogleLoginHelper {

    fun getClient(
        context: Context
    ): GoogleSignInClient {

        val webClientId =
            context.getString(R.string.default_web_client_id)

        Log.e("LOGIN", "packageName=${context.packageName}")
        Log.e("LOGIN", "webClientId=$webClientId")

        val options =
            GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
            )
                .requestIdToken(webClientId)
                .requestEmail()
                .build()

        Log.d("GoogleLogin", "GoogleSignInOptions created")

        return GoogleSignIn.getClient(
            context,
            options
        )
    }
}
