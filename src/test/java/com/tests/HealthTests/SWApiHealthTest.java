package com.tests.HealthTests;

import core.components.SwapiDevComponent;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constants.Constants.STATUS_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("HEALTH")
class SWApiHealthTest {

    @DisplayName("Health Check for SWApi")
    @Description("Health Check for SWAPI endpoint")
    @Test
    void test() {
        String suiteName = "Health Check for SWApi";

        Response response = SwapiDevComponent.getSwapiHealthStatus();
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }
}
