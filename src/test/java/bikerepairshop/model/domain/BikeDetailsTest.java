package bikerepairshop.model.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BikeDetailsTest {
    private BikeDetails bikeDetailsToTest;
    private String brand = "EA";
    private String model = "2026";
    private String serialNumber = "1234";


    @BeforeEach
    public void setUp() {
        bikeDetailsToTest = new BikeDetails(brand, model, serialNumber);

    }

    @Test
    public void getBrand() {
        String brandTest = bikeDetailsToTest.getBrand();
        Assertions.assertEquals(brand, brandTest);

    }
}