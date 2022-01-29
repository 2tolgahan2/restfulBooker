package services.booking;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.AuthRequest;
import models.BookingRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static config.RestfulBookerAPI.BASE_URL;
import static config.RestfulBookerAPI.STATUS_CODE_SUCCESS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class CreateBookingTest {
    public Response post(String request){
        Response response = given()
                .header("Content-Type", "application/json")
                .body(request).
                when()
                .post(BASE_URL +"/booking");
        return response;
    }


    @Test
    public void createBookingHappyPath(){
        BookingRequest.BookingDates bookingDates = new BookingRequest.BookingDates("2022-11-09", "2022-11-11");
        BookingRequest  bookingRequest = new BookingRequest("Tolgahan", "Bardakci", 72, true, bookingDates, "Has a cat xd");
        String request = new Gson().toJson(bookingRequest);
        Response response = post(request);

        // Check all firstname-surname
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), bookingRequest.firstname);
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), bookingRequest.lastname);
        /*
        Assert.assertTrue(response.jsonPath().getString("booking.totalprice").equals(72));
        Assert.assertEquals(response.jsonPath().getString("booking.depositpaid"), bookingRequest.depositpaid);
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"), bookingDates.checkin);
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"), bookingDates.checkout);
        Assert.assertEquals(response.jsonPath().getString("booking.additionalneeds"), bookingRequest.additionalneeds);
         */
    }

    @Test
    public void createBookingInvalidDates(){
        BookingRequest.BookingDates bookingDates = new BookingRequest.BookingDates("11092022", "11112022");
        BookingRequest  bookingRequest = new BookingRequest("Tolgahan", "Bardakci", 72, true, bookingDates, "Has a cat xd");
        String request = new Gson().toJson(bookingRequest);
        Response response = post(request);

        Assert.assertTrue(response.getBody().asString().equals("Invalid date"));
    }

}
