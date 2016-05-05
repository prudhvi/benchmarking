package org.fneves.benchmark

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol


case class Person(name: String, age: Int, address: Address, contact: Contact)

case class Address(street: String, number: Int, house: String)

case class Contact(phoneNumber: String, email: String)

case class HelloWorld(message: String)

trait Protocols extends DefaultJsonProtocol {
  implicit val helloWorldFormat = jsonFormat1(HelloWorld)
  implicit val addressFormat = jsonFormat3(Address)
  implicit val contactFormat = jsonFormat2(Contact)
  implicit val personFormat = jsonFormat4(Person)
}


object AkkaBenchmarkServer extends App with Protocols {

  def buildPerson(age: Int): Person = {
    val address = Address("Street of Nowhere", 10, "Best Mega House")
    val contact = Contact("12312390814327956", "some_email@domain.com")
    Person("John Doe", age, address, contact)
  }

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher


  val route =
    get {
      path("small_payload") {

        complete(
          HelloWorld("hellow wonderful world")
        )
      } ~
        path("big_payload") {
          val persons = 1.to(100).map(index => buildPerson(index))
          complete(
            persons
          )
        }
    }


  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
  println(s"Server online at http://localhost:8080/")
}


