package org.example.user

import io.ktor.server.auth.UserPasswordCredential
import io.ktor.server.auth.principal
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.request.receiveParameters
import io.ktor.server.resources.href
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.RoutingCall
import io.ktor.server.sessions.clear
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import io.ktor.server.util.getOrFail
import org.example.Index
import org.example.LoginErrorParameters
import org.example.SignUp
import org.example.SignUpErrorParameters
import org.example.plugins.UserIdPrincipal
import org.example.plugins.UserSession
import org.example.user.view.LoginError
import org.example.user.view.SignUpError
import org.example.user.view.SignUpForm
import org.example.user.view.indexView
import org.example.user.view.loginView
import org.example.user.view.signUpView

class UserController(val userService: UserService) {

  suspend fun showSignUp(call: RoutingCall, error: String?) {
    when (error) {
      SignUpErrorParameters.USERNAME_TAKEN -> signUpView(call, SignUpError.USERNAME_TAKEN)
      else -> signUpView(call)
    }
  }

  suspend fun signUp(call: RoutingCall) {
    with(call) {
      val formParameters = receiveParameters()
      val username = formParameters.getOrFail(SignUpForm.USERNAME)
      val password = formParameters.getOrFail(SignUpForm.PASSWORD)
      if (!userService.isPasswordSecure(password)) {
        throw RequestValidationException(formParameters, listOf("Password is not secure"))
      }
      if (!userService.isUsernameAvailable(username)) {
        respondRedirect(application.href(SignUp(SignUpErrorParameters.USERNAME_TAKEN)))
        return
      }
      val user =
          runCatching { userService.createUser(UserPasswordCredential(username, password)) }
              .getOrElse { exception ->
                if (exception is UsernameTakenException) {
                  respondRedirect(application.href(SignUp(SignUpErrorParameters.USERNAME_TAKEN)))
                  return
                }
                throw exception
              }
      sessions.set(UserSession(userId = user.id))
      respondRedirect(application.href(Index()))
    }
  }

  suspend fun showLogin(call: RoutingCall, error: String?) {
    when(error) {
      LoginErrorParameters.INVALID_CREDENTIALS -> LoginError.INVALID_CREDENTIALS
      else -> loginView(call)
    }
  }

  suspend fun initUserSession(call: RoutingCall) {
    with(call) {
      val userId = principal<UserIdPrincipal>()?.id
      sessions.set(UserSession(userId = userId!!))
      respondRedirect(application.href(Index()))
    }
  }

  suspend fun index(call: RoutingCall) {
    val userSession = call.principal<UserSession>()
    indexView(call, userSession!!.userId)
  }

  suspend fun logout(call: RoutingCall) {
    with(call) {
      sessions.clear<UserSession>()
      respondRedirect(application.href(Index()))
    }
  }
}
