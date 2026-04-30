package bikerepairshop.integration;

import bikerepairshop.model.entity.RepairOrderEntity;
import java.util.HashMap;

public class RepairOrderRegistryIntegration {
    private static final HashMap<String, RepairOrderEntity> map;
        static{
            map = new HashMap<>();
        }

    public RepairOrderRegistryIntegration() {
    }
}
