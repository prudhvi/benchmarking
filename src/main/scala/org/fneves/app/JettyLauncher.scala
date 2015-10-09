package org.fneves.app

import org.eclipse.jetty.server.{ServerConnector, Server}
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.util.thread.QueuedThreadPool
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

object JettyLauncher { // this is my entry object as specified in sbt project definition
  def main(args: Array[String]) {
    val port = if(System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080

    val threadPool = new QueuedThreadPool(1000, 1000)
    val server = new Server(threadPool)
    val context = new WebAppContext()

    // HTTP connector
    val http = new ServerConnector(server)
    http.setHost("0.0.0.0")
    http.setPort(8080)
    http.setIdleTimeout(30000)

    // Set the connector
    server.addConnector(http)

    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")

    server.setHandler(context)

    server.start
    server.join
  }
}
