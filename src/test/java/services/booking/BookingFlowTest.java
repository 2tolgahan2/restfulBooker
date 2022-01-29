package services.booking;

import health.HealthTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.auth.CreateTokenTest;


public class BookingFlowTest {

    HealthTest healthTest = new HealthTest();
    CreateTokenTest createTokenTest = new CreateTokenTest();
    CreateBookingTest createBookingTest = new CreateBookingTest();
    GetBookingIdsTest getBookingIdsTest = new GetBookingIdsTest();
    GetBookingTest getBookingTest = new GetBookingTest();

    @BeforeClass
    public void background(){
        healthTest.checkHealth();
    }

    @Test
    public void bookingE2EHappyPath(){
        createTokenTest.createTokenHappyPath();
        createBookingTest.createBookingHappyPath();
        getBookingIdsTest.getBookingIdsHappyPath("Tolgahan", "Bardakci", "2022-11-11", "2022-12-12", 200);
        getBookingTest.getBookingHappyPath();
    }


    @AfterClass(alwaysRun = true)
    public void after(){
        System.out.println("yy");
    }

}
