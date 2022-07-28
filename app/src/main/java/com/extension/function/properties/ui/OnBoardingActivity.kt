package com.extension.function.properties.ui

import android.content.Intent
import android.os.Bundle
import com.extension.function.properties.R
import com.extension.function.properties.databinding.ActivityOnboardingBinding
import com.extension.function.properties.utils.loadImage

class OnBoardingActivity : BaseActivity() {
  private lateinit var binding: ActivityOnboardingBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityOnboardingBinding.inflate(layoutInflater)
    setContentView(binding.root)

    //TODO: 2 Use  extension function from Extensions.kt file
    binding.imageIcon.loadImage(getString(R.string.logo_url))

    binding.loginButton.setOnClickListener {
      startActivity(Intent(this, LoginActivity::class.java))
    }

    binding.registerButton.setOnClickListener {
      startActivity(Intent(this, RegisterActivity::class.java))
    }

    //TODO: 6 Call showGreetingMessage
    showGreetingMessage(this)

  }

  //TODO: 5 Add method below to use greeting extension function
  private fun showGreetingMessage(activity: BaseActivity) {
    activity.greet()
    /*
    The toast will show string defined in R.string.welcome_base_activity and not
    one you expected.this bcoz extension function depends on the declared type of
    parameter which is 'BaseActivity' and not the type that's resolved at runtime 'OnBoardActivity'
    */
  }

}
