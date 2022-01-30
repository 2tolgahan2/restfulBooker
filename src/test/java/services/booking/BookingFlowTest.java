package services.booking;

import com.google.gson.Gson;
import health.HealthTest;
import io.qameta.allure.Allure;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import models.AuthRequest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.auth.CreateTokenTest;

import static config.RestfulBookerAPI.BASE_URL;
import static config.RestfulBookerAPI.STATUS_CODE_SUCCESS;
import static io.restassured.RestAssured.given;


public class BookingFlowTest {

    HealthTest healthTest = new HealthTest();
    CreateTokenTest createTokenTest = new CreateTokenTest();
    CreateBookingTest createBookingTest = new CreateBookingTest();
    GetBookingIdsTest getBookingIdsTest = new GetBookingIdsTest();
    GetBookingTest getBookingTest = new GetBookingTest();
    UpdateBookingTest updateBookingTest = new UpdateBookingTest();

    @BeforeClass
    public void background(){
        healthTest.checkHealth();
    }
      /*
    @Test
    public void bookingE2EHappyPath(){
        createBookingTest.createBookingHappyPath();
        getBookingIdsTest.getBookingIdsHappyPath("Tolgahan", "Bardakci", "2022-11-11", "2022-12-12", 200);
        getBookingTest.getBookingHappyPath();
        updateBookingTest.updateBookingHappyPath();
    }
        @Test
    public void bookingE2EHappyPath() {
        RequestSpecification restAssuredReq = RestAssured.given()
                .header("Content-Type", "application/json")
                .log()
                .all(true);

        Response response = restAssuredReq.get(BASE_URL +"/booking");
        attachment(restAssuredReq, BASE_URL + "/booking", response);
        Assert.assertEquals(response.getStatusCode(), STATUS_CODE_SUCCESS);
    }
    Response response = given()
            .header("Content-Type", "application/json")
            .body(request).
            when()
            .post(BASE_URL +"/auth");
         */
      @Test
      public void createToken() {
          AuthRequest authRequest = new AuthRequest("admin", "password123");
          String request = new Gson().toJson(authRequest);
          Response response = createTokenTest.post(request);

          attachment(request, BASE_URL + "/auth", response);
          Assert.assertEquals(response.getStatusCode(), STATUS_CODE_SUCCESS);
      }

    public String attachment(String request, String url, Response response) {
        String html = "Url = " + url + "\n \n" +
                "Request Body = " + request + "\n \n" +
                "Response Body = " + response.getBody().asString();
        Allure.addAttachment("Request Detail", html);
        return html;
    }


    /*
	RestAssured.baseURI ="https://restapi.demoqa.com/customer";
	RequestSpecification request = RestAssured.given();

	JSONObject requestParams = new JSONObject();
	requestParams.put("username", "admin");
	requestParams.put("password", "password123");

	request.body(requestParams.toJSONString());
	Response response = request.post("/register");

     */

    @AfterClass(alwaysRun = true)
    // Delete
    public void after(){
        System.out.println("yy");
    }

}
