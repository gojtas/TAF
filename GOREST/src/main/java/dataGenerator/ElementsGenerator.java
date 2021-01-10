package dataGenerator;

import core.ResponseConverter;
import core.components.SwapiDevComponent;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class ElementsGenerator {

    private ElementsGenerator() {
    }

    public static List<String> generateListOfStarWarsElements(String element) {

        List<String> responseList = new ArrayList<>();
        for (int i = 1; i < ElementsGenerator.getNumberOfElements(element); i++) {
            Response response = SwapiDevComponent.getSwapiElement(element, i);
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
        int elementCounter = Integer.parseInt(responseConverter.retrieveValue("count")) + 1;
        return elementCounter;
    }
}
