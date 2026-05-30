package se.kth.IV1350.bikerepairshop.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.IV1350.bikerepairshop.model.dto.DiagnosticReportDTO;
import se.kth.IV1350.bikerepairshop.model.dto.RepairOrderUpdatedDTO;
import se.kth.IV1350.bikerepairshop.model.dto.RepairTaskDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.kth.IV1350.bikerepairshop.TestUtil.BIKE_BRAND;
import static se.kth.IV1350.bikerepairshop.TestUtil.BIKE_MODEL;
import static se.kth.IV1350.bikerepairshop.TestUtil.BIKE_SERIAL_NUMBER;
import static se.kth.IV1350.bikerepairshop.TestUtil.CUSTOMER_EMAIL;
import static se.kth.IV1350.bikerepairshop.TestUtil.CUSTOMER_NAME;
import static se.kth.IV1350.bikerepairshop.TestUtil.CUSTOMER_PHONE;
import static se.kth.IV1350.bikerepairshop.TestUtil.ORDER_ID;
import static se.kth.IV1350.bikerepairshop.TestUtil.ORDER_PROBLEM_DESCRIPTION;
import static se.kth.IV1350.bikerepairshop.TestUtil.TASK_COST;
import static se.kth.IV1350.bikerepairshop.TestUtil.TASK_DESCRIPTION;

/**
 * Tests for all informational printouts in {@link RepairOrderView}. Each test
 * redirects {@link System#out} to an in-memory buffer, calls
 * {@link RepairOrderView#stateHasChanged} with a {@link RepairOrderUpdatedDTO} for a
 * specific state, and then asserts that the captured output contains or does not
 * contain the expected information.
 *
 * <p>{@link RepairOrderView} has no external dependencies and can therefore be
 * instantiated directly without mocking.
 *
 * <p>The test cases cover all four state values that the view can receive:
 * {@code NEWLY_CREATED}, {@code READY_FOR_APPROVAL}, {@code ACCEPTED}, and
 * {@code REJECTED}. The state determines whether the diagnosis section and the repair
 * tasks are included in the printout.
 */
class RepairOrderViewTest {

    private RepairOrderView repairOrderView;
    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    private static final String DIAGNOSIS_DESCRIPTION = "Brustet hjul och punktering";

    @BeforeEach
    void setUp() {
        repairOrderView = new RepairOrderView();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private String capturedOutput() {
        return outContent.toString(StandardCharsets.UTF_8);
    }

    private RepairOrderUpdatedDTO buildDTO(String state) {
        DiagnosticReportDTO diagnosticReport = DiagnosticReportDTO.builder()
                .description(DIAGNOSIS_DESCRIPTION)
                .estimatedRepairTime(LocalDateTime.of(2026, 5, 20, 21, 56))
                .build();
        RepairTaskDTO task = RepairTaskDTO.builder()
                .description(TASK_DESCRIPTION)
                .cost(TASK_COST)
                .build();
        return RepairOrderUpdatedDTO.builder()
                .repairOrderId(ORDER_ID)
                .name(CUSTOMER_NAME)
                .email(CUSTOMER_EMAIL)
                .phoneNumber(CUSTOMER_PHONE)
                .bikeBrand(BIKE_BRAND)
                .bikeModel(BIKE_MODEL)
                .bikeSerialNumber(BIKE_SERIAL_NUMBER)
                .problemDescription(ORDER_PROBLEM_DESCRIPTION)
                .state(state)
                .diagnosticReport(diagnosticReport)
                .proposedRepairTasks(List.of(task))
                .build();
    }

    @Test
    void stateHasChanged_shouldPrintOrderIdCustomerBikeProblemAndStatus_whenStateIsNewlyCreated() {
        repairOrderView.stateHasChanged(buildDTO("NEWLY_CREATED"));

        String output = capturedOutput();
        assertTrue(output.contains("REPAIR ORDER STATE CHANGED"),
                "should print the state-changed heading");
        assertTrue(output.contains("ORDER ID: " + ORDER_ID),
                "should print the order id");
        assertTrue(output.contains(CUSTOMER_NAME),
                "should print the customer name");
        assertTrue(output.contains(CUSTOMER_PHONE),
                "should print the customer phone number");
        assertTrue(output.contains(CUSTOMER_EMAIL),
                "should print the customer email");
        assertTrue(output.contains(BIKE_BRAND),
                "should print the bike brand");
        assertTrue(output.contains(BIKE_MODEL),
                "should print the bike model");
        assertTrue(output.contains(BIKE_SERIAL_NUMBER),
                "should print the bike serial number");
        assertTrue(output.contains(ORDER_PROBLEM_DESCRIPTION),
                "should print the problem description");
        assertTrue(output.contains("NEWLY_CREATED"),
                "should print the current state");
    }

    @Test
    void stateHasChanged_shouldNotPrintDiagnosisOrRepairTasks_whenStateIsNewlyCreated() {
        repairOrderView.stateHasChanged(buildDTO("NEWLY_CREATED"));

        String output = capturedOutput();
        assertFalse(output.contains(DIAGNOSIS_DESCRIPTION),
                "should not print the diagnosis when the order is only newly created");
        assertFalse(output.contains(TASK_DESCRIPTION),
                "should not print repair tasks when the order is only newly created");
    }

    @Test
    void stateHasChanged_shouldPrintDiagnosisEstimatedTimeAndRepairTasks_whenStateIsReadyForApproval() {
        repairOrderView.stateHasChanged(buildDTO("READY_FOR_APPROVAL"));

        String output = capturedOutput();
        assertTrue(output.contains("REPAIR ORDER STATE CHANGED"),
                "should print the state-changed heading");
        assertTrue(output.contains(ORDER_ID),
                "should print the order id");
        assertTrue(output.contains("READY_FOR_APPROVAL"),
                "should print the current state");
        assertTrue(output.contains("Diagnos:"),
                "should print the diagnosis label");
        assertTrue(output.contains(DIAGNOSIS_DESCRIPTION),
                "should print the diagnosis description");
        assertTrue(output.contains("Beräknas klar:"),
                "should print the estimated repair time label");
        assertTrue(output.contains("Repair Tasks:"),
                "should print the repair tasks heading");
        assertTrue(output.contains(TASK_DESCRIPTION),
                "should print the repair task description");
    }

    @Test
    void stateHasChanged_shouldPrintBasicInfoButNotDiagnosisSection_whenStateIsAccepted() {
        repairOrderView.stateHasChanged(buildDTO("ACCEPTED"));

        String output = capturedOutput();
        assertTrue(output.contains(ORDER_ID),
                "should print the order id");
        assertTrue(output.contains("ACCEPTED"),
                "should print the current state");
        assertFalse(output.contains(DIAGNOSIS_DESCRIPTION),
                "should not print the diagnosis section for an accepted order");
        assertFalse(output.contains(TASK_DESCRIPTION),
                "should not print repair tasks for an accepted order");
    }

    @Test
    void stateHasChanged_shouldPrintBasicInfoButNotDiagnosisSection_whenStateIsRejected() {
        repairOrderView.stateHasChanged(buildDTO("REJECTED"));

        String output = capturedOutput();
        assertTrue(output.contains(ORDER_ID),
                "should print the order id");
        assertTrue(output.contains("REJECTED"),
                "should print the current state");
        assertFalse(output.contains(DIAGNOSIS_DESCRIPTION),
                "should not print the diagnosis section for a rejected order");
        assertFalse(output.contains(TASK_DESCRIPTION),
                "should not print repair tasks for a rejected order");
    }
}