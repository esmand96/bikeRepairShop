package se.kth.IV1350.bikerepairshop.integration;

import se.kth.IV1350.bikerepairshop.model.entity.CustomerDetailsEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static se.kth.IV1350.bikerepairshop.TestUtil.CUSTOMER_NAME;


public class CustomerRegistryIntegrationTest {
    private CustomerRegistryIntegration customerRegistry;

    @BeforeEach
    public void setUp(){
        customerRegistry = new CustomerRegistryIntegration();
    }

    @Test
    void testFindCustomerEntityByPhoneNumberReturnsCustomer(){
        CustomerDetailsEntity customer = customerRegistry.findCustomerEntityByPhoneNumber("070123");

        assertNotNull(customer, "Customer not found");
        assertEquals(CUSTOMER_NAME, customer.getName(), "Name does not match");
        assertEquals("first@customer.now", customer.getEmail(), "email does not match");
    }

    @Test
    void testFindCustomerEntityByPhoneNumberReturnsNullForUnknownPhoneNumber(){
        CustomerDetailsEntity customer = customerRegistry.findCustomerEntityByPhoneNumber("987654"); //testar om metoden returner null vid fel nummer

        assertNull(customer, "does not return null when phone number does not exist");
    }

    @Test
    void testFindCustomerByConsultationIdReturnsCustomer(){
        CustomerDetailsEntity customer = customerRegistry.findCustomerEntityByPhoneNumber("070123"); //hämtar kunden
        String consultationId = customer.getConsultations().get(0).getId(); //hämta id:et
        CustomerDetailsEntity customerFound = customerRegistry.findCustomerByConsultationId(consultationId); //själva metoden vi vill testa

        assertNotNull(customerFound, "Customer not found");
        assertEquals(customer.getPhoneNumber(), customerFound.getPhoneNumber(), "phone number does not match"); //vne om man ska ta bort
        assertEquals(consultationId, customerFound.getConsultations().get(0).getId(), "consultationId does not match");
    }

    @Test
    void testFindCustomerByConsultationIdReturnsNullForUnknownId(){
        CustomerDetailsEntity customer = customerRegistry.findCustomerByConsultationId("test");

        assertNull(customer, "does not return null when consultationId does not exist");
    }
}
