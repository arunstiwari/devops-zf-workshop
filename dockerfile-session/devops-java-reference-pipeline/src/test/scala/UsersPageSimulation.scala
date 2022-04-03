
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class UsersPageSimulation extends Simulation {

	val url = scala.util.Properties.propOrElse("url","http://localhost:8080")

  private val httpProtocol = http
    .baseUrl(url)
    .inferHtmlResources()
    .acceptHeader("application/json")
//    .acceptEncodingHeader("gzip, deflate")
//    .acceptLanguageHeader("en-US,en;q=0.9,zu;q=0.8")
//    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Mobile Safari/537.36")

	val headers_11 = Map(
	    "accept" -> "application/json, text/plain, */*",
	    "accept-encoding" -> "gzip, deflate, br",
	    "accept-language" -> "en-US,en;q=0.9",
	    "content-type" -> "application/json;charset=UTF-8")

  private val scn = scenario("UsersPageSimulation")
    .exec(
      http("request_0")
        .get("/users")
        .headers(headers_11)
    )

//	setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)
	setUp(
		scn.inject(
			nothingFor(5),
			atOnceUsers(20),
			constantUsersPerSec(10).during(30.seconds)
			//        rampUsers(40) during (15 seconds),
		)
	).protocols(httpProtocol).maxDuration( 1 minute)
}
