package `in`.rajmahendra.movie

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class TestMovie {
    @Test
    fun initiallyMoviesIsEmpty() {
        given()
            .`when`().get("/movies")
            .then()
            .statusCode(equalTo(200))
    }

    @Test
    fun shouldBeEmptyWhenInvalidMovieIdProvided() {
        given()
            .pathParam("id", 2)
            .`when`().get("/movies/id/{id}")
            .then()
            .statusCode(200).body("id", equalTo(2)).body("title", equalTo("Ponnien Selvan 2"))

    }

    @Test
    fun shouldAddAMovie() {
        val jsonStr = """{"id" : 3,"title" : "Sardar","released" : 2022}"""

        given().contentType(ContentType.JSON).
        and().body(jsonStr).
        `when`().post("/movies").
        then().body("id", equalTo(3))
    }


}