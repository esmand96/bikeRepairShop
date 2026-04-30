package bikerepairshop.view;

import bikerepairshop.controller.Controller;
import bikerepairshop.model.dto.CustomerDetailsDTO;
import bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import java.util.List;
import java.util.Scanner;

public class View {
    private final Controller controller;
    public View(Controller controller) {
        this.controller = controller;

    }
    public void askForPhoneNumber(){
        System.out.println("Enter phone number: ");
        Scanner scanner = new Scanner(System.in);
        String phoneNumber =  scanner.nextLine();
        CustomerDetailsDTO customerDetailsDTO = controller.findCustomer(phoneNumber);
        String consultationId = customerDetailsDTO.getConsultationId();
        enterDescription(consultationId);
    }

    public void enterDescription(String consultationId){
        System.out.println("Enter problem description: ");
        Scanner scanner = new Scanner(System.in);
        String description =  scanner.nextLine();
        controller.enterCustomerDescription(consultationId, description);
        technicianChooseNewlyCreatedRepairOrders();
    }

    public void technicianChooseNewlyCreatedRepairOrders(){
       List<PresentNewlyCreatedRepairOrderDTO> newlyCreatedRepairOrderDTOS = controller.getAllNewlyCreatedRepairOrders();
       for (PresentNewlyCreatedRepairOrderDTO presentNewlyCreatedRepairOrderDTO : newlyCreatedRepairOrderDTOS){
           System.out.println(presentNewlyCreatedRepairOrderDTO.getRepairOrderId());
       }
    }



}
