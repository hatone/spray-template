package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

/**
 * @author Takako Ohshima
 */
class SmallRouteActor extends Actor with SmallRoute {
  def actorRefFactory = context

  def receive = runRoute(smallRoute)
}

trait SmallRoute extends HttpService {
  val smallRoute =
    path("SmallRoute") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray</i>!</h1>
              </body>
            </html>
          }
        }
      }
    } ~
    path("ping") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <h1>ping!</h1>
              </body>
            </html>
          }
        }
      } ~
      path("pong") {
        get {
          complete("pingpong!")
        }
      }
    } ~
    path("pong") {
      get {
        complete("pong!")
      }

    } ~
    path("order" / IntNumber) { id =>
      get {
        complete("hello" + id *2)
      }
    } ~
    path("add" /IntNumber) { id =>
      get {

        complete("hello" + id)
      }
    } ~ smallTestRoute

  val smallTestRoute: Route = path("add" / IntNumber) { b => innerRoute(b) }
  def innerRoute(a: Int): Route =
    path("test"/IntNumber) { b =>
      get {
        complete("hello" + a+b)
      }
  }
}
