package com.tests.ComponentTests;

import core.ResponseConverter;
import core.components.SwapiDevComponent;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utils.dataGenerator.RestUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

import static config.UriProvider.SWAPIAPI;
import static constants.Constants.NUM_LINES_TO_SKIP;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Element check for SW API")
@Feature("User tries to get the successful response with search endpoint")
@Tag("COMPONENT")
class SWApiSearchTests {
    private final Logger logger = Logger.getLogger(SWApiHeadersTests.class.getName());

    @BeforeAll
    static void methodSetUp() {
        RestUtils.setFullUri(SWAPIAPI.getUrl(), "");
    }

    @ParameterizedTest(name = "Path {0} and keyword {1}.")
    @DisplayName("Element check for SWApi")
    @Story("User tries to get the proper response for Search Endpoint")
    @CsvFileSource(resources = "/ComponentTests/InputForSearch.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    void test(String element, String keyword) {

        Response response = SwapiDevComponent.getSearchElement(element, keyword);
        String convertedBody = response.getBody().asString();
        logger.log(Level.INFO, convertedBody);

        JSONObject jsonObject = (new ResponseConverter(response)).toJsonObject();
        String count = jsonObject.get("count").toString();
        String name = jsonObject.getJSONArray("results")
                .getJSONObject(0)
                .get("name").toString();

        assertThat(count).isEqualTo("1");
        assertThat(name).isEqualTo("Luke Skywalker");

    }
}
