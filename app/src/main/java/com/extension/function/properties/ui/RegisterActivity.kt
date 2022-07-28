package com.extension.function.properties.ui

import android.os.Bundle
import android.widget.Toast
import com.extension.function.properties.R
import com.extension.function.properties.databinding.ActivityRegisterBinding
import com.extension.function.properties.db.RegistrationState
import com.extension.function.properties.models.Hand
import com.extension.function.properties.utils.startActivityAndClearStack
import com.extension.function.properties.utils.validateUsername

class RegisterActivity : BaseActivity() {
  private lateinit var binding: ActivityRegisterBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityRegisterBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.registerButton.setOnClickListener { registerHand() }
  }

  private fun registerHand() {
    val username = binding.usernameInput.text.toString()
    val fingersCount = binding.fingersCountInput.text.toString()
    val thumbsCount = binding.thumbCountInput.text.toString()
    val bio = binding.bioInput.text.toString()
    val password = binding.passwordInput.text.toString()

    if (username.isEmpty() || fingersCount.isEmpty() || thumbsCount.isEmpty() ||
        bio.isEmpty() || password.isEmpty()) {
      Toast.makeText(this, getString(R.string.mandatory_fields), Toast.LENGTH_SHORT).show()
      return
    }

    //TODO: 12 Add validation for username

    val isUsernameValid = binding.usernameInput.validateUsername()
    if (!isUsernameValid){
      return
    }
    val hand = Hand(username, fingersCount.toInt(), thumbsCount.toInt(), bio, password)
    val registrationResult = handsDb.registerHand(hand)

    if (registrationResult == RegistrationState.SUCCESS) {
      //TODO: 10 use startActivityAndClearStack function
      startActivityAndClearStack(MainActivity::class.java, null)

    } else {
      Toast.makeText(this@RegisterActivity, getString(R.string.username_already_exists),
          Toast.LENGTH_SHORT).show()
    }
  }
}
