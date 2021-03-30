package SupportMethods;

import io.restassured.response.Response;
import org.json.JSONObject;

public class SupportingMethods {

    public static String getOperationId(Response response) {
        String output = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(output);

        return jsonObject.getJSONObject("data").get("id").toString();
    }
}
