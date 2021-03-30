package ComponentTests;

import CustomAssertions.CheckAssertions;
import SupportMethods.SupportingMethods;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import readers.InputFileReader;
import utils.dataGenerator.RestUtils;

import static config.UriProvider.GORESTURL;


@Tag("COMPONENT")
class GoRestApiCRUDTests {
    private static final Logger logger = Logger.getLogger(GoRestApiCRUDTests.class.getName());
    private static RequestSpecification requestSpecification;
    private static String idToCleanUp = "";
    private String inputFileForNewUser = "GoRestInput/GoRestNewUser.json";
    private String inputFileForUpdateUser = "GoRestInput/GoRestUserUpdate.json";
    private CheckAssertions checkAssertions;
    private SupportingMethods supportingMethods;

    @BeforeAll
    static void methodSetUp() {
        RestUtils.setFullUri(GORESTURL.getUrl(), "/users");
    }

    @AfterAll
    static void cleanUp() {
        requestSpecification = RestUtils.getRequestSpecificationWithoutBody("application/json",
                idToCleanUp);
        Response deleteResponse = RestUtils.deleteResponse(requestSpecification);
    }

    @DisplayName("Remove user - CREATE & DELETE")
    @Test
    void crateAndDeleteUserTest() {
        String file = InputFileReader.readDataFromFile(inputFileForNewUser);

        requestSpecification = RestUtils.getRequestSpecificationWithJsonBody(file, "application/json");
        Response response = RestUtils.postResponse(requestSpecification);
        requestSpecification = RestUtils.getRequestSpecificationWithoutBody("application/json",
                SupportingMethods.getOperationId(response));
        Response deleteResponse = RestUtils.deleteResponse(requestSpecification);

        CheckAssertions.checkDeleteOperation(deleteResponse);
        logger.log(Level.INFO, "User with id: " + SupportingMethods.getOperationId(response) +
                " successfully removed");
    }

    @DisplayName("Update user - CREATE & PATCH")
    @Test
    void createAndUpdateUserTest() {
        String file = InputFileReader.readDataFromFile(inputFileForNewUser);
        String fileForUpdate = InputFileReader.readDataFromFile(inputFileForUpdateUser);

        requestSpecification = RestUtils.getRequestSpecificationWithJsonBody(file, "application/json");
        Response response = RestUtils.postResponse(requestSpecification);
        requestSpecification = RestUtils.getRequestSpecificationWithJsonBody(fileForUpdate, "application/json",
                SupportingMethods.getOperationId(response));
        Response updateResponse = RestUtils.patchResponse(requestSpecification);

        CheckAssertions.checkPatchOperation(updateResponse);
        logger.log(Level.INFO, "User with id: " + SupportingMethods.getOperationId(response) +
                " updated successfully");

        idToCleanUp = SupportingMethods.getOperationId(response);
    }

    @DisplayName("List users - GET")
    @Test
    void listUsersTests() {
        requestSpecification = RestUtils.getRequestSpecificationWithoutBodyForGet("application/json");
        Response response = RestUtils.getResponse(requestSpecification);

        CheckAssertions.checkGetOperation(response);
    }
}
