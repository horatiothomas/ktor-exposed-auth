package org.example.user.view

import io.ktor.server.html.respondHtmlTemplate
import io.ktor.server.routing.RoutingContext
import kotlinx.html.FormEncType
import kotlinx.html.FormMethod
import kotlinx.html.fieldSet
import kotlinx.html.form
import kotlinx.html.label
import kotlinx.html.passwordInput
import kotlinx.html.submitInput
import kotlinx.html.textInput
import org.example.LayoutTemplate

suspend fun RoutingContext.loginView() {
  call.respondHtmlTemplate(LayoutTemplate()) {
    pageTitle { +"Login" }
    content {
      form(
          action = "login",
          encType = FormEncType.applicationXWwwFormUrlEncoded,
          method = FormMethod.post,
      ) {
        fieldSet {
          label {
            +"Username"
            textInput {
              name = "username"
              placeholder = "username"
            }
          }
          label {
            +"Password"
            passwordInput { name = "password" }
          }
          submitInput { value = "Login" }
        }
      }
    }
  }
}
