package api

import config.baseHelper._
import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object product {


  def product_request(productType: String): ChainBuilder = {
    doSwitch(productType)(
      "table" -> exec(get_product_page(table_id, "tablesId"))
        .exec(get_products_id("tablesId")),
      "chair" -> exec(get_product_page(chair_id, "chairsId"))
        .exec(get_products_id("chairsId"))
    )
  }


  def get_product_page(feeder: BatchableFeederBuilder[String], product: String): ChainBuilder = {
    feed(feeder.circular.random)
      .exec(
        http("ProductPage")
          .get(baseUrl + productReq + "#{" + product + "}")
      )
  }

  def get_products_id(product: String): ChainBuilder = {
    exec(
      http("productsReq")
        .get(baseUrl + port + products + "#{" + product + "}")
        .queryParamSeq(shortParam)
        .check(regex("\"sku\":\"(.+?)\",\"preOrder\"").saveAs("productSku"))
    )

  }

}
