package UnitTests;

import ComponentTests.GoRESTApiUsersTests;
import core.ResponseConverter;
import core.components.GoRestAPIComponent;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoRESTApiUsersCRUDTests {
    final static String elementType = "users";
    static Integer idFromResponse = 0;

    @Test
    @Order(1)
    @DisplayName("Post for GOREST")
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
    @DisplayName("PATCH for GOREST")
    void test2() {
        Response response = GoRestAPIComponent.patchGoRESTElement(elementType, idFromResponse);
        assertThat(response.getStatusCode()).isEqualTo(200);

        ResponseConverter responseConverter = new ResponseConverter(response);
        JSONObject jsonObject = responseConverter.toJsonObject();
        Integer json = jsonObject.getInt("code");
        String patchedName = jsonObject.getJSONObject("data").getString("name");

        assertTrue(json.equals(200) && patchedName.equals("PatchGojtas"));
        Logger.getLogger(GoRESTApiUsersCRUDTests.class.getName()).log(Level.INFO,
                "\n Current status code is " + json +
                        "\n and name: " + patchedName);
    }

    @Test
    @Order(3)
    @DisplayName("GET for GOREST")
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
    @Order(4)
    @DisplayName("DELETE for GOREST")
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
