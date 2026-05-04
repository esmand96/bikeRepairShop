package se.kth.IV1350.bikerepairshop.model.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepairOrderTest {

    private RepairOrder createRepairOrderWithState(RepairOrderState state) {
        return new RepairOrder(null, null, state, null, null, null, null);
    }

    private RepairOrder createRepairOrderWithTasks(List<RepairTask> tasks) {
        return new RepairOrder(null, null, RepairOrderState.NEWLY_CREATED, null, tasks, null, null);
    }

    // calculateTotalCost

    @Test
    void calculateTotalCost_shouldReturnSumOfAllTaskCosts_whenMultipleTasksExist() {
        RepairTask task1 = new RepairTask(100.0, "Change tire");
        RepairTask task2 = new RepairTask(50.5, "Adjust gears");
        RepairOrder repairOrder = createRepairOrderWithTasks(List.of(task1, task2));

        double totalCost = repairOrder.calculateTotalCost();

        assertEquals(150.5, totalCost);
    }

    @Test
    void calculateTotalCost_shouldReturnZero_whenRepairTasksIsNull() {
        RepairOrder repairOrder = createRepairOrderWithTasks(null);

        double totalCost = repairOrder.calculateTotalCost();

        assertEquals(0.0, totalCost);
    }

    @Test
    void calculateTotalCost_shouldReturnZero_whenRepairTasksIsEmpty() {
        RepairOrder repairOrder = createRepairOrderWithTasks(new ArrayList<>());

        double totalCost = repairOrder.calculateTotalCost();

        assertEquals(0.0, totalCost);
    }

    // transitionState - allowed transitions

    @Test
    void transitionState_shouldChangeStateToReadyForApproval_whenCurrentStateIsNewlyCreated() {
        RepairOrder repairOrder = createRepairOrderWithState(RepairOrderState.NEWLY_CREATED);

        repairOrder.transitionState(RepairOrderState.READY_FOR_APPROVAL);

        assertEquals(RepairOrderState.READY_FOR_APPROVAL, repairOrder.getState());
    }

    @Test
    void transitionState_shouldChangeStateToAccepted_whenCurrentStateIsReadyForApproval() {
        RepairOrder repairOrder = createRepairOrderWithState(RepairOrderState.READY_FOR_APPROVAL);

        repairOrder.transitionState(RepairOrderState.ACCEPTED);

        assertEquals(RepairOrderState.ACCEPTED, repairOrder.getState());
    }

    // transitionState - disallowed transitions

    @Test
    void transitionState_shouldNotChangeState_whenTryingToGoFromNewlyCreatedDirectlyToAccepted() {
        RepairOrder repairOrder = createRepairOrderWithState(RepairOrderState.NEWLY_CREATED);

        repairOrder.transitionState(RepairOrderState.ACCEPTED);

        assertEquals(RepairOrderState.NEWLY_CREATED, repairOrder.getState());
    }

    @Test
    void transitionState_shouldNotChangeState_whenTryingToGoBackFromAcceptedToReadyForApproval() {
        RepairOrder repairOrder = createRepairOrderWithState(RepairOrderState.ACCEPTED);

        repairOrder.transitionState(RepairOrderState.READY_FOR_APPROVAL);

        assertEquals(RepairOrderState.ACCEPTED, repairOrder.getState());
    }
}