package services.booking;

import com.google.gson.Gson;
import health.HealthTest;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.AuthRequest;
import models.BookingRequest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.auth.CreateTokenTest;

import static config.RestfulBookerAPI.BASE_URL;
import static config.RestfulBookerAPI.STATUS_CODE_SUCCESS;
import static io.restassured.RestAssured.given;


public class BookingFlowTest {

    public static Response responseCreateBooking;
    HealthTest healthTest = new HealthTest();
    CreateTokenTest createTokenTest = new CreateTokenTest();
    CreateBookingTest createBookingTest = new CreateBookingTest();
    UpdateBookingTest updateBookingTest = new UpdateBookingTest();
    DeleteBookingTest deleteBookingTest = new DeleteBookingTest();

    @BeforeClass
    public void background(){
        healthTest.checkHealth();
    }

    @Test
    public void createToken() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");
        String request = new Gson().toJson(authRequest);
        Response response = createTokenTest.post(request);

        attachment(request, BASE_URL + "/auth", response);
        Assert.assertEquals(response.getStatusCode(), STATUS_CODE_SUCCESS);
    }

    @Test
    public void createBooking(){
        BookingRequest.BookingDates bookingDates = new BookingRequest.BookingDates("2022-11-09", "2022-11-11");
        BookingRequest  bookingRequest = new BookingRequest("Tolgahan", "Bardakci", 72, true, bookingDates, "Has a cat xd");
        String request = new Gson().toJson(bookingRequest);
        BookingFlowTest.responseCreateBooking = createBookingTest.post(request);

        attachment(request, BASE_URL + "/booking", responseCreateBooking);
        Assert.assertEquals(responseCreateBooking.getStatusCode(), STATUS_CODE_SUCCESS);
    }

    @Test
    public void updateBooking(){
        int bookingId = 1;
        BookingRequest.BookingDates bookingDates = new BookingRequest.BookingDates("2018-01-01", "2019-01-01");
        BookingRequest  bookingRequest = new BookingRequest("James", "Brown", 111, true, bookingDates, "Has a cat xd");
        String request = new Gson().toJson(bookingRequest);
        Response response = updateBookingTest.put(request, bookingId);

        attachment(request, BASE_URL + "/booking" + "/" + bookingId, response);
        Assert.assertEquals(response.getStatusCode(), STATUS_CODE_SUCCESS);
    }

    @Test
    public void deleteBooking(){
        int bookingId = 4;
        Response response = deleteBookingTest.delete(bookingId);
        attachment("", BASE_URL + "/booking" + "/" + bookingId, response);
        Assert.assertEquals(response.getStatusCode(), STATUS_CODE_SUCCESS);
      }

    public String attachment(String request, String url, Response response) {
        String html = "Url = " + url + "\n \n" +
                "Request Body = " + request + "\n \n" +
                "Response Body = " + response.getBody().asString();
        Allure.addAttachment("Request Detail", html);
        return html;
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        System.out.println("Created Booking: -->" + responseCreateBooking.getBody().asString());
    }
}
