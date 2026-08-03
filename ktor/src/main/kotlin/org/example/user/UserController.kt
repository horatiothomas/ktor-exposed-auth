package org.example.user

import io.ktor.server.auth.UserPasswordCredential
import io.ktor.server.auth.principal
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.RoutingCall
import io.ktor.server.sessions.clear
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import org.example.Path
import org.example.plugins.UserIdPrincipal
import org.example.plugins.UserSession
import org.example.user.view.SignUpError
import org.example.user.view.SignUpForm
import org.example.user.view.indexView
import org.example.user.view.loginView
import org.example.user.view.signUpView

object QueryParams {
  object Signup {
    object Error {
      const val KEY = "error"

      object Value {
        const val USERNAME_TAKEN = "username-taken"
      }
    }
  }
}

class UserController(val userService: UserService) {

  suspend fun showSignUp(call: RoutingCall) {
    when (call.request.queryParameters[QueryParams.Signup.Error.KEY]) {
      QueryParams.Signup.Error.Value.USERNAME_TAKEN -> signUpView(call, SignUpError.USERNAME_TAKEN)
      else -> signUpView(call)
    }
  }

  suspend fun signUp(call: RoutingCall) {
    val formParameters = call.receiveParameters()
    val username = formParameters[SignUpForm.USERNAME].toString()
    val password = formParameters[SignUpForm.PASSWORD].toString()
    if (!userService.isPasswordSecure(password)) {
      throw RequestValidationException(formParameters, listOf("Password is not secure"))
    }
    if (!userService.isUsernameAvailable(username)) {
      call.respondRedirect(
          "${Path.SIGN_UP}?${QueryParams.Signup.Error.KEY}=${QueryParams.Signup.Error.Value.USERNAME_TAKEN}"
      )
    } else {
      userService.createUser(UserPasswordCredential(username, password))
      call.respondRedirect(Path.INDEX)
    }
  }

  suspend fun showLogin(call: RoutingCall) {
    loginView(call)
  }

  suspend fun initUserSession(call: RoutingCall) {
    val userId = call.principal<UserIdPrincipal>()?.id
    call.sessions.set(UserSession(userId = userId!!))
    call.respondRedirect(Path.INDEX)
  }

  suspend fun index(call: RoutingCall) {
    val userSession = call.principal<UserSession>()
    indexView(call, userSession!!.userId)
  }

  suspend fun logout(call: RoutingCall) {
    call.sessions.clear<UserSession>()
    call.respondRedirect(Path.INDEX)
  }
}
