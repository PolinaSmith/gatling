package api

import config.baseHelper
import config.baseHelper._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object cart {


  def post_cart(): ChainBuilder = {
    exec(
      http("AddToCart")
        .post(baseUrl + port + cart_default)
        .body(StringBody(session => """{"attributes":[],"product":"""" + session("productSku").as[String] + """","quantity":1}""")).asJson
        .check(regex("\"code\":\"(.+?)\",\"s").saveAs("cartNumber")))
  }

  def get_cart_number(): ChainBuilder = {
    exec(
      http("CartNum")
        .get(session => baseUrl + port + baseHelper.cart + session("cartNumber").as[String])
    )
  }

  def put_cart(): ChainBuilder = {
    exec(
      http("CartPut")
        .put(session => baseUrl + port + baseHelper.cart + session("cartNumber").as[String])
        .body(StringBody(session => """{"attributes":[],"product":"""" + session("productSku").as[String] + """","quantity":1}""")).asJson
    )
  }

  def get_cart(): ChainBuilder = {
    exec(
      http("CartPage")
        .get(baseUrl + "/cart")
    )
  }
}
