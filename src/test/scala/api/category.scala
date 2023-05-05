package api

import config.baseHelper
import config.baseHelper._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._


object category {

  def categoryReq(category: String): ChainBuilder = {
    doSwitch(category)(
      "table" -> exec(get_category("tableCatId")),
      "chair" -> exec(get_category("chairCatId"))
    )
      .exec(get_products())
  }

  def get_category(categoryId: String): ChainBuilder = {
    exec(
      http("CategoryPage")
        .get(session => baseUrl + baseHelper.categoryReq + session(categoryId).as[String])
    )
  }

  def get_products(): ChainBuilder = {
    exec(
      http("Products")
        .get(baseUrl + port + products)
    )
  }
}
