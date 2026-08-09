package org.example.user.view

import io.ktor.server.html.respondHtmlTemplate
import io.ktor.server.resources.href
import io.ktor.server.routing.RoutingCall
import kotlinx.html.FormEncType
import kotlinx.html.FormMethod
import kotlinx.html.a
import kotlinx.html.fieldSet
import kotlinx.html.footer
import kotlinx.html.form
import kotlinx.html.label
import kotlinx.html.p
import kotlinx.html.passwordInput
import kotlinx.html.submitInput
import kotlinx.html.textInput
import org.example.LayoutTemplate
import org.example.Login
import org.example.SignUp
import org.example.security.PASSWORD_REGEX
import org.example.user.view.components.errorMessage

object SignUpForm {
  const val USERNAME = "username"
  const val PASSWORD = "password"
}

enum class SignUpError(val message: String) {
  USERNAME_TAKEN("Username is already taken"),
}

const val INSECURE_PASSWORD_MESSAGE =
    "Password must be 8 characters long contain at least one uppercase and lowercase letter and number"

suspend fun signUpView(call: RoutingCall, error: SignUpError? = null) {
  call.respondHtmlTemplate(LayoutTemplate()) {
    pageTitle { +"Sign Up" }
    content {
      error?.message?.let(::errorMessage)
      form(
          action = call.application.href(SignUp()),
          encType = FormEncType.applicationXWwwFormUrlEncoded,
          method = FormMethod.post,
      ) {
        fieldSet {
          label {
            +"Username"
            textInput {
              name = SignUpForm.USERNAME
              placeholder = "username"
              required = true
            }
          }
          label {
            +"Password"
            passwordInput {
              name = SignUpForm.PASSWORD
              required = true
              minLength = "8"
              pattern = PASSWORD_REGEX
              attributes["title"] = INSECURE_PASSWORD_MESSAGE
            }
          }
          submitInput { value = "Sign Up" }
        }
        footer {
          p {
            +"Already have an account? "
            a(href = call.application.href(Login())) { +"Login" }
          }
        }
      }
    }
  }
}
