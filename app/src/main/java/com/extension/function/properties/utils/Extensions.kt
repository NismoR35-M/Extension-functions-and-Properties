package com.extension.function.properties.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.extension.function.properties.R
import com.extension.function.properties.models.Hand
import com.extension.function.properties.ui.BaseActivity
import com.extension.function.properties.ui.OnBoardingActivity
import java.util.regex.Pattern

//TODO: 1 Add an extension function with ImageView as receiver to load image using glide
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this) //this keyword corresponds to the receiver object(instance of ImageView)
        .load(imageUrl)
        .into(this)
}

//TODO: 3 Add extension function for BaseActivity to show greeting message
fun BaseActivity.greet(){
    Toast.makeText(this,getString(R.string.welcome_base_activity),Toast.LENGTH_SHORT).show()
}

//TODO: 4 Add extension function for OnBoardingActivity to show greeting message
fun OnBoardingActivity.greet(){
    Toast.makeText(this,getString(R.string.welcome_onboarding_activity),Toast.LENGTH_SHORT).show()
}
/*
OnBoardingActivity class extends BaseActivity but you create two different extension functions
with different messages.
*/

//TODO: 8 Add startActivityAndClearStack extension function below, this adds functionality to Context class without inheriting
fun Context.startActivityAndClearStack(clazz: Class<*>, extras: Bundle?) {
    val intent = Intent(this, clazz)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    if (extras != null) {
        intent.putExtras(extras)
    }
    startActivity(intent)
}

//TODO: 11 Add extension function to validate and suggest alternate usernames
fun EditText.validateUsername(): Boolean {
    val username = text.toString() //takes input from EditText and converts to string

    //declares a regex pattern that accepts string ending with some numbers
    val pattern = Pattern.compile("^[a-zA-Z]+[0-9]+$")
    //matches the input with regex
    val matcher = pattern.matcher(username)
    //stores whether it's valid string or not
    val isValid = matcher.matches()

    //ets error hint if the username is not valid
    if (!isValid) {
        error = context.getString(R.string.username_validation_error, username)
    }

    //returns whether the input entered is valid or not
    return isValid
}

//TODO: 13 Add extension property to get total fingers
val Hand?.totalFingers: String get() {
    if (this == null) return "-"
    return (fingersCount + thumbsCount).toString()
}
