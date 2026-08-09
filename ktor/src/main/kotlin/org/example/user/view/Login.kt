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
import org.example.user.view.components.errorMessage

object LoginForm {
  const val USERNAME = "username"
  const val PASSWORD = "password"
}

enum class LoginError(val message: String) {
  INVALID_CREDENTIALS("Invalid credentials")
}

suspend fun loginView(call: RoutingCall, error: LoginError? = null) {
  call.respondHtmlTemplate(LayoutTemplate()) {
    pageTitle { +"Login" }
    content {
      error?.message?.let(::errorMessage)
      form(
          action = call.application.href(Login()),
          encType = FormEncType.applicationXWwwFormUrlEncoded,
          method = FormMethod.post,
      ) {
        fieldSet {
          label {
            +"Username"
            textInput {
              name = LoginForm.USERNAME
              placeholder = "username"
              required = true
            }
          }
          label {
            +"Password"
            passwordInput {
              name = LoginForm.PASSWORD
              required = true
            }
          }
          submitInput { value = "Login" }
        }
        footer {
          p {
            +"Don't have an account? "
            a(href = call.application.href(SignUp())) { +"Sign up" }
          }
        }
      }
    }
  }
}
