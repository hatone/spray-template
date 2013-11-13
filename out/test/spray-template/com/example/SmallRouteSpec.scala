package com.example

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest

/**
 * @author Takako Ohshima
 */
class SmallRouteSpec extends Specification with Specs2RouteTest with SmallRoute {
  def actorRefFactory = system

  "SmallRoute" should {
    "return say hello as HTML" in {
      Get("/SmallRoute") ~> smallRoute ~> check {
        responseAs[String] must contain("Say hello")
      }
    }

    "return ping as HTML" in {
      Get("/ping") ~> smallRoute ~> check {
        responseAs[String] must contain("ping!")
      }
    }

    "return pong as HTML" in {
      Get("/pong") ~> smallRoute ~> check {
        responseAs[String] must contain("pong!")
      }
    }

    //    "return pongpong as HTML" in {
    //      Get("/ping") ~> smallRoute ~> check {
    //        responseAs[String] must contain("pingpong!")
    //      }
    //    }

    "return hello and id with valiable" in {
      Get("/order/2") ~> smallRoute ~> check {
        responseAs[String] === "hello4"
      }
    }
  }

  //"SmallRoute" should {
  //  "return adding value" in {
  //   Get("/add/2/3") ~> smallTestRoute ~> check {
  //     responseAs[String] === "hello5"
  //   }
  //  }
  //}
}
