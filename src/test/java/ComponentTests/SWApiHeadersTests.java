package ComponentTests;

import constants.Constants;
import core.components.SwapiDevComponent;
import io.restassured.response.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static constants.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static testdata.MessagesForSWApi.STATUS_OK_EXPECTED;

@Tag("COMPONENT")
class SWApiHeadersTests {
    private final Logger logger = Logger.getLogger(SWApiHeadersTests.class.getName());

    @ParameterizedTest(name = "Path {0} and index {1}.")
    @DisplayName("Element check for SWApi")
    @CsvFileSource(resources = "/ComponentTests/Swapi.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    void test(String element, int index) {

        Response response = SwapiDevComponent.getSwapiElement(element, index);
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
