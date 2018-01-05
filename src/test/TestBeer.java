import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestBeer {
    String baseUrl = "https://api.punkapi.com/v2";

    @Test
    public void GetListOfBeers() {
        given().
        when().
            get(baseUrl + "/beers").
        then().
            header("Content-Type", "application/json; charset=utf-8").
            statusCode(200).
            log().everything();
    }

    @Test
    public void GetASpecificBeer() {
        given().
        when().
            get(baseUrl + "/beers?ids=1").
        then().
            body(
                "id", hasItem(1),
                "name", hasItem("Buzz"),
                "tagline", hasItem("A Real Bitter Experience.")
            ).
            log().everything();
    }

    @Test
    public void GetBeerAboveAlcoholPercentage() {
        given().
        when().
            get(baseUrl + "/beers?abv_gt=6").
        then().
            body("abv.find {it}", greaterThanOrEqualTo(6f)).
            log().everything();
    }

    @Test
    public void GetBeerBelowAlcoholPercentage() {
        given().
        when().
            get(baseUrl + "/beers?abv_lt=6").
        then().
            body("abv.find {it}", lessThanOrEqualTo(6f)).
            log().everything();
    }
}
