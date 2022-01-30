package services.booking;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static config.RestfulBookerAPI.BASE_URL;
import static config.RestfulBookerAPI.STATUS_CODE_SUCCESS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetBookingIdsTest {

    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider(){
        return new Object[][]{
                {"Mark", "Ericsson", "2020-02-27", "2021-12-29"},
                {"firstname2", "lastname2", "2019-10-10", "2021-04-03"}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void getBookingIds(String firstName, String lastName, String checkIn, String checkOut) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("firstname", firstName);
        queryParams.put("lastname", lastName);

        given()
                .log().all().
                queryParams(queryParams).
                when()
                .get(BASE_URL + "/booking").
                then()
                .statusCode(STATUS_CODE_SUCCESS)
                .log().all();
    }
}
