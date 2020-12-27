package utils.dataGenerator;

import core.ResponseConverter;
import core.components.GoRestAPIComponent;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoRestElementsGenerator {

    private GoRestElementsGenerator() {
    }

    public static List<String> generateListOfUsers(String element) {

        List<String> responseList = new ArrayList<>();
        for (int i = 1; i < GoRestElementsGenerator.getNumberOfElements(element); i++) {
            Response response = GoRestAPIComponent.getGoRESTElement(element, i);
            String convertedBody = response.getBody().asString();
            if (!convertedBody.contains("\"message\":\"Resource not found\"")) {
                responseList.add(convertedBody);
            }
        }
        return responseList;
    }

    public static List<Integer> generateListOfUsersIds(String element, int limit) {

        List<Integer> responseListIds = new ArrayList<>();
        for (int i = 1; i < limit; i++) {
            Response response = GoRestAPIComponent.getGoRESTElement(element, i);
            String convertedBody = response.getBody().asString();
            if (!convertedBody.contains("\"message\":\"Resource not found\"")) {
                responseListIds.add(i);
            }
        }
        return responseListIds;
    }

    public static int getNumberOfElements(String elementType) {
        Response response = GoRestAPIComponent.getGoRESTElementCounter(elementType);
        ResponseConverter responseConverter = new ResponseConverter(response);
        JSONObject jsonObject = responseConverter.toJsonObject();
        JSONObject json = jsonObject.getJSONObject("meta").getJSONObject("pagination");
        Integer jsonAsInt = json.getInt("total");
        int elementCounter = jsonAsInt + 1;
        return elementCounter;
    }
    public static String getBodyForPost(){
        String body = "{\"name\":\"Mateusz Gojtowski\", \"gender\":\"Male\", " +
                "\"email\":\"Matthew.Relevant@" + Math.random() + ".com\", \"status\":\"Active\"}";
        return body;
    }
}
