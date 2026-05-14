package se.kth.IV1350.bikerepairshop.integration;

import se.kth.IV1350.bikerepairshop.exceptions.DatabaseFailureException;
import se.kth.IV1350.bikerepairshop.model.entity.RepairOrderEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to the external repair order registry. The registry is simulated by
 * an in-memory list of repair orders that is shared across all instances of this class.
 */
public class RepairOrderRegistryIntegration {
    private static final List<RepairOrderEntity> repairOrders = new ArrayList<>();

    /**
     * Saves the specified repair order to the registry. If a repair order with the same
     * id already exists, it is replaced by the specified one.
     *
     * @param repairOrderEntity The repair order to save.
     */
    public void saveRepairOrder(RepairOrderEntity repairOrderEntity) {
        repairOrders.removeIf(entity -> entity.getId().equals(repairOrderEntity.getId()));
        repairOrders.add(repairOrderEntity);
    }

    /**
     * Checks whether a repair order has already been created for the consultation with
     * the specified id.
     *
     * @param consultationId The id of the consultation to check.
     * @return {@code true} if a repair order exists for the consultation, {@code false} otherwise.
     */
    public boolean existsByConsultationId(String consultationId) {
        for (RepairOrderEntity entity : repairOrders) {
            if (entity.getConsultationId().equals(consultationId)) {
                return true;
            }
        }
        return false;
    }
    /**
    * Retrieves the repair order with the specified id.
    *
    * @param repairOrderId The id of the repair order to retrieve.
    * @return The matching repair order, or {@code null} if no repair order has the specified id.
    */
   public RepairOrderEntity getRepairOrderById(String repairOrderId){
       for (RepairOrderEntity entity : repairOrders) {
           if (entity.getId().equals(repairOrderId)) {
               return entity;
           }
       }
       return null;
   }

    /**
     * Retrieves all repair orders that have been created but not yet diagnosed.
     *
     * @return A list of all repair orders in the {@code NEWLY_CREATED} state.
     */
    public List<RepairOrderEntity> getAllNewlyCreatedRepairOrders() {
        return getAllWithState("NEWLY_CREATED");
    }

    /**
     * Retrieves all repair orders that are ready to be approved by the customer.
     *
     * @return A list of all repair orders in the {@code READY_FOR_APPROVAL} state.
     */
    public List<RepairOrderEntity> getAllReadyForApprovalOrders() {
        return getAllWithState("READY_FOR_APPROVAL");
    }

    private List<RepairOrderEntity> getAllWithState(String state) {
        List<RepairOrderEntity> result = new ArrayList<>();
        for (RepairOrderEntity entity : repairOrders) {
            if (entity.getState().equals(state)) {
                result.add(entity);
            }
        }
        return result;
    }
}