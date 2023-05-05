package api

import api.cart.get_cart_number
import config.baseHelper.{baseUrl, cart, port, shortParam}
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object checkout {

  def checkoutReq(): ChainBuilder = {
    exec(get_checkout())
      .exec(get_cart_number())
      .exec(get_shipping_country())
      .exec(get_config())
      .exec(get_cart_total())
  }

  def get_checkout(): ChainBuilder = {
    exec(
      http("CheckoutPage")
        .get(baseUrl + "/checkout")
    )
  }

  def get_shipping_country(): ChainBuilder = {
    exec(
      http("ShippingCountry")
        .get(baseUrl + port + "/api/v1/shipping/country")
        .queryParamSeq(shortParam)
    )
  }

  def get_config(): ChainBuilder = {
    exec(
      http("Config")
        .get(baseUrl + port + "/api/v1/config/")
    )
  }

  def get_cart_total(): ChainBuilder = {
    exec(
      http("CartTotal")
        .get(session => baseUrl + port + cart + session("cartNumber").as[String] + "/total")
    )
  }
}
