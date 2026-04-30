package bikerepairshop.controller;

import bikerepairshop.model.dto.CustomerDetailsDTO;
import bikerepairshop.service.Service;

public class Controller {
    private final Service service;
    public Controller(Service service) {
        this.service = service;
    }

    public CustomerDetailsDTO findCustomer (String phoneNumber){
        CustomerDetailsDTO customerDetails = service.findCustomerByPhoneNumber(phoneNumber);
        return customerDetails;
    }
        ///man kan skapa en dTO som parameter.
    public void enterCustomerDescription(String consultationId, String problemDescription){
        service.createRepairOrder(consultationId, problemDescription);
    }
}
