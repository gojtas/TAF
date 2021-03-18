package HealthTests;

import core.components.SwapiDevComponent;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constants.Constants.STATUS_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("HEALTH")
class SWApiHealthTest {

    @Test
    @DisplayName("Health Check for SWApi")
    void test() {
        String suiteName = "Health Check for SWApi";

        Response response = SwapiDevComponent.getSwapiHealthStatus();
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }
}
