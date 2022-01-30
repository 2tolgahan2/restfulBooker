package services.booking;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.BookingRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static config.RestfulBookerAPI.BASE_URL;
import static io.restassured.RestAssured.given;

public class DeleteBookingTest {
    public Response delete(int bookingId){
        Response response = given()
                .header("Content-Type", "application/json").
                when()
                .delete(BASE_URL +"/booking" + "/" + bookingId);
        return response;
    }


    @Test
    public void deleteBookingHappyPath(){
        int bookingId = 4;
        Response response = delete(bookingId);

        System.out.println(response.getBody().asString());
        /*
        Assert.assertTrue(response.jsonPath().getString("booking.totalprice").equals(72));
        Assert.assertEquals(response.jsonPath().getString("booking.depositpaid"), bookingRequest.depositpaid);
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"), bookingDates.checkin);
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"), bookingDates.checkout);
        Assert.assertEquals(response.jsonPath().getString("booking.additionalneeds"), bookingRequest.additionalneeds);
         */
    }
}
