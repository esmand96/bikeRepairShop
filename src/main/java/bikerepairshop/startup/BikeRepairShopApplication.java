package bikerepairshop.startup;

import bikerepairshop.controller.Controller;
import bikerepairshop.integration.CustomerRegistryIntegration;
import bikerepairshop.integration.PrinterIntegration;
import bikerepairshop.integration.RepairOrderRegistryIntegration;
import bikerepairshop.service.Mapper;
import bikerepairshop.service.Service;
import bikerepairshop.view.View;

/**
 * Starts the bike repair shop application by creating and wiring together the central
 * objects of the system, and then handing control over to the view.
 */
public class BikeRepairShopApplication {

    /**
     * Application entry point. Instantiates the integrations, the mapper, the service,
     * the controller and the view, and starts the main flow.
     *
     * @param args Command line arguments, not used.
     */
    public static void main(String[] args) {
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