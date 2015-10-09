import javax.servlet.ServletContext

import org.fneves.app.Benchmarker
import org.scalatra._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new Benchmarker, "/*")
  }
}
