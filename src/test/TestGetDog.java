import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TestGetDog {
    String baseUrl = "https://dog.ceo";

    @Test
    public void GetListOfDogsShouldReturnResponse200() {
        given().
        when().
            get(baseUrl + "/api/breeds/list/all").
        then().
            statusCode(200);

    }

    @Test
    public void GetListOfDogsShouldReturnResponse404() {
        given().
        when().
            get(baseUrl + "/api/breeds/list/allz").
        then().
            statusCode(404);
    }
}
