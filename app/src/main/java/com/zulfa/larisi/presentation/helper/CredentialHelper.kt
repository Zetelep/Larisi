package com.zulfa.larisi.presentation.helper

import android.app.Activity
import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential


class CredentialHelper(private val context: Context) {

    private val credentialManager by lazy {
        CredentialManager.create(context)
    }
    suspend fun getGoogleIdTokenCredential(activity: Activity, webClientId: String): String {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(webClientId)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val credentialManager = CredentialManager.create(activity)

        val result = credentialManager.getCredential(
            request = request,
            context = activity
        )

        val credential = result.credential as? CustomCredential
            ?: throw Exception("Invalid Credential Type")

        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
        return googleIdTokenCredential.idToken
    }

    suspend fun clearCredentialState() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}