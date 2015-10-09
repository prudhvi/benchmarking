package org.fneves.app

import io.undertow.Undertow
import io.undertow.servlet.Servlets
import io.undertow.servlet.api.DeploymentInfo

object UndertowLauncher { // this is my entry object as specified in sbt project definition
  def main(args: Array[String]) {
    val servlet = Servlets.servlet("MessageServlet", classOf[Benchmarker]).addInitParam("message", "Hello World").addMapping("/*")

    val deploymentInfo = new DeploymentInfo().setContextPath("/").addServlet(servlet).setDeploymentName("Benchmarker").setClassLoader(ClassLoader.getSystemClassLoader())
    val deploymentManager = Servlets.defaultContainer().addDeployment(deploymentInfo)
    deploymentManager.deploy()

    val server = Undertow.builder()
      .addHttpListener(8080, "localhost")
      .setHandler(deploymentManager.start()).build()
    server.start()

  }
}
