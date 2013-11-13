package com.example

import org.specs2.mutable.Specification
import spray.http._
import StatusCodes._
import spray.testkit.Specs2RouteTest

class MyServiceSpec extends Specification with Specs2RouteTest with MyService {
  def actorRefFactory = system

  "MyService" should {

    "return a greeting for GET requests to the root path" in {
      Get("/test") ~> sealRoute(myRoute) ~> check {
        responseAs[String] must contain("Say hello")
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> myRoute ~> check {
        handled must beFalse
      }
    }

    "leave GET requests to other paths unhandled /" in {
      Get("/") ~> myRoute ~> check {
        handled must beFalse
      }
    }

    "return a NotFound error for PUT requests to the root path" in {
      Put("/") ~> sealRoute(myRoute) ~> check {
        status === NotFound
        handled must beTrue
      }
    }

    "return a MethodNotAllowed error for PUT requests to the test path" in {
      Put("/test") ~> sealRoute(myRoute) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}
