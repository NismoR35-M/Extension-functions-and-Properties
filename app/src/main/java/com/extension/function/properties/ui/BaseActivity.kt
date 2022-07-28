package com.extension.function.properties.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.extension.function.properties.R
import com.extension.function.properties.db.HandsDb

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

  protected lateinit var handsDb: HandsDb

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    handsDb = HandsDb(this)
  }

  //TODO: 7 Add greet method with same signature
  fun greet() {
    Toast.makeText(this,getString(R.string.welcome_base_activity_member),
    Toast.LENGTH_SHORT).show()
  }
  /*
  Kotlin says MEMBER FUNCTION ALWAYS WINS ;) and this why this member function will
  show despite been the same name and same signature as the extension function.
  */

}