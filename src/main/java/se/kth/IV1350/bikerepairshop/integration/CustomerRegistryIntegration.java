package se.kth.IV1350.bikerepairshop.integration;

import se.kth.IV1350.bikerepairshop.exceptions.CustomerNotFoundException;
import se.kth.IV1350.bikerepairshop.exceptions.DatabaseFailureException;
import se.kth.IV1350.bikerepairshop.model.entity.BikeRepairConsultationEntity;
import se.kth.IV1350.bikerepairshop.model.entity.CustomerDetailsEntity;
import se.kth.IV1350.bikerepairshop.util.Util;
import java.util.*;

/**
 * Provides access to the external customer registry. The registry is simulated by an
 * in-memory list of customers, populated with seed data at class load time.
 */
public class CustomerRegistryIntegration {
    private static final List<CustomerDetailsEntity> customerDetailsList;
    static {
        customerDetailsList = new ArrayList<>();
        List<BikeRepairConsultationEntity> consultationEntities = new ArrayList<>();
        consultationEntities.add(BikeRepairConsultationEntity.builder()
                .date("24jan")
                .id(Util.generateRandomId())
                .model("someModel")
                .brand("someBrand")
                .serialNumber("serial123")
                .build());
        CustomerDetailsEntity firstCustomer = CustomerDetailsEntity.builder()
                .name("Customer Customersson")
                .email("first@customer.now")
                .phoneNumber("070123")
                .consultations(consultationEntities)
                .build();
        customerDetailsList.add(firstCustomer);
    }

    /**
     * Creates a new instance.
     */
    public CustomerRegistryIntegration() {
    }

    /**
     * Finds the customer registered with the specified phone number.
     *
     * @param phoneNumber The phone number used to identify the customer.
     * @return The matching customer entity.
     * * @throws DatabaseFailureException if the customer registry cannot be accessed.
     */
    public CustomerDetailsEntity findCustomerEntityByPhoneNumber(String phoneNumber) throws DatabaseFailureException {
        if (phoneNumber.equals("1")) {
            throw new DatabaseFailureException("Customer registry är inte tillgänglig");
        }
        for (CustomerDetailsEntity customerDetailsEntity : customerDetailsList) {
            if (customerDetailsEntity.getPhoneNumber().equals(phoneNumber))
                return customerDetailsEntity;
        }
        return null; 
    }

    /**
     * Finds the customer that owns the consultation with the specified id.
     *
     * @param consultationId The id of the consultation.
     * @return The customer entity that owns the consultation, or {@code null} if no
     *         customer has a consultation with the specified id.
     */
    public CustomerDetailsEntity findCustomerByConsultationId(String consultationId) {
        for (CustomerDetailsEntity customerDetailsEntity : customerDetailsList) {
            for (BikeRepairConsultationEntity bikeRepairConsultationEntity : customerDetailsEntity.getConsultations()) {
                if (bikeRepairConsultationEntity.getId().equals(consultationId))
                    return customerDetailsEntity;
            }
        }
        return null;
    }
}