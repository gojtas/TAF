package HealthTests;

import core.components.GoRestAPIComponent;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("GORESTHEALTHCHECK")
public class GoRestAPIHealthTest {
    private int STATUS_OK = 200;

    @Test
    @DisplayName("Health Check for GoRest")
    void test() {
        String suiteName = "Health Check for GoRest";

        Response response = GoRestAPIComponent.getGoRESTHealthStatus();
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }
}
