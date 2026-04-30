package bikerepairshop.service;

import bikerepairshop.model.domain.CustomerDetails;
import bikerepairshop.model.dto.CustomerDetailsDTO;

public class Mapper {
    public Mapper() {
    }
    public CustomerDetailsDTO mapToCustomerDetailsDTO(CustomerDetails customerDetails){
        CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO(customerDetails);
        return customerDetailsDTO;
    }
}
