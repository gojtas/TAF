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

import static constants.Constants.NUM_LINES_TO_SKIP;
import static org.assertj.core.api.Assertions.assertThat;
import static testdata.MessagesForSWApi.STATUS_OK_EXPECTED;

@Tag("COMPONENT")
class SWApiApiElementsTests {
    private final Logger logger = Logger.getLogger(SWApiApiElementsTests.class.getName());

    @ParameterizedTest(name = "Path {0} and index {1}.")
    @DisplayName("Element check for SWApi")
    @CsvFileSource(resources = "/ComponentTests/Swapi.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    void test(String element, int index) {

        Response response = SwapiDevComponent.getSwapiElement(element, index);
        String convertedBody = response.getBody().asString();

        assertThat(response.getStatusCode()).isEqualTo(Constants.STATUS_OK);
        logger.log(Level.INFO, STATUS_OK_EXPECTED +
                "\n Body content: " + convertedBody);
    }

}
