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
import static constants.Constants.EXPECTED_SCHEMA;
import static constants.Constants.SCHEMA_STARSHIPS;
import static constants.Constants.ELEMENT_TYPE_STARSHIPS;
import static readers.InputFileReader.readDataFromFile;
import static utils.dataGenerator.ElementsGenerator.generateListOfStarWarsElements;

@Epic("Contract Schema Check - Starships")
@Feature("User verifies if the schema for starships is valid")
@Tag("CONTRACT")
class SWApiStarshipsSchemaTests {

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = generateListOfStarWarsElements(ELEMENT_TYPE_STARSHIPS);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Starships schema check: {0}")
    @MethodSource("dataProvider")
    @Story("User checks the starships schemas")
    @DisplayName("Verify Schema for Starships")
    void testSchema(String transactionJsonOutput) {

        EXPECTED_SCHEMA = readDataFromFile(SCHEMA_STARSHIPS);
        SchemaCheck.checkSchema(transactionJsonOutput, EXPECTED_SCHEMA);
    }
}
