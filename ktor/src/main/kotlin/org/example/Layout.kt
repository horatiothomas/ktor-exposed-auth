package org.example

import io.ktor.server.html.Placeholder
import io.ktor.server.html.Template
import io.ktor.server.html.insert
import kotlinx.html.FlowContent
import kotlinx.html.HTML
import kotlinx.html.TITLE
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.main
import kotlinx.html.styleLink
import kotlinx.html.title

class LayoutTemplate : Template<HTML> {

  val pageTitle = Placeholder<TITLE>()
  val content = Placeholder<FlowContent>()

  override fun HTML.apply() {
    head {
      styleLink {
        rel = "stylesheet"
        href = "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
        type = "text/css"
      }
      title { insert(pageTitle) }
    }
    body { main(classes = "container") { insert(content) } }
  }
}
