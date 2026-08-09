package org.example.user.view.components

import kotlinx.html.FlowContent
import kotlinx.html.p

fun FlowContent.errorMessage(errorText: String) = p("pico-color-pink-500") { +"Error: $errorText" }
