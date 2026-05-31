package se.kth.IV1350.bikerepairshop.observer;

import se.kth.IV1350.bikerepairshop.model.dto.RepairOrderUpdatedDTO;

public abstract class AbstractRepairOrderObserver implements RepairOrderObserver {

    @Override
    public void stateHasChanged(RepairOrderUpdatedDTO repairOrderUpdatedDTO){
        handleStateChange(repairOrderUpdatedDTO);
    }

    private void handleStateChange(RepairOrderUpdatedDTO repairOrderUpdatedDTO){ //själva template metoden för den bestämmer ordningen på algoritmen
        try {
            doHandleStateChange(repairOrderUpdatedDTO);
            }
        catch(Exception e){
            handleErrors(e);
        }
    }

    protected abstract void doHandleStateChange(RepairOrderUpdatedDTO repairOrderUpdatedDTO) throws Exception;

    protected abstract void handleErrors(Exception e);
}