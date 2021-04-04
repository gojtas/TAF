package utils.dataGenerator;

import core.ResponseConverter;
import core.components.SwapiDevComponent;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static core.components.SwapiDevComponent.getSwapiElement;

public class ElementsGenerator {

    private ElementsGenerator() {
    }

    public static List<String> generateListOfStarWarsElements(String element) {

        List<String> responseList = new ArrayList<>();
        for (int i = 1; i < getNumberOfElements(element); i++) {
            Response response = getSwapiElement(element, i);
            String convertedBody = response.getBody().asString();
            if (!convertedBody.contains("\"detail\":\"Not found\"")) {
                responseList.add(convertedBody);
            }
        }
        return responseList;
    }

    public static int getNumberOfElements(String elementType) {
        Response response = SwapiDevComponent.getSwapiElementCounter(elementType);
        ResponseConverter responseConverter = new ResponseConverter(response);
        return Integer.parseInt(responseConverter.retrieveValue("count")) + 1;
    }
}
