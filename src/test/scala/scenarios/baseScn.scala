package scenarios

import api.cart.{get_cart, get_cart_number, post_cart, put_cart}
import api.category.categoryReq
import api.checkout.checkoutReq
import api.commonReq.generalRequests.generalReq
import api.commonReq.productRequests.get_product_price
import api.home.homePage
import api.product.product_request
import config.baseHelper.thinkTime
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

object baseScn {

  def scnShopizer: ScenarioBuilder = {
    scenario("Test scenario")
      .forever {
        exec(flushHttpCache)
          .exec(flushCookieJar)
          .exitBlockOnFail(
            group("HomePage") {
              exec(generalReq())
                .exec(get_product_price())
                .exec(homePage())
            }
              .exec(scnTableFlow)
              .exec(scnAddToCart)
              .randomSwitch(
                50.0 -> exec(exec(scnChairFlow)
                  .exec(scnPutToCart)
                ))
              .randomSwitch(
                30.0 -> exec(scnCheckout)
              )
          )
      }
  }

  val scnTableFlow =
    scenario("Open Table category, open Table page")
      .group("TableCategory") {
        exec(categoryReq("table")
          .exec(generalReq())
          .exec(get_product_price())
        )
          .exec(thinkTime())
      }
      .group("TablePage") {
        exec(product_request("table")
          .exec(generalReq())
          .exec(get_product_price()))
          .exec(thinkTime())
      }

  val scnChairFlow =
    scenario("Open Chair category, open Chair page")
      .group("ChairCategory") {
        exec(categoryReq("chair")
          .exec(generalReq())
          .exec(get_product_price()))
          .exec(thinkTime())

      }
      .group("ChairPage") {
        exec(product_request("chair")
          .exec(generalReq())
          .exec(get_product_price()))
          .exec(thinkTime())

      }

  val scnAddToCart =
    scenario("Add product to cart")
      .group("AddToCart") {
        exec(post_cart()
          .exec(get_cart_number()))
          .exec(thinkTime())

      }

  val scnPutToCart =
    scenario("Add product to cart")
      .group("AddToCart") {
        exec(put_cart()
          .exec(get_cart_number()))
          .exec(thinkTime())
      }


  val scnOpenCart =
    scenario("Open cart")
      .group("OpenCart") {
        exec(get_cart()
          .exec(get_cart_number())
          .exec(generalReq())
          .exec(get_product_price()))
          .exec(thinkTime())
      }

  val scnCheckout =
    scenario("Open CheckoutPage")
      .group("Checkout") {
        exec(checkoutReq()
          .exec(generalReq())
          .exec(get_product_price()))
          .exec(thinkTime())
      }
}