package ComponentTests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import readers.InputFileReader;
import utils.dataGenerator.RestUtils;

import static config.UriProvider.GORESTURL;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("COMPONENT")
class GoRestApiCRUDTests {
    private final Logger logger = Logger.getLogger(GoRestApiCRUDTests.class.getName());
    private RequestSpecification requestSpecification;

    private String inputFileForNewUser = "GoRestInput/GoRestNewUser.json";

    @BeforeAll
    static void methodSetUp() {
        RestUtils.setFullUri(GORESTURL.getUrl(), "/users");
    }

    @DisplayName("Remove user from GoRestApi")
    @Test
    void deleteUserTest() {

        String file = InputFileReader.readDataFromFile(inputFileForNewUser);
        requestSpecification = RestUtils.getRequestSpecificationWithJsonBody(file, "application/json");
        Response response = RestUtils.postResponse(requestSpecification);

        String output = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(output);
        String operationId = jsonObject.getJSONObject("data").get("id").toString();
        logger.log(Level.INFO, "Operation Id is: " + operationId);

        requestSpecification = RestUtils.getRequestSpecificationWithoutBody("application/json", operationId);
        Response deleteResponse = RestUtils.deleteResponse(requestSpecification);

        String outputAfterDelete = deleteResponse.getBody().asString();
        JSONObject deleteJsonObject = new JSONObject(outputAfterDelete);
        String actualDeleteCode = deleteJsonObject.get("code").toString();
        assertThat(actualDeleteCode).isEqualTo("204");
        logger.log(Level.INFO, "User successfully removed");
    }
}
