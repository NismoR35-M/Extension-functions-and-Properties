package com.extension.function.properties.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.extension.function.properties.R
import com.extension.function.properties.databinding.ActivityMainBinding
import com.extension.function.properties.models.Hand
import com.extension.function.properties.utils.totalFingers

class MainActivity : BaseActivity() {
  private lateinit var binding: ActivityMainBinding

    //TODO: 15 Change the Hand type to nullable
    private  var currentHand: Hand? = null



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    currentHand = handsDb.getLoggedInHand()
    //TODO: 16 Call getLoggedInHand without non-null assertion

    showDescription(currentHand)

    binding.logoutButton.setOnClickListener {
      handsDb.logoutHand()
      val mainIntent = Intent(this, SplashActivity::class.java)
      mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      startActivity(mainIntent)
    }

    //TODO: 19 Call showGreeting on currentHand
    currentHand.showGreeting()
  }

  private fun showDescription(hand: Hand?) {
    binding.welcomeTv.text = getString(
      R.string.welcome_username,
      hand?.userName ?: "-"
    )
    //TODO: 14 Use total fingers extension property
    binding.userDescriptionTv.text = getString(
      R.string.user_description_total_fingers,
      hand?.bio ?: "-", hand?.totalFingers
    )

    //TODO: 17 Change the code to use nullable Hand

  }

  //TODO: 18 Add showGreeting method with scope of MainActivity
  private fun Hand?.showGreeting() {
    if (this == null) {
      Toast.makeText(
        this@MainActivity, getString(R.string.greeting_anonymous),
        Toast.LENGTH_SHORT
      ).show()
    } else {
      Toast.makeText(this@MainActivity, getString(R.string.greeting_user, userName),
      Toast.LENGTH_SHORT).show()
    }
    /*
The scope of the extension is inside MainActivity only meaning that classes outside MainActivity
can't call the extension.
In case of name conflict between members of the dispatch and extension receivers,the extension receiver
takes precedence.Hence here using 'this' keyword will refer to the instance of Hand.
To refer to an instance of a dispatch receiver like 'MainActivity' the code use-qualified 'this'
syntax as 'this@MainActivity'
You can declare extension as 'open' instead of 'private' so that subclasses of 'MainActivity' can
override it.
*/
  }

}
