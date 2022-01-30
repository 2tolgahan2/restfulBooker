package services.booking;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static config.RestfulBookerAPI.*;
import static io.restassured.RestAssured.given;

public class GetBookingTest {

    public Response get(int bookingId){
        Response response = given()
                .header("Content-Type", "application/json").
                when()
                .get(BASE_URL + "/booking" +"/"+ bookingId);
        return response;
        }

    @Test
    public void getBookingHappyPath() {
        int bookingId = 7;
        Response response = get(bookingId);
        String responseBody = response.getBody().asString();
       if (responseBody == "Not Found"){
            System.out.println("There is no record for the id #" +bookingId);
        }
       else {
           System.out.println("Booking Information: " + responseBody);
       };

        Assert.assertEquals(response.getStatusCode(), STATUS_CODE_SUCCESS);
    }

}
