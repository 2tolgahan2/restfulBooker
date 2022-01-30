package services.booking;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.BookingRequest;
import models.BookingRequestPartial;
import org.testng.Assert;
import org.testng.annotations.Test;

import static config.RestfulBookerAPI.BASE_URL;
import static config.RestfulBookerAPI.STATUS_CODE_SUCCESS;
import static io.restassured.RestAssured.given;

public class PartialUpdateBookingTest {
    public Response patch(String request, int bookingId){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie","token=8f71a8eaaac173b")
                .body(request).
                when()
                .patch(BASE_URL +"/booking" + "/" + bookingId);
        return response;
    }

    @Test
    public void partialUpdateBookingHappyPath(){
        int bookingId = 1;
        BookingRequestPartial bookingRequestPartial = new BookingRequestPartial("xx", "yy");
        String request = new Gson().toJson(bookingRequestPartial);
        Response response = patch(request, bookingId);

        Assert.assertEquals(response.statusCode(), STATUS_CODE_SUCCESS);
    }

}
