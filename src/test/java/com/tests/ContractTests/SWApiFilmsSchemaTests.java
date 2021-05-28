package com.tests.ContractTests;

import core.SchemaCheck;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static constants.Constants.ELEMENT_TYPE_FILMS;
import static constants.Constants.EXPECTED_SCHEMA;
import static constants.Constants.SCHEMA_FILMS;
import static readers.InputFileReader.readDataFromFile;
import static utils.dataGenerator.ElementsGenerator.generateListOfStarWarsElements;

@Epic("Contract Schema Check - Films")
@Feature("User verifies if the schema for films is valid")
@Tag("CONTRACT")
class SWApiFilmsSchemaTests {

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = generateListOfStarWarsElements(ELEMENT_TYPE_FILMS);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Films schema check: {0}")
    @MethodSource("dataProvider")
    @Story("User checks the films schemas")
    @DisplayName("Verify Schema for Films")
    void testSchema(String transactionJsonOutput) {

        EXPECTED_SCHEMA = readDataFromFile(SCHEMA_FILMS);
        SchemaCheck.checkSchema(transactionJsonOutput, EXPECTED_SCHEMA);
    }
}
