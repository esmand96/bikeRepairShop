package bikerepairshop.startup;

import bikerepairshop.controller.Controller;
import bikerepairshop.integration.CustomerRegistryIntegration;
import bikerepairshop.integration.PrinterIntegration;
import bikerepairshop.integration.RepairOrderRegistryIntegration;
import bikerepairshop.service.Mapper;
import bikerepairshop.service.Service;
import bikerepairshop.view.View;

public class BikeRepairShopApplication  {


    public static void main(String[] args)  {
        PrinterIntegration printerIntegration = new PrinterIntegration();
        CustomerRegistryIntegration customerRegistryIntegration = new CustomerRegistryIntegration();
        Mapper mapper = new Mapper();
        RepairOrderRegistryIntegration repairOrderRegistryIntegration = new RepairOrderRegistryIntegration();
        Service service = new Service(repairOrderRegistryIntegration, customerRegistryIntegration, printerIntegration, mapper);
        Controller controller = new Controller(service);
        View view = new View(controller);
        view.askForPhoneNumber();

    }
}
