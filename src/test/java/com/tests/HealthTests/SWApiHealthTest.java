package com.tests.HealthTests;

import core.components.SwapiDevComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static constants.Constants.STATUS_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Health check for SW API")
@Feature("User verifies if the status for SW API is OK")
@Tag("HEALTH")
class SWApiHealthTest {

    @DisplayName("Health Check for SW Api")
    @Description("Health Check for SW API endpoint")
    @Story("User verifies the health status")
    @Test
    void test() {
        String suiteName = "Health Check for SWApi";

        Response response = SwapiDevComponent.getSwapiHealthStatus();
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }
}
