package bikerepairshop.integration;

import bikerepairshop.model.entity.BikeRepairConsultationEntity;
import bikerepairshop.model.entity.CustomerDetailsEntity;
import bikerepairshop.util.Util;
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
        consultationEntities.add(new BikeRepairConsultationEntity("24jan", Util.generateRandomId(), "someModel", "someBrand", "serial123"));
        CustomerDetailsEntity firstCustomer = new CustomerDetailsEntity("Esmeralda", "first@customer.now", "070123", consultationEntities);
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
     * @return The matching customer entity, or {@code null} if no customer has the specified phone number.
     */
    public CustomerDetailsEntity findCustomerEntityByPhoneNumber(String phoneNumber) {
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