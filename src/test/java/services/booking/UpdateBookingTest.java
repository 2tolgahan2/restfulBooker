package services.booking;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.BookingRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static config.RestfulBookerAPI.BASE_URL;
import static config.RestfulBookerAPI.STATUS_CODE_SUCCESS;
import static io.restassured.RestAssured.given;

public class UpdateBookingTest {
    public Response put(String request, int bookingId){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie","token=8f71a8eaaac173b")
                .body(request).
                when()
                .put(BASE_URL +"/booking" + "/" + bookingId);
        return response;
    }

    @Test
    public void updateBookingHappyPath(){
        int bookingId = 1;
        BookingRequest.BookingDates bookingDates = new BookingRequest.BookingDates("2018-01-01", "2019-01-01");
        BookingRequest  bookingRequest = new BookingRequest("James", "Brown", 111, true, bookingDates, "Has a cat xd");
        String request = new Gson().toJson(bookingRequest);
        Response response = put(request, bookingId);

        Assert.assertEquals(response.statusCode(), STATUS_CODE_SUCCESS);
    }
}
