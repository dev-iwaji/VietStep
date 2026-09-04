package com.iwaji.vietstep.util

import android.content.Context

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.iwaji.vietstep.R

object GoogleLoginHelper {

    fun getClient(
        context: Context
    ): GoogleSignInClient {

        return GoogleSignIn.getClient(
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
    }
}