package CustomAssertions;

import io.restassured.response.Response;
import org.json.JSONObject;

import static constants.Constants.NO_CONTENT;
import static constants.Constants.STATUS_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckAssertions {

    public static void checkGetOperation(Response response) {

        String outputAfterUpdate = response.getBody().asString();
        JSONObject outputJson = new JSONObject(outputAfterUpdate);
        String currentStatusCode = outputJson.get("code").toString();
        assertThat(currentStatusCode).isEqualTo("200");

        String limit = outputJson.getJSONObject("meta")
                .getJSONObject("pagination")
                .get("limit")
                .toString();
        assertThat(limit).isEqualTo("20");
    }

    public static void checkPatchOperation(Response response) {

        String outputAfterUpdate = response.getBody().asString();
        JSONObject outputJson = new JSONObject(outputAfterUpdate);
        String currentStatusCode = outputJson.get("code").toString();
        assertThat(currentStatusCode).isEqualTo("200");
    }

    public static void checkDeleteOperation(Response response) {

        String outputAfterDelete = response.getBody().asString();
        JSONObject deleteJsonObject = new JSONObject(outputAfterDelete);
        String actualDeleteCode = deleteJsonObject.get("code").toString();
        assertThat(actualDeleteCode).isEqualTo("204");
    }
    public static void checkCreateOperation(Response response) {

        String responseAfterCreate = response.getBody().asString();
        JSONObject createJsonObject = new JSONObject(responseAfterCreate);
        String actualCreateCode = createJsonObject.get("code").toString();
        assertThat(actualCreateCode).isEqualTo("201");
    }
}
