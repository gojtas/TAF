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
import static constants.Constants.JSON_HEADER_VALUE;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("COMPONENT")
class GoRestApiCRUDTests {
    private static final Logger logger = Logger.getLogger(GoRestApiCRUDTests.class.getName());
    private static RequestSpecification requestSpecification;
    private static String idToCleanUp = "";
    private String inputFileForNewUser = "GoRestInput/GoRestNewUser.json";
    private String inputFileForUpdateUser = "GoRestInput/GoRestUserUpdate.json";

    @BeforeAll
    static void methodSetUp() {
        RestUtils.setFullUri(GORESTURL.getUrl(), "/users");
    }

    @AfterAll
    static void cleanUp() {
        requestSpecification = RestUtils.getRequestSpecificationWithoutBody(JSON_HEADER_VALUE,
                idToCleanUp);
        Response deleteResponse = RestUtils.deleteResponse(requestSpecification);
    }

    @Order(1)
    @DisplayName("List users - GET")
    @Test
    void listUsersTests() {
        requestSpecification = RestUtils.getRequestSpecificationWithoutBodyForGet(JSON_HEADER_VALUE);
        Response response = RestUtils.getResponse(requestSpecification);

        CheckAssertions.checkGetOperation(response);
    }

    @Order(2)
    @DisplayName("Create user - CREATE")
    @Test
    void createUserTest() {
        String file = InputFileReader.readDataFromFile(inputFileForNewUser);

        requestSpecification = RestUtils.getRequestSpecificationWithJsonBody(file, JSON_HEADER_VALUE);
        Response response = RestUtils.postResponse(requestSpecification);

        CheckAssertions.checkCreateOperation(response);
        idToCleanUp = SupportingMethods.getOperationId(response);
    }

    @Order(3)
    @DisplayName("Update user - PATCH")
    @Test
    void createAndUpdateUserTest() {
        String fileForUpdate = InputFileReader.readDataFromFile(inputFileForUpdateUser);

        requestSpecification = RestUtils.getRequestSpecificationWithJsonBody(fileForUpdate, JSON_HEADER_VALUE,
                idToCleanUp);
        Response updateResponse = RestUtils.patchResponse(requestSpecification);

        CheckAssertions.checkPatchOperation(updateResponse);
        logger.log(Level.INFO, "User with id: " + idToCleanUp +
                " updated successfully");
    }

    @Order(4)
    @DisplayName("Remove user - DELETE")
    @Test
    void crateAndDeleteUserTest() {
        requestSpecification = RestUtils.getRequestSpecificationWithoutBody(JSON_HEADER_VALUE,
                idToCleanUp);
        Response deleteResponse = RestUtils.deleteResponse(requestSpecification);

        CheckAssertions.checkDeleteOperation(deleteResponse);
        logger.log(Level.INFO, "User with id: " + idToCleanUp +
                " successfully removed");
    }
}
