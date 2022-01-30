package services.auth;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.AuthRequest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static config.RestfulBookerAPI.BASE_URL;
import static config.RestfulBookerAPI.STATUS_CODE_SUCCESS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CreateTokenTest {
    public Response post(String request){
        Response response = given()
                .header("Content-Type", "application/json")
                .body(request).
                when()
                .post(BASE_URL +"/auth");
        return response;
    }

    @Test
    public void createTokenHappyPath() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");
        String request = new Gson().toJson(authRequest);
        Response response = post(request);

        Assert.assertEquals(response.getStatusCode(), STATUS_CODE_SUCCESS);
        Assert.assertNotNull(response.jsonPath().getString("token"));
    }

    @Test
    public void createTokenInvalidUsername(){
        AuthRequest authRequest = new AuthRequest("Tolgahan", "password123");
        String request = new Gson().toJson(authRequest);
        Response response = post(request);

        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
    }

    @Test
    public void createTokenInvalidPassword(){
        AuthRequest authRequest = new AuthRequest("admin", "dummy-password");
        String request = new Gson().toJson(authRequest);
        Response response = post(request);

        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
    }

    @Test
    public void createTokenEmptyInput(){
        AuthRequest authRequest = new AuthRequest("", "");
        String request = new Gson().toJson(authRequest);
        Response response = post(request);

        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
    }
}
