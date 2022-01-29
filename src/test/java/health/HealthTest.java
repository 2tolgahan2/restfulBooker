package health;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static config.RestfulBookerAPI.BASE_URL;
import static io.restassured.RestAssured.given;

public class HealthTest {

    @Test
    public void checkHealth(){
        int statusCode = 201;
        given()
                .log().all().
                when()
                .get(BASE_URL+"/ping")
                .then()
                .statusCode(statusCode)
                .log().all();
    }
}
