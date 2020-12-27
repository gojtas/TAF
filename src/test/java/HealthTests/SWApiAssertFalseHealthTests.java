package HealthTests;

import core.components.SwapiDevComponent;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Disabled
public class SWApiAssertFalseHealthTests {
    private int STATUS_OK = 200;

    @Test
    @DisplayName("Negative Health Check for SWApi")
    void test() {
        String suiteName = "Negative Health Check for SWApi";

        Response response = SwapiDevComponent.getSwapiHealthStatus();
        assertFalse((response.getStatusCode() != STATUS_OK));
    }
}
