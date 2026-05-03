package bikerepairshop.integration;

import bikerepairshop.model.entity.BikeRepairConsultationEntity;
import bikerepairshop.model.entity.CustomerDetailsEntity;
import bikerepairshop.util.Util;
import java.util.*;

public class CustomerRegistryIntegration {
    private static final List<CustomerDetailsEntity> customerDetailsList;
    static {
        customerDetailsList = new ArrayList<>();
        List<BikeRepairConsultationEntity> consultationEntities = new ArrayList<>();
        consultationEntities.add(new BikeRepairConsultationEntity("24jan", Util.generateRandomId(), "someModel", "someBrand", "serial123"));
        CustomerDetailsEntity firstCustomer = new CustomerDetailsEntity("Esmeralda", "first@customer.now", "070123",consultationEntities );
        customerDetailsList.add(firstCustomer);
    }

    public CustomerRegistryIntegration() {
    }

    public CustomerDetailsEntity findCustomerEntityByPhoneNumber(String phoneNumber){
        for(CustomerDetailsEntity customerDetailsEntity : customerDetailsList){
            if(customerDetailsEntity.getPhoneNumber().equals(phoneNumber))
                return customerDetailsEntity;
        }
        return null;
    }

    public CustomerDetailsEntity findCustomerByConsultationId(String consultationId){
        for(CustomerDetailsEntity customerDetailsEntity : customerDetailsList){
            for(BikeRepairConsultationEntity bikeRepairConsultationEntity : customerDetailsEntity.getConsultations()){
                if(bikeRepairConsultationEntity.getId().equals(consultationId))
                    return customerDetailsEntity;
            }

        }
        return null;
    }

}
