package se.kth.IV1350.bikerepairshop.startup;

import se.kth.IV1350.bikerepairshop.controller.Controller;
import se.kth.IV1350.bikerepairshop.integration.CustomerRegistryIntegration;
import se.kth.IV1350.bikerepairshop.integration.PrinterIntegration;
import se.kth.IV1350.bikerepairshop.integration.RepairOrderRegistryIntegration;
import se.kth.IV1350.bikerepairshop.logging.*;
import se.kth.IV1350.bikerepairshop.observer.AbstractRepairOrderObserver;
import se.kth.IV1350.bikerepairshop.observer.RepairOrderObserver;
import se.kth.IV1350.bikerepairshop.service.Mapper;
import se.kth.IV1350.bikerepairshop.service.Service;
import se.kth.IV1350.bikerepairshop.view.RepairOrderView;
import se.kth.IV1350.bikerepairshop.view.View;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    public static void main(String[] args) throws IOException {
        PrinterIntegration printerIntegration = new PrinterIntegration();
        CustomerRegistryIntegration customerRegistryIntegration = CustomerRegistryIntegration.getInstance();
        Mapper mapper = new Mapper();
        RepairOrderRegistryIntegration repairOrderRegistryIntegration = RepairOrderRegistryIntegration.getInstance();
        AbstractRepairOrderObserver repairOrderObserver = new RepairOrderView();

        TimestampedLogWriter repairOrderWriter = new TimestampedLogWriter(new FileWriter("src/main/resources/repairOrderLog.txt", true));

        AbstractRepairOrderObserver repairOrderObserver1 = new RepairOrderLogger(repairOrderWriter);
        Service service = new Service(repairOrderRegistryIntegration, customerRegistryIntegration, printerIntegration, mapper);
        service.addObserver(repairOrderObserver);
        service.addObserver(repairOrderObserver1);
        Controller controller = new Controller(service);


        PrintWriter printWriter = new TimestampedPrintWriter(new FileWriter("src/main/resources/log.txt", true));

        Logger <String> logger = new FileLogger(printWriter);
        View view = new View(controller, logger);
        view.askForPhoneNumber();
    }
}