package bikerepairshop.integration;

import bikerepairshop.model.entity.BikeRepairConsultationEntity;
import bikerepairshop.model.entity.CustomerDetailsEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class CustomerRegistryIntegration {
    private static final HashMap<String, CustomerDetailsEntity> customerDetailsEntityHashMap;

    static { /// a customer consultation
        customerDetailsEntityHashMap = new HashMap<>();
        List<BikeRepairConsultationEntity> list = new ArrayList<>();
        list.add(new BikeRepairConsultationEntity("24jan", randomId(), "someModel", "someBrand", "serial123"));
        CustomerDetailsEntity firstCustomer = new CustomerDetailsEntity("Esmeralda", "first@customer.now", "070123",list );
        customerDetailsEntityHashMap.put(firstCustomer.getPhoneNumber(), firstCustomer);

    }
    private static String randomId (){
        return UUID.randomUUID().toString();
    }

    public CustomerRegistryIntegration() {
    }


    public CustomerDetailsEntity findCustomerEntityByPhoneNumber(String phoneNumber){
        return customerDetailsEntityHashMap.get(phoneNumber);
    }

    public CustomerDetailsEntity findCustomerByConsultationId(String consultationId){
        AtomicReference<CustomerDetailsEntity> customerDetailsEntity = new AtomicReference<>();
        customerDetailsEntityHashMap.forEach( (phoneNumber,entity) -> {
            List <BikeRepairConsultationEntity> consultations = entity.getConsultations();
            for(BikeRepairConsultationEntity consultation : consultations){
                if (consultation.getId().equals(consultationId)){
                    customerDetailsEntity.set(entity);
                }

            }
        });
        return customerDetailsEntity.get();
    }







}
