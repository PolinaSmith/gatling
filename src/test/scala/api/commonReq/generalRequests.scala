package api.commonReq

import config.baseHelper
import config.baseHelper.{baseUrl, generalParam, port}
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object generalRequests {

  def get_default_store(): ChainBuilder = {
    exec(
      http("defaultStore")
        .get(baseUrl + port + baseHelper.defaultStore)
    )
  }

  def get_content_pages(): ChainBuilder = {
    exec(
      http("contentPages")
        .get(baseUrl + port + baseHelper.contentPages)
        .queryParamSeq(generalParam)
    )
  }

  def get_category(): ChainBuilder = {
    exec(
      http("categoryGeneral")
        .get(baseUrl + port + baseHelper.categoryReq)
        .queryParamSeq(generalParam)
    )
  }

  def generalReq(): ChainBuilder = {
    group("GeneralRequests") {
      exec(get_default_store())
        .exec(get_content_pages())
        .exec(get_category())

    }
  }

}
