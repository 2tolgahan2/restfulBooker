package services.booking;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static config.RestfulBookerAPI.BASE_URL;

public class test {
    @Test
    public void sample() {

        RequestSpecification restAssuredReq = RestAssured.given()
                .header("Content-Type", "application/json")
                .log()
                .all(true);
        Response response = restAssuredReq.get(BASE_URL +"/booking");
        attachment(restAssuredReq, BASE_URL + "/booking", response);
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    public String attachment(RequestSpecification httpRequest, String baseUrl, Response response) {
        String html = "Url = " + baseUrl + "\n \n" +
                "Request Headers = " + ((RequestSpecificationImpl) httpRequest).getHeaders() + "\n \n" +
                "Request Body = " + ((RequestSpecificationImpl) httpRequest).getBody() + "\n \n" +
                "Response Body = " + response.getBody().asString();
        Allure.addAttachment("Request Detail", html);
        return html;
    }
}
