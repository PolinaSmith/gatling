package simulations

import config.baseHelper.{httpProtocol, usersNumber}
import io.gatling.core.Predef._
import scenarios.baseScn.scnShopizer

class gatlingTask extends Simulation {
  //mvn clean
  //mvn gatling:test -DusersNumber=100

  setUp(scnShopizer.inject(atOnceUsers(usersNumber))).maxDuration(600).protocols(httpProtocol)

}