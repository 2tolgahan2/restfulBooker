package services.booking;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static config.RestfulBookerAPI.BASE_URL;
import static io.restassured.RestAssured.given;

public class GetBookingIdsTest {

    public Response get(){
        Response response = given().
                header("Content-Type", "application/json").
                when()
                .get(BASE_URL +"/booking");
        return response;
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider(){
        return new Object[][]{
                {"Sally","Ericsson", "", "", 200},
        };
    }


    @Test(dataProvider = "dataProvider")
    public void getBookingIdsHappyPath(String firstName, String lastName, String checkIn, String checkOut, int statusCode) {
        Response response = get();
        System.out.println(response.getBody().asString());
    }

}
