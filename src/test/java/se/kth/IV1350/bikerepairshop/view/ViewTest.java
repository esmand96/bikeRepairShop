package se.kth.IV1350.bikerepairshop.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import se.kth.IV1350.bikerepairshop.controller.Controller;
import se.kth.IV1350.bikerepairshop.exceptions.CustomerNotFoundException;
import se.kth.IV1350.bikerepairshop.exceptions.DatabaseFailureException;
import se.kth.IV1350.bikerepairshop.logging.Logger;
import se.kth.IV1350.bikerepairshop.model.dto.CustomerDetailsDTO;
import se.kth.IV1350.bikerepairshop.model.dto.DiagnosticReportDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import se.kth.IV1350.bikerepairshop.model.dto.ReceiptDTO;
import se.kth.IV1350.bikerepairshop.model.dto.RepairTaskDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static se.kth.IV1350.bikerepairshop.TestUtil.BIKE_BRAND;
import static se.kth.IV1350.bikerepairshop.TestUtil.BIKE_MODEL;
import static se.kth.IV1350.bikerepairshop.TestUtil.BIKE_SERIAL_NUMBER;
import static se.kth.IV1350.bikerepairshop.TestUtil.CONSULTATION_ID;
import static se.kth.IV1350.bikerepairshop.TestUtil.CUSTOMER_EMAIL;
import static se.kth.IV1350.bikerepairshop.TestUtil.CUSTOMER_NAME;
import static se.kth.IV1350.bikerepairshop.TestUtil.CUSTOMER_PHONE;
import static se.kth.IV1350.bikerepairshop.TestUtil.ORDER_ID;
import static se.kth.IV1350.bikerepairshop.TestUtil.ORDER_PROBLEM_DESCRIPTION;
import static se.kth.IV1350.bikerepairshop.TestUtil.TASK_COST;
import static se.kth.IV1350.bikerepairshop.TestUtil.TASK_DESCRIPTION;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ViewTest {

    @Mock
    private Controller controller;

    @Mock
    private Logger<String> logger;

    private View view;
    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void createView() {
        createViewWithStdin("");
    }

    private void createViewWithStdin(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        view = new View(controller, logger);
    }

    private String capturedOutput() {
        return outContent.toString(StandardCharsets.UTF_8);
    }

    private CustomerDetailsDTO createCustomerDetailsDTO() {
        return CustomerDetailsDTO.builder()
                .name(CUSTOMER_NAME)
                .email(CUSTOMER_EMAIL)
                .phoneNumber(CUSTOMER_PHONE)
                .bikeBrand(BIKE_BRAND)
                .bikeModel(BIKE_MODEL)
                .bikeSerialNumber(BIKE_SERIAL_NUMBER)
                .consultationId(CONSULTATION_ID)
                .build();
    }

    private PresentNewlyCreatedRepairOrderDTO createNewlyCreatedDTO() {
        return PresentNewlyCreatedRepairOrderDTO.builder()
                .name(CUSTOMER_NAME)
                .email(CUSTOMER_EMAIL)
                .phoneNumber(CUSTOMER_PHONE)
                .bikeBrand(BIKE_BRAND)
                .bikeModel(BIKE_MODEL)
                .bikeSerialNumber(BIKE_SERIAL_NUMBER)
                .problemDescription(ORDER_PROBLEM_DESCRIPTION)
                .state("NEWLY_CREATED")
                .repairOrderId(ORDER_ID)
                .build();
    }

    private PresentRepairOrderForApprovalDTO createApprovalDTO() {
        DiagnosticReportDTO diagnosticReportDTO = DiagnosticReportDTO.builder()
                .description("Brustet hjul och punktering")
                .estimatedRepairTime(LocalDateTime.of(2026, 5, 20, 21, 56))
                .build();
        RepairTaskDTO task = RepairTaskDTO.builder()
                .description(TASK_DESCRIPTION)
                .cost(TASK_COST)
                .build();
        return PresentRepairOrderForApprovalDTO.builder()
                .repairOrderId(ORDER_ID)
                .customerPhoneNumber(CUSTOMER_PHONE)
                .bikeBrand(BIKE_BRAND)
                .bikeModel(BIKE_MODEL)
                .bikeSerialNumber(BIKE_SERIAL_NUMBER)
                .diagnosticReport(diagnosticReportDTO)
                .proposedRepairTasks(List.of(task))
                .totalCost(TASK_COST)
                .build();
    }

    private ReceiptDTO createReceiptDTO() {
        DiagnosticReportDTO diagnosticReportDTO = DiagnosticReportDTO.builder()
                .description("Brustet hjul och punktering")
                .estimatedRepairTime(LocalDateTime.of(2026, 5, 20, 21, 56))
                .build();
        RepairTaskDTO task = RepairTaskDTO.builder()
                .description(TASK_DESCRIPTION)
                .cost(TASK_COST)
                .build();
        return ReceiptDTO.builder()
                .name(CUSTOMER_NAME)
                .email(CUSTOMER_EMAIL)
                .phoneNumber(CUSTOMER_PHONE)
                .bikeBrand(BIKE_BRAND)
                .bikeModel(BIKE_MODEL)
                .bikeSerialNumber(BIKE_SERIAL_NUMBER)
                .problemDescription(ORDER_PROBLEM_DESCRIPTION)
                .state("ACCEPTED")
                .diagnosticReport(diagnosticReportDTO)
                .repairTasks(List.of(task))
                .totalCost(TASK_COST)
                .build();
    }

    private void stubCustomer() throws CustomerNotFoundException, DatabaseFailureException {
        when(controller.findCustomer(CUSTOMER_PHONE)).thenReturn(createCustomerDetailsDTO());
    }

    private void stubNewlyCreatedList() {
        when(controller.getAllNewlyCreatedRepairOrders()).thenReturn(List.of(createNewlyCreatedDTO()));
    }

    private void stubApprovalList() {
        when(controller.getAllReadyForApprovalOrders()).thenReturn(List.of(createApprovalDTO()));
    }

    private void stubReceipt() {
        when(controller.getReceipt(ORDER_ID)).thenReturn(createReceiptDTO());
    }

    @Test
    void askForPhoneNumber_shouldPrintCustomerHeaderNameEmailPhoneAndBikeDetails_onHappyPath()
            throws CustomerNotFoundException, DatabaseFailureException {
        stubCustomer();
        stubNewlyCreatedList();
        stubApprovalList();
        stubReceipt();
        createViewWithStdin("j\n");

        view.askForPhoneNumber();

        String output = capturedOutput();
        assertTrue(output.contains("RECEPTIONIST HÄMTAR KUNDINFORMATION FÖR 070123"),
                "should print the customer-info header including the phone number");
        assertTrue(output.contains(CUSTOMER_NAME),
                "should print the customer's name");
        assertTrue(output.contains(CUSTOMER_EMAIL),
                "should print the customer's email");
        assertTrue(output.contains(CUSTOMER_PHONE),
                "should print the customer's phone number");
        assertTrue(output.contains(BIKE_BRAND),
                "should print the bike brand");
        assertTrue(output.contains(BIKE_MODEL),
                "should print the bike model");
        assertTrue(output.contains(BIKE_SERIAL_NUMBER),
                "should print the bike serial number");
        assertTrue(output.contains("Konsultations-ID:"),
                "should print the consultation id label");
        assertTrue(output.contains(CONSULTATION_ID),
                "should print the consultation id value");
    }

    @Test
    void askForPhoneNumber_shouldPrintExceptionMessage_whenCustomerNotFoundExceptionIsThrown()
            throws CustomerNotFoundException, DatabaseFailureException {
        String message = "Ingen kund kopplad till telefonnummer 070123";
        when(controller.findCustomer(CUSTOMER_PHONE)).thenThrow(new CustomerNotFoundException(message));
        createView();

        view.askForPhoneNumber();

        assertTrue(capturedOutput().contains(message),
                "should print the message from CustomerNotFoundException");
    }

    @Test
    void askForPhoneNumber_shouldPrintSystemFailureMessageAndLog_whenDatabaseFailureExceptionIsThrown()
            throws CustomerNotFoundException, DatabaseFailureException {
        String message = "Customer registry är inte tillgänglig";
        when(controller.findCustomer(CUSTOMER_PHONE)).thenThrow(new DatabaseFailureException(message));
        createView();

        view.askForPhoneNumber();

        assertTrue(capturedOutput().contains("Systemfel: Databasen är inte tillgänglig"),
                "should print a generic system-failure message to the user");
        verify(logger).logg(message);
    }

    @Test
    void enterDescription_shouldPrintRegisterHeaderAndProblemDescription() {
        stubNewlyCreatedList();
        stubApprovalList();
        stubReceipt();
        createViewWithStdin("j\n");

        view.enterDescription(CONSULTATION_ID);

        String output = capturedOutput();
        assertTrue(output.contains("RECEPTIONIST REGISTRERAR NY REPAIR ORDER"),
                "should print the register-new-order header");
        assertTrue(output.contains("Beskrivning:"),
                "should print the description label");
        assertTrue(output.contains("Punktering på bakhjulet."),
                "should print the problem description value");
        verify(controller).enterCustomerDescription(CONSULTATION_ID, "Punktering på bakhjulet.");
    }

    @Test
    void technicianChooseNewlyCreatedRepairOrders_shouldPrintHeaderAndSelectedOrderId() {
        stubNewlyCreatedList();
        stubApprovalList();
        stubReceipt();
        createViewWithStdin("j\n");

        view.technicianChooseNewlyCreatedRepairOrders();

        String output = capturedOutput();
        assertTrue(output.contains("TEKNIKER  HÄMTAR ALLA NYA REPARATIONSORDRAR"),
                "should print the technician-fetches-new-orders header");
        assertTrue(output.contains("Väljer Order ID som visats på displayen :"),
                "should print the order-selection label");
        assertTrue(output.contains(ORDER_ID),
                "should print the selected order id");
    }

    @Test
    void technicianEntersDiagnosticReportAndRepairTasks_shouldPrintDiagnosisOrderIdRepairTasksAndEstimatedRepairTime() {
        stubApprovalList();
        stubReceipt();
        createViewWithStdin("j\n");

        view.technicianEntersDiagnosticReportAndRepairTasks(ORDER_ID);

        String output = capturedOutput();
        assertTrue(output.contains("TEKNIKER SKAPAR DIAGNOSRAPPORT OCH REPARATIONSÅTGÄRDER"),
                "should print the create-diagnosis header");
        assertTrue(output.contains("Order ID:"),
                "should print the order id label");
        assertTrue(output.contains(ORDER_ID),
                "should print the order id value");
        assertTrue(output.contains("Diagnos:"),
                "should print the diagnosis label");
        assertTrue(output.contains("Brustet hjul och punktering"),
                "should print the diagnosis description");
        assertTrue(output.contains("Repair Tasks:"),
                "should print the repair tasks heading");
        assertTrue(output.contains("Laga brustet hjul"),
                "should print the first repair task description");
        assertTrue(output.contains("Laga punktering"),
                "should print the second repair task description");
        assertTrue(output.contains("Beräknas klar:"),
                "should print the estimated repair time label");
    }

    @Test
    void receptionistGetAllReadyForApprovalOrders_shouldPrintApprovalHeaderOrderDetailsAndTotalCost() {
        stubApprovalList();
        stubReceipt();
        createViewWithStdin("j\n");

        view.receptionistGetAllReadyForApprovalOrders();

        String output = capturedOutput();
        assertTrue(output.contains("RECEPTIONIST HÄMTAR  ORDRAR KLARA FÖR GODKÄNNANDE"),
                "should print the approval-orders header");
        assertTrue(output.contains("ORDER ID: " + ORDER_ID),
                "should print the order id");
        assertTrue(output.contains("Telefon:"),
                "should print the phone label");
        assertTrue(output.contains(CUSTOMER_PHONE),
                "should print the customer phone number");
        assertTrue(output.contains("Cykel:"),
                "should print the bike label");
        assertTrue(output.contains("Diagnos:"),
                "should print the diagnosis label");
        assertTrue(output.contains("Beräknas klar:"),
                "should print the estimated repair time label");
        assertTrue(output.contains("Repair Tasks:"),
                "should print the repair tasks heading");
        assertTrue(output.contains("Total kostnad:"),
                "should print the total cost label");
        assertTrue(output.contains("ÄR REPAIR ORDER GODKÄND AV KUND"),
                "should ask the receptionist whether the order is approved");
    }

    @Test
    void receptionistGetAllReadyForApprovalOrders_shouldAskAgainAndPrintInvalidInputMessage_whenInputIsNotJOrN() {
        stubApprovalList();
        stubReceipt();
        createViewWithStdin("x\nj\n");

        view.receptionistGetAllReadyForApprovalOrders();

        assertTrue(capturedOutput().contains("OGILTIG INPUT! SVARA MED J/N"),
                "should print an invalid-input message when the receptionist enters anything other than J or N");
    }

    @Test
    void receptionistGetAllReadyForApprovalOrders_shouldCallApproveRepairOrderOnController_whenInputIsJ() {
        stubApprovalList();
        stubReceipt();
        createViewWithStdin("j\n");

        view.receptionistGetAllReadyForApprovalOrders();

        verify(controller).approveRepairOrder(ORDER_ID);
    }

    @Test
    void receptionistGetAllReadyForApprovalOrders_shouldCallRejectRepairOrderOnController_whenInputIsN() {
        stubApprovalList();
        createViewWithStdin("n\n");

        view.receptionistGetAllReadyForApprovalOrders();

        verify(controller).rejectRepairOrder(ORDER_ID);
    }

    @Test
    void approveRepairOrder_shouldPrintApprovalHeaderOrderIdAndStatusUpdated() {
        stubReceipt();
        createView();

        view.approveRepairOrder(ORDER_ID);

        String output = capturedOutput();
        assertTrue(output.contains("GODKÄNNER REPARATIONSORDER"),
                "should print the approve-order header");
        assertTrue(output.contains("Godkänner Order ID:"),
                "should print the approving-order-id label");
        assertTrue(output.contains(ORDER_ID),
                "should print the order id");
        assertTrue(output.contains("Status uppdaterad till: GODKÄND/PÅGÅENDE"),
                "should print the status-updated line for an approved order");
        verify(controller).approveRepairOrder(ORDER_ID);
    }

    @Test
    void rejectRepairOrder_shouldPrintRejectHeaderOrderIdAndStatusUpdated() {
        createView();

        view.rejectRepairOrder(ORDER_ID);

        String output = capturedOutput();
        assertTrue(output.contains("NEKAR REPARATIONSORDER"),
                "should print the reject-order header");
        assertTrue(output.contains("Nekar Order ID:"),
                "should print the rejecting-order-id label");
        assertTrue(output.contains(ORDER_ID),
                "should print the order id");
        assertTrue(output.contains("Status uppdaterad till: REJECTED"),
                "should print the status-updated line for a rejected order");
        verify(controller).rejectRepairOrder(ORDER_ID);
    }

    @Test
    void printReceipt_shouldPrintAllReceiptInformation() {
        stubReceipt();
        createView();

        view.printReceipt(ORDER_ID);

        String output = capturedOutput();
        assertTrue(output.contains("SYSTEMET SKRIVER UT KVITTO FÖR " + ORDER_ID),
                "should print the receipt header with the order id");
        assertTrue(output.contains("CUSTOMER"),
                "should print the CUSTOMER section heading");
        assertTrue(output.contains(CUSTOMER_NAME),
                "should print the customer name on the receipt");
        assertTrue(output.contains(CUSTOMER_EMAIL),
                "should print the customer email on the receipt");
        assertTrue(output.contains(CUSTOMER_PHONE),
                "should print the customer phone on the receipt");
        assertTrue(output.contains("BIKE"),
                "should print the BIKE section heading");
        assertTrue(output.contains(BIKE_BRAND),
                "should print the bike brand on the receipt");
        assertTrue(output.contains(BIKE_MODEL),
                "should print the bike model on the receipt");
        assertTrue(output.contains(BIKE_SERIAL_NUMBER),
                "should print the bike serial number on the receipt");
        assertTrue(output.contains("PROBLEM"),
                "should print the PROBLEM section heading");
        assertTrue(output.contains(ORDER_PROBLEM_DESCRIPTION),
                "should print the problem description on the receipt");
        assertTrue(output.contains("DIAGNOSTIC REPORT"),
                "should print the DIAGNOSTIC REPORT section heading");
        assertTrue(output.contains("REPAIR TASKS"),
                "should print the REPAIR TASKS section heading");
        assertTrue(output.contains(TASK_DESCRIPTION),
                "should print the repair task description on the receipt");
        assertTrue(output.contains("TOTAL"),
                "should print the TOTAL row");
        assertTrue(output.contains("Status: ACCEPTED"),
                "should print the final status line");
    }

    @Test
    void printReceipt_shouldNotPrintCustomerName_whenControllerReturnsNullReceipt() {
        when(controller.getReceipt(ORDER_ID)).thenReturn(null);
        createView();

        try {
            view.printReceipt(ORDER_ID);
        } catch (NullPointerException ignored) {

        }

        String output = capturedOutput();
        assertTrue(output.contains("SYSTEMET SKRIVER UT KVITTO FÖR " + ORDER_ID),
                "should print the receipt header before attempting to read receipt fields");
        assertFalse(output.contains(CUSTOMER_NAME),
                "should not print customer name when no receipt is available");
    }
}