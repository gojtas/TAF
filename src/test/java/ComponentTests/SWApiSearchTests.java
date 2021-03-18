package ComponentTests;

import core.ResponseConverter;
import core.components.SwapiDevComponent;
import io.restassured.response.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.assertj.core.api.Assertions.assertThat;

import static constants.Constants.NUM_LINES_TO_SKIP;

@Tag("COMPONENT")
class SWApiSearchTests {
    private final Logger logger = Logger.getLogger(SWApiApiElementsTests.class.getName());

    @ParameterizedTest(name = "Path {0} and keyword {1}.")
    @DisplayName("Element check for SWApi")
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
