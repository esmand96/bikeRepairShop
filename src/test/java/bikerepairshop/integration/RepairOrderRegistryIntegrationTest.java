package bikerepairshop.integration;

import bikerepairshop.model.domain.BikeDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepairOrderRegistryIntegrationTest {
    RepairOrderRegistryIntegration repairOrderRegistryIntegration;

    @BeforeEach
    public void setUp() {
        repairOrderRegistryIntegration = new RepairOrderRegistryIntegration();

    }
}
