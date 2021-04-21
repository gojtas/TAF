package com.tests.ComponentTests;

import CustomAssertions.CheckAssertions;
import SupportMethods.SupportingMethods;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import utils.dataGenerator.RestUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import static config.UriProvider.GORESTURL;
import static constants.Constants.JSON_HEADER_VALUE;
import static readers.InputFileReader.readDataFromFile;
import static utils.dataGenerator.RestUtils.*;

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
        requestSpecification = getRequestSpecificationWithoutBody(JSON_HEADER_VALUE,
                idToCleanUp);
        Response deleteResponse = deleteResponse(requestSpecification);
    }

    @Order(1)
    @DisplayName("List users - GET")
    @Description("Test Description: List users")
    @Test
    void listUsersTests() {
        requestSpecification = getRequestSpecificationWithoutBodyForGet(JSON_HEADER_VALUE);
        Response response = getResponse(requestSpecification);

        CheckAssertions.checkGetOperation(response);
    }

    @Order(2)
    @DisplayName("Create user - CREATE")
    @Description("Test Description: Create user")
    @Test
    void createUserTest() {
        String file = readDataFromFile(inputFileForNewUser);

        requestSpecification = getRequestSpecificationWithJsonBody(file, JSON_HEADER_VALUE);
        Response response = postResponse(requestSpecification);

        CheckAssertions.checkCreateOperation(response);
        idToCleanUp = SupportingMethods.getOperationId(response);
    }

    @Order(3)
    @DisplayName("Update user - PATCH")
    @Description("Test Description: Update user")
    @Test
    void createAndUpdateUserTest() {
        String fileForUpdate = readDataFromFile(inputFileForUpdateUser);

        requestSpecification = getRequestSpecificationWithJsonBody(fileForUpdate, JSON_HEADER_VALUE,
                idToCleanUp);
        Response updateResponse = patchResponse(requestSpecification);

        CheckAssertions.checkPatchOperation(updateResponse);
        logger.log(Level.INFO, "User with id: " + idToCleanUp +
                " updated successfully");
    }

    @Order(4)
    @DisplayName("Remove user - DELETE")
    @Description("Test Description: Remove user")
    @Test
    void crateAndDeleteUserTest() {
        requestSpecification = getRequestSpecificationWithoutBody(JSON_HEADER_VALUE,
                idToCleanUp);
        Response deleteResponse = deleteResponse(requestSpecification);

        CheckAssertions.checkDeleteOperation(deleteResponse);
        logger.log(Level.INFO, "User with id: " + idToCleanUp +
                " successfully removed");
    }
}
