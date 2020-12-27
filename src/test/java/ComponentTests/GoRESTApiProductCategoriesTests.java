package ComponentTests;

import core.ResponseConverter;
import core.components.GoRestAPIComponent;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.dataGenerator.GoRestElementsGenerator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testdata.MessagesForSWApi.STATUS_OK;

@Tag("GORESTCOMPONENT")
public class GoRESTApiProductCategoriesTests {
    final static String elementType = "product-categories";
    final static int limit = 50;

    public static Stream<Integer> dataProvider() {
        List<Integer> elementsList;
        elementsList = GoRestElementsGenerator.generateListOfUsersIds(elementType, limit);
        return elementsList.stream();
    }

    @MethodSource("dataProvider")
    @ParameterizedTest(name = "Check for ProductCategories with id {0}")
    @DisplayName("Element check for GOREST")
    void test1(int index) {
        Response response = GoRestAPIComponent.getGoRESTElement(elementType, index);
        String convertedBody = response.getBody().asString();

        assertThat(response.getStatusCode()).isEqualTo(200);
        Logger.getLogger(GoRESTApiProductCategoriesTests.class.getName()).log(Level.INFO, STATUS_OK +
                "\n Body content: " + convertedBody);
    }

    @Test
    @DisplayName("Elements number should be => to 800")
    void test2() {
        Response responseNumberCheck = GoRestAPIComponent.getGoRESTElementCounter(elementType);
        ResponseConverter responseConverter = new ResponseConverter(responseNumberCheck);
        JSONObject jsonObject = responseConverter.toJsonObject();
        JSONObject json = jsonObject.getJSONObject("meta").getJSONObject("pagination");
        Integer counterValue = json.getInt("total");

        assertTrue(counterValue >= 800);
        Logger.getLogger(GoRESTApiProductCategoriesTests.class.getName()).log(Level.INFO, STATUS_OK +
                "\n Number of elements: " + counterValue);
    }
}
