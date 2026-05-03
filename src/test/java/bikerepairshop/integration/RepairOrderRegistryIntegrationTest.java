package bikerepairshop.integration;

import bikerepairshop.model.domain.RepairOrderState;
import bikerepairshop.model.entity.RepairOrderEntity;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static bikerepairshop.TestUtil.createRepairOrderEntity;
import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderRegistryIntegrationTest {
    RepairOrderRegistryIntegration repairOrderRegistry;


    @BeforeEach
    public void setUp() {
        repairOrderRegistry = new RepairOrderRegistryIntegration();
    }

    @Test
    void testSaveRepairOrderStoreRepairOrderInEntity(){
        RepairOrderEntity firstOrder = createRepairOrderEntity();

        repairOrderRegistry.saveRepairOrder(firstOrder);
        RepairOrderEntity findOrder = repairOrderRegistry.getRepairOrderById(firstOrder.getId());

        assertNotNull(findOrder, "repair order not saved");
        assertEquals(firstOrder.getId(), findOrder.getId(), "repair order id does not match");
        assertEquals(firstOrder.getProblemDescription(), findOrder.getProblemDescription(), "problem description does not match");
        assertEquals(RepairOrderState.NEWLY_CREATED.name(), findOrder.getState(), "state does not match");
    }

    @Test
    void testExistsByConsultationIdReturnsTrue(){
        RepairOrderEntity order = createRepairOrderEntity();
        repairOrderRegistry.saveRepairOrder(order);
        boolean exists = repairOrderRegistry.existsByConsultationId(order.getConsultationId());

        assertTrue(exists, "could not find existing consultationId"); //om boolean returns True då testet passed
    }

    @Test
    void testExistByConsultationIdReturnsFalse(){
        RepairOrderEntity order = createRepairOrderEntity();
        repairOrderRegistry.saveRepairOrder(order);
        boolean exists = repairOrderRegistry.existsByConsultationId("fakeConsultationId");

        assertFalse(exists, "should have returned false");
    }

    @Test
    void testGetRepairOrderByIdReturnsRepairOrderEntity(){
        RepairOrderEntity order = createRepairOrderEntity();
        repairOrderRegistry.saveRepairOrder(order);
        RepairOrderEntity findOrder = repairOrderRegistry.getRepairOrderById(order.getId());

        assertNotNull(findOrder, "repair order not found");
        assertEquals(order.getId(), findOrder.getId(), "Id does not match");
        assertEquals(order.getProblemDescription(), findOrder.getProblemDescription(), "problem description does not match");
        assertEquals(order.getName(), findOrder.getName(), "customer name does not match");
    }

    @Test
    void testGetRepairOrderByIdReturnsNull(){
        RepairOrderEntity order = createRepairOrderEntity();
        repairOrderRegistry.saveRepairOrder(order);
        RepairOrderEntity findOrder = repairOrderRegistry.getRepairOrderById("fakeId");

        assertNull(findOrder, "a repair order should not have been found");
    }

    @Test
    void testGetAllNewlyCreatedRepairOrdersReturnsRepairOrderEntityList(){
        RepairOrderEntity firstOrder = createRepairOrderEntity();

        RepairOrderEntity secondOrder = createRepairOrderEntity("NEWLY_CREATED");

        repairOrderRegistry.saveRepairOrder(firstOrder);
        repairOrderRegistry.saveRepairOrder(secondOrder);
        List<RepairOrderEntity> repairOrders = repairOrderRegistry.getAllNewlyCreatedRepairOrders();

        for (RepairOrderEntity entity : repairOrders){
            assertEquals("NEWLY_CREATED", entity.getState(), "state does not match");
            assertEquals(secondOrder.getId(), entity.getId(), "repair order Id does not match");
        }
    }

    @Test
    void testGetAllReadyForApprovalOrdersReturnsRepairOrderEntityList(){
        RepairOrderEntity firstOrder = createRepairOrderEntity("READY_FOR_APPROVAL");

        repairOrderRegistry.saveRepairOrder(firstOrder);

        List<RepairOrderEntity> repairOrders = repairOrderRegistry.getAllReadyForApprovalOrders();

        assertFalse(repairOrders.isEmpty(), "should return at least one repair order");
        for(RepairOrderEntity order : repairOrders){
            assertEquals("READY_FOR_APPROVAL", order.getState(), "should only contain repair orders ready for approval");
        }

    }

}