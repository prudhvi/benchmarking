package com.zendesk.app

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport

// JSON-related libraries

// JSON handling support from Scalatra

case class Person(name :String, age: Int, address: String)

class Benchmarker extends BenchmarkStack with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  get("/getHtml") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/getJSON") {
    contentType = formats("json")
    Person("John Doe", 25, "Middle of nowhere")
  }
}
