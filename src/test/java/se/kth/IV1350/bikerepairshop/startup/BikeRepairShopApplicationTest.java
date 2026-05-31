package se.kth.IV1350.bikerepairshop.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.IV1350.bikerepairshop.integration.RepairOrderRegistryIntegration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BikeRepairShopApplicationTest {

    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
        System.setIn(new ByteArrayInputStream("j\n".getBytes(StandardCharsets.UTF_8)));
        clearRepairOrderRegistry();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private String capturedOutput() {
        return outContent.toString(StandardCharsets.UTF_8);
    }

    private void clearRepairOrderRegistry() throws Exception {
        Field repairOrdersField = RepairOrderRegistryIntegration.class.getDeclaredField("repairOrders");
        repairOrdersField.setAccessible(true);
        List<?> list = (List<?>) repairOrdersField.get(null);
        list.clear();
    }

    @Test
    void main_shouldPrintCustomerInformationSection() throws Exception {
        BikeRepairShopApplication.main(new String[]{});

        String output = capturedOutput();
        assertTrue(output.contains("RECEPTIONIST HÄMTAR KUNDINFORMATION"),
                "main should print the customer-information header");
        assertTrue(output.contains("Customer Customersson"),
                "main should print the customer name");
    }

    @Test
    void main_shouldPrintProblemDescription() throws Exception {
        BikeRepairShopApplication.main(new String[]{});

        assertTrue(capturedOutput().contains("Punktering på bakhjulet."),
                "main should print the problem description entered by the receptionist");
    }

    @Test
    void main_shouldNotifyObserversAndPrintRepairOrderStateChangedAtLeastOnce() throws Exception {
        BikeRepairShopApplication.main(new String[]{});

        assertTrue(capturedOutput().contains("REPAIR ORDER STATE CHANGED"),
                "main should trigger at least one observer notification printed by RepairOrderView");
    }

    @Test
    void main_shouldPrintApprovalPrompt() throws Exception {
        BikeRepairShopApplication.main(new String[]{});

        assertTrue(capturedOutput().contains("ÄR REPAIR ORDER GODKÄND AV KUND"),
                "main should ask the receptionist whether the repair order is approved");
    }

    @Test
    void main_shouldPrintReceiptWithCustomerNameAndAcceptedStatus() throws Exception {
        BikeRepairShopApplication.main(new String[]{});

        String output = capturedOutput();
        assertTrue(output.contains("SYSTEMET SKRIVER UT KVITTO"),
                "main should print the receipt header after approval");
        assertTrue(output.contains("Customer Customersson"),
                "main should print the customer name on the receipt");
        assertTrue(output.contains("Status: ACCEPTED"),
                "main should print the final accepted status on the receipt");
    }
}