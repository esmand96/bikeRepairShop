package bikerepairshop.model.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BikeDetailsTest {
    private BikeDetails bikeDetailsToTest;
    private String brand = "EA";
    private String model = "2026";
    private String serialNumber = "1234";


    @Before
    public void setUp() {
        bikeDetailsToTest = new BikeDetails(brand, model, serialNumber);

    }

    @Test
    public void getBrand() {
        String brandTest = bikeDetailsToTest.getBrand();
        Assert.assertEquals(brand, brandTest);

    }
}