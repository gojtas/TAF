package ComponentTests;

import core.components.SwapiDevComponent;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static testdata.MessagesForSWApi.STATUS_OK;

@Tag("COMPONENT")
public class SwapiApiFilmsTests {
    private static final int NUM_LINES_TO_SKIP = 1;

    @ParameterizedTest(name = "Path {0} and index {1}.")
    @DisplayName("Element check for SWApi")
    @CsvFileSource(resources = "/ComponentTests/Swapi.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    void test(String element, int index) {

        Response response = SwapiDevComponent.getSwapiElement(element, index);
        String convertedBody = response.getBody().asString();

        assertThat(response.getStatusCode()).isEqualTo(200);
        Logger.getLogger(SwapiApiFilmsTests.class.getName()).log(Level.INFO, STATUS_OK +
                "\n Body content: " + convertedBody);
    }

}
