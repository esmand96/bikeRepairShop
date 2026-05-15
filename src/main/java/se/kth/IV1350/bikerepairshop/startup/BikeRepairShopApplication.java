package se.kth.IV1350.bikerepairshop.startup;

import se.kth.IV1350.bikerepairshop.controller.Controller;
import se.kth.IV1350.bikerepairshop.integration.CustomerRegistryIntegration;
import se.kth.IV1350.bikerepairshop.integration.PrinterIntegration;
import se.kth.IV1350.bikerepairshop.integration.RepairOrderRegistryIntegration;
import se.kth.IV1350.bikerepairshop.logging.RepairOrderLogger;
import se.kth.IV1350.bikerepairshop.observer.RepairOrderObserver;
import se.kth.IV1350.bikerepairshop.service.Mapper;
import se.kth.IV1350.bikerepairshop.service.Service;
import se.kth.IV1350.bikerepairshop.logging.FileLogger;
import se.kth.IV1350.bikerepairshop.logging.Logger;
import se.kth.IV1350.bikerepairshop.view.RepairOrderView;
import se.kth.IV1350.bikerepairshop.view.View;

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
        RepairOrderObserver repairOrderObserver = new RepairOrderView();
        RepairOrderObserver repairOrderObserver1 = new RepairOrderLogger();
        Service service = new Service(repairOrderRegistryIntegration, customerRegistryIntegration, printerIntegration, mapper);
        service.addObserver(repairOrderObserver);
        service.addObserver(repairOrderObserver1);
        Controller controller = new Controller(service);
        Logger <String> logger = new FileLogger();
        View view = new View(controller, logger);
        view.askForPhoneNumber();
    }
}