package api


import config.baseHelper
import config.baseHelper.{baseUrl, port, shortParam}
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object home {

  def get_main_page(): ChainBuilder = {
    exec(
      http("defaultStore")
        .get(baseUrl)
    )
  }


  def get_featured_items(): ChainBuilder = {
    exec(
      http("featuredItem")
        .get(session => baseUrl + port + baseHelper.featuredItems)
        .queryParamSeq(shortParam)
        .check(regex("\"lineage\":\"/(.+?)/\"").find(0).saveAs("tableCatId"))
        .check(regex("\"lineage\":\"/(.+?)/\"").find(1).saveAs("chairCatId"))
    )
  }

  def homePage(): ChainBuilder = {
    group("MainReq") {
      exec(get_main_page())
        .exec(get_featured_items())
    }
  }

}
