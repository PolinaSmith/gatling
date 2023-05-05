package config

import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

object baseHelper {

  val usersNumber: Int = System.getProperty("usersNumber", "5").toInt
  val minTime: Int = System.getProperty("minTime", "1").toInt
  val maxTime: Int = System.getProperty("maxTime", "3").toInt

  //urls
  val port = ":8080"
  val baseUrl = "http://localhost"
  val defaultStore = "/api/v1/store/DEFAULT"
  val contentPages = "/api/v1/content/pages/"
  val categoryReq = "/api/v1/category/"
  val productReq = "/api/v1/product/"
  val priceReq = "/price/"
  val featuredItems = "/api/v1/products/group/FEATURED_ITEM"
  val products = "/api/v1/products/"
  val cart_default = "/api/v1/cart/?store=DEFAULT"
  val cart = "/api/v1/cart/"


  //csv
  val chair_id: BatchableFeederBuilder[String] = csv("feeders/chairs_ids.csv")
  val table_id: BatchableFeederBuilder[String] = csv("feeders/tables_ids.csv")


  //req_param
  val generalParam: Seq[(String, String)] = Seq(("count", "20"), ("page", "0"), ("store", "DEFAULT"), ("lang", "en"))
  val shortParam: Seq[(String, String)] = Seq(("store", "DEFAULT"), ("lang", "en"))


  def thinkTime(Min: Int = minTime, Max: Int = maxTime): ChainBuilder = {
    pause(Min, Max)
  }

  val httpProtocol: HttpProtocolBuilder = http
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/110.0")

}
