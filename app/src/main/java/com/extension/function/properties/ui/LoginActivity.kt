package com.extension.function.properties.ui

import android.os.Bundle
import android.widget.Toast
import com.extension.function.properties.R
import com.extension.function.properties.databinding.ActivityLoginBinding
import com.extension.function.properties.db.LoginState
import com.extension.function.properties.utils.startActivityAndClearStack


class LoginActivity : BaseActivity() {
  private lateinit var binding: ActivityLoginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.loginButton.setOnClickListener { loginHand() }
  }

  private fun loginHand() {
    val loginStatus = handsDb.performLogin(
        binding.usernameInput.text.toString(),
        binding.passwordInput.text.toString()
    )

    when (loginStatus) {
      LoginState.SUCCESS -> {
        //TODO: 9 Use startActivityAndClearStack function
        startActivityAndClearStack(MainActivity::class.java, null)
      }
      LoginState.WRONG_PASSWORD -> {
        Toast.makeText(this@LoginActivity, getString(R.string.wrong_password),
            Toast.LENGTH_SHORT).show()
      }
      else -> {
        Toast.makeText(this@LoginActivity, getString(R.string.username_not_found),
            Toast.LENGTH_SHORT).show()
      }
    }
  }
}
