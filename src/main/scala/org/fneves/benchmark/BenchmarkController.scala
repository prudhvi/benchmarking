package org.fneves.benchmark

import com.twitter.finagle.httpx.Request
import com.twitter.finatra.http.Controller
import com.twitter.inject.Logging

case class Person(name :String, age: Int, address: Address, contact: Contact)
case class Address(street: String, number: Int, house: String)
case class Contact(phoneNumber: String, email: String)

class BenchmarkController extends Controller with Logging {
  case class HelloWorld(message: String)

  get("/getHTML") { request: Request =>
    info("getHtml")
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>.toString()
  }

  get("/small_payload") { request: Request =>
    response.ok.json(HelloWorld("hellow wonderful world"))
  }

  get("/big_payload") { request: Request =>
    val persons = 1.to(100).map(index => buildPerson(index))
    response.ok.json(persons)
  }

  def buildPerson(age: Int): Person = {
    val address = Address("Street of Nowhere", 10, "Best Mega House")
    val contact = Contact("12312390814327956", "some_email@domain.com")
    Person("John Doe", age, address, contact)
  }
}
