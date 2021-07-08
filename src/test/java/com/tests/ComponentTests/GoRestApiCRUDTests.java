package com.tests.ComponentTests;

import CustomAssertions.CheckAssertions;
import SupportMethods.SupportingMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import utils.dataGenerator.RestUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import static config.UriProvider.GORESTURL;
import static constants.Constants.JSON_HEADER_VALUE;
import static readers.InputFileReader.readDataFromFile;
import static utils.dataGenerator.RestUtils.deleteResponse;
import static utils.dataGenerator.RestUtils.getRequestSpecificationWithJsonBody;
import static utils.dataGenerator.RestUtils.getRequestSpecificationWithoutBody;
import static utils.dataGenerator.RestUtils.getRequestSpecificationWithoutBodyForGet;
import static utils.dataGenerator.RestUtils.getResponse;
import static utils.dataGenerator.RestUtils.patchResponse;
import static utils.dataGenerator.RestUtils.postResponse;

@Epic("API CRUD Operations")
@Feature("Users try to send different REST API commands")
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
         deleteResponse(requestSpecification);
    }

    @Order(1)
    @DisplayName("List users - GET")
    @Description("Test Description: List users")
    @Story("User tries to send GET request")
    @Test
    void listUsersTests() {
        requestSpecification = getRequestSpecificationWithoutBodyForGet(JSON_HEADER_VALUE);
        Response response = getResponse(requestSpecification);

        CheckAssertions.checkGetOperation(response);
    }

    @Order(2)
    @DisplayName("Create user - CREATE")
    @Description("Test Description: Create user")
    @Story("User tries to send Create request")
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
    @Story("User tries to send Patch request")
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
    @Story("User tries to send Delete request")
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
