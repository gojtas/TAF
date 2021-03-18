package HealthTests;

import core.components.SwapiDevComponent;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static constants.Constants.STATUS_OK;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SWApiAssertFalseHealthTests {

    @Test
    @DisplayName("Negative Health Check for SWApi")
    void test() {
        String suiteName = "Negative Health Check for SWApi";

        Response response = SwapiDevComponent.getSwapiHealthStatus();
        assertFalse((response.getStatusCode() != STATUS_OK));
    }
}
