package ComponentTests;

import core.components.SwapiDevComponent;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static constants.Constants.NOT_FOUND_CODE;
import static constants.Constants.NUM_LINES_TO_SKIP;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static testdata.MessagesForSWApi.STATUS_OK;

class SWApiAssertFalseElements {
    private final Logger logger = Logger.getLogger(SWApiAssertFalseElements.class.getName());

    @ParameterizedTest(name = "Path {0} and index {1}.")
    @DisplayName("Not found element check for SWApi")
    @CsvFileSource(resources = "/ComponentTests/SwapiNotFoundIndex.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    void test1(String element, int index) {

        Response response = SwapiDevComponent.getSwapiElement(element, index);
        String convertedBody = response.getBody().asString();

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND_CODE);
        logger.log(Level.INFO, STATUS_OK +
                "\n Body content: " + convertedBody);
    }

    @ParameterizedTest(name = "Path {0} and index {1}.")
    @DisplayName("Missing path for element check for SWApi")
    @CsvFileSource(resources = "/ComponentTests/SwapiMissingPath.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    void test2(String element, int index) {

        Response response = SwapiDevComponent.getSwapiElement(element, index);
        String convertedBody = response.getBody().asString();

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND_CODE);
        logger.log(Level.INFO, STATUS_OK +
                "\n Body content: " + convertedBody);
    }
}
