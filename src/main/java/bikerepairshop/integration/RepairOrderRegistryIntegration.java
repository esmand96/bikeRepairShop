package bikerepairshop.integration;

import bikerepairshop.model.entity.BikeRepairConsultationEntity;
import bikerepairshop.model.entity.RepairOrderEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepairOrderRegistryIntegration {
    private static final HashMap<String, RepairOrderEntity> map;
        static{
            map = new HashMap<>();
        }

    public RepairOrderRegistryIntegration() {
    }

    public void insertRepairOrder(RepairOrderEntity repairOrderEntity) {
            map.put(repairOrderEntity.getId(),repairOrderEntity);
    }

    public List<RepairOrderEntity> getAllNewlyCreatedRepairOrders() {
        List<RepairOrderEntity> newlyCreatedRepairOrders = new ArrayList<>();
        map.forEach( (phoneNumber,entity) -> {
            if (entity.getState().equals("NEWLY_CREATED"))
                newlyCreatedRepairOrders.add(entity);
        });
        return newlyCreatedRepairOrders;
    }
}
