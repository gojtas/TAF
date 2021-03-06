package com.tests.ComponentTests;

import constants.Constants;
import core.components.SwapiDevComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utils.dataGenerator.RestUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

import static config.UriProvider.SWAPIAPI;
import static constants.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static testdata.MessagesForSWApi.STATUS_OK_EXPECTED;

@Epic("Headers Check")
@Feature("User verifies if mandatory headers are present in response")
@Tag("COMPONENT")
class SWApiHeadersTests {
    private final static Logger logger = Logger.getLogger(SWApiHeadersTests.class.getName());

    @BeforeAll
    static void methodSetUp() {
        RestUtils.setFullUri(SWAPIAPI.getUrl(), "");
    }

    @Description("Test Description: Header Tests")
    @DisplayName("Element check for SWApi")
    @Story("User checks headers in different endpoints responses")
    @CsvFileSource(resources = "/ComponentTests/Swapi.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    @ParameterizedTest(name = "Path {0} and index {1}.")
    void headerTest(String element, int index) {

        final Response response = SwapiDevComponent.getSwapiElement(element, index);
        String convertedBody = response.getBody().asString();

        assertThat(response.getStatusCode()).withFailMessage("Wrong status code")
                .isEqualTo(Constants.STATUS_OK);
        logger.log(Level.INFO, STATUS_OK_EXPECTED.getUrl() +
                "\n Body content: " + convertedBody);

        assertThat(response.getContentType()).withFailMessage("Wrong Content Type.")
                .isEqualTo(CONTENT_TYPE);
        logger.log(Level.INFO, "Content Type: " + response.getContentType());

        assertThat(response.getHeader(CONNECTION)).withFailMessage("Wrong connection type")
                .isEqualTo(CONNECTION_EXPECTED);
        logger.log(Level.INFO, CONNECTION + " " + response.getHeader(CONNECTION));

        assertThat(response.getHeader(VARY)).withFailMessage("Wrong connection type")
                .isEqualTo(VARY_EXPECTED);
        logger.log(Level.INFO, VARY + " " + response.getHeader(VARY));

        assertThat(response.getHeader(X_FRAME_OPTIONS)).withFailMessage("Wrong connection type")
                .isEqualTo(X_FRAME_OPTIONS_EXPECTED);
        logger.log(Level.INFO, X_FRAME_OPTIONS + " " + response.getHeader(X_FRAME_OPTIONS));

    }

}
