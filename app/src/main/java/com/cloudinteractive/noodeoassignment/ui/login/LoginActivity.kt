package com.cloudinteractive.noodeoassignment.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.cloudinteractive.noodeoassignment.R
import com.cloudinteractive.noodeoassignment.databinding.ActivityLoginBinding
import com.cloudinteractive.noodeoassignment.repository.NetworkResponse
import com.cloudinteractive.noodeoassignment.ui.viewBinding
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by viewBinding(ActivityLoginBinding::inflate)
    private val viewModel: LoginViewModel by viewModels()

    private val emailTextWatcher by lazy {
        object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.tfEmail.error = null
            }
        }
    }

    private val passwordTextWatcher by lazy {
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.tfPassword.error = null
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            etEmail.addTextChangedListener(emailTextWatcher)
            etPassword.addTextChangedListener(passwordTextWatcher)
            btnLogin.setOnClickListener {
                login(etEmail.text.toString(), etPassword.text.toString())
            }

            viewModel.loginResponse.observe(this@LoginActivity) { networkResponse ->
                when (networkResponse) {
                    is NetworkResponse.Success -> {
                        finish()
                    }

                    is NetworkResponse.ApiError -> {
                        Toast.makeText(this@LoginActivity, networkResponse.body.error, Toast.LENGTH_SHORT).show()
                    }

                    is NetworkResponse.NetworkError ->
                        Toast.makeText(this@LoginActivity, networkResponse.error.message ?: "Network Error", Toast.LENGTH_SHORT).show()

                    is NetworkResponse.UnknownError ->
                        Toast.makeText(this@LoginActivity, networkResponse.error?.message ?: "Unknown Error", Toast.LENGTH_SHORT).show()
                }

                binding.pbLoading.visibility = GONE
                binding.btnLogin.isEnabled = true
            }
        }
    }


    private fun validateEmail(email: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9_\\-.]{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$")
        return pattern.matcher(email).matches()
    }


    private fun validatePassword(password: String): Boolean {
        val pattern = Pattern.compile("^[\\w\\-.\$@!%*#?&]+$")
        return pattern.matcher(password).matches()
    }

    private fun login(email: String, password: String) {
        if (!validateEmail(email)) {
            binding.tfEmail.error = getString(R.string.email_format_error)
            return
        }

        if (!validatePassword(password)) {
            binding.tfPassword.error = getString(R.string.password_format_error)
            return
        }

        binding.pbLoading.visibility = VISIBLE
        binding.btnLogin.isEnabled = false
        viewModel.login(email, password)


    }
}