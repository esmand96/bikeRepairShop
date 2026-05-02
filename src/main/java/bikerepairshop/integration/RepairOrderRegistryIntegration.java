package bikerepairshop.integration;

import bikerepairshop.model.entity.RepairOrderEntity;
import java.util.ArrayList;
import java.util.List;

public class RepairOrderRegistryIntegration {
    private static final List<RepairOrderEntity> repairOrders = new ArrayList<>();

    public void saveRepairOrder(RepairOrderEntity repairOrderEntity) {
        repairOrders.removeIf(entity -> entity.getId().equals(repairOrderEntity.getId()));
        repairOrders.add(repairOrderEntity);
    }

    public boolean existsByConsultationId(String consultationId) {
        for (RepairOrderEntity entity : repairOrders) {
            if (entity.getConsultationId().equals(consultationId)) {
                return true;
            }
        }
        return false;
    }

    public RepairOrderEntity getRepairOrderById(String repairOrderId) {
        for (RepairOrderEntity entity : repairOrders) {
            if (entity.getId().equals(repairOrderId)) {
                return entity;
            }
        }
        return null;
    }

    public List<RepairOrderEntity> getAllNewlyCreatedRepairOrders() {
        return getAllWithState("NEWLY_CREATED");
    }

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