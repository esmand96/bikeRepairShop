package se.kth.IV1350.bikerepairshop.observer;

import se.kth.IV1350.bikerepairshop.model.dto.RepairOrderUpdatedDTO;

/**
 * A listener for changes to repair orders. Implementations are notified by the
 * observed object whenever a repair order has been created or updated.
 */
public interface RepairOrderObserver {

    /**
     * Called when a repair order has changed.
     *
     * @param repairOrderUpdatedDTO The data describing the updated repair order.
     */
    void stateHasChanged(RepairOrderUpdatedDTO repairOrderUpdatedDTO);
}