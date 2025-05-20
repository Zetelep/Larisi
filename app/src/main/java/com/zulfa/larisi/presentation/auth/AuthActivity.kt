package com.zulfa.larisi.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zulfa.larisi.MainActivity
import com.zulfa.larisi.R
import com.zulfa.larisi.core.data.Resource
import com.zulfa.larisi.databinding.ActivityAuthBinding
import com.zulfa.larisi.presentation.helper.CredentialHelper
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModel()
    private val credentialHelper: CredentialHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnSigninGoogle.setOnClickListener {
            signIn()
        }

        observeSignInResult()

    }

    private fun signIn() {
        lifecycleScope.launch {
            try {
                val idToken = credentialHelper.getGoogleIdTokenCredential(
                    this@AuthActivity,
                    getString(R.string.web_client_id)
                )
                authViewModel.signInWithGoogle(idToken)
            } catch (e: Exception) {
                Log.e("AuthActivity", "Sign-in error", e)
                Toast.makeText(this@AuthActivity, "Gagal login", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeSignInResult() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
            authViewModel.signInState.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        Toast.makeText(this@AuthActivity, "Loading", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                        finish()
                    }

                    is Resource.Error -> {
                        Log.e("AuthActivity", "observeSignInResult: ${result.message}")
                        Toast.makeText(this@AuthActivity, result.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
            }
        }
    }
}