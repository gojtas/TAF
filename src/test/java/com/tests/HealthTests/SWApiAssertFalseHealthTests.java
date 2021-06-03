package com.tests.HealthTests;

import core.components.SwapiDevComponent;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static constants.Constants.STATUS_OK;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Epic("Negative Health Status Test")
@Feature("User verifies if the health status fails as expected")
@Tag("SMOKE")
class SWApiAssertFalseHealthTests {


    @DisplayName("Negative Health Check for SWApi")
    @Story("User checks the negative health status")
    @Test
    void test() {
        String suiteName = "Negative Health Check for SWApi";

        Response response = SwapiDevComponent.getSwapiHealthStatus();
        assertFalse((response.getStatusCode() != STATUS_OK));
    }
}
