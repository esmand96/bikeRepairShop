package se.kth.IV1350.bikerepairshop.observer;

import se.kth.IV1350.bikerepairshop.model.dto.RepairOrderUpdatedDTO;

public interface RepairOrderObserver {
    void stateHasChanged(RepairOrderUpdatedDTO repairOrderUpdatedDTO);
}
