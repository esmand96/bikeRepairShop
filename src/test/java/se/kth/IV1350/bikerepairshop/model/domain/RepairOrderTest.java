package se.kth.IV1350.bikerepairshop.model.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepairOrderTest {

    private RepairOrder createRepairOrderWithState(RepairOrderState state) {
        return new RepairOrder.Builder()
                .state(state)
                .build();
    }

    private RepairOrder createRepairOrderWithTasks(List<RepairTask> tasks) {
        return new RepairOrder.Builder()
                .state(RepairOrderState.NEWLY_CREATED)
                .repairTasks(tasks)
                .build();
    }


    @Test
    void calculateTotalCost_shouldReturnSumOfAllTaskCosts_whenMultipleTasksExist() {
        RepairTask task1 = new RepairTask.Builder()
                .cost(100.0)
                .description("Change tire")
                .build();
        RepairTask task2 = new RepairTask.Builder()
                .cost(50.5)
                .description("Adjust gears")
                .build();
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