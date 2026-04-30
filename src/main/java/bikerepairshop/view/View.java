package bikerepairshop.view;

import bikerepairshop.controller.Controller;
import bikerepairshop.model.dto.CustomerDetailsDTO;

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

    }




}
