package bikerepairshop.integration;
import bikerepairshop.model.entity.RepairOrderEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepairOrderRegistryIntegration {
    private static final HashMap<String, RepairOrderEntity> map;
        static{
            map = new HashMap<>();
        }

    public void insertRepairOrder(RepairOrderEntity repairOrderEntity) {
            map.put(repairOrderEntity.getId(),repairOrderEntity);
    }

    public List<RepairOrderEntity> getAllNewlyCreatedRepairOrders() {
        return getAllWithState("NEWLY_CREATED");
    }

    public RepairOrderEntity getRepairOrderById(String repairOrderId) {
            return map.get(repairOrderId);
    }

    public List<RepairOrderEntity> getAllReadyForApprovalOrders() {
        return getAllWithState("READY_FOR_APPROVAL") ;
    }
    private List<RepairOrderEntity> getAllWithState(String state) {
        List<RepairOrderEntity> repairOrderEntities = new ArrayList<>();
        map.forEach( (phoneNumber,entity) -> {
            if (entity.getState().equals(state))
                repairOrderEntities.add(entity);
        });
        return repairOrderEntities;
    }
}
