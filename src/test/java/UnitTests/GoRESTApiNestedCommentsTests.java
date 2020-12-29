package UnitTests;

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

public class GoRESTApiNestedCommentsTests {
    final static String elementType = "posts";
    final static String elementTypeNested = "comments";
    static Integer idFromResponse = 0;

    @Test
    @Order(1)
    @DisplayName("Post for GOREST")
    void test1() {
        Response response = GoRestAPIComponent.postGoRESTElementPosts(elementType);
        assertThat(response.getStatusCode()).isEqualTo(200);

        ResponseConverter responseConverter = new ResponseConverter(response);
        JSONObject jsonObject = responseConverter.toJsonObject();
        Integer json = jsonObject.getInt("code");
        idFromResponse = jsonObject.getJSONObject("data").getInt("id");

        assertTrue(json.equals(201));
        Logger.getLogger(GoRESTApiNestedCommentsTests.class.getName()).log(Level.INFO,
                "\n Current status code is " + json);
    }

    @Test
    @Order(2)
    @DisplayName("Post for GOREST nested")
    void test2() {
        Response response = GoRestAPIComponent.postGoRESTElements(elementType, idFromResponse, elementTypeNested);
        assertThat(response.getStatusCode()).isEqualTo(200);

        ResponseConverter responseConverter = new ResponseConverter(response);
        JSONObject jsonObject = responseConverter.toJsonObject();
        Integer json = jsonObject.getInt("code");

        assertTrue(json.equals(201));
        Logger.getLogger(GoRESTApiNestedCommentsTests.class.getName()).log(Level.INFO,
                "\n Current status code is " + json);
    }
}
