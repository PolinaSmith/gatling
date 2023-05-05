package api.commonReq

import config.baseHelper
import config.baseHelper._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object productRequests {

  def get_product_price(): ChainBuilder = {
    repeat(3) {
      feed(chair_id.circular)
        .exec(
          http("ChairsPrices")
            .post(baseUrl + port + baseHelper.productReq + "#{chairsId}" + baseHelper.priceReq)
            .body(StringBody("""{"options":[]}""")).asJson)
    }
      .feed(table_id.circular)
      .exec(
        http("TablePrices")
          .post(baseUrl + port + baseHelper.productReq + "#{tablesId}" + baseHelper.priceReq)
          .body(StringBody("""{"options":[]}""")).asJson)
  }

}
