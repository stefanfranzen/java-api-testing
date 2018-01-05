import static io.restassured.RestAssured.*;
import org.junit.Test;

public class TestQRCode {
    String baseUrl = "http://api.qrserver.com/v1";

    @Test
    public void CreateQRCodeWithMostBasicRequest() {
        given().
            queryParam("data", "myDataForQRCode").  //mandatory
        when().
            post(baseUrl + "/create-qr-code/").
        then().
            statusCode(200).
            log().everything();
    }

    @Test
    public void CreateQRCodeWithManyOptionalParameters() {
        given().
            queryParam("data", "myDataForQRCode").  //mandatory
            queryParam("size","10x10").             //optional
            queryParam("charset-target","UTF-8").   //optional
            queryParam("bgcolor","255-255-255").    //optional (white)
            queryParam("color","255-0-0").          //optional (red)
            queryParam("format","gif").             //optional
        when().
            post(baseUrl + "/create-qr-code/").
        then().
            statusCode(200).
            log().everything();
    }

    @Test
    public void CreateQRCodeWithNoParameters_500InternalServerError() {
        given().
        when().
            post(baseUrl + "/create-qr-code/").
        then().
            statusCode(500).
            log().everything();
    }

    @Test
    public void CreateQRCodeMissingMandatoryParameter_400BadRequest() {
        given().
            queryParam("size","10x10").             //optional
        when().
            post(baseUrl + "/create-qr-code/").
        then().
            statusCode(400).
            log().everything();
    }
}
