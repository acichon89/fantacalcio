package com.javangarda.fantacalcio.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {
  
  val httpConf = http
    .baseURL("http://computer-database.herokuapp.com") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

	
	val headers_1 = Map(
			"Accept" -> """text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"""
	)
			
  val scn = scenario("Scenario Name")
		.exec(http("request_1")
					.get("/")
					.headers(headers_1)
			)

  setUp(scn.inject(atOnceUsers(12)).protocols(httpConf))
}