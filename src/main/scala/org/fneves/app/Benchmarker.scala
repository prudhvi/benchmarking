package org.fneves.app

import grizzled.slf4j.Logging
import org.fneves.app.models.{Address, Contact, Person}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport

class Benchmarker extends BenchmarkStack with JacksonJsonSupport with Logging {

  protected implicit val jsonFormats: Formats = DefaultFormats

  case class HelloWorld(message: String)

  get("/getHTML") {
    info("getHtml")
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/small_payload") {
    contentType = formats("json")
    HelloWorld("Hello Wonderful world")
  }

  get("/big_payload") {
    contentType = formats("json")
    1.to(100).map(index => buildPerson(index))
  }

  def buildPerson(age: Int): Person = {
    val address = Address("Street of Nowhere", 10, "Yellow House")
    val contact = Contact("12312390814327956", "some_email@domain.com")
    Person("John Doe", age, address, contact)
  }
}
