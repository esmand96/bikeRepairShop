package se.kth.IV1350.bikerepairshop.model.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepairOrderTest {

    private static final String DATE = "2026-05-04";
    private static final String PROBLEM_DESCRIPTION = "Flat tire";
    private static final String ORDER_ID = "order-1";

    @Test
    public void calculateTotalCost_shouldReturnSumOfAllRepairTaskCosts() {
        RepairTask task1 = new RepairTask(500.0, "Replace tire");
        RepairTask task2 = new RepairTask(150.0, "Adjust brakes");
        RepairOrder repairOrder = createRepairOrder(List.of(task1, task2), RepairOrderState.READY_FOR_APPROVAL);

        double result = repairOrder.calculateTotalCost();

        assertEquals(650.0, result);
    }

    @Test
    public void calculateTotalCost_shouldReturnZeroWhenRepairTasksIsNull() {
        RepairOrder repairOrder = createRepairOrder(null, RepairOrderState.NEWLY_CREATED);

        double result = repairOrder.calculateTotalCost();

        assertEquals(0.0, result);
    }

    @Test
    public void transitionState_shouldTransitionFromNewlyCreatedToReadyForApproval() {
        RepairOrder repairOrder = createRepairOrder(null, RepairOrderState.NEWLY_CREATED);

        repairOrder.transitionState(RepairOrderState.READY_FOR_APPROVAL);

        assertEquals(RepairOrderState.READY_FOR_APPROVAL, repairOrder.getState());
    }

    @Test
    public void transitionState_shouldTransitionFromReadyForApprovalToAccepted() {
        RepairOrder repairOrder = createRepairOrder(null, RepairOrderState.READY_FOR_APPROVAL);

        repairOrder.transitionState(RepairOrderState.ACCEPTED);

        assertEquals(RepairOrderState.ACCEPTED, repairOrder.getState());
    }

    @Test
    public void transitionState_shouldNotTransitionToAcceptedFromNewlyCreated() {
        RepairOrder repairOrder = createRepairOrder(null, RepairOrderState.NEWLY_CREATED);

        repairOrder.transitionState(RepairOrderState.ACCEPTED);

        assertEquals(RepairOrderState.NEWLY_CREATED, repairOrder.getState());
    }

    @Test
    public void transitionState_shouldNotTransitionToReadyForApprovalFromAccepted() {
        RepairOrder repairOrder = createRepairOrder(null, RepairOrderState.ACCEPTED);

        repairOrder.transitionState(RepairOrderState.READY_FOR_APPROVAL);

        assertEquals(RepairOrderState.ACCEPTED, repairOrder.getState());
    }

    private RepairOrder createRepairOrder(List<RepairTask> repairTasks, RepairOrderState state) {
        return new RepairOrder(DATE, PROBLEM_DESCRIPTION, state, null, repairTasks, null, ORDER_ID);
    }
}