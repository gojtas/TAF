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
import static constants.Constants.SCHEMA_SPECIES;
import static constants.Constants.ELEMENT_TYPE_SPECIES;
import static readers.InputFileReader.readDataFromFile;
import static utils.dataGenerator.ElementsGenerator.generateListOfStarWarsElements;

@Epic("Contract Schema Check - Species")
@Feature("User verifies if the schema for species is valid")
@Tag("CONTRACT")
class SWApiSpeciesSchemaTests {

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = generateListOfStarWarsElements(ELEMENT_TYPE_SPECIES);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Species schema check: {0}")
    @MethodSource("dataProvider")
    @Story("User checks the species schemas")
    @DisplayName("Verify Schema for Species")
    void testSchema(String transactionJsonOutput) {

        EXPECTED_SCHEMA = readDataFromFile(SCHEMA_SPECIES);
        SchemaCheck.checkSchema(transactionJsonOutput, EXPECTED_SCHEMA);
    }
}
