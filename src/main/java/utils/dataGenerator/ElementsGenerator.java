package utils.dataGenerator;

import core.components.SwapiDevComponent;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class ElementsGenerator {

    private ElementsGenerator() {
    }

    public static List<String> generateListOfStarWarsElements(String element, int numberOfElementsToCheckSchemaFor) {

        List<String> responseList = new ArrayList<>();
        for (int i = 1; i < numberOfElementsToCheckSchemaFor; i++) {
            Response response = SwapiDevComponent.getSwapiElement(element, i);
            String convertedBody = response.getBody().asString();
            if (!convertedBody.contains("\"detail\":\"Not found\"")) {
                responseList.add(convertedBody);
            }
        }
        return responseList;
    }
}
