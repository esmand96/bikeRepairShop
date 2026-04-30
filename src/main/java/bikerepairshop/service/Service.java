package bikerepairshop.service;

import bikerepairshop.integration.CustomerRegistryIntegration;
import bikerepairshop.integration.PrinterIntegration;
import bikerepairshop.integration.RepairOrderRegistryIntegration;
import bikerepairshop.model.domain.BikeDetails;
import bikerepairshop.model.domain.CustomerDetails;
import bikerepairshop.model.dto.CustomerDetailsDTO;
import bikerepairshop.model.entity.BikeRepairConsultationEntity;
import bikerepairshop.model.entity.CustomerDetailsEntity;
import java.util.List;

public class Service {
    private final RepairOrderRegistryIntegration repairOrderRegistryIntegration;
    private final CustomerRegistryIntegration customerRegistryIntegration;
    private final PrinterIntegration printerIntegration;
    private final Mapper mapper;

    public Service(RepairOrderRegistryIntegration repairOrderRegistryIntegration, CustomerRegistryIntegration customerRegistryIntegration, PrinterIntegration printerIntegration, Mapper mapper) {
        this.repairOrderRegistryIntegration = repairOrderRegistryIntegration;
        this.customerRegistryIntegration = customerRegistryIntegration;
        this.printerIntegration = printerIntegration;
        this.mapper = mapper;
    }

    /**
     * Skapandet av BikeDetails och CustomerDetails kan flyttas ut i egna/egen metoder för att få högre sammanhållning och bättre inkapsling.
     * @param phoneNumber
     * @return
     */
    public CustomerDetailsDTO findCustomerByPhoneNumber(String phoneNumber) {
        CustomerDetailsEntity customerDetailsEntity = customerRegistryIntegration.findCustomerEntityByPhoneNumber(phoneNumber);
        BikeRepairConsultationEntity bikeRepairConsultationEntity = selectFirstUnhandledConsultation(customerDetailsEntity);
        BikeDetails bikeDetails = createBikeDetails(bikeRepairConsultationEntity);
        CustomerDetails customerDetails = createCustomerDetails(customerDetailsEntity, bikeDetails, bikeRepairConsultationEntity.getId());
        CustomerDetailsDTO customerDetailsDTO = mapper.mapToCustomerDetailsDTO(customerDetails);
        return customerDetailsDTO;
    }

    public void createRepairOrder(String consultationId, String problemDescription){
        CustomerDetailsEntity customerDetailsEntity = customerRegistryIntegration.findCustomerByConsultationId(consultationId);
        BikeRepairConsultationEntity consultationEntity = selectConsultation(consultationId, customerDetailsEntity);
        BikeDetails bikeDetails = createBikeDetails(consultationEntity);
        CustomerDetails customerDetails = createCustomerDetails(customerDetailsEntity, bikeDetails, consultationId);
    }
    ///lossas att den consultation högst upp i listan gäller. sorterar ej. (kanske fixar detta sen),
    /// BYT NAMN
    private BikeRepairConsultationEntity selectFirstUnhandledConsultation (CustomerDetailsEntity customerDetailsEntity){ ///metodens sorterar inte på första unhandeld
        List<BikeRepairConsultationEntity> consultations = customerDetailsEntity.getConsultations();
        return consultations.getFirst();
    }

    private BikeRepairConsultationEntity selectConsultation(String consultationId, CustomerDetailsEntity customerDetailsEntity){
        List <BikeRepairConsultationEntity> consultations = customerDetailsEntity.getConsultations();
        for (BikeRepairConsultationEntity consultation : consultations){
            if( consultationId.equals(consultation.getId()))
                return consultation;
        }
        return null;
    }

    ///skapade metoden pga DRY
    private BikeDetails createBikeDetails(BikeRepairConsultationEntity consultationEntity){
        String serialNumber = consultationEntity.getSerialNumber();
        String brand = consultationEntity.getBrand();
        String model = consultationEntity.getModel();
        BikeDetails bikeDetails = new BikeDetails(brand, serialNumber, model);
        return bikeDetails;
    }
    ///skapade metoden pga DRY
    private CustomerDetails createCustomerDetails(CustomerDetailsEntity customerDetailsEntity, BikeDetails bikeDetails,String consultationId){
        String email = customerDetailsEntity.getEmail();
        String phoneNumber = customerDetailsEntity.getPhoneNumber();
        String name = customerDetailsEntity.getName();
        CustomerDetails customerDetails = new CustomerDetails(name,email, phoneNumber, bikeDetails, consultationId);
        return customerDetails;
    }


}
