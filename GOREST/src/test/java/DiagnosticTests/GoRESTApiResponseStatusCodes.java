package DiagnosticTests;

import UnitTests.GoRESTApiUsersCRUDTests;
import core.ResponseConverter;
import core.components.GoRestAPIComponent;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoRESTApiResponseStatusCodes {
    final static String elementType = "users";
    static Integer idFromResponse = 1;

    @Test
    @Order(1)
    @DisplayName("Post for GOREST - 201 - Expected")
    void test1() {
        Response response = GoRestAPIComponent.postGoRESTElement(elementType);
        assertThat(response.getStatusCode()).isEqualTo(200);

        ResponseConverter responseConverter = new ResponseConverter(response);
        JSONObject jsonObject = responseConverter.toJsonObject();
        Integer json = jsonObject.getInt("code");
        idFromResponse = jsonObject.getJSONObject("data").getInt("id");

        assertTrue(json.equals(201));
        Logger.getLogger(GoRESTApiUsersCRUDTests.class.getName()).log(Level.INFO,
                "\n Current status code is " + json);
    }

    @Test
    @Order(2)
    @DisplayName("GET for GOREST - 200 - Expected")
    void test3() {
        Response response = GoRestAPIComponent.getGoRESTElement(elementType, idFromResponse);
        assertThat(response.getStatusCode()).isEqualTo(200);

        ResponseConverter responseConverter = new ResponseConverter(response);
        JSONObject jsonObject = responseConverter.toJsonObject();
        String patchedName = jsonObject.getJSONObject("data").getString("name");

        Logger.getLogger(GoRESTApiUsersCRUDTests.class.getName()).log(Level.INFO,
                "\n Current status code is " + response.getStatusCode() +
                        "\n and name: " + patchedName);
    }

    @Test
    @Order(3)
    @DisplayName("DELETE for GOREST - 204 - Expected")
    void test4() {
        Response response = GoRestAPIComponent.deleteGoRESTElement(elementType, idFromResponse);
        assertThat(response.getStatusCode()).isEqualTo(200);

        ResponseConverter responseConverter = new ResponseConverter(response);
        JSONObject jsonObject = responseConverter.toJsonObject();
        Integer json = jsonObject.getInt("code");

        assertTrue(json.equals(204));
        Logger.getLogger(GoRESTApiUsersCRUDTests.class.getName()).log(Level.INFO,
                "\n Current status code is " + json);
    }
}
