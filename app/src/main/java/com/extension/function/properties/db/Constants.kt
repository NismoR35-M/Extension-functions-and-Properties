package com.extension.function.properties.db

enum class RegistrationState {
  SUCCESS, HAND_ALREADY_EXIST
}

enum class LoginState {
  SUCCESS, HAND_NOT_REGISTERED, WRONG_PASSWORD
}

enum class LogoutState {
  SUCCESS, NOT_LOGGED_IN
}
